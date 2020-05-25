package luj.bean.internal.dynamic;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Defaults;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class BeanProxySetter implements InvocationHandler {

  public BeanProxySetter(BeanProxyValue proxyValue) {
    _proxyValue = proxyValue;
  }

  public void setField(Supplier<?> field, Object value) {
    field.get();
    checkNotNull(_settingField);

    _proxyValue.setField(_settingField, value);
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
    _settingField = methodName;
    return Defaults.defaultValue(method.getReturnType());
  }

  private Object _instance;
  private String _settingField;

  private final BeanProxyValue _proxyValue;
}
