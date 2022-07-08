package luj.bean.internal.bean.mutable;

import java.lang.reflect.Proxy;
import luj.bean.api.bean.Bean;
import luj.bean.internal.dynamic.BeanProxyValue;

public enum BeanFromValueGetter {
  GET;

  @SuppressWarnings("unchecked")
  public <T> Bean<T> getBean(Object valueInstance) {
    BeanProxyValue proxy = (BeanProxyValue) Proxy.getInvocationHandler(valueInstance);

    BeanImpl<T> bean = new BeanImpl<>();
    bean._beanType = (Class<T>) proxy.getBeanType();
    bean._proxyValue = proxy;

    return bean;
  }
}
