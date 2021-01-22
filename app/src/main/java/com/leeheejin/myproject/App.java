package com.leeheejin.myproject;

import com.leeheejin.myproject.handler.BoardHandler;
import com.leeheejin.myproject.handler.MemberHandler;
import com.leeheejin.myproject.handler.MenuHandler;
import com.leeheejin.util.Prompt;

public class App {
  public static void main(String[] args) {
    while (true) {
      System.out.println("<동물 보호소 관리 시스템>");
      System.out.println("[1] 회원가입");
      System.out.println("[2] 관리자 로그인"); 
      System.out.println("[3] 구조 동물 목록");
      System.out.println("[4] 게시판");
      System.out.println("[5] 시스템 종료");
      int command = Prompt.inputInt(">> ");
      if (command == 1) {
        MemberHandler.signUp();
      } else if (command == 2) {
        MemberHandler.logIn();
      } else if (command == 3) {
        MenuHandler.listMenu();
      } else if (command == 4) {
        BoardHandler.menu();
      } else if (command == 5) {
        System.out.println("종료합니다. ");
        break;
      } else {
        System.out.println("잘못 입력하셨습니다. ");
        System.out.println();
      }
    }
    Prompt.close();
  }
}
