package luj.bean.api.bean;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Bean<T> {

  <F> void setField(Function<T, Supplier<F>> field, F value);

  Class<T> getBeanType();

  T getSetterInstance();

  T getValueInstance();

  /**
   * @see #getValueInstance
   */
  @Deprecated
  T getInstance();
}
