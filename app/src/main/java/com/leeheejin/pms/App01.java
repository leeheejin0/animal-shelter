package com.leeheejin.pms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.leeheejin.context.ApplicationContextListener;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.pms.handler.BoardAddHandler;
import com.leeheejin.pms.handler.BoardListHandler;
import com.leeheejin.pms.handler.CatAddHandler;
import com.leeheejin.pms.handler.CatGeneralListHandler;
import com.leeheejin.pms.handler.CatManagerListHandler;
import com.leeheejin.pms.handler.Command;
import com.leeheejin.pms.handler.DogAddHandler;
import com.leeheejin.pms.handler.DogGeneralListHandler;
import com.leeheejin.pms.handler.DogManagerListHandler;
import com.leeheejin.pms.handler.MemberAddHandler;
import com.leeheejin.pms.handler.MemberLogInHandler;
import com.leeheejin.pms.handler.MemberUpdateHandler;
import com.leeheejin.pms.handler.OtherAddHandler;
import com.leeheejin.pms.handler.OtherGeneralListHandler;
import com.leeheejin.pms.handler.OtherManagerListHandler;
import com.leeheejin.pms.listener.AppListener;
import com.leeheejin.pms.listener.FileListener;
import com.leeheejin.util.Prompt;

public class App01 {

  List<ApplicationContextListener> listeners = new ArrayList<>();

  Map<String, Object> appContext = new HashMap<>();

  public static void main(String[] args) {
    App01 app = new App01();

    app.addApplicationContextListener(new AppListener());
    app.addApplicationContextListener(new FileListener());

    app.service();
  }

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }


  public void service() {

    notifyOnServiceStarted();

    loop:
      while (true) {
        try {
          System.out.println("< 동물 보호소 관리 시스템 >");
          System.out.println("[ 홈* ]");
          System.out.println("(1) 회원가입 / 로그인");
          System.out.println("(2) 비회원 둘러보기"); 
          System.out.println("(3) 시스템 종료"); 
          int command = Prompt.inputInt(">> ");

          switch (command) {
            case 1:
              logInMenu();
              break;
            case 2:
              generalMenu();
              break;
            case 3:
              System.out.println("- 종료합니다. ");
              break loop;
            default:
              System.out.println("- 잘못 입력하셨습니다. ");
              System.out.println();
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }

    notifyOnServiceStopped();
  }

  private void notifyOnServiceStarted() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(appContext);
    }
  }

  private void notifyOnServiceStopped() {
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(appContext);
    }
  }




  @SuppressWarnings("unchecked")
  public void logInMenu() {
    List<Member> memberList = (List<Member>) appContext.get("memberList");

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("1", new MemberAddHandler(memberList));
    MemberLogInHandler memberLogInHandler = new MemberLogInHandler(memberList);

    System.out.println("[ 홈 > 회원가입/로그인* ]");
    System.out.println("(1) 회원가입");
    System.out.println("(2) 로그인"); 
    System.out.println("(3) 뒤로가기"); 
    String command = Prompt.inputString(">> ");
    switch (command) {
      case "2":
        if (memberLogInHandler.logIn() == 1) {
          managerMenu();
        }
        break;
      case "3":
        // 뒤로가기 return
        return;
      default:
        Command commandHandler = commandMap.get(command);
        if (commandHandler == null ) {
          System.out.println("실행할 수 없는 명령입니다."); 
        } else {
          commandHandler.service();
        }
        break;
    }

  }

  public void generalMenu() {
    loop: 
      while (true) {
        System.out.println("[ 홈 > 메뉴* ]");
        System.out.println("(1) 구조 동물 목록");
        System.out.println("(2) 게시판");
        System.out.println("(3) 보호소 후원");
        System.out.println("(4) 뒤로가기");
        int command = Prompt.inputInt(">> ");
        try {
          switch (command) {
            case 1:
              generalListMenu();
              break;
            case 2:
              generalBoardMenu();
              break;
            case 3:
              // 보호소 후원페이지 예정
            case 4:
              break loop;
            default:
              System.out.println("실행할 수 없는 명령입니다.");
              System.out.println();
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.println("---------------------");
        }
      }
  }

  @SuppressWarnings("unchecked")
  public void managerMenu() {
    List<Member> memberList = (List<Member>) appContext.get("memberList");

    HashMap<String, Command> commandMap = new HashMap<>();

    loop:
      while (true) {
        commandMap.put("1", new MemberUpdateHandler(memberList));

        System.out.println("[ 홈 > 관리자 메뉴* ]");
        System.out.println("(1) 회원정보수정");
        System.out.println("(2) 로그아웃"); 
        System.out.println("(3) 구조 동물 목록");
        System.out.println("(4) 게시판");
        System.out.println("(5) 보호소 후원");
        String command = Prompt.inputString(">> ");
        try {
          switch (command) {
            case "2":
              System.out.println("- 로그아웃 되었습니다. \n");
              break loop;
            case "3":
              managerListMenu();
              break;
            case "4":
              managerBoardMenu();
              break;
            case "5":
              //보호소 후원 관리 메뉴 예정
              break;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
                if (command.equals("1")) {
                  if (MemberUpdateHandler.accountRemove == -1) {
                    break loop;
                  }
                }
              }
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.println("---------------------");
        }
      }
  }

  @SuppressWarnings("unchecked")
  public void generalListMenu() {
    List<Cat> catList = (List<Cat>) appContext.get("catList");
    List<Dog> dogList = (List<Dog>) appContext.get("dogList");
    List<Other> otherList = (List<Other>) appContext.get("otherList");

    HashMap<String, Command> commandMap = new HashMap<>();

    loop: 
      while (true) {
        commandMap.put("1", new CatGeneralListHandler(catList));
        commandMap.put("2", new DogGeneralListHandler(dogList));
        commandMap.put("3", new OtherGeneralListHandler(otherList));

        System.out.println("[ 홈 > 메뉴 > 구조동물목록* ]");
        System.out.println("(1) 고양이 목록 보기");
        System.out.println("(2) 개 목록 보기"); 
        System.out.println("(3) 기타 동물 목록 보기");
        System.out.println("(4) 뒤로가기"); 
        String command = Prompt.inputString(">> ");
        try {
          switch (command) {
            case "4":
              break loop;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
              }
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.println("---------------------");
        }
      }
  }

  @SuppressWarnings("unchecked")
  public void managerListMenu() {
    List<Cat> catList = (List<Cat>) appContext.get("catList");
    List<Dog> dogList = (List<Dog>) appContext.get("dogList");
    List<Other> otherList = (List<Other>) appContext.get("otherList");

    HashMap<String, Command> commandMap = new HashMap<>();

    loop:
      while (true) {
        commandMap.put("2", new CatManagerListHandler(catList));
        commandMap.put("3", new DogManagerListHandler(dogList));
        commandMap.put("4", new OtherManagerListHandler(otherList));

        System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록* ]");
        System.out.println("(1) 신규 등록");
        System.out.println("(2) 고양이 목록 보기");
        System.out.println("(3) 개 목록 보기"); 
        System.out.println("(4) 기타 동물 목록 보기");
        System.out.println("(5) 뒤로가기"); 
        String command = Prompt.inputString(">> ");
        try {
          switch (command) {
            case "1": 
              addAnimalMenu();
              break;
            case "5":
              break loop;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null ) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
              }
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }
  }

  @SuppressWarnings("unchecked")
  public void addAnimalMenu() {
    List<Cat> catList = (List<Cat>) appContext.get("catList");
    List<Dog> dogList = (List<Dog>) appContext.get("dogList");
    List<Other> otherList = (List<Other>) appContext.get("otherList");

    HashMap<String, Command> commandMap = new HashMap<>();

    loop:
      while (true) {
        commandMap.put("1", new CatAddHandler(catList));
        commandMap.put("2", new DogAddHandler(dogList));
        commandMap.put("3", new OtherAddHandler(otherList));


        System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록* ]");
        System.out.println("(1) 신규 고양이 등록");
        System.out.println("(2) 신규 개 등록"); 
        System.out.println("(3) 신규 기타동물 등록");
        System.out.println("(4) 뒤로가기"); 
        String command = Prompt.inputString(">> ");
        try {
          switch (command) {
            case "4":
              break loop;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
              }
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.println("---------------------");
        }
      }

  }

  public void generalBoardMenu() {
    loop:
      while (true) {
        System.out.println("[ 홈 > 메뉴 > 게시판* ]");
        System.out.println("(1) 입양이야기");
        System.out.println("(2) 구조이야기");
        System.out.println("(3) 뒤로가기");
        int command = Prompt.inputInt(">> ");
        try {
          switch (command) {
            case 1:
              board1("메뉴");
              break;
            case 2:
              board2("메뉴");
            case 3:
              break loop;
            default:
              System.out.println("실행할 수 없는 명령입니다.");
              System.out.println();
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }
  }

  public void managerBoardMenu() {
    loop:
      while (true) {
        System.out.println("[ 홈 > 관리자 메뉴 > 게시판* ]");
        System.out.println("(1) 입양이야기");
        System.out.println("(2) 구조이야기");
        System.out.println("(3) 뒤로가기");
        int command = Prompt.inputInt(">> ");
        try {
          switch (command) {
            case 1:
              board1("관리자 메뉴");
              break;
            case 2:
              board2("관리자 메뉴");
              break;
            case 3:
              break loop;
            default:
              System.out.println("실행할 수 없는 명령입니다.");
              System.out.println();
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }
  }

  @SuppressWarnings("unchecked")
  public void board1(String menuName) {
    List<Board> boardList1 = (List<Board>) appContext.get("boardList1");

    HashMap<String, Command> commandMap = new HashMap<>();
    loop:
      while (true) {
        commandMap.put("1", new BoardAddHandler(boardList1));
        commandMap.put("2", new BoardListHandler(boardList1));

        System.out.printf("[ 홈 > %s > 게시판 > 입양이야기* ]\n", menuName);
        System.out.println("(1) 게시글 등록");
        System.out.println("(2) 게시글 목록");
        System.out.println("(3) 뒤로가기");
        String command = Prompt.inputString(">> ");
        try {
          switch (command) {
            case "3":
              break loop;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service(menuName, "입양이야기");
              }
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }


  }

  @SuppressWarnings("unchecked")
  public void board2(String menuName) {
    List<Board> boardList2 = (List<Board>) appContext.get("boardLsit2");

    HashMap<String, Command> commandMap = new HashMap<>();
    loop:
      while (true) {
        commandMap.put("1", new BoardAddHandler(boardList2));
        commandMap.put("2", new BoardListHandler(boardList2));

        System.out.printf("[ 홈 > %s > 게시판 > 구조이야기* ]\n", menuName);
        System.out.println("(1) 게시글 등록");
        System.out.println("(2) 게시글 목록");
        System.out.println("(3) 뒤로가기");
        String command = Prompt.inputString(">> ");
        try {
          switch (command) {
            case "3":
              break loop;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service(menuName, "구조이야기");
              }
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.printf("명령어 실행 중 오류 발생: %s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("---------------------");
        }
      }
  }
}


