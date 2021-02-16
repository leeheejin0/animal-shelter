package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.domain.Other;
import com.leeheejin.util.List;
import com.leeheejin.util.Prompt;

public class OtherHandler {

  List otherList = new List();

  public void add() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규기타동물등록* ]");

    Other o = new Other();

    o.setIds(otherList.size() + 1);
    System.out.printf("[%d]\n",o.getIds());
    o.setSpecies(Prompt.inputString("종류? "));
    o.setPhotos(Prompt.inputString("사진? "));
    o.setBreeds(Prompt.inputString("품종? "));
    o.setGenders(Prompt.inputString("성별? "));
    o.setAges(Prompt.inputInt("나이? "));
    o.setDates(Prompt.inputDate("구조일? "));
    o.setPlaces(Prompt.inputString("구조장소? "));
    o.setStatus("신규");
    System.out.println();

    otherList.add(o);

    System.out.println("- 등록이 완료되었습니다. ");
    System.out.println();
  }

  public void generalList() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 기타동물구조목록* ]");
    print();
    int command = Prompt.inputInt("1: 뒤로가기 \n>>");
    switch (command) {
      case 1:
        break;
      default:
        break;
    }
  }

  public void managerList() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 기타동물구조목록* ]");
    print();
    int command = Prompt.inputInt("1: 상태수정 | 2: 삭제 | 3: 뒤로가기 \n>>");
    switch (command) {
      case 1:
        update();
        break;
      case 2:
        remove();
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

    if (updateNo <= otherList.size()) {

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

      Object obj = otherList.get(updateNo);
      Other o = (Other) obj;
      o.setStatus(stateLabel);
      backToList("<수정완료>");
      print(updateNo);
    } else {
      backToList("- 잘못 입력하셨습니다. ");
    }
  }

  void remove() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= otherList.size()) {
      print(removeNo);
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        backToList("- 목록으로 돌아갑니다. ");
      } else if (command.equalsIgnoreCase("y")) {
        otherList.remove(otherList.get(removeNo));

        for (int i = removeNo; i <= otherList.size(); i++) {
          Other o = (Other) otherList.get(i);
          o.setIds(o.getIds() - 1);
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
    Object[] list = otherList.toArray();

    for (Object obj : list) {
      Other o = (Other) obj;
      System.out.printf("  [%d] %s / %s   ", o.getIds(), o.getSpecies(), o.getPhotos());
      System.out.printf("%s/%s/%d살   ", o.getBreeds(), o.getGenders(), o.getAges());
      System.out.printf("%s, %s, %s\n", o.getDates(), o.getPlaces(), o.getStatus());
    }
  }

  private void print(int printNo) {
    Object obj = otherList.get(printNo);
    Other o = (Other) obj;
    System.out.printf("  [%d] %s / %s   ", o.getIds(), o.getSpecies(), o.getPhotos());
    System.out.printf("%s/%s/%d살   ", o.getBreeds(), o.getGenders(), o.getAges());
    System.out.printf("%s, %s, %s\n", o.getDates(), o.getPlaces(), o.getStatus());
  }

  private void backToList(String message) {
    System.out.println(message);
    System.out.println();
    managerList();
  }

  private Other findByNo(int otherNo) {
    Object [] list = otherList.toArray();
    for (Object obj : list) {
      Other o = (Other) obj;
      if (o.getIds() == otherNo) {
        return o;
      }
    }
    return null;
  }
}
