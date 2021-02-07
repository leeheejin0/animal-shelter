package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardHandler {

  final int LENGTH = 100;
  Board[] boards = new Board[LENGTH];
  int size = 0; 

  public void generalMenu() {
    System.out.println("[ 홈 > 메뉴 > 게시판* ]");
    System.out.println("(1) 입양이야기");
    System.out.println("(2) 구조이야기");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        board("입양이야기");
        break;
      case 2:
        board("구조이야기");
      default:
        break;
    }
  }
  public void managerMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 게시판* ]");
    System.out.println("(1) 입양이야기");
    System.out.println("(2) 구조이야기");
    System.out.println("(3) 게시판 관리");
    System.out.println("(4) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        board2("입양이야기");
        break;
      case 2:
        board2("구조이야기");
        break;
      case 3:
        // 게시판 관리
        break;
      default:
        //MenuHandler.managerMenu();
        break;
    }
  }

  public void board(String name) {
    System.out.println("[ 홈 > 메뉴 > 게시판 > 입양이야기* ]");
    System.out.printf("<%s>\n", name);
    System.out.println("(1) 게시글 등록");
    System.out.println("(2) 게시글 목록");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        add();
        board(name);
        break;
      case 2:
        list();
        board(name);
        break;
      default:
        generalMenu();
        break;
    }
  }

  public void board2(String name) {
    System.out.println("[ 홈 > 관리자 메뉴 > 게시판 > 입양이야기* ]");
    System.out.printf("<%s>\n", name);
    System.out.println("(1) 게시글 등록");
    System.out.println("(2) 게시글 목록");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        add();
        board2(name);
        break;
      case 2:
        managerList();
        board2(name);
        break;
      default:
        managerMenu();
        break;
    }
  }

  void add() {
    System.out.println("<글쓰기>");
    Board b = new Board();
    b.no = size + 1;
    b.name = Prompt.inputString("이름>> ");
    b.password = Prompt.inputString("비밀번호>> ");
    b.title = Prompt.inputString("제목>> ");
    b.content = Prompt.inputString("내용>> ");
    b.registeredDate = new Date(System.currentTimeMillis());
    boards[size++] = b;
  }
  void list() {
    System.out.println("<글목록>");
    for (int i = 0; i < size; i++) {
      Board b = boards[i];
      System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
          b.no, b.title, b.registeredDate, b.name, b.viewCount, b.like);
    }
    int command = Prompt.inputInt("1: 삭제 | 2: 뒤로가기\n>>");
    switch (command) {
      case 1:
        delete();
        break;
      default:
        break;
    }
  }

  void managerList() {
    System.out.println("<글목록>");
    for (int i = 0; i < size; i++) {
      Board b = boards[i];
      System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
          b.no, b.title, b.registeredDate, b.name, b.viewCount, b.like);
    }
    int command = Prompt.inputInt("1: 삭제 | 2: 뒤로가기\n>>");
    switch (command) {
      case 1:
        delete();
        break;
      default:
        managerMenu();
        break;
    }
  }

  void delete() {
    int deleteId = Prompt.inputInt("<삭제>\n번호? ");
    if (deleteId <= size) {
      for (int i = deleteId - 1; i < deleteId; i++) {
        Board b = boards[i];
        System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
            b.no, b.title, b.registeredDate, b.name, b.viewCount, b.like);
      }
      String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (dcommand.equalsIgnoreCase("n") || dcommand.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
        list();
      } else if (dcommand.equalsIgnoreCase("y")) {
        for (int i = deleteId - 1; i < size; i++) {
          boards[i] = boards[i + 1];
        }
        size--;
        System.out.println("- <삭제완료>");
        System.out.println();
        list();
      } else {
        System.out.println("- 잘못 입력하셨습니다. ");
        System.out.println();
        list();
      }
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
      list();
    }
  }
}