package luj.bean.internal.beanfrom;

import luj.bean.api.bean.BeanLike;
import luj.bean.api.bean.CustomBean;
import luj.bean.internal.bean.custom.CustomBeanMaker;
import luj.bean.internal.dynamic.BeanProxyCustom;

import java.lang.reflect.Proxy;

final class BeanLikeImpl<T> implements BeanLike<T> {

  @SuppressWarnings("unchecked")
  @Override
  public CustomBean<T> asCustom() {
    BeanProxyCustom proxy = (BeanProxyCustom) Proxy.getInvocationHandler(_valueInstance);
    return (CustomBean<T>) CustomBeanMaker.GET.make(proxy.getBeanType(), proxy);
  }

  Object _valueInstance;
}
