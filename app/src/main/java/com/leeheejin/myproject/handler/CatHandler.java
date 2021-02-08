package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatHandler {

  CatList catList = new CatList();

  public void add() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규고양이등록* ]");

    Cat c = new Cat();

    c.setIds(catList.size() + 1);
    System.out.printf("[%d]\n",c.getIds());
    c.setPhotos(Prompt.inputString("사진? "));
    c.setBreeds(Prompt.inputString("품종? "));
    c.setGenders(Prompt.inputString("성별? "));
    c.setAges(Prompt.inputInt("나이? "));
    c.setDates(Prompt.inputDate("구조일? "));
    c.setPlaces(Prompt.inputString("구조장소? "));
    c.setStatus("신규");

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

  void update() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= catList.size()) {

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
      Object obj = catList.get(updateNo);
      Cat c = (Cat) obj;
      c.setStatus(stateLabel);
      backToList("<수정완료>");
      print(updateNo);
    } else {
      backToList("- 잘못 입력하셨습니다. ");
    }
  }

  void delete() {
    int deleteNo = Prompt.inputInt("<삭제>\n번호? ");
    if (deleteNo <= catList.size()) {
      print(deleteNo);
      String dcommand = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (dcommand.equalsIgnoreCase("n") || dcommand.isEmpty()) {
        backToList("- 목록으로 돌아갑니다. ");
      } else if (dcommand.equalsIgnoreCase("y")) {
        catList.delete(catList.get(deleteNo));

        // 번호 바꾸기
        for (int i = deleteNo; i <= catList.size(); i++) {
          Cat c = (Cat) catList.get(i);
          c.setIds(c.getIds() - 1);
        }

        backToList("- <삭제완료>");
      } else {
        backToList("- 잘못 입력하셨습니다. ");
      }
    } else {
      backToList("- 잘못 입력하셨습니다. ");
    }
  }

  private void print() {
    Object[] list = catList.toArray();

    for (Object obj : list) {
      Cat c = (Cat) obj;

      System.out.printf("  [%d] %s   %s/%s/%d살   ", 
          c.getIds(), c.getPhotos(), c.getBreeds(), c.getGenders(), c.getAges());
      System.out.printf("%s, %s, %s\n", c.getDates(), c.getPlaces(), c.getStatus());
    }
  }

  private void print(int printNo) {
    Object obj = catList.get(printNo);
    Cat c = (Cat) obj;
    System.out.printf("  [%d] %s   %s/%s/%d살   ", 
        c.getIds(), c.getPhotos(), c.getBreeds(), c.getGenders(), c.getAges());
    System.out.printf("%s, %s, %s\n", c.getDates(), c.getPlaces(), c.getStatus());
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
      if (c.getIds() == catNo) {
        return c;
      }
    }
    return null;
  }
}
