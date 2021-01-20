package com.leeheejin.myproject.handler;

import java.sql.Date;
import com.leeheejin.myproject.Menu;
import com.leeheejin.util.Prompt;

public class DogHandler {
  static class Dog {
    int ids;
    String photos;
    String breeds;
    String genders;
    int ages;
    Date dates;
    String places;
    String status;
  }
  final static int LENGTH = 100;
  static Dog[] dogs = new Dog[LENGTH];
  static int size = 0;

  public static void add() {
    System.out.println("ㄴ<개 구조>");
    Dog d = new Dog();
    d.ids = size + 1;
    System.out.printf("  [%d]\n",d.ids);
    d.photos = Prompt.inputString("  사진? ");
    d.breeds = Prompt.inputString("  품종? ");
    d.genders = Prompt.inputString("  성별? ");
    d.ages = Prompt.inputInt("  나이? ");
    d.dates = Prompt.inputDate("  구조일? ");
    d.places = Prompt.inputString("  구조장소? ");
    d.status = "신규";
    System.out.println();
    dogs[size++] = d;
  }

  public static void list() {
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
      Dog d = dogs[i];
      System.out.printf("    [%d] %s   %s/%s/%d살   ", 
          d.ids, d.photos, d.breeds, d.genders, d.ages);
      System.out.printf("%s, %s, %s\n", d.dates, d.places, d.status);
    }
  }

  static void edit() {
    System.out.println();
    int editId = Prompt.inputInt("    <상태수정>\n    번호? ");

    if (editId <= size) {
      print(editId - 1, editId);
      int editStatus = Prompt.inputInt("    1: 공고중 | 2: 입양완료\n    >>");
      Dog d = dogs[editId - 1];
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
      d.status = stateLabel;
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
          dogs[i] = dogs[i + 1];
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
