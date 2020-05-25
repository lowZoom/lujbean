package luj.bean.internal.dynamic;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.function.BiConsumer;
import luj.bean.api.BeanContext;

public class DynamicBeanCreator {

  public DynamicBeanCreator(Class<?> beanType,
      BiConsumer<BeanContext.Builder, Object> beanBuilder) {
    _beanType = beanType;
    _beanBuilder = beanBuilder;
  }

  public BeanProxyValue create() {
    BeanProxyValue proxyValue = createProxyValue();
    BeanProxySetter proxySetter = createProxySetter(proxyValue);

    BeanBuilderImpl builder = new BeanBuilderImpl(proxySetter);
    _beanBuilder.accept(builder, proxySetter.getInstance());

    return proxyValue;
  }

  private BeanProxyValue createProxyValue() {
    BeanProxyValue proxyValue = new BeanProxyValue(new HashMap<>());

    Object proxyInstance = Proxy.newProxyInstance(
        _beanType.getClassLoader(), new Class[]{_beanType}, proxyValue);

    proxyValue.setInstance(proxyInstance);
    return proxyValue;
  }

  private BeanProxySetter createProxySetter(BeanProxyValue proxyValue) {
    BeanProxySetter proxySetter = new BeanProxySetter(proxyValue);

    Object proxyInstance = Proxy.newProxyInstance(
        _beanType.getClassLoader(), new Class[]{_beanType}, proxySetter);

    proxySetter.setInstance(proxyInstance);
    return proxySetter;
  }

  private final Class<?> _beanType;

  private final BiConsumer<BeanContext.Builder, Object> _beanBuilder;
}
