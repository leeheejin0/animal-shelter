package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogHandler {
  Dog d = new Dog();

  Node first;
  Node last;

  int size = 0;

  public void add() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규개등록* ]");
    Dog d = new Dog();
    d.ids = size + 1;
    System.out.printf("[%d]\n",d.ids);
    d.photos = Prompt.inputString("사진? ");
    d.breeds = Prompt.inputString("품종? ");
    d.genders = Prompt.inputString("성별? ");
    d.ages = Prompt.inputInt("나이? ");
    d.dates = Prompt.inputDate("구조일? ");
    d.places = Prompt.inputString("구조장소? ");
    d.status = "신규";
    System.out.println();

    Node node = new Node(d);
    if (last == null) {
      first = node;
      last = node;
    } else {
      last.next = node;
      node.prev = last;
      last = node;
    }
    size++;
    System.out.println("- 등록이 완료되었습니다. ");
    System.out.println();
  }

  public void generalList() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 개구조목록* ]");
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
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 개구조목록* ]");
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
    Node cursor = first;
    while (cursor != null) {
      Dog d = cursor.dog;
      System.out.printf("  [%d] %s   %s/%s/%d살   ", 
          d.ids, d.photos, d.breeds, d.genders, d.ages);
      System.out.printf("%s, %s, %s\n", d.dates, d.places, d.status);

      cursor = cursor.next;
    }
  }
  void print(int printNo) {
    Node cursor = first;
    while (cursor != null) {
      Dog d = cursor.dog;
      if (d.ids == printNo) {
        System.out.printf("  [%d] %s   %s/%s/%d살   ", 
            d.ids, d.photos, d.breeds, d.genders, d.ages);
        System.out.printf("%s, %s, %s\n", d.dates, d.places, d.status);
      }
      cursor = cursor.next;
    }
  }

  void update() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= size) {
      print(updateNo);
      int editStatus = Prompt.inputInt("1: 공고중 | 2: 입양완료\n>>");
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
      Node cursor = first;
      while (cursor != null) {
        Dog d = cursor.dog;
        if (d.ids == updateNo) {
          d.status = stateLabel;
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
      String dcommand = Prompt.inputString("    - 삭제하시겠습니까?(y/N) ");
      if (dcommand.equalsIgnoreCase("n") || dcommand.isEmpty()) {
        backToList("    - 목록으로 돌아갑니다. ");
      } else if (dcommand.equalsIgnoreCase("y")) {
        Node cursor = first;
        while (cursor != null) {
          if (cursor.dog.ids == deleteNo) {
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

  static class Node {
    Dog dog;
    Node next;
    Node prev;
    Node(Dog dog){
      this.dog = dog;
    }
  }
}
