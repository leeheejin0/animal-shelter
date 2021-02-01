package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.App;
import com.leeheejin.util.Prompt;

public class MenuHandler {
  MemberHandler memberList = new MemberHandler();

  public static void generalMenu() {
    System.out.println("[ 홈 > 메뉴* ]");
    System.out.println("(1) 구조 동물 목록");
    System.out.println("(2) 게시판");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    if (command == 1) {
      MenuHandler.listMenu1();
    } else if (command == 2) {
      BoardHandler.menu();
    } else if (command == 3) {
      //뒤로가기
      return;
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
  public static void managerMenu() {
    System.out.println("[ 홈 > 관리자 메뉴* ]");
    System.out.println("(1) 회원정보수정");
    System.out.println("(2) 로그아웃"); 
    System.out.println("(3) 구조 동물 목록");
    System.out.println("(4) 게시판");
    int mCommand = Prompt.inputInt(">> ");
    if (mCommand == 1) {
      MemberHandler.updateInfo();
      managerMenu();
    } else if (mCommand == 2) {
      System.out.println("- 로그아웃 되었습니다. \n");
      return;
    } else if (mCommand == 3) {
      listMenu2();
    } else if (mCommand == 4) {
      BoardHandler.menu2();
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }


  }
  //
  //
  public static void listMenu1() {
    System.out.println("[ 홈 > 메뉴 > 구조동물 목록* ]");
    System.out.println("(1) 고양이 목록 보기");
    System.out.println("(2) 개 목록 보기"); 
    System.out.println("(3) 기타 동물 목록 보기");
    System.out.println("(4) 뒤로가기"); 
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        CatHandler.list1();
        listMenu1();
        break;
      case 2:
        DogHandler.list1();
        listMenu1();
        break;
      case 3:
        OtherHandler.list1();
        listMenu1();
        break;
      default:
        App.main(null);
        break;
    }
  }

  public static void listMenu2() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록* ]");
    System.out.println("[1] 신규 등록");
    System.out.println("[2] 고양이 목록 보기");
    System.out.println("[3] 개 목록 보기"); 
    System.out.println("[4] 기타 동물 목록 보기");
    System.out.println("[5] 뒤로가기"); 
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1: 
        addAnimal();
        listMenu2();
        break;
      case 2:
        CatHandler.list2();
        listMenu2();
        break;
      case 3:
        DogHandler.list2();
        listMenu2();
        break;
      case 4:
        OtherHandler.list2();
        listMenu2();
        break;
      default:
        managerMenu();
        break;
    }
  }

  public static void addAnimal() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록* ]");
    System.out.println("(1) 신규 고양이 등록");
    System.out.println("(2) 신규 개 등록"); 
    System.out.println("(3) 신규 기타동물 등록");
    System.out.println("(4) 뒤로가기"); 
    int command = Prompt.inputInt(" >> ");
    switch (command) {
      case 1:
        CatHandler.add();
        addAnimal();
        break;
      case 2:
        DogHandler.add();
        addAnimal();
        break;
      case 3:
        OtherHandler.add();
        addAnimal();
        break;
      default:
        listMenu2();
        break;
    }
  }
}
