package luj.bean.internal.dynamic;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class BeanProxyValue implements InvocationHandler {

  public BeanProxyValue(Map<String, Object> valueMap) {
    _valueMap = valueMap;
  }

  public void setField(String name, Object value) {
    _valueMap.put(name, value);
  }

  public Object getInstance() {
    return _instance;
  }

  public void setInstance(Object instance) {
    _instance = instance;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    String methodName = method.getName();
    if ("toString".equals(methodName)) {
      return toString();
    }
    Object fieldValue = _valueMap.get(methodName);
    if (fieldValue == null) {
      return getDefaultValue(method.getReturnType());
    }
    return fieldValue;
  }

  @Override
  public String toString() {
    return _valueMap.toString();
  }

  private Object getDefaultValue(Class<?> fieldType) {
    if (fieldType.isAssignableFrom(List.class)) {
      return ImmutableList.of();
    }
    return null;
  }

  private Object _instance;

  private final Map<String, Object> _valueMap;
}