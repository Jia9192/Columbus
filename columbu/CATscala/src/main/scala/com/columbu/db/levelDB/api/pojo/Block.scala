package com.galileo.db.levelDB.api.pojo

//import lombok.Data
import java.util
import java.util.List

//@Data(staticConstructor = "of")
class Block {

  private val id : String = null
  private val number : Long = 0L
  private val transactionIds : util.List[String] = null
}
