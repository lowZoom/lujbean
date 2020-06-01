package luj.bean.api;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface BeanContext {

  interface Builder {

    <T> Builder set(Supplier<T> field, T value);
  }

  <T> T create(Class<T> beanType, BiConsumer<Builder, T> builder);

  <T> T create(Class<T> beanType, Map<String, Object> initValue);
}
