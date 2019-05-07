package com.galileo.db.keystore

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException
import java.security.InvalidAlgorithmParameterException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner
import org.apache.commons.lang3.StringUtils
import com.galileo.encrypt.ECKey
import com.galileo.utils.Utils


object WalletUtils {

  private val objectMapper = new ObjectMapper

   {
    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  }

  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  @throws[InvalidAlgorithmParameterException]
  @throws[CipherException]
  @throws[IOException]
  def generateFullNewWalletFile(password: String, destinationDirectory: File): String = generateNewWalletFile(password, destinationDirectory, true)

  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  @throws[InvalidAlgorithmParameterException]
  @throws[CipherException]
  @throws[IOException]
  def generateLightNewWalletFile(password: String, destinationDirectory: File): String = generateNewWalletFile(password, destinationDirectory, false)

  @throws[CipherException]
  @throws[IOException]
  @throws[InvalidAlgorithmParameterException]
  @throws[NoSuchAlgorithmException]
  @throws[NoSuchProviderException]
  def generateNewWalletFile(password: String, destinationDirectory: File, useFullScrypt: Boolean): String = {
    val ecKeyPair = new ECKey(Utils.getRandom)
    generateWalletFile(password, ecKeyPair, destinationDirectory, useFullScrypt)
  }

  @throws[CipherException]
  @throws[IOException]
  def generateWalletFile(password: String, ecKeyPair: ECKey, destinationDirectory: File, useFullScrypt: Boolean): String = {
    var walletFile : WalletFile = null
    if (useFullScrypt) walletFile = Wallet.createStandard(password, ecKeyPair)
    else walletFile = Wallet.createLight(password, ecKeyPair)
    val fileName = getWalletFileName(walletFile)
    val destination = new File(destinationDirectory, fileName)
    objectMapper.writeValue(destination, walletFile)
    fileName
  }

  @throws[CipherException]
  @throws[IOException]
  def updateWalletFile(password: String, ecKeyPair: ECKey, source: File, useFullScrypt: Boolean): Unit = {
    var walletFile = objectMapper.readValue(source, classOf[WalletFile])
    if (useFullScrypt) walletFile = Wallet.createStandard(password, ecKeyPair)
    else walletFile = Wallet.createLight(password, ecKeyPair)
    objectMapper.writeValue(source, walletFile)
  }


  @throws[IOException]
  @throws[CipherException]
  def loadCredentials(password: String, source: File): Credentials = {
    val walletFile = objectMapper.readValue(source, classOf[WalletFile])
    Credentials.create(Wallet.decrypt(password, walletFile))
  }

  //
  //    public static Credentials loadBip39Credentials(String password, String mnemonic) {
  //        byte[] seed = MnemonicUtils.generateSeed(mnemonic, password);
  //        return Credentials.create(ECKeyPair.create(sha256(seed)));
  //    }

  private def getWalletFileName(walletFile: WalletFile) = {
    val format = DateTimeFormatter.ofPattern("'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'")
    val now = ZonedDateTime.now(ZoneOffset.UTC)
    now.format(format) + walletFile.getAddress + ".json"
  }

  def getDefaultKeyDirectory: String = getDefaultKeyDirectory(System.getProperty("os.name"))

  private[keystore] def getDefaultKeyDirectory(osName1: String) = {
    val osName = osName1.toLowerCase
    if (osName.startsWith("mac")) String.format("%s%sLibrary%sEthereum", System.getProperty("user.home"), File.separator, File.separator)
    else if (osName.startsWith("win")) String.format("%s%sEthereum", System.getenv("APPDATA"), File.separator)
    else String.format("%s%s.ethereum", System.getProperty("user.home"), File.separator)
  }

  def getTestnetKeyDirectory: String = String.format("%s%stestnet%skeystore", getDefaultKeyDirectory, File.separator, File.separator)

  def getMainnetKeyDirectory: String = String.format("%s%skeystore", getDefaultKeyDirectory, File.separator)

  //    public static boolean isValidPrivateKey(String privateKey) {
  //        String cleanPrivateKey = Numeric.cleanHexPrefix(privateKey);
  //        return cleanPrivateKey.length() == PRIVATE_KEY_LENGTH_IN_HEX;
  //    public static boolean isValidAddress(String input) {
  //        String cleanInput = Numeric.cleanHexPrefix(input);
  //        try {
  //            Numeric.toBigIntNoPrefix(cleanInput);
  //        } catch (NumberFormatException e) {
  //            return false;
  //        }
  //        return cleanInput.length() == ADDRESS_LENGTH_IN_HEX;
  def passwordValid(password: String): Boolean = {
    if (StringUtils.isEmpty(password)) return false
    if (password.length < 6) return false
    //Other rule;
    true
  }

  def inputPassword: String = {
    var in :Scanner = null
    var password : String= null
    val cons = System.console
    if (cons == null)
      in = new Scanner(System.in)
    while ( {
      true
    }) {
      if (cons != null) {
        val pwd = cons.readPassword("password: ")
        password = String.valueOf(pwd)
      }
      else {
        val input = in.nextLine.trim
        password = input.split("\\s+")(0)
      }
      if (passwordValid(password))
        return password
      System.out.println("Invalid password, please input again.")
    }
    password
  }

  def inputPassword2Twice: String = {
    var password0: String  = null
    var password1 : String = ""
    while ( {
      !password0.equals(password1)
    }) {
      System.out.println("Please input password.")
      password0 = inputPassword
      System.out.println("Please input password again.")
      password1 = inputPassword
      if (!password0.equals(password1))
        System.out.println("The passwords do not match, please input again.")//todo: break is not supported

    }
    password0
  }
}
