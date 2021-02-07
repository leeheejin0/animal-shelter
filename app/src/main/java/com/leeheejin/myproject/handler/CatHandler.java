package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatHandler {

  CatList catList = new CatList();

  public void add() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규고양이등록* ]");

    Cat c = new Cat();

    c.ids= catList.size + 1;
    System.out.printf("[%d]\n",c.ids);
    c.photos = Prompt.inputString("사진? ");
    c.breeds = Prompt.inputString("품종? ");
    c.genders = Prompt.inputString("성별? ");
    c.ages = Prompt.inputInt("나이? ");
    c.dates = Prompt.inputDate("구조일? ");
    c.places = Prompt.inputString("구조장소? ");
    c.status = "신규";

    catList.add(c);

    System.out.println("- 등록이 완료되었습니다. ");
    System.out.println();
  }

  public void generalList() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 고양이구조목록* ]");
    print();
    int command = Prompt.inputInt("1: 뒤로가기 | 2: 홈\n>>");
    switch (command) {
      case 1:
        break;
      default:
        break;
    }
    System.out.println();
  }

  public void managerList() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 고양이구조목록* ]");
    print();
    int command = Prompt.inputInt("1: 상태수정 | 2: 삭제 | 3: 뒤로가기 | 4: 홈\n>>");
    switch (command) {
      case 1:
        update();
        break;
      case 2:
        delete();
        break;
      case 3:
        break;
      default:
        break;
    }
    System.out.println();
  }


  void print() {
    Object[] list = catList.toArray();

    for (Object obj : list) {
      Cat c = (Cat) obj;

      System.out.printf("  [%d] %s   %s/%s/%d살   ", 
          c.ids, c.photos, c.breeds, c.genders, c.ages);
      System.out.printf("%s, %s, %s\n", c.dates, c.places, c.status);
    }
  }

  void print(int printNo) {
    Node cursor = first;
    while (cursor != null) {
      Cat c = cursor.cat;
      if (c.ids == printNo) {
        System.out.printf("  [%d] %s   %s/%s/%d살   ", 
            c.ids, c.photos, c.breeds, c.genders, c.ages);
        System.out.printf("%s, %s, %s\n", c.dates, c.places, c.status);

      }
      cursor = cursor.next;
    }
  }


  void update() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= size) {

      print(updateNo);
      int updateStatus = Prompt.inputInt("1: 공고중 | 2: 입양완료\n>>");
      String stateLabel = null;
      switch (updateStatus) {
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
      Node cursor = first;
      while (cursor != null) {
        Cat c = cursor.cat;
        if (c.ids == updateNo) {
          c.status = stateLabel;
          backToList("<수정완료>");
          print(updateNo);
          break;
        }
        cursor = cursor.next;
      }
    } else {
      backToList("- 잘못 입력하셨습니다. ");
    }
  }

  void delete() {
    int deleteNo = Prompt.inputInt("<삭제>\n번호? ");
    if (deleteNo <= size) {
      print(deleteNo);
      String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (dcommand.equalsIgnoreCase("n") || dcommand.isEmpty()) {
        backToList("- 목록으로 돌아갑니다. ");
      } else if (dcommand.equalsIgnoreCase("y")) {
        Node cursor = first;
        while (cursor != null) {
          Cat c = cursor.cat;
          if (cursor.cat.ids == deleteNo) {
            if (first == last) {
              first = last = null;
              break;
            }
            if (cursor == first) {
              first = cursor.next;
              cursor.prev = null;
            } else {
              cursor.prev.next = cursor.next;
              if (cursor.next != null) {
                cursor.next.prev = cursor.prev;
              }
              if (cursor == last) {
                last = cursor.prev;
              }
              break;
            }
          }
          cursor = cursor.next;
        }
        size--;
        backToList("- <삭제완료>");
      } else {
        backToList("- 잘못 입력하셨습니다. ");
      }
    } else {
      backToList("- 잘못 입력하셨습니다. ");
    }
  }

  void backToList(String message) {
    System.out.println(message);
    System.out.println();
    managerList();
  }

  private Cat findByNo(int catNo) {
    Object [] list = catList.toArray();
    for (Object obj : list) {
      Cat c = (Cat) obj;
      if ()
    }
  }
}
