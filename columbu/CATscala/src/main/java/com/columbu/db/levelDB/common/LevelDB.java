package org.tron.core.db2.common;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.iq80.leveldb.WriteOptions;
import com.galileo.db.levelDB.LevelDbDataSourceImpl;
import com.galileo.db.levelDB.common.WrappedByteArray;
import com.galileo.db.levelDB.common.iterator.DBIterator;

public class LevelDB implements DB<byte[], byte[]> {
  @Getter
  private LevelDbDataSourceImpl db;
  private WriteOptions writeOptions = new WriteOptions().sync(true);

  public LevelDB(String parentName, String name) {
    db = new LevelDbDataSourceImpl(parentName, name);
    db.initDB();
  }

  @Override
  public byte[] get(byte[] key) {
    return db.getData(key);
  }

  @Override
  public void put(byte[] key, byte[] value) {
    db.putData(key, value);
  }

  @Override
  public void remove(byte[] key) {
    db.deleteData(key);
  }

  @Override
  public DBIterator iterator() {
    return db.iterator();
  }

  public void flush(Map<WrappedByteArray, WrappedByteArray> batch) {
    Map<byte[], byte[]> rows = batch.entrySet().stream()
        .map(e -> Maps.immutableEntry(e.getKey().getBytes(), e.getValue().getBytes()))
        .collect(HashMap::new, (m, k) -> m.put(k.getKey(), k.getValue()), HashMap::putAll);
    db.updateByBatch(rows, writeOptions);
  }

  public void close() {
    db.closeDB();
  }

  public void reset() {
    db.resetDb();
  }
}
