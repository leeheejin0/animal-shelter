package com.leeheejin.myproject.handler;

import com.leeheejin.util.Prompt;

public class MenuHandler {

  public static void listMenu() {
    System.out.println("ㄴ<구조 동물 목록>");
    System.out.println("  [1] 고양이 목록 보기");
    System.out.println("  [2] 개 목록 보기"); 
    System.out.println("  [3] 기타 동물 목록 보기");
    System.out.println("  [4] 뒤로가기"); 
    int command = Prompt.inputInt("  >> ");
    switch (command) {
      case 1:
        CatHandler.list();
        break;
      case 2:
        DogHandler.list();
        break;
      case 3:
        OtherHandler.list();
        break;
      default:
        break;
    }
  }
  public static void managerMenu() {
    System.out.println("  ㄴ<관리자 메뉴>");
    System.out.println("    [1] 신규 고양이 등록");
    System.out.println("    [2] 신규 개 등록"); 
    System.out.println("    [3] 신규 기타동물 등록");
    System.out.println("    [4] 게시판 관리");
    System.out.println("    [5] 뒤로가기"); 
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
      case 4:
        BoardHandler.managerMenu();
        break;
      default:
        break;
    }
  }
}
