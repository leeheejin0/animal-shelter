package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatRemoveHandler extends AbstractCatHandler{

  public CatRemoveHandler(List<Cat> catList) {
    super(catList);
  }

  @Override
  public void service() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= catList.size()) {
      print(removeNo - 1);
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
      } else if (command.equalsIgnoreCase("y")) {
        catList.remove(catList.get(removeNo - 1));

        // 번호 바꾸기
        for (int i = removeNo; i <= catList.size(); i++) {
          Cat c = catList.get(i);
          c.setIds(c.getIds() - 1);
        }
        System.out.println("- <삭제완료>");
        System.out.println();
      } else {
        System.out.println("- 잘못 입력하셨습니다. ");
        System.out.println();
      }
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }

  //  private void backToList(String message) {
  //    System.out.println(message);
  //    System.out.println();
  //    managerList();
  //  }
}
