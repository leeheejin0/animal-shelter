package com.leeheejin.pms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.pms.domain.Cat;
import com.leeheejin.pms.domain.Dog;
import com.leeheejin.pms.domain.Member;
import com.leeheejin.pms.domain.Other;
import com.leeheejin.pms.handler.MenuHandler;
import com.leeheejin.util.CsvObject;
import com.leeheejin.util.ObjectFactory;
import com.leeheejin.util.Prompt;

public class App {
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

  public static void main(String[] args) {
    loadObjects(memberFile, memberList, Member::valueOfCsv);
    loadObjects(catFile, catList, Cat::valueOfCsv);
    loadObjects(dogFile, dogList, Dog::valueOfCsv);
    loadObjects(otherFile, otherList, Other::valueOfCsv);
    loadObjects(boardFile1, boardList1, Board::valueOfCsv);
    loadObjects(boardFile2, boardList2,Board::valueOfCsv);

    MenuHandler menuHandler = new MenuHandler();

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
              menuHandler.logInMenu();
              break;
            case 2:
              menuHandler.generalMenu();
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

    saveObjects(memberFile, memberList);
    saveObjects(catFile, catList);
    saveObjects(dogFile, dogList);
    saveObjects(otherFile, otherList);
    saveObjects(boardFile1, boardList1);
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


