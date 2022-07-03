package luj.bean.internal.beanfrom;

import luj.bean.api.bean.BeanLike;

public enum BeanFromValueGetterV2 {
  GET;

  public <T> BeanLike<T> getBean(T value) {
    BeanLikeImpl<T> result = new BeanLikeImpl<>();
    result._valueInstance = value;
    return result;
  }
}
