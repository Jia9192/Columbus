package com.galileo.db.levelDB

import java.util
import java.util.Set

trait DbSourceInter[V] extends BatchSourceInter[Array[Byte], V] {

  def getDBName: String

  def setDBName(name: String): Unit

  def initDB(): Unit


  def isAlive: Boolean


  def closeDB(): Unit

  def resetDb(): Unit

  @throws[RuntimeException]
  def allKeys: util.Set[Array[Byte]]

  @throws[RuntimeException]
  def allValues: util.Set[Array[Byte]]

  @throws[RuntimeException]
  def getTotal: Long
}
