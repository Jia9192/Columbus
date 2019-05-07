package com.galileo.p2p.discover.dht

import java.math.BigInteger
import org.spongycastle.util.BigIntegers
import org.spongycastle.util.encoders.Hex
import com.galileo.utils.Utils

class Peer {

  private var id: Array[Byte]  = null
  private var host = "127.0.0.1"
  private var port: Int = 0

  def this(id: Array[Byte], host: String, port: Int) {
    this()
    this.id = id
    this.host = host
    this.port = port
  }

  def this(ip: Array[Byte]) {
    this()
    this.id = ip
  }

  def nextBit(startPattern: String): Byte = if (this.toBinaryString.startsWith(startPattern + "1")) 1
  else 0

  def calcDistance(toPeer: Peer): Array[Byte] = {
    val aaPeer = new BigInteger(getId)
    val bbPeer = new BigInteger(toPeer.getId)
    val distance = aaPeer.xor(bbPeer)
    BigIntegers.asUnsignedByteArray(distance)
  }

  def getId: Array[Byte] = id

  def setId(id: Array[Byte]) {
    this.id = id
  }

  override def toString: String = {
    return "Peer {\n id=%s, \n host=%s, \n port=%d\n}".format(Hex.toHexString(id), host, port)
  }

  def toBinaryString: String = {
    val bi = new BigInteger(1, id)
    var out = "%512s".format(bi.toString(2))
    out = out.replace(' ', '0')
    return out
  }

  def randomPeerId: Array[Byte] = {
    val peerIdBytes = new BigInteger(512, Utils.getRandom).toByteArray
    var peerId: String = null
    if (peerIdBytes.length > 64){
      peerId = Hex.toHexString(peerIdBytes, 1, 64)
    } else {
      peerId = Hex.toHexString(peerIdBytes)
    }
    return Hex.decode(peerId)
  }

}
