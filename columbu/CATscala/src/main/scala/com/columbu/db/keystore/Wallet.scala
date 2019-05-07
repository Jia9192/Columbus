package com.galileo.db.keystore

import java.nio.charset.StandardCharsets.UTF_8
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util
import java.util.{Arrays, UUID}
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import org.spongycastle.crypto.digests.SHA256Digest
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator
import org.spongycastle.crypto.generators.SCrypt
import org.spongycastle.crypto.params.KeyParameter
import com.galileo.encrypt.ECKey
import com.galileo.encrypt.Hash
import com.galileo.utils.ByteArray

object Wallet {

  private val N_LIGHT = 1 << 12
  private val P_LIGHT = 6

  private val N_STANDARD = 1 << 18
  private val P_STANDARD = 1

  private val R = 8
  private val DKLEN = 32

  private val CURRENT_VERSION = 3

  private val CIPHER = "aes-128-ctr"
  protected val AES_128_CTR = "pbkdf2"
  protected val SCRYPT = "scrypt"

  @throws[CipherException]
  def create(password: String, ecKeyPair: ECKey, n: Int, p: Int): WalletFile = {
    val salt = generateRandomBytes(32)
    val derivedKey = generateDerivedScryptKey(password.getBytes(UTF_8), salt, n, R, p, DKLEN)
    val encryptKey = util.Arrays.copyOfRange(derivedKey, 0, 16)
    val iv = generateRandomBytes(16)
    val privateKeyBytes = ecKeyPair.getPrivKeyBytes
    val cipherText = performCipherOperation(Cipher.ENCRYPT_MODE, iv, encryptKey, privateKeyBytes)
    val mac = generateMac(derivedKey, cipherText)
    createWalletFile(ecKeyPair, cipherText, iv, salt, mac, n, p)
  }

  @throws[CipherException]
  def createStandard(password: String, ecKeyPair: ECKey): WalletFile = create(password, ecKeyPair, N_STANDARD, P_STANDARD)

  @throws[CipherException]
  def createLight(password: String, ecKeyPair: ECKey): WalletFile = create(password, ecKeyPair, N_LIGHT, P_LIGHT)

  private def createWalletFile(ecKeyPair: ECKey, cipherText: Array[Byte], iv: Array[Byte], salt: Array[Byte], mac: Array[Byte], n: Int, p: Int) = {
    val walletFile = new WalletFile
    walletFile.setAddress(com.galileo.core.Wallet.encode58Check(ecKeyPair.getAddress))

    val crypto = new WalletFile.Crypto
    crypto.setCipher(CIPHER)
    crypto.setCiphertext(ByteArray.toHexString(cipherText))
    walletFile.setCrypto(crypto)

    val cipherParams = new WalletFile.CipherParams
    cipherParams.setIv(ByteArray.toHexString(iv))
    crypto.setCipherparams(cipherParams)

    crypto.setKdf(SCRYPT)
    val kdfParams = new WalletFile.ScryptKdfParams
    kdfParams.setDklen(DKLEN)
    kdfParams.setN(n)
    kdfParams.setP(p)
    kdfParams.setR(R)
    kdfParams.setSalt(ByteArray.toHexString(salt))
    crypto.setKdfparams(kdfParams)

    crypto.setMac(ByteArray.toHexString(mac))
    walletFile.setCrypto(crypto)
    walletFile.setId(UUID.randomUUID.toString)
    walletFile.setVersion(CURRENT_VERSION)

    walletFile
  }

  @throws[CipherException]
  private def generateDerivedScryptKey(password: Array[Byte], salt: Array[Byte], n: Int, r: Int, p: Int, dkLen: Int) = SCrypt.generate(password, salt, n, r, p, dkLen)

  @throws[CipherException]
  private def generateAes128CtrDerivedKey(password: Array[Byte], salt: Array[Byte], c: Int, prf: String) = {
    if (!(prf == "hmac-sha256")) throw new CipherException("Unsupported prf:" + prf)
    // Java 8 supports this, but you have to convert the password to a character array, see
    // http://stackoverflow.com/a/27928435/3211687
    val gen = new PKCS5S2ParametersGenerator(new SHA256Digest)
    gen.init(password, salt, c)
    gen.generateDerivedParameters(256).asInstanceOf[KeyParameter].getKey
  }

  @throws[CipherException]
  private def performCipherOperation(mode: Int, iv: Array[Byte], encryptKey: Array[Byte], text: Array[Byte]) = try {
    val ivParameterSpec = new IvParameterSpec(iv)
    val cipher = Cipher.getInstance("AES/CTR/NoPadding")
    val secretKeySpec = new SecretKeySpec(encryptKey, "AES")
    cipher.init(mode, secretKeySpec, ivParameterSpec)
    cipher.doFinal(text)
  } catch {
    case e@(_: NoSuchPaddingException | _: NoSuchAlgorithmException | _: InvalidAlgorithmParameterException | _: InvalidKeyException | _: BadPaddingException | _: IllegalBlockSizeException) =>
      throw new CipherException("Error performing cipher operation", e)
  }

  private def generateMac(derivedKey: Array[Byte], cipherText: Array[Byte]) = {
    val result = new Array[Byte](16 + cipherText.length)
    System.arraycopy(derivedKey, 16, result, 0, 16)
    System.arraycopy(cipherText, 0, result, 16, cipherText.length)
    Hash.sha3(result)
  }

  @throws[CipherException]
  def decrypt(password: String, walletFile: WalletFile): ECKey = {
    validate(walletFile)
    val crypto = walletFile.getCrypto
    val mac = ByteArray.fromHexString(crypto.getMac)
    val iv = ByteArray.fromHexString(crypto.getCipherparams.getIv)
    val cipherText = ByteArray.fromHexString(crypto.getCiphertext)
    var derivedKey : Array[Byte]  = null
    val kdfParams = crypto.getKdfparams
    if (kdfParams.isInstanceOf[WalletFile.ScryptKdfParams]) {
      val scryptKdfParams = crypto.getKdfparams.asInstanceOf[WalletFile.ScryptKdfParams]
      val dklen = scryptKdfParams.getDklen
      val n = scryptKdfParams.getN
      val p = scryptKdfParams.getP
      val r = scryptKdfParams.getR
      val salt = ByteArray.fromHexString(scryptKdfParams.getSalt)
      derivedKey = generateDerivedScryptKey(password.getBytes(UTF_8), salt, n, r, p, dklen)
    }
    else if (kdfParams.isInstanceOf[WalletFile.Aes128CtrKdfParams]) {
      val aes128CtrKdfParams = crypto.getKdfparams.asInstanceOf[WalletFile.Aes128CtrKdfParams]
      val c = aes128CtrKdfParams.getC
      val prf = aes128CtrKdfParams.getPrf
      val salt = ByteArray.fromHexString(aes128CtrKdfParams.getSalt)
      derivedKey = generateAes128CtrDerivedKey(password.getBytes(UTF_8), salt, c, prf)
    }
    else throw new CipherException("Unable to deserialize params: " + crypto.getKdf)
    val derivedMac = generateMac(derivedKey, cipherText)
    if (!Arrays.equals(derivedMac, mac)) throw new CipherException("Invalid password provided")
    val encryptKey = util.Arrays.copyOfRange(derivedKey, 0, 16)
    val privateKey = performCipherOperation(Cipher.DECRYPT_MODE, iv, encryptKey, cipherText)
    ECKey.fromPrivate(privateKey)
  }

  @throws[CipherException]
  private[keystore] def validate(walletFile: WalletFile): Unit = {
    val crypto = walletFile.getCrypto
    if (walletFile.getVersion != CURRENT_VERSION) throw new CipherException("Wallet version is not supported")
    if (!(crypto.getCipher == CIPHER)) throw new CipherException("Wallet cipher is not supported")
    if (!(crypto.getKdf == AES_128_CTR) && !(crypto.getKdf == SCRYPT)) throw new CipherException("KDF type is not supported")
  }

  private[keystore] def generateRandomBytes(size: Int) = {
    val bytes = new Array[Byte](size)
    new SecureRandom().nextBytes(bytes)
    bytes
  }
}
