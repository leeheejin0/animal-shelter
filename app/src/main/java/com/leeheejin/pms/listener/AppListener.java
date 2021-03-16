package com.leeheejin.pms.listener;

import java.util.Map;
import com.leeheejin.context.ApplicationContextListener;

public class AppListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("<동물보호소 관리 시스템에 오신 걸 환영합니다>");
  }

  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("<동물보호소 관리 시스템을 종료합니다>");
  }

}
