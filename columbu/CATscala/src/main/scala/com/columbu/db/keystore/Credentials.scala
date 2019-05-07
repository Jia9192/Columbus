package com.galileo.db.keystore

import com.galileo.encrypt.ECKey
import com.galileo.utils.ByteArray

class Credentials {
  private var ecKeyPair : ECKey = null
  private var address : String  = null

  def this(ecKeyPairP: ECKey, addressP: String) {
    this()
    this.ecKeyPair = ecKeyPairP
    this.address = addressP
  }

  def getEcKeyPair: ECKey = ecKeyPair

  def getAddress: String = address



  override def equals(o: Any): Boolean = {
    if (this == o)
      return true
    if (o == null || (getClass ne o.getClass))
      return false
    val that = o.asInstanceOf[Credentials]
    if (if (ecKeyPair != null) !(ecKeyPair == that.getEcKeyPair)
    else that.getEcKeyPair != null) return false
    if (address != null) address == that.getAddress
    else that.getAddress == null
  }

  override def hashCode: Int = {
    var result = if (ecKeyPair != null) ecKeyPair.hashCode
    else 0
    result = 31 * result + (if (address != null) address.hashCode
    else 0)
    result
  }
}
object Credentials{
  def create(ecKeyPair: ECKey): Credentials = {
    val address = com.galileo.core.Wallet.encode58Check(ecKeyPair.getAddress)
    new Credentials(ecKeyPair, address)
  }

  def create(privateKey: String): Credentials = {
    val eCkey = ECKey.fromPrivate(ByteArray.fromHexString(privateKey))
    create(eCkey)
  }
}
