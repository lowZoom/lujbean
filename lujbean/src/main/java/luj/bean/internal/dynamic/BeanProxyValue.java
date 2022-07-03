package luj.bean.internal.dynamic;

import luj.bean.api.bean.CustomBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class BeanProxyValue implements InvocationHandler, CustomBean.Custom {

  public static BeanProxyValue create(Class<?> beanType, Map<String, Object> valueMap) {
    BeanProxyValue result = new BeanProxyValue(valueMap);
    result._impl = new BeanProxyCustom(beanType, result);
    return result;
  }

  public Object getField(String name) {
    return _valueMap.get(name);
  }

  public void setField(String name, Object value) {
    _valueMap.put(name, value);
  }

  public Object getInstance() {
    return _impl.getInstance();
  }

  public void setInstance(Object instance) {
    _impl.setInstance(instance);
  }

  public Class<?> getBeanType() {
    return _impl.getBeanType();
  }

  public Map<String, Object> getValueMap() {
    return _valueMap;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    return _impl.invoke(proxy, method, args);
  }

  @Override
  public Object getFieldValue(String name) {
    return _valueMap.get(name);
  }

  @Override
  public String toString() {
    return _valueMap.toString();
  }

  BeanProxyValue(Map<String, Object> valueMap) {
    _valueMap = valueMap;
  }

  BeanProxyCustom _impl;

  private final Map<String, Object> _valueMap;
}
