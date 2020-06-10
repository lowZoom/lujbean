package luj.bean.api.bean;

public interface Bean<T> {

  Class<T> getType();

  T getInstance();
}
