package com.leeheejin.myproject.handler;

import java.util.LinkedList;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.myproject.domain.Dog;
import com.leeheejin.myproject.domain.Member;
import com.leeheejin.myproject.domain.Other;
import com.leeheejin.util.Prompt;

public class MenuHandler {

  LinkedList<Member> memberList = new LinkedList<>();
  MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
  MemberAccountHandler memberAccountHandler = new MemberAccountHandler(memberList);

  LinkedList<Board> boardList = new LinkedList<>();
  BoardAddHandler boardAddHandler = new BoardAddHandler(boardList);
  BoardListHandler boardListHandler = new BoardListHandler(boardList);

  LinkedList<Board> boardList2 = new LinkedList<>();
  BoardAddHandler boardAddHandler2 = new BoardAddHandler(boardList2);
  BoardListHandler boardListHandler2 = new BoardListHandler(boardList2);

  LinkedList<Cat> catList = new LinkedList<>();
  CatAddHandler catAddHandler = new CatAddHandler(catList);
  CatListHandler catListHandler = new CatListHandler(catList);

  LinkedList<Dog> dogList = new LinkedList<>();
  DogAddHandler dogAddHandler = new DogAddHandler(dogList);
  DogListHandler dogListHandler = new DogListHandler(dogList);

  LinkedList<Other> otherList = new LinkedList<>();
  OtherAddHandler otherAddHandler = new OtherAddHandler(otherList);
  OtherListHandler otherListHandler = new OtherListHandler(otherList);

  public void logInMenu() {
    System.out.println("[ 홈 > 회원가입/로그인* ]");
    System.out.println("(1) 회원가입");
    System.out.println("(2) 로그인"); 
    System.out.println("(3) 뒤로가기"); 
    int command = Prompt.inputInt(" >> ");
    switch (command) {
      case 1:
        memberAddHandler.signUp();
        break;
      case 2:
        int logInNo = memberAccountHandler.logIn();
        if (logInNo == 1) {
          managerMenu();
        }
        break;
      case 3:
        // 뒤로가기
        break;
      default:
        System.out.println("- 잘못 입력하셨습니다. ");
        System.out.println();
        break;
    }
  }

  public void generalMenu() {
    System.out.println("[ 홈 > 메뉴* ]");
    System.out.println("(1) 구조 동물 목록");
    System.out.println("(2) 게시판");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          generalListMenu();
          break;
        case 2:
          generalBoardMenu();
          break;
        case 3:
          //뒤로가기
          break;
        default:
          System.out.println("- 잘못 입력하셨습니다. ");
          System.out.println();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void managerMenu() {
    System.out.println("[ 홈 > 관리자 메뉴* ]");
    System.out.println("(1) 회원정보수정");
    System.out.println("(2) 로그아웃"); 
    System.out.println("(3) 구조 동물 목록");
    System.out.println("(4) 게시판");
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          memberAccountHandler.updateInfo();
          managerMenu();
          break;
        case 2:
          System.out.println("- 로그아웃 되었습니다. \n");
          break;
        case 3:
          managerListMenu();
          break;
        case 4:
          managerBoardMenu();
          break;
        default:
          System.out.println("- 잘못 입력하셨습니다. ");
          System.out.println();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void generalListMenu() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록* ]");
    System.out.println("(1) 고양이 목록 보기");
    System.out.println("(2) 개 목록 보기"); 
    System.out.println("(3) 기타 동물 목록 보기");
    System.out.println("(4) 뒤로가기"); 
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          catListHandler.generalList();
          generalListMenu();
          break;
        case 2:
          dogListHandler.generalList();
          generalListMenu();
          break;
        case 3:
          otherListHandler.generalList();
          generalListMenu();
          break;
        default:
          generalMenu();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void managerListMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록* ]");
    System.out.println("(1) 신규 등록");
    System.out.println("(2) 고양이 목록 보기");
    System.out.println("(3) 개 목록 보기"); 
    System.out.println("(4) 기타 동물 목록 보기");
    System.out.println("(5) 뒤로가기"); 
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1: 
          addAnimalMenu();
          managerListMenu();
          break;
        case 2:
          catListHandler.managerList();
          managerListMenu();
          break;
        case 3:
          dogListHandler.managerList();
          managerListMenu();
          break;
        case 4:
          otherListHandler.managerList();
          managerListMenu();
          break;
        default:
          managerMenu();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void addAnimalMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록* ]");
    System.out.println("(1) 신규 고양이 등록");
    System.out.println("(2) 신규 개 등록"); 
    System.out.println("(3) 신규 기타동물 등록");
    System.out.println("(4) 뒤로가기"); 
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          catAddHandler.add();
          addAnimalMenu();
          break;
        case 2:
          dogAddHandler.add();
          addAnimalMenu();
          break;
        case 3:
          otherAddHandler.add();
          addAnimalMenu();
          break;
        default:
          managerListMenu();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
          e.getClass().getName(), e.getMessage());
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void generalBoardMenu() {
    System.out.println("[ 홈 > 메뉴 > 게시판* ]");
    System.out.println("(1) 입양이야기");
    System.out.println("(2) 구조이야기");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          board1("메뉴");
          generalBoardMenu();
          break;
        case 2:
          board2("메뉴");
          generalBoardMenu();
        default:
          generalMenu();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }
  public void managerBoardMenu() {
    System.out.println("[ 홈 > 관리자 메뉴 > 게시판* ]");
    System.out.println("(1) 입양이야기");
    System.out.println("(2) 구조이야기");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          board1("관리자 메뉴");
          managerBoardMenu();
          break;
        case 2:
          board2("관리자 메뉴");
          managerBoardMenu();
          break;
        default:
          managerMenu();
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void board1(String menuName) {
    System.out.printf("[ 홈 > %s > 게시판 > 입양이야기* ]\n", menuName);
    System.out.println("(1) 게시글 등록");
    System.out.println("(2) 게시글 목록");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          boardAddHandler.add(menuName, "입양이야기");
          board1(menuName);
          break;
        case 2:
          boardListHandler.list(menuName, "입양이야기");
          board1(menuName);
          break;
        default:
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

  public void board2(String menuName) {
    System.out.printf("[ 홈 > %s > 게시판 > 구조이야기* ]\n", menuName);
    System.out.println("(1) 게시글 등록");
    System.out.println("(2) 게시글 목록");
    System.out.println("(3) 뒤로가기");
    int command = Prompt.inputInt(" >> ");
    try {
      switch (command) {
        case 1:
          boardAddHandler2.add(menuName, "구조이야기");
          board2(menuName);
          break;
        case 2:
          boardListHandler2.list(menuName, "구조이야기");
          board2(menuName);
          break;
        default:
          break;
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
    System.out.println();
  }

}
