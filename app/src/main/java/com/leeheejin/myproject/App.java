package com.leeheejin.myproject;

import com.leeheejin.myproject.handler.MemberHandler;
import com.leeheejin.myproject.handler.MenuHandler;
import com.leeheejin.util.Prompt;

public class App {
  public static void main(String[] args) {
    //    BoardHandler boardList1 = new BoardHandler();
    //    BoardHandler boardList2 = new BoardHandler();
    //MemberHandler memberList = new MemberHandler();
    MenuHandler menuHandler = new MenuHandler();

    loop:
      while (true) {
        System.out.println("< 동물 보호소 관리 시스템 >");
        System.out.println("[ 홈* ]");
        System.out.println("(1) 회원가입 / 로그인");
        System.out.println("(2) 비회원 둘러보기"); 
        System.out.println("(3) 시스템 종료"); 
        int input = Prompt.inputInt(">> ");
        switch (input) {
          case 1:
            System.out.println("[ 홈 > 회원가입/로그인* ]");
            System.out.println("(1) 회원가입");
            System.out.println("(2) 로그인"); 
            System.out.println("(3) 뒤로가기"); 
            int memberInput = Prompt.inputInt(">> ");
            if (memberInput == 1) {
              MemberHandler.signUp();
            } else if (memberInput == 2) {
              int menuNo = MemberHandler.logIn();
              if (menuNo == 1) {
                menuHandler.managerMenu();
              }
            } else if (memberInput == 3) {
              break;
            } else {
              System.out.println("- 잘못 입력하셨습니다. ");
            }
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
        //    if (input == 1) {
        //      System.out.println("[ 홈 > 회원가입/로그인* ]");
        //      System.out.println("(1) 회원가입");
        //      System.out.println("(2) 로그인"); 
        //      System.out.println("(3) 뒤로가기"); 
        //      int memberInput = Prompt.inputInt(">> ");
        //      if (memberInput == 1) {
        //        MemberHandler.signUp();
        //      } else if (memberInput == 2) {
        //        int command = MemberHandler.logIn();
        //        switch (command) {
        //          case 1: 
        //            MenuHandler.managerMenu();
        //            break;
        //          default:
        //            break;
        //        }
        //      } else if (memberInput == 3) {
        //
        //      }else {
        //        System.out.println("- 잘못 입력하셨습니다. ");
        //      }
        //    } else if (input == 2) {
        //      MenuHandler.generalMenu();
        //    } else if (input == 3) {
        //      System.out.println("- 종료합니다. ");
        //      //
        //    } else {
        //      System.out.println("- 잘못 입력하셨습니다. ");
        //    }
        //    //Prompt.close();
      }
  }
}


