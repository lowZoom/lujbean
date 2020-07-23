package luj.bean.internal.bean;

import java.util.function.Function;
import java.util.function.Supplier;
import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxySetter;
import luj.bean.internal.dynamic.BeanProxyValue;

final class BeanImpl<T> implements Bean<T> {

  @Override
  public <F> void setField(Function<T, Supplier<F>> field, F value) {
    _proxySetter.setField(field.apply((T) _proxySetter.getInstance()), value);
  }

  @Override
  public Class<T> getBeanType() {
    return _beanType;
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getSetterInstance() {
    return (T) _proxySetter.getInstance();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getValueInstance() {
    return (T) _proxyValue.getInstance();
  }

  @Override
  public T getInstance() {
    return getValueInstance();
  }

  Class<T> _beanType;

  BeanProxyValue _proxyValue;
  BeanProxySetter _proxySetter;
}