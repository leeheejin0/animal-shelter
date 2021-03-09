package com.leeheejin.pms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.leeheejin.context.ApplicationContextListener;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.pms.handler.MenuHandler;
import com.leeheejin.pms.listener.FileListener;
import com.leeheejin.util.Prompt;

public class App {
  List<ApplicationContextListener> listeners = new LinkedList<>();
  Map<String,Object> appContext = new HashMap<>();

  public static void main(String[] args) {
    App app = new App();
    app.addApplicationContextListener(new FileListener());
    app.service();
  }

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  public void service() {

    notifyOnServiceStarted();

    List<Member> memberList = (List<Member>)appContext.get("memberList");
    List<Cat> catList = (List<Cat>)appContext.get("memberList");
    List<Dog> dogList = (List<Dog>)appContext.get("dogList");
    List<Other> otherList = (List<Other>)appContext.get("otherList");
    List<Board> boardList1 = (List<Board>)appContext.get("memberList");
    List<Board> boardList2 = (List<Board>)appContext.get("memberList");


    MenuHandler menuHandler = new MenuHandler();
    loop:
      while (true) {
        try {
          System.out.println("< 동물 보호소 관리 시스템 >");
          System.out.println("[ 홈* ]");
          System.out.println("(1) 회원가입 / 로그인");
          System.out.println("(2) 비회원 둘러보기"); 
          System.out.println("(3) 시스템 종료"); 
          int command = Prompt.inputInt(">> ");

          switch (command) {
            case 1:
              menuHandler.logInMenu();
              break;
            case 2:
              menuHandler.generalMenu();
              break;
            case 3:
              System.out.println("- 종료합니다. ");
              break loop;
            default:
              System.out.println("- 잘못 입력하셨습니다. ");
              System.out.println();
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }
    Prompt.close();

    notifyOnServiceStoped();
  }

  private void notifyOnServiceStarted() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(appContext);
    }
  }

  private void notifyOnServiceStoped() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(appContext);
    }
  }

}


