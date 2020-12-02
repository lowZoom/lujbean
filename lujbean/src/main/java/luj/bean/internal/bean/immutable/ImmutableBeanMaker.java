package luj.bean.internal.bean.immutable;

import com.google.common.collect.ImmutableMap;
import java.lang.reflect.Proxy;
import java.util.Map;
import luj.bean.api.bean.ImmutableBean;
import luj.bean.internal.dynamic.BeanProxyValue;

public class ImmutableBeanMaker<T> {

  public ImmutableBeanMaker(Class<T> beanType, Map<String, Object> valueMap) {
    _beanType = beanType;
    _valueMap = valueMap;
  }

  public ImmutableBean<T> make() {
    BeanImpl<T> bean = new BeanImpl<>();

    bean._beanType = _beanType;
    bean._proxyValue = createProxyValue();

    return bean;
  }

  private BeanProxyValue createProxyValue() {
    BeanProxyValue proxyValue = new BeanProxyValue(_beanType, ImmutableMap.copyOf(_valueMap));

    Object proxyInstance = Proxy.newProxyInstance(
        _beanType.getClassLoader(), new Class[]{_beanType}, proxyValue);

    proxyValue.setInstance(proxyInstance);
    return proxyValue;
  }

  private final Class<T> _beanType;

  private final Map<String, Object> _valueMap;
}
