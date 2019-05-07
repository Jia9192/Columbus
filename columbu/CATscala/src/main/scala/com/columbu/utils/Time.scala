package com.galileo.utils

import java.sql.Timestamp

object Time {
  
  def getCurrentMillis : Long = System.currentTimeMillis()
  
  def getTimeString(time : Long) : String = new Timestamp(time).toString()
  
}