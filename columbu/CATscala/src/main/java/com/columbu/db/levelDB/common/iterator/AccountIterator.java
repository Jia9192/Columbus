package com.galileo.db.levelDB.common.iterator;

import com.galileo.core.capsule.AccountCapsule;

import java.util.Iterator;
import java.util.Map.Entry;

public class AccountIterator extends AbstractIterator<AccountCapsule> {

  public AccountIterator(Iterator<Entry<byte[], byte[]>> iterator) {
    super(iterator);
  }

  @Override
  protected AccountCapsule of(byte[] value) {
    return new AccountCapsule(value);
  }
}
