package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.util.Prompt;

public class CatUpdateHandler extends AbstractCatHandler{

  public CatUpdateHandler(List<Cat> catList) {
    super(catList);
  }

  @Override
  public void service() {
    System.out.println();
    int updateNo = Prompt.inputInt("<상태수정>\n번호? ");

    if (updateNo <= catList.size()) {

      print(updateNo - 1);
      String updateStatus = Prompt.inputString("1: 공고중 | 2: 입양완료\n>> ");
      String stateLabel = null;
      switch (updateStatus) {
        case "1":
          stateLabel = "공고중";
          break;
        case "2":
          stateLabel = "입양완료";
          break;
        default:
          stateLabel = "신규";
          break;
      }
      Cat c = catList.get(updateNo - 1);
      c.setStatus(stateLabel);
      System.out.println("<수정완료>");
      System.out.println();
      return;
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
}
