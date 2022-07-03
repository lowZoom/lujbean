package luj.bean.internal.dynamic;

import com.google.common.base.Defaults;
import com.google.common.collect.ImmutableList;
import luj.bean.api.bean.CustomBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class BeanProxyCustom implements InvocationHandler {

  public BeanProxyCustom(Class<?> beanType, CustomBean.Custom custom) {
    _beanType = beanType;
    _custom = custom;
  }

  public Object getInstance() {
    return _instance;
  }

  public void setInstance(Object instance) {
    _instance = instance;
  }

  public Class<?> getBeanType() {
    return _beanType;
  }

  public CustomBean.Custom getCustom() {
    return _custom;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    String methodName = method.getName();
    if ("toString".equals(methodName)) {
      return _custom.toString();
    }

    Object fieldValue = _custom.getFieldValue(methodName);
    if (fieldValue != null) {
      return fieldValue;
    }

    return getDefaultValue(method.getReturnType());
  }

  private Object getDefaultValue(Class<?> fieldType) {
    if (fieldType.isAssignableFrom(List.class)) {
      return ImmutableList.of();
    }
    return Defaults.defaultValue(fieldType);
  }

  private Object _instance;

  private final Class<?> _beanType;
  private final CustomBean.Custom _custom;
}
