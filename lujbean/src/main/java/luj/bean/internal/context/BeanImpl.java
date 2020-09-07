package luj.bean.internal.context;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxyValue;

/**
 * @see luj.bean.internal.bean.BeanImpl
 */
@Deprecated
final class BeanImpl<T> implements Bean<T> {

  BeanImpl(Class<T> beanType, BeanProxyValue proxyValue) {
    _beanType = beanType;
    _proxyValue = proxyValue;
  }

  @Override
  public <F> void setField(Function<T, Supplier<F>> field, F value) {
    throw new UnsupportedOperationException("setField");
  }

  @Override
  public Object getField(String name) {
    throw new UnsupportedOperationException("getField");
  }

  @Override
  public Map<String, Object> getFieldMap() {
    throw new UnsupportedOperationException("getFieldMap");
  }

  @Override
  public Class<T> getBeanType() {
    return _beanType;
  }

  @Override
  public T getSetterInstance() {
    throw new UnsupportedOperationException("getSetterInstance");
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

  private final Class<T> _beanType;

  private final BeanProxyValue _proxyValue;
}
