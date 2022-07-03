package luj.bean.internal.bean.custom;

import luj.bean.api.bean.CustomBean;
import luj.bean.internal.dynamic.BeanProxyCustom;

import java.lang.reflect.Proxy;

public enum CustomBeanMaker {
  GET;

  public <T> CustomBean<T> make(Class<T> beanType, CustomBean.Custom custom) {
    BeanProxyCustom proxy = createProxy(beanType, custom);
    return make(beanType, proxy);
  }

  public <T> CustomBean<T> make(Class<T> beanType, BeanProxyCustom proxy) {
    BeanImpl<T> bean = new BeanImpl<>();
    bean._beanType = beanType;
    bean._proxy = proxy;
    return bean;
  }

  private BeanProxyCustom createProxy(Class<?> beanType, CustomBean.Custom custom) {
    BeanProxyCustom proxy = new BeanProxyCustom(beanType, custom);

    Object proxyInstance = Proxy.newProxyInstance(
        beanType.getClassLoader(), new Class[]{beanType}, proxy);

    proxy.setInstance(proxyInstance);
    return proxy;
  }
}
