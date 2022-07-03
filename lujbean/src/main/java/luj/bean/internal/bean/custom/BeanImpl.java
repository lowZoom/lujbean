package luj.bean.internal.bean.custom;

import luj.bean.api.bean.CustomBean;
import luj.bean.internal.dynamic.BeanProxyCustom;

final class BeanImpl<T> implements CustomBean<T> {

  @Override
  public Class<T> getBeanType() {
    return _beanType;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <C extends CustomBean.Custom> C getCustom(Class<C> type) {
    return (C) _proxy.getCustom();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getValueInstance() {
    return (T) _proxy.getInstance();
  }

  Class<T> _beanType;

  BeanProxyCustom _proxy;
}
