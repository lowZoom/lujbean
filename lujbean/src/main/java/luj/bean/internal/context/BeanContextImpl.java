package luj.bean.internal.context;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.BiConsumer;
import luj.bean.api.BeanContext;
import luj.bean.internal.dynamic.BeanProxyValue;
import luj.bean.internal.dynamic.DynamicBeanCreator;

final class BeanContextImpl implements BeanContext {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(Class<T> beanType, BiConsumer<Builder, T> builder) {
    BeanProxyValue proxyValue = new DynamicBeanCreator(beanType,
        (BiConsumer<Builder, Object>) builder, ImmutableMap.of()).create();
    return (T) proxyValue.getInstance();
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(Class<T> beanType, Map<String, Object> initValue) {
    BeanProxyValue proxyValue = new DynamicBeanCreator(beanType, (b, o) -> {
    }, initValue).create();
    return (T) proxyValue.getInstance();
  }
}
