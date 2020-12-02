package luj.bean.api.bean;

import java.util.Map;

public interface ImmutableBean<T> {

  Object getField(String name);

  Map<String, Object> getFieldMap();

  Class<T> getBeanType();

  T getValueInstance();
}
