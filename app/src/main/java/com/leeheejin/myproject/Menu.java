package com.leeheejin.myproject;

public class Menu {

  static void listMenu() {
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
}
