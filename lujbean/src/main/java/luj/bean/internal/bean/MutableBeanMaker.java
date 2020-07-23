package luj.bean.internal.bean;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxySetter;
import luj.bean.internal.dynamic.BeanProxyValue;

public class MutableBeanMaker<T> {

  public MutableBeanMaker(Class<T> beanType) {
    _beanType = beanType;
  }

  public Bean<T> make() {
    BeanImpl<T> bean = new BeanImpl<>();
    bean._beanType = _beanType;

    BeanProxyValue proxyVal = createProxyValue();
    bean._proxyValue = proxyVal;
    bean._proxySetter = createProxySetter(proxyVal);

    return bean;
  }

  private BeanProxyValue createProxyValue() {
    BeanProxyValue proxyValue = new BeanProxyValue(new HashMap<>());

    Object proxyInstance = Proxy.newProxyInstance(
        _beanType.getClassLoader(), new Class[]{_beanType}, proxyValue);

    proxyValue.setInstance(proxyInstance);
    return proxyValue;
  }

  private BeanProxySetter createProxySetter(BeanProxyValue proxyValue) {
    BeanProxySetter proxySetter = new BeanProxySetter(proxyValue);

    Object proxyInstance = Proxy.newProxyInstance(
        _beanType.getClassLoader(), new Class[]{_beanType}, proxySetter);

    proxySetter.setInstance(proxyInstance);
    return proxySetter;
  }

  private final Class<T> _beanType;
}
