package luj.bean.internal.context;

import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxyValue;

final class BeanImpl<T> implements Bean<T> {

  BeanImpl(Class<T> beanType, BeanProxyValue proxyValue) {
    _beanType = beanType;
    _proxyValue = proxyValue;
  }

  @Override
  public Class<T> getType() {
    return _beanType;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getInstance() {
    return (T) _proxyValue.getInstance();
  }

  private final Class<T> _beanType;

  private final BeanProxyValue _proxyValue;
}
