package com.galileo.db.levelDB.api.index;

import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.persistence.disk.DiskPersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.galileo.utils.ByteArray;
import com.galileo.core.capsule.AccountCapsule;
import com.galileo.db.levelDB.common.WrappedByteArray;
import com.galileo.db.levelDB.core.ITronChainBase;
import com.galileo.protos.protos.Protocol.Account;

import javax.annotation.PostConstruct;

import static com.googlecode.cqengine.query.QueryFactory.attribute;

@Component
@Slf4j
public class AccountIndex extends AbstractIndex<AccountCapsule, Account> {

  public static SimpleAttribute<WrappedByteArray, String> Account_ADDRESS;

  @Autowired
  public AccountIndex(@Qualifier("accountStore") final ITronChainBase<AccountCapsule> database) {
    super(database);
  }

  @PostConstruct
  public void init() {
    initIndex(DiskPersistence.onPrimaryKeyInFile(Account_ADDRESS, indexPath));
//    index.addIndex(DiskIndex.onAttribute(Account_ADDRESS));
  }

  @Override
  protected void setAttribute() {
    Account_ADDRESS = attribute("account address",
        bytes -> ByteArray.toHexString(bytes.getBytes()));
  }
}
