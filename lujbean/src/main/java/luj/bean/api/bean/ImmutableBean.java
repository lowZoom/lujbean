package luj.bean.api.bean;

import java.util.Map;

public interface ImmutableBean<T> {

  Object getField(String name);

  Map<String, Object> getFieldMap();

  Class<T> getBeanType();

  T getValueInstance();

  <T2> ImmutableBean<T2> as(Class<T2> newType);
}
