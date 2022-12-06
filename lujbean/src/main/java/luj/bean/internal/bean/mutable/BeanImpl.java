package luj.bean.internal.bean.mutable;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxySetter;
import luj.bean.internal.dynamic.BeanProxyValue;

final class BeanImpl<T> implements Bean<T> {

  @SuppressWarnings("unchecked")
  @Override
  public <F> void setField(Function<T, Supplier<F>> field, F value) {
    _proxySetter.setField(field.apply((T) _proxySetter.getInstance()), value);
  }

  @Override
  public <F> void setField(Supplier<F> field, F value) {
    _proxySetter.setField(field, value);
  }

  @Override
  public void setField(String name, Object value) {
    _proxyValue.setField(name, value);
  }

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
  public T getSetterInstance() {
    return (T) _proxySetter.getInstance();
  }

  @SuppressWarnings("unchecked")
  @Override
  public T getValueInstance() {
    return (T) _proxyValue.getInstance();
  }

  Class<T> _beanType;

  BeanProxyValue _proxyValue;
  BeanProxySetter _proxySetter;
}
