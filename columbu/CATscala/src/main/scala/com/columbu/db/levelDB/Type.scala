package com.galileo.db.levelDB

class Type {



  protected var `type`: Int = Type.VALUE_TYPE_NORMAL

  /**
    * @param type
    */
  def this(`type`: Int) {
    this()
    this.`type` |= `type`
  }



  /**
    * @param T
    */
  def this(T: Type) {
    this()
    this.`type` = T.getType
  }

  /**
    * @return
    */
  override def clone = new Type(this)

  def isDirty: Boolean = (this.`type` & Type.VALUE_TYPE_DIRTY) == Type.VALUE_TYPE_DIRTY

  def isNormal: Boolean = this.`type` == Type.VALUE_TYPE_NORMAL

  def isCreate: Boolean = (this.`type` & Type.VALUE_TYPE_CREATE) == Type.VALUE_TYPE_CREATE

  def shouldCommit: Boolean = this.`type` != Type.VALUE_TYPE_NORMAL

  def getType: Int = `type`

  /**
    * @param type
    * @return
    */
  def isValidType(`type`: Int): Boolean = {
    if ((`type` & Type.VALUE_TYPE_UNKNOWN) != Type.VALUE_TYPE_NORMAL) return false
    true
  }

  def setType(`type`: Int): Unit = {
    if (isValidType(`type`)) this.`type` = `type`
  }

  def addType(`type`: Int): Unit = {
    if (isValidType(`type`)) this.`type` |= `type`
  }

  def addType(T: Type): Unit = {
    addType(T.getType)
  }

  override def equals(obj: Any): Boolean = {
    if (this equals  obj) return true
    if (obj == null || (obj.getClass ne getClass)) return false
    val T = obj.asInstanceOf[Type]
    if (this.`type` != T.getType) return false
    true
  }

  override def hashCode: Int = new Integer(`type`).hashCode

  override def toString: String = "Type{" + "type=" + `type` + '}'
}

object Type{
  /**
    * Default Mode : VALUE_TYPE_NORMAL
    */
  var VALUE_TYPE_NORMAL = 0
  var VALUE_TYPE_DIRTY: Int = 1 << 0
  var VALUE_TYPE_CREATE: Int = 1 << 1
  var VALUE_TYPE_UNKNOWN = 0xFFFFFFFC
}
