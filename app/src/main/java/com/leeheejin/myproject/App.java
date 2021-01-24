package com.leeheejin.myproject;

import com.leeheejin.myproject.handler.BoardHandler;
import com.leeheejin.myproject.handler.MemberHandler;
import com.leeheejin.myproject.handler.MenuHandler;
import com.leeheejin.util.Prompt;

public class App {
  public static void main(String[] args) {
    BoardHandler boardList1 = new BoardHandler();
    BoardHandler boardList2 = new BoardHandler();
    MemberHandler memberList = new MemberHandler();

    while (true) {

      MenuHandler.homeInMenu();
      int command = Prompt.inputInt(">> ");
      if (command == 1) {
        memberList.signUp();
      } else if (command == 2) {
        memberList.logIn();
        MenuHandler.homeOutMenu();
      } else if (command == 3) {
        MenuHandler.listMenu1();
      } else if (command == 4) {
        boardList1.menu();
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
