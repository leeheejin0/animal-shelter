package com.leeheejin.myproject.handler;

import java.util.LinkedList;
import com.leeheejin.myproject.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogHandler {

  LinkedList<Dog> dogList = new LinkedList<>();

  public void add() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록 > 신규개등록* ]");

    Dog d = new Dog();

    d.setIds(dogList.size() + 1);
    System.out.printf("[%d]\n",d.getIds());
    d.setPhotos(Prompt.inputString("사진? "));
    d.setBreeds(Prompt.inputString("품종? "));
    d.setGenders(Prompt.inputString("성별? "));
    d.setAges(Prompt.inputInt("나이? "));
    d.setDates(Prompt.inputDate("구조일? "));
    d.setPlaces(Prompt.inputString("구조장소? "));
    d.setStatus("신규");
    System.out.println();

    dogList.add(d);

    System.out.println("- 등록이 완료되었습니다. ");
    System.out.println();
  }

  public void generalList() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 개구조목록* ]");
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
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 개구조목록* ]");
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

    if (updateNo <= dogList.size()) {

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
      Object obj = dogList.get(updateNo);
      Dog d = (Dog) obj;
      d.setStatus(stateLabel);
      backToList("<수정완료>");
      print(updateNo);
    } else {
      backToList("- 잘못 입력하셨습니다. ");
    }
  }

  void remove() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= dogList.size()) {
      print(removeNo);
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        backToList("- 목록으로 돌아갑니다. ");
      } else if (command.equalsIgnoreCase("y")) {
        dogList.remove(dogList.get(removeNo));

        for (int i = removeNo; i <= dogList.size(); i++) {
          Dog d = dogList.get(i);
          d.setIds(d.getIds() - 1);
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
    Object[] list = dogList.toArray();

    for (Object obj : list) {
      Dog d = (Dog) obj;
      System.out.printf("  [%d] %s   %s/%s/%d살   ", 
          d.getIds(), d.getPhotos(), d.getBreeds(), d.getGenders(), d.getAges());
      System.out.printf("%s, %s, %s\n", d.getDates(), d.getPlaces(), d.getStatus());
    }
  }

  private void print(int printNo) {
    Object obj = dogList.get(printNo);
    Dog d = (Dog) obj;
    System.out.printf("  [%d] %s   %s/%s/%d살   ", 
        d.getIds(), d.getPhotos(), d.getBreeds(), d.getGenders(), d.getAges());
    System.out.printf("%s, %s, %s\n", d.getDates(), d.getPlaces(), d.getStatus());
  }

  private void backToList(String message) {
    System.out.println(message);
    System.out.println();
    managerList();
  }

  private Dog findByNo(int dogNo) {
    Dog [] list = dogList.toArray(new Dog[0]);
    for (Dog d : list) {
      if (d.getIds() == dogNo) {
        return d;
      }
    }
    return null;
  }

}
