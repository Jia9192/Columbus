package com.galileo.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.galileo.core.config.args.Args;
import com.galileo.db.levelDB.store.RevokingDatabase;
import com.galileo.db.levelDB.store.RevokingStore;
import com.galileo.db.levelDB.api.IndexHelper;
import com.galileo.db.levelDB.core.SnapshotManager;

@Configuration
@Import(CommonConfig.class)
public class DefaultConfig {

  private static Logger logger = LoggerFactory.getLogger("general");

  @Autowired
  ApplicationContext appCtx;

  @Autowired
  CommonConfig commonConfig;

  public DefaultConfig() {
    Thread.setDefaultUncaughtExceptionHandler((t, e) -> logger.error("Uncaught exception", e));
  }

  @Bean
  public IndexHelper indexHelper() {
    if (!Args.getInstance().isSolidityNode()) {
      return null;
    }
    return new IndexHelper();
  }

  @Bean
  public RevokingDatabase revokingDatabase() {
    int dbVersion = Args.getInstance().getStorage().getDbVersion();
    if (dbVersion == 1) {
      return RevokingStore.getInstance();
    } else if (dbVersion == 2) {
      return new SnapshotManager();
    } else {
      throw new RuntimeException("db version is error.");
    }
  }

}
