package luj.bean.internal.context;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.BiConsumer;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.bean.api.bean.BeanLike;
import luj.bean.api.bean.CustomBean;
import luj.bean.api.bean.ImmutableBean;
import luj.bean.internal.bean.custom.CustomBeanMaker;
import luj.bean.internal.bean.immutable.ImmutableBeanMaker;
import luj.bean.internal.bean.mutable.MutableBeanMaker;
import luj.bean.internal.beanfrom.BeanFromValueGetterV2;
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

  @Override
  public <T> Bean<T> createBean(Class<T> beanType, BiConsumer<Builder, T> builder) {
    BeanProxyValue proxyValue = new DynamicBeanCreator(beanType,
        (BiConsumer<Builder, Object>) builder, ImmutableMap.of()).create();
    return new BeanImpl<>(beanType, proxyValue);
  }

  @Override
  public <T> Bean<T> createBean(Class<T> beanType, Map<String, Object> initValue) {
    return new MutableBeanMaker<>(beanType, initValue).make();
  }

  @Override
  public <T> Bean<T> createBean(Class<T> beanType) {
    return createBean(beanType, ImmutableMap.of());
  }

  @Override
  public <T> ImmutableBean<T> createImmutable(Class<T> beanType, Map<String, ?> initValue) {
    return ImmutableBeanMaker.GET.make(beanType, initValue);
  }

  @Override
  public <T> CustomBean<T> createCustom(Class<T> beanType, CustomBean.Custom custom) {
    return CustomBeanMaker.GET.make(beanType, custom);
  }

  @Override
  public <T> BeanLike<T> getBeanFrom(T valueInstance) {
    return BeanFromValueGetterV2.GET.getBean(valueInstance);
  }
}
