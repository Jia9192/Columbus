package com.galileo.db.levelDB.core

import java.util.Map.Entry


trait Snapshot extends Iterable[Entry[Array[Byte], Array[Byte]]]{

  def get(key: Array[Byte]): Array[Byte]

  def put(key: Array[Byte], value: Array[Byte]): Unit

  def remove(key: Array[Byte]): Unit

  def merge(from: Snapshot): Unit

  def advance: Snapshot

  def retreat: Snapshot

  def getPrevious: Snapshot

  def getRoot: Snapshot

  def setPrevious(previous: Snapshot): Unit

  def close(): Unit

  def reset(): Unit
}
