package com.galileo.utils

import java.util.HashMap
import java.util.Map
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import com.galileo.p2p.message.Message

class SafeMessageMap {
  
  protected val storage : Map[Sha256Hash, Message] = new HashMap[Sha256Hash, Message]()
  protected var rwLock : ReadWriteLock = new ReentrantReadWriteLock()
  protected var readLock : ALock = new ALock(rwLock.readLock())
  protected var writeLock : ALock = new ALock(rwLock.writeLock())
  

  
  def put(msgId : Sha256Hash, msg : Message) : Unit = {
    if (msg == null) {
      delete(msgId)
    } else {
      try {
        var l : ALock = writeLock.lock
        storage.put(msgId, msg)
      }
    }
  }
  
  def put(msg : Message) : Unit = {
    put(Sha256Hash.of(msg.getData), msg)
  }
  
  def get(msgId : Sha256Hash) : Message = {
    try {
      var l : ALock = readLock.lock
      return storage.get(msgId)
    }
  }
  
  def delete(msgId : Sha256Hash) : Unit = {
    try {
      var l : ALock = writeLock.lock
      storage.remove(msgId)
    }
  }
  
}