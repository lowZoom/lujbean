package luj.bean.internal.dynamic;

import java.util.function.Supplier;
import luj.bean.api.BeanContext;

final class BeanBuilderImpl implements BeanContext.Builder {

  BeanBuilderImpl(BeanProxySetter proxySetter) {
    _proxySetter = proxySetter;
  }

  @Override
  public <T> BeanContext.Builder set(Supplier<T> field, T value) {
    _proxySetter.setField(field, value);
    return this;
  }

  private final BeanProxySetter _proxySetter;
}
