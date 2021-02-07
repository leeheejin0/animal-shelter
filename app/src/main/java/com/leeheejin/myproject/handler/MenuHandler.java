package com.leeheejin.myproject.handler;

import com.leeheejin.util.Prompt;

public class MenuHandler {
  CatHandler catHandler = new CatHandler();
  DogHandler dogHandler = new DogHandler();
  OtherHandler otherHandler = new OtherHandler();
  BoardHandler boardHandler = new BoardHandler();

  public void generalMenu() {
    System.out.println("[ 홈 > 메뉴* ]");
    System.out.println("(1) 구조 동물 목록");
    System.out.println("(2) 게시판");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    if (command == 1) {
      generalListMenu();
    } else if (command == 2) {
      boardHandler.generalMenu();
    } else if (command == 3) {
      //뒤로가기
      return;
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
  public void managerMenu() {
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
      managerListMenu();
    } else if (mCommand == 4) {
      boardHandler.managerMenu();
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }


  }
  //
  //
  public void generalListMenu() {
    System.out.println("[ 홈 > 메뉴 > 구조동물 목록* ]");
    System.out.println("(1) 고양이 목록 보기");
    System.out.println("(2) 개 목록 보기"); 
    System.out.println("(3) 기타 동물 목록 보기");
    System.out.println("(4) 뒤로가기"); 
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        catHandler.generalList();
        generalListMenu();
        break;
      case 2:
        dogHandler.generalList();
        generalListMenu();
        break;
      case 3:
        otherHandler.generalList();
        generalListMenu();
        break;
      default:
        break;
    }
  }

  public void managerListMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록* ]");
    System.out.println("[1] 신규 등록");
    System.out.println("[2] 고양이 목록 보기");
    System.out.println("[3] 개 목록 보기"); 
    System.out.println("[4] 기타 동물 목록 보기");
    System.out.println("[5] 뒤로가기"); 
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1: 
        addAnimalMenu();
        managerListMenu();
        break;
      case 2:
        catHandler.managerList();
        managerListMenu();
        break;
      case 3:
        dogHandler.managerList();
        managerListMenu();
        break;
      case 4:
        otherHandler.managerList();
        managerListMenu();
        break;
      default:
        managerMenu();
        break;
    }
  }

  public void addAnimalMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록* ]");
    System.out.println("(1) 신규 고양이 등록");
    System.out.println("(2) 신규 개 등록"); 
    System.out.println("(3) 신규 기타동물 등록");
    System.out.println("(4) 뒤로가기"); 
    int command = Prompt.inputInt(" >> ");
    switch (command) {
      case 1:
        catHandler.add();
        addAnimalMenu();
        break;
      case 2:
        dogHandler.add();
        addAnimalMenu();
        break;
      case 3:
        otherHandler.add();
        addAnimalMenu();
        break;
      default:
        managerListMenu();
        break;
    }
  }
}
