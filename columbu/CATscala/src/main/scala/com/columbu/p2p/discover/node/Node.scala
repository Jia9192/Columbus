package com.galileo.p2p.discover.node

import com.galileo.encrypt.Hash.sha3
import java.io.Serializable
import java.net.URI
import java.net.URISyntaxException
import org.apache.commons.lang3.StringUtils
import org.spongycastle.util.encoders.Hex
import com.galileo.encrypt.ECKey
import com.galileo.utils.ByteArray
import com.galileo.utils.Utils
import scala.beans.BeanProperty

class Node extends Serializable{

  private val serialVersionUID = -4267600517925770636L
  private var id: Array[Byte] = null
  private var host: String = null
  private var port: Int = 0
  @BeanProperty
  var isFakeNodeId = false

  def getReputation: Int = reputation

  def setReputation(reputation: Int) {
    this.reputation = reputation
  }

  private var reputation = 0

  def getEnodeURL: String = {
    return new StringBuilder("enode://").append(ByteArray.toHexString(id)).append("@").append(host).append(":").append(port).toString
  }

  def this(enodeURL: String){
    this()
    try{
      val uri = new URI(enodeURL)
      if (!uri.getScheme().equals("enode")){
        throw new RuntimeException("expecting URL in the format enode://PUBKEY@HOST:PORT")
      }
      this.id = Hex.decode(uri.getUserInfo)
      this.host = uri.getHost
      this.port = uri.getPort
    }catch {
      case e: URISyntaxException => {
        throw new RuntimeException("expecting URL in the format enode://PUBKEY@HOST:PORT", e)
      }
    }
  }

  def this(id: Array[Byte], host: String, port: Int){
    this()
    if (id != null){
      this.id = id.clone
    }
    this.host = host
    this.port = port
  }

  def getHexId: String = Hex.toHexString(id)

  def getHexIdShort: String = Utils.getIdShort(getHexId)

  def isDiscoveryNode: Boolean = isFakeNodeId

  def getId: Array[Byte] =  {
    return if (id == null) id else id.clone
  }

  def setId(id: Array[Byte]) {
    if (id == null){
      this.id = null
    } else  {
      this.id = id.clone
    }
  }

  def getHost: String = host

  def getPort: Int = port

  def setPort(port: Int) {
    this.port = port
  }

  def getIdString: String = {
    if (id == null){
      return null
    }
    return  new String(id)
  }

  override def toString: String = {
    return "Node{" + " host='" + host + '\'' + ", port=" + port + ", id=" + ByteArray.toHexString(id) + '}'
  }

  override def hashCode: Int = {
    return this.toString.hashCode
  }

  override def equals(o: Any): Boolean = {
    if (o == null) return false
    if (o == this) return true
    if (o.getClass eq getClass) return StringUtils.equals(getIdString, o.asInstanceOf[Node].getIdString)
    return false
  }

}

object Node{

  def instanceOf(addressOrEnode: String): Node = {
    try{
      val uri = new URI(addressOrEnode)
      if (uri.getScheme == "enode") return new Node(addressOrEnode)
    }catch {
      case e: URISyntaxException => {
        // continue
      }
    }
    val generatedNodeKey = ECKey.fromPrivate(sha3(addressOrEnode.getBytes))
    val generatedNodeId = Hex.toHexString(generatedNodeKey.getNodeId)
    val node = new Node("enode://" + generatedNodeId + "@" + addressOrEnode)
    node.isFakeNodeId = true
    return node
  }

}
