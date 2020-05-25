package luj.bean.internal.context;

import java.util.function.BiConsumer;
import luj.bean.api.BeanContext;
import luj.bean.internal.dynamic.BeanProxyValue;
import luj.bean.internal.dynamic.DynamicBeanCreator;

final class BeanContextImpl implements BeanContext {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(Class<T> beanType, BiConsumer<Builder, T> builder) {
    BeanProxyValue proxyValue = new DynamicBeanCreator(
        beanType, (BiConsumer<Builder, Object>) builder).create();
    return (T) proxyValue.getInstance();
  }
}
