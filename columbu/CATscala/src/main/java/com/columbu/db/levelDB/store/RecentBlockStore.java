package com.galileo.db.levelDB.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.galileo.core.capsule.BytesCapsule;
import com.galileo.core.exception.ItemNotFoundException;

@Component
public class RecentBlockStore extends TronStoreWithRevoking<BytesCapsule> {

  @Autowired
  private RecentBlockStore(@Value("recent-block") String dbName) {
    super(dbName);
  }

  @Override
  public BytesCapsule get(byte[] key) throws ItemNotFoundException {
    byte[] value = revokingDB.get(key);

    return new BytesCapsule(value);
  }
}
