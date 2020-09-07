package luj.bean.internal.bean;

import java.lang.reflect.Proxy;
import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxyValue;

public class BeanFromValueGetter<T> {

  public BeanFromValueGetter(Object valueInstance) {
    _valueInstance = valueInstance;
  }

  @SuppressWarnings("unchecked")
  public Bean<T> getBean() {
    BeanProxyValue proxy = (BeanProxyValue) Proxy.getInvocationHandler(_valueInstance);

    BeanImpl<T> bean = new BeanImpl<>();
    bean._beanType = (Class<T>) proxy.getBeanType();
    bean._proxyValue = proxy;

    return bean;
  }

  private final Object _valueInstance;
}
