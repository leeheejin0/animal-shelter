package com.leeheejin.myproject;

public class App {
  public static void main(String[] args) {
    while (true) {
      System.out.println("<동물 보호소 관리 시스템>");
      System.out.println("[1] 고양이 구조");
      System.out.println("[2] 개 구조"); 
      System.out.println("[3] 기타 동물 구조");
      System.out.println("[4] 구조 동물 목록 보기");
      System.out.println("[5] 시스템 종료");
      int command = Prompt.inputInt(">> ");
      if (command == 1) {
        CatHandler.add();
      } else if (command == 2) {
        DogHandler.add();
      } else if (command == 3) {
        OtherHandler.add();
      } else if (command == 4) {
        Menu.listMenu();
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
