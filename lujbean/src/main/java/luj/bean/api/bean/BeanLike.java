package luj.bean.api.bean;

public interface BeanLike<T> {

  Bean<T> asMutable();

  CustomBean<T> asCustom();
}
