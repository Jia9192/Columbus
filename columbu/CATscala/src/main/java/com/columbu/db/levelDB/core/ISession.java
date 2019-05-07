package com.galileo.db.levelDB.core;

public interface ISession extends AutoCloseable {

  void commit();

  void revoke();

  void merge();

  void destroy();

  void close();

}
