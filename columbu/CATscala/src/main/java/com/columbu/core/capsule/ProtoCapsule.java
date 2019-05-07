package com.galileo.core.capsule;

public interface ProtoCapsule<T> {

  byte[] getData();

  T getInstance();
}
