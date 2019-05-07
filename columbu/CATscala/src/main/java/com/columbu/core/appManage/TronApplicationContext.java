package com.galileo.core.appManage;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.galileo.db.levelDB.store.Manager;

public class TronApplicationContext extends AnnotationConfigApplicationContext {

    public TronApplicationContext() {
    }

    public TronApplicationContext(DefaultListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    public TronApplicationContext(Class<?>... annotatedClasses) {
        super(annotatedClasses);
    }

    public TronApplicationContext(String... basePackages) {
        super(basePackages);
    }

    @Override
    public void destroy() {

        Manager dbManager = getBean(Manager.class);
        dbManager.stopRepushThread();

        super.destroy();
    }
}
