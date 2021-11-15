package luj.bean.internal.bean.immutable;

import com.google.common.collect.ImmutableMap;
import java.lang.reflect.Proxy;
import java.util.Map;
import luj.bean.api.bean.ImmutableBean;
import luj.bean.internal.dynamic.BeanProxyValue;

public enum ImmutableBeanMaker {
  GET;

  public <T> ImmutableBean<T> make(Class<T> beanType, Map<String, ?> valueMap) {
    BeanImpl<T> bean = new BeanImpl<>();

    bean._beanType = beanType;
    bean._proxyValue = createProxyValue(beanType, valueMap);

    return bean;
  }

  private BeanProxyValue createProxyValue(Class<?> beanType, Map<String, ?> valueMap) {
    BeanProxyValue proxyValue = new BeanProxyValue(beanType, ImmutableMap.copyOf(valueMap));

    Object proxyInstance = Proxy.newProxyInstance(
        beanType.getClassLoader(), new Class[]{beanType}, proxyValue);

    proxyValue.setInstance(proxyInstance);
    return proxyValue;
  }
}
