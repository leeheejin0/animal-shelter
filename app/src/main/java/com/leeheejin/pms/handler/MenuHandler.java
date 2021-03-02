package com.leeheejin.pms.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.util.CsvObject;
import com.leeheejin.util.ObjectFactory;
import com.leeheejin.util.Prompt;

public class MenuHandler {

  static ArrayList<Member> memberList = new ArrayList<>();
  static LinkedList<Board> boardList1 = new LinkedList<>();
  static LinkedList<Board> boardList2 = new LinkedList<>();
  static LinkedList<Cat> catList = new LinkedList<>();
  static LinkedList<Dog> dogList = new LinkedList<>();
  static LinkedList<Other> otherList = new LinkedList<>();

  static File memberFile = new File("members.csv");
  static File boardFile1 = new File("boards1.csv");
  static File boardFile2 = new File("boards2.csv");
  static File catFile = new File("cats.csv");
  static File dogFile = new File("dogs.csv");
  static File otherFile = new File("others.csv");

  public void logInMenu() {
    loadObjects(memberFile, memberList, Member::valueOfCsv);

    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("1", new MemberAddHandler(memberList));
    MemberAccountHandler memberAccountHandler = new MemberAccountHandler(memberList);

    System.out.println("[ 홈 > 회원가입/로그인* ]");
    System.out.println("(1) 회원가입");
    System.out.println("(2) 로그인"); 
    System.out.println("(3) 뒤로가기"); 
    String command = Prompt.inputString(">> ");
    switch (command) {
      case "2":
        if (memberAccountHandler.logIn() == 1) {
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
    saveObjects(memberFile, memberList);
  }

  public void generalMenu() {
    loadObjects(catFile, catList, Cat::valueOfCsv);
    loadObjects(dogFile, dogList, Dog::valueOfCsv);
    loadObjects(otherFile, otherList, Other::valueOfCsv);

    loop: 
      while (true) {
        System.out.println("[ 홈 > 메뉴* ]");
        System.out.println("(1) 구조 동물 목록");
        System.out.println("(2) 게시판");
        System.out.println("(3) 뒤로가기");
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

  public void managerMenu() {
    loadObjects(catFile, catList, Cat::valueOfCsv);
    loadObjects(dogFile, dogList, Dog::valueOfCsv);
    loadObjects(otherFile, otherList, Other::valueOfCsv);

    MemberAccountHandler memberAccountHandler = new MemberAccountHandler(memberList);

    loop:
      while (true) {
        System.out.println("[ 홈 > 관리자 메뉴* ]");
        System.out.println("(1) 회원정보수정");
        System.out.println("(2) 로그아웃"); 
        System.out.println("(3) 구조 동물 목록");
        System.out.println("(4) 게시판");
        int command = Prompt.inputInt(">> ");
        try {
          switch (command) {
            case 1:
              memberAccountHandler.updateInfo();
              if (memberAccountHandler.accountRemove == -1) {
                break loop;
              }
              break;
            case 2:
              System.out.println("- 로그아웃 되었습니다. \n");
              break loop;
            case 3:
              managerListMenu();
              break;
            case 4:
              managerBoardMenu();
              break;
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

  public void generalListMenu() {

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

  public void managerListMenu() {

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

  public void addAnimalMenu() {
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
    saveObjects(catFile, catList);
    saveObjects(dogFile, dogList);
    saveObjects(otherFile, otherList);
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
          System.out.println("---------------------");
        }
      }
  }

  public void board1(String menuName) {
    loadObjects(boardFile1, boardList1, Board::valueOfCsv);

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
          System.out.println("---------------------");
        }
      }

    saveObjects(boardFile1, boardList1);
  }

  public void board2(String menuName) {
    loadObjects(boardFile2, boardList2,Board::valueOfCsv);

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
          System.out.println("---------------------");
        }
      }

    saveObjects(boardFile2, boardList2);
  }

  static <T> void loadObjects(File file, List<T> list, ObjectFactory<T> objectFactory) {
    try (BufferedReader in = new BufferedReader(new FileReader(file))) {
      String csvStr = null;
      while ((csvStr = in.readLine()) != null) {
        list.add(objectFactory.create(csvStr));
      }
      System.out.printf("%s 파일 데이터 로딩!\n", file.getName());
    } catch (Exception e) {
      System.out.printf("%s 파일 데이터 로딩 중 오류 발생!\n", file.getName());
    }
  }

  static <T extends CsvObject> void saveObjects(File file, List<T> list) {
    try (BufferedWriter out = new BufferedWriter(new FileWriter(file))){
      for (CsvObject csvObj : list){
        out.write(csvObj.toCsvString() + "\n");
      }
      System.out.printf("파일 %s 데이터 저장!\n", file.getName());
    } catch (Exception e) {
      System.out.printf("파일 %s에 데이터를 저장하는 중에 오류 발생!\n", file.getName());
    }
  }
}
