package com.leeheejin.pms.handler;

public interface Command {
  void service();
  void service(int no);
  void service(String str1, String str2);
}
