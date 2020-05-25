package luj.bean.api;

import luj.bean.internal.context.BeanContextFactory;

public enum LujBean {
  ;

  public static BeanContext start() {
    return new BeanContextFactory().create();
  }
}
