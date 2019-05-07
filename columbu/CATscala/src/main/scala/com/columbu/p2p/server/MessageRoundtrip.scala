package com.galileo.p2p.server

import com.galileo.p2p.message.Message

class MessageRoundtrip {

  private var msg: Message = null
  var lastTimestamp: Long = 0
  var retryTimes: Long = 0
  var answered: Boolean = false

  def this(msg: Message) {
    this()
    this.msg = msg
    saveTime()
  }

  def isAnswered: Boolean = answered

  def answer() {
    answered = true
  }

  def getRetryTimes: Long = retryTimes

  def incRetryTimes() {
    retryTimes += 1
  }

  def saveTime() {
    lastTimestamp = System.currentTimeMillis
  }

  def getTime: Long = lastTimestamp

  def hasToRetry: Boolean = 20000 < System.currentTimeMillis - lastTimestamp

  def getMsg: Message = {
    return msg
  }
}
