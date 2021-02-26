package com.leeheejin.pms.handler;

import java.util.LinkedList;
import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardSearchHandler extends AbstractBoardHandler {

  public BoardSearchHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    String keyword = Prompt.inputString("| 검색 | ");

    if (keyword.length() == 0) {
      System.out.println("- 검색어를 입력하세요. ");
      return;
    }

    LinkedList<Board> list = new LinkedList<>();
    Board[] boards = boardList.toArray(new Board[boardList.size()]);
    for (Board b : boards) {
      if (b.getTitle().contains(keyword) ||
          b.getContent().contains(keyword) ||
          b.getName().contains(keyword)) {
        list.add(b);
      }
    }

    if (list.isEmpty()) {
      System.out.println("- 검색어에 해당하는 게시글이 없습니다. ");
    }
    //출력
    for (Board b : list) {
      System.out.printf("(%d) %s |%s| %s |%d|%d|\n", 
          b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
          b.getViewCount(), b.getLike());
    }
  }
}