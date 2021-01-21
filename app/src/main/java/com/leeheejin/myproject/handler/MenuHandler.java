package com.leeheejin.myproject.handler;

import com.leeheejin.util.Prompt;

public class MenuHandler {

  public static void listMenu() {
    System.out.println("ㄴ<구조 동물 목록>");
    System.out.println("  [1] 고양이 목록 보기");
    System.out.println("  [2] 개 목록 보기"); 
    System.out.println("  [3] 기타 동물 목록 보기");
    System.out.println("  [4] 뒤로가기"); 
    int listCommand = Prompt.inputInt("  >> ");
    switch (listCommand) {
      case 1:
        CatHandler.list();
        break;
      case 2:
        DogHandler.list();
        break;
      case 3:
        OtherHandler.list();
      default:
        break;
    }
  }
  public static void managerMenu() {
    System.out.println("  ㄴ<관리자 메뉴>");
    System.out.println("    [1] 고양이 구조");
    System.out.println("    [2] 개 구조"); 
    System.out.println("    [3] 기타 동물 구조");
    System.out.println("    [4] 뒤로가기"); 
    int listCommand = Prompt.inputInt("    >> ");
    switch (listCommand) {
      case 1:
        CatHandler.add();
        managerMenu();
        break;
      case 2:
        DogHandler.add();
        managerMenu();
        break;
      case 3:
        OtherHandler.add();
        managerMenu();
      default:
        break;
    }
  }
  public static void boardMenu() {

  }
}
