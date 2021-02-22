package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Dog;
import com.leeheejin.util.Prompt;

public class DogListHandler extends AbstractDogHandler {

  public DogListHandler(List<Dog> dogList) {
    super(dogList);
  }

  @Override
  public void service() {

  }

  public void generalList() {
    System.out.println("[ 홈 > 메뉴 > 구조동물목록 > 개구조목록* ]");
    print();
    int command = Prompt.inputInt("1: 뒤로가기 \n>> ");
    switch (command) {
      case 1:
        return;
      default:
        System.out.println("실행할 수 없는 명령입니다.");
        System.out.println();
        break;
    }
  }

  public void managerList() {
    System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 개구조목록* ]");
    print();
    int command = Prompt.inputInt("1: 상태수정 | 2: 삭제 | 3: 뒤로가기 \n>> ");
    switch (command) {
      case 1:
        update();
        break;
      case 2:
        remove();
        break;
      case 3:
        return;
      default:
        System.out.println("실행할 수 없는 명령입니다.");
        System.out.println();
        break;
    }
    System.out.println();
  }

  void update() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= dogList.size()) {

      print(updateNo - 1);
      int updateStatus = Prompt.inputInt("1: 공고중 | 2: 입양완료\n>> ");
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
      Dog d = dogList.get(updateNo - 1);
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
      print(removeNo - 1);
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        backToList("- 목록으로 돌아갑니다. ");
      } else if (command.equalsIgnoreCase("y")) {
        dogList.remove(dogList.get(removeNo - 1));

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

  private void backToList(String message) {
    System.out.println(message);
    System.out.println();
    managerList();
  }
}
