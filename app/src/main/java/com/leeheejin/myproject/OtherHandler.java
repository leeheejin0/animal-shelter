package com.leeheejin.myproject;

import java.sql.Date;

public class OtherHandler {
  // 기타
  final static int LENGTH = 100;
  static int[] ids = new int[LENGTH];
  static String[] species = new String[LENGTH];
  static String[] photos = new String[LENGTH];
  static String[] breeds = new String[LENGTH];
  static String[] genders = new String[LENGTH];
  static int[] ages = new int[LENGTH];
  static Date[] dates = new Date[LENGTH];
  static String[] places = new String[LENGTH];
  static String[] status = new String[LENGTH];
  static int size = 0;

  static void add() {
    System.out.println("ㄴ<기타 동물 구조>");

    ids[size] = size + 1;
    System.out.printf("  [%d]\n",ids[size]);
    species[size] = Prompt.inputString("  종류? ");
    photos[size] = Prompt.inputString("  사진? ");
    breeds[size] = Prompt.inputString("  품종? ");
    genders[size] = Prompt.inputString("  성별? ");
    ages[size] = Prompt.inputInt("  나이? ");
    dates[size] = Prompt.inputDate("  구조일? ");
    places[size] = Prompt.inputString("  구조장소? ");
    status[size] = "신규";
    System.out.println();
    size++;
  }

  static void list() {
    System.out.println("  ㄴ<개 구조 목록>");
    print(0, size);
    int command = Prompt.inputInt("    1: 상태수정 | 2: 삭제 | 3: 뒤로가기 | 4: 홈\n    >>");
    switch (command) {
      case 1:
        edit();
        break;
      case 2:
        delete();
        break;
      case 3:
        Menu.listMenu();
        break;
      default:
        break;
    }
    System.out.println();
  }

  static void print(int startNum, int size) {
    for (int i = startNum; i < size; i++) {
      System.out.printf("    [%d] %s / %s   ", ids[i], species[i], photos[i]);
      System.out.printf("%s/%s/%d살   ", breeds[i], genders[i], ages[i]);
      System.out.printf("%s, %s, %s\n", dates[i], places[i], status[i]);
    }
  }

  static void edit() {
    System.out.println();
    int editId = Prompt.inputInt("    <상태수정>\n    번호? ");

    if (editId <= size) {
      print(editId - 1, editId);
      int editStatus = Prompt.inputInt("    1: 공고중 | 2: 입양완료\n    >>");
      String stateLabel = null;
      switch (editStatus) {
        case 1:
          stateLabel = "공고중";
          break;
        case 2:
          stateLabel = "입양완료";
          break;
        default:
          stateLabel = "신규";
          break;
      }
      status[editId - 1] = stateLabel;
      backToList("    <수정완료>");
      print(editId - 1, editId);
    } else {
      backToList("    - 잘못 입력하셨습니다. ");
    }
  }

  static void delete() {
    int deleteId = Prompt.inputInt("    <삭제>\n    번호? ");
    if (deleteId <= size) {
      print(deleteId - 1, deleteId);
      String dcommand = Prompt.inputString("    - 삭제하시겠습니까?(y/N) ");
      if (dcommand.equalsIgnoreCase("n") || dcommand.isEmpty()) {
        backToList("    - 목록으로 돌아갑니다. ");
      } else if (dcommand.equalsIgnoreCase("y")) {
        for (int i = deleteId - 1; i < size; i++) {
          species[i] = species[i + 1];
          photos[i] = photos[i + 1];
          breeds[i] = breeds[i + 1];
          genders[i] = genders[i + 1];
          ages[i] = ages[i + 1];
          dates[i] = dates[i + 1];
          places[i] = places[i + 1];
          status[i] = status[i + 1];
        }
        size--;
        backToList("    - <삭제완료>");
      } else {
        backToList("    - 잘못 입력하셨습니다. ");
      }
    } else {
      backToList("    - 잘못 입력하셨습니다. ");
    }
  }

  static void backToList(String message) {
    System.out.println(message);
    System.out.println();
    list();
  }
}
