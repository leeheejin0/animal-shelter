package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardRemoveHandler extends AbstractBoardHandler {

  public BoardRemoveHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service(int removeNo) {
    int password = Prompt.inputInt("<삭제>\n비밀번호? ");
    if (findByPw(removeNo, password)) {
      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
      } else if (command.equalsIgnoreCase("y")) {
        boardList.remove(boardList.get(removeNo - 1));
        for (int i = removeNo - 1; i < boardList.size(); i++) {
          Board board = boardList.get(i);
          board.setNo(board.getNo() - 1);
        }
        System.out.println("- <삭제완료>");
        System.out.println();
      }
    } else {
      System.out.println("- 비밀번호가 일치하지 않습니다. ");
      System.out.println();
    }
  }
}