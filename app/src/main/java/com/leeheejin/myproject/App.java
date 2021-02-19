package com.leeheejin.myproject;

import com.leeheejin.myproject.handler.MenuHandler;
import com.leeheejin.util.Prompt;

public class App {
  public static void main(String[] args) {

    MenuHandler menuHandler = new MenuHandler();

    loop:
      while (true) {
        System.out.println("< 동물 보호소 관리 시스템 >");
        System.out.println("[ 홈* ]");
        System.out.println("(1) 회원가입 / 로그인");
        System.out.println("(2) 비회원 둘러보기"); 
        System.out.println("(3) 시스템 종료"); 
        int command = Prompt.inputInt(" >> ");
        try {
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
        System.out.println();
      }
  }
}


