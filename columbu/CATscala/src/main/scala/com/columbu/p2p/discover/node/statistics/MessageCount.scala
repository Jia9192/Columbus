package com.galileo.p2p.discover.node.statistics

import org.slf4j.LoggerFactory

class MessageCount {

  private val logger = LoggerFactory.getLogger(getClass())

  private val SIZE = 60
  private val szCount = new Array[Int](SIZE)
  private var indexTime = System.currentTimeMillis / 1000
  private var index = (indexTime % SIZE).toInt
  private var totalCount = 0

  private def update() {
    val time = System.currentTimeMillis / 1000
    val gap = time - indexTime
    val k = if (gap > SIZE) SIZE
    else gap.toInt
    if (k > 0) {
      var i = 1
      while (i <= k) {
        {
          szCount((index + i) % SIZE) = 0
        }
        {
          i += 1; i - 1
        }
      }
      index = (time % SIZE).toInt
      indexTime = time
    }
  }

  def add() {
    update()
    szCount(index) += 1
    totalCount += 1
  }

  def add(count: Int) {
    update()
    szCount(index) += count
    totalCount += count
  }

  def getCount(interval: Int): Int = {
    if (interval > SIZE) {
      logger.warn("Param interval("+interval+") is gt SIZE("+SIZE+")")
      return 0
    }
    update()
    var count = 0
    var i = 0
    while (i < interval) {
      {
        count += szCount((SIZE + index - i) % SIZE)
      }
      {
        i += 1; i - 1
      }
    }
    count
  }

  def getTotalCount: Long = {
    return totalCount
  }

  def reset() {
    totalCount = 0
  }

  override def toString: String = {
    return String.valueOf(totalCount)
  }

}
