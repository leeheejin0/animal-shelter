package com.leeheejin.util;

public interface ObjectFactory<T> {
  T create(String csvStr);
}
