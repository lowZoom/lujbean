package luj.bean.internal.bean.immutable;

import java.util.Map;
import luj.bean.api.bean.ImmutableBean;
import luj.bean.internal.dynamic.BeanProxyValue;

final class BeanImpl<T> implements ImmutableBean<T> {

  @Override
  public Object getField(String name) {
    return _proxyValue.getField(name);
  }

  @Override
  public Map<String, Object> getFieldMap() {
    return _proxyValue.getValueMap();
  }

  @Override
  public Class<T> getBeanType() {
    return _beanType;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getValueInstance() {
    return (T) _proxyValue.getInstance();
  }

  Class<T> _beanType;

  BeanProxyValue _proxyValue;
}
