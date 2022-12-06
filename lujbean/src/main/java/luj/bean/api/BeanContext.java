package luj.bean.api;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import luj.bean.api.bean.Bean;
import luj.bean.api.bean.BeanLike;
import luj.bean.api.bean.CustomBean;
import luj.bean.api.bean.ImmutableBean;

public interface BeanContext {

  interface Builder {

    <T> Builder set(Supplier<T> field, T value);
  }

  @Deprecated
  <T> T create(Class<T> beanType, BiConsumer<Builder, T> builder);

  @Deprecated
  <T> Bean<T> createBean(Class<T> beanType, BiConsumer<Builder, T> builder);

  <T> Bean<T> createBean(Class<T> beanType);

  <T> Bean<T> createBean(Class<T> beanType, Map<String, Object> initValue);

  <T> ImmutableBean<T> createImmutable(Class<T> beanType, Map<String, ?> initValue);

  <T> CustomBean<T> createCustom(Class<T> beanType, CustomBean.Custom custom);

  <T> BeanLike<T> getBeanFrom(T valueInstance);
}
