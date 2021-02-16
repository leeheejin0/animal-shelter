package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.util.List;
import com.leeheejin.util.Prompt;

public class BoardHandler {
  List boardList = new List();

  public void generalMenu() {
    System.out.println("[ 홈 > 메뉴 > 게시판* ]");
    System.out.println("(1) 입양이야기");
    System.out.println("(2) 구조이야기");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        generalBoard("입양이야기");
        break;
      case 2:
        generalBoard("구조이야기");
      default:
        break;
    }
  }
  public void managerMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 게시판* ]");
    System.out.println("(1) 입양이야기");
    System.out.println("(2) 구조이야기");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        managerBoard("입양이야기");
        break;
      case 2:
        managerBoard("구조이야기");
        break;
      default:
        //MenuHandler.managerMenu();
        break;
    }
  }

  public void generalBoard(String name) {
    System.out.printf("[ 홈 > 메뉴 > 게시판 > %s* ]", name);
    System.out.printf("<%s>\n", name);
    System.out.println("(1) 게시글 등록");
    System.out.println("(2) 게시글 목록");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        add(name);
        generalBoard(name);
        break;
      case 2:
        list(name);
        generalBoard(name);
        break;
      default:
        generalMenu();
        break;
    }
  }

  public void managerBoard(String name) {
    System.out.printf("[ 홈 > 관리자 메뉴 > 게시판 > %s* ]", name);
    System.out.printf("<%s>\n", name);
    System.out.println("(1) 게시글 등록");
    System.out.println("(2) 게시글 목록");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(">> ");
    switch (command) {
      case 1:
        add(name);
        managerBoard(name);
        break;
      case 2:
        list(name);
        managerBoard(name);
        break;
      default:
        managerMenu();
        break;
    }
  }

  void add(String name) {
    System.out.printf("[ 홈 > 메뉴 > 게시판 > %s > 글쓰기* ]", name);
    Board b = new Board();
    b.setNo(boardList.size() + 1);
    b.setName(Prompt.inputString("이름>> "));
    b.setPassword(Prompt.inputString("비밀번호>> "));
    b.setTitle(Prompt.inputString("제목>> "));
    b.setContent(Prompt.inputString("내용>> "));
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("- 게시글 등록이 완료되었습니다. ");
    System.out.println();
  }

  void list(String name) {
    System.out.printf("[ 홈 > 메뉴 > 게시판 > %s > 글목록* ]", name);
    Object[] list = boardList.toArray();
    for (Object obj : list) {
      Board b = (Board) obj;
      System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
          b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
          b.getViewCount(), b.getLike());
    }

    int command = Prompt.inputInt("1: 수정 | 2: 삭제 | 3: 뒤로가기\n>>");
    switch (command) {
      case 1:
        update();
        break;
      case 2:
        remove();
        break;
      default:
        break;
    }
  }

  void update() {
    int updateNo = Prompt.inputInt("<수정>\n번호? ");
    if (updateNo <= boardList.size()) {
      String password = Prompt.inputString("\n비밀번호? ");
      if (findByPw(password) != null) {
        // 수정
        Board b = (Board) boardList.get(updateNo);
        b.setName(Prompt.inputString("이름>> "));
        b.setPassword(Prompt.inputString("비밀번호>> "));
        b.setTitle(Prompt.inputString("제목>> "));
        b.setContent(Prompt.inputString("내용>> "));
        b.setRegisteredDate(new Date(System.currentTimeMillis()));
      } else {
        System.out.println("- 비밀번호가 일치하지 않습니다. ");
      }
    }
  }

  void remove() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= boardList.size()) {
      for (int i = removeNo - 1; i < removeNo; i++) {
        Board b = boards[i];
        System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
            b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
            b.getViewCount(), b.getLike());
      }
      String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (dcommand.equalsIgnoreCase("n") || dcommand.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
        list();
      } else if (dcommand.equalsIgnoreCase("y")) {
        for (int i = removeNo - 1; i < size; i++) {
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

  private Board findByPw(String password) {
    Object[] arr = boardList.toArray();
    for (Object obj : arr) {
      Board b = (Board) obj;
      if (b.getPassword().equals(password)) {
        return b;
      }
    }
    return null;
  }
}