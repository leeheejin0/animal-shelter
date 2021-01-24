package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.App;
import com.leeheejin.util.Prompt;

public class MenuHandler {
  public static void homeInMenu() {
    System.out.println("<동물 보호소 관리 시스템>");
    System.out.println("[1] 회원가입");
    System.out.println("[2] 로그인"); 
    System.out.println("[3] 구조 동물 목록");
    System.out.println("[4] 게시판");
    System.out.println("[5] 시스템 종료");
  }
  public static void homeOutMenu() {
    System.out.println("<동물 보호소 관리 시스템>");
    System.out.println("[1] 회원정보수정");
    System.out.println("[2] 로그아웃"); 
    System.out.println("[3] 구조 동물 목록");
    System.out.println("[4] 게시판");
    System.out.println("[5] 시스템 종료");
    int mCommand = Prompt.inputInt(">> ");
    if (mCommand == 1) {
      MemberHandler.editInfo();
    } else if (mCommand == 2) {
      System.out.println("- 로그아웃 되었습니다. \n");
      App.main(null);
    } else if (mCommand == 3) {
      listMenu2();
    } else if (mCommand == 4) {
      BoardHandler.menu();
    } else if (mCommand == 5) {
      System.out.println("종료합니다. ");
      //break;
    } else {
      System.out.println("잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
  //
  //
  public static void listMenu1() {
    System.out.println("ㄴ<구조 동물 목록>");
    System.out.println("  [1] 고양이 목록 보기");
    System.out.println("  [2] 개 목록 보기"); 
    System.out.println("  [3] 기타 동물 목록 보기");
    System.out.println("  [4] 뒤로가기"); 
    int command = Prompt.inputInt("  >> ");
    switch (command) {
      case 1:
        CatHandler.list1();
        break;
      case 2:
        DogHandler.list1();
        break;
      case 3:
        OtherHandler.list1();
        break;
      default:
        break;
    }
  }

  public static void listMenu2() {
    System.out.println("ㄴ<구조 동물 목록>");
    System.out.println("  [1] 신규 등록");
    System.out.println("  [2] 고양이 목록 보기");
    System.out.println("  [3] 개 목록 보기"); 
    System.out.println("  [4] 기타 동물 목록 보기");
    System.out.println("  [5] 뒤로가기"); 
    int command = Prompt.inputInt("  >> ");
    switch (command) {
      case 1: 
        addAnimal();
        break;
      case 2:
        CatHandler.list2();
        break;
      case 3:
        DogHandler.list2();
        break;
      case 4:
        OtherHandler.list2();
        break;
      default:
        break;
    }
  }

  public static void addAnimal() {
    System.out.println("  ㄴ<신규 등록>");
    System.out.println("    [1] 신규 고양이 등록");
    System.out.println("    [2] 신규 개 등록"); 
    System.out.println("    [3] 신규 기타동물 등록");
    System.out.println("    [4] 뒤로가기"); 
    int command = Prompt.inputInt("    >> ");
    switch (command) {
      case 1:
        CatHandler.add();
        break;
      case 2:
        DogHandler.add();
        break;
      case 3:
        OtherHandler.add();
        break;
      default:
        break;
    }
  }
}
