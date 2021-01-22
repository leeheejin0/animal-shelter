package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.util.Prompt;

public class BoardHandler {
  static class Board {
    int no;
    String name;
    String password;
    String title;
    String content;
    Date registeredDate;
    int viewCount;
    int like;
  }
  final static int LENGTH = 100;
  static Board[] boards = new Board[LENGTH];
  static int size = 0; 

  public static void menu() {
    System.out.println("  ㄴ<게시판>");
    System.out.println("    [1] 글쓰기");
    System.out.println("    [2] 글목록");
    System.out.println("    [3] 뒤로가기");
    int command = Prompt.inputInt("    >> ");
    switch (command) {
      case 1:
        add();
        break;
      case 2:
        list();
      default:
        break;
    }
  }
  static void add() {
    System.out.println("    ㄴ<글쓰기>");
    Board b = new Board();
    b.no = size + 1;
    b.name = Prompt.inputString("      이름>> ");
    b.password = Prompt.inputString("      비밀번호>> ");
    b.title = Prompt.inputString("      제목>> ");
    b.content = Prompt.inputString("      내용>> ");
    b.registeredDate = new Date(System.currentTimeMillis());
    boards[size++] = b;
  }
  static void list() {
    System.out.println("    ㄴ<글목록>");
    for (int i = 0; i < size; i++) {
      Board b = boards[i];
      System.out.printf("      [%d] %s |%s| %s |%d|%d|\n", 
          b.no, b.title, b.registeredDate, b.name, b.viewCount, b.like);
    }
    int command = Prompt.inputInt("    1: 수정 | 2: 삭제 | 3: 뒤로가기\n    >>");
    switch (command) {
      case 1:
        edit();
        break;
      case 2:
        delete();
        break;
      default:
        break;
    }
  }
  static void edit() {

  }
  static void delete() {

  }
  public static void managerMenu() {
    System.out.println("    ㄴ<게시판>");
    System.out.println("      [1] 게시판 추가");
    System.out.println("      [2] 게시판 수정");
    System.out.println("      [3] 게시판 삭제");
    System.out.println("      [4] 뒤로가기");
    int command = Prompt.inputInt("    >> ");
    switch (command) {
      case 1:
        mAdd();
        break;
      case 2:
        mEdit();
        break;
      case 3:
        mDelete();
        break;
      default:
        break;
    }
  }
  static void mAdd() {

  }
  static void mEdit() {

  }
  static void mDelete() {

  }
}
