package luj.bean.api.bean;

public interface CustomBean<T> {

  interface Custom {

    Object getFieldValue(String name);

    @Override
    String toString();
  }

  Class<T> getBeanType();

  <C extends Custom> C getCustom(Class<C> type);

  T getValueInstance();
}
