package luj.bean.internal.context;

import luj.bean.api.BeanContext;

public class BeanContextFactory {

  public BeanContext create() {
    return new BeanContextImpl();
  }
}
