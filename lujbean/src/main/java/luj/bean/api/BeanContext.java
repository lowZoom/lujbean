package luj.bean.api;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import luj.bean.api.bean.Bean;

public interface BeanContext {

  interface Builder {

    <T> Builder set(Supplier<T> field, T value);
  }

  @Deprecated
  <T> T create(Class<T> beanType, BiConsumer<Builder, T> builder);

  @Deprecated
  <T> T create(Class<T> beanType, Map<String, Object> initValue);

  @Deprecated
  <T> Bean<T> createBean(Class<T> beanType, BiConsumer<Builder, T> builder);

  <T> Bean<T> createBean(Class<T> beanType);
}
