package com.galileo.db.levelDB.store;

import org.tron.core.db2.common.IRevokingDB;
import com.galileo.db.levelDB.core.ISession;
import com.galileo.core.exception.RevokingStoreIllegalStateException;

public interface RevokingDatabase {

  ISession buildSession();

  ISession buildSession(boolean forceEnable);

  void add(IRevokingDB revokingDB);

  void merge() throws RevokingStoreIllegalStateException;

  void revoke() throws RevokingStoreIllegalStateException;

  void commit() throws RevokingStoreIllegalStateException;

  void pop() throws RevokingStoreIllegalStateException;

  void fastPop() throws RevokingStoreIllegalStateException;

  void enable();

  int size();

  void check();

  void setMaxSize(int maxSize);

  void disable();

  void shutdown();
}
