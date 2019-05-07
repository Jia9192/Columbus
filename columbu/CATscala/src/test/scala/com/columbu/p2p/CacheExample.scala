package com.galileo.p2p

import java.util.concurrent.TimeUnit

import com.galileo.p2p.net.message.TransactionMessage
import com.galileo.utils.Sha256Hash
import com.google.common.cache.{Cache, CacheBuilder}

object CacheExample {

  def main(args: Array[String]): Unit = {
    val TrxCache: Cache[Sha256Hash, TransactionMessage] = CacheBuilder.newBuilder.maximumSize(50000).expireAfterWrite(1, TimeUnit.HOURS).initialCapacity(50000).recordStats.build.asInstanceOf[Cache[Sha256Hash, TransactionMessage]]
  }

}
