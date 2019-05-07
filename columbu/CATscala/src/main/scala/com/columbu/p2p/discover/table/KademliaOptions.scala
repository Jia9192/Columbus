package com.galileo.p2p.discover.table

object KademliaOptions {

  val BUCKET_SIZE = 16
  val ALPHA = 3
  val BINS = 256
  val MAX_STEPS = 8
  val REQ_TIMEOUT = 300
  val BUCKET_REFRESH = 7200 //bucket refreshing interval in millis
  val DISCOVER_CYCLE = 30 //discovery cycle interval in seconds

}
