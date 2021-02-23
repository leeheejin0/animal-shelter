package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardRemoveHandler extends AbstractBoardHandler {

  public BoardRemoveHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {
    int removeNo = Prompt.inputInt("<삭제>\n번호? ");
    if (removeNo <= boardList.size()) {
      Object obj = boardList.get(removeNo);
      Board b = (Board) obj;
      System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
          b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
          b.getViewCount(), b.getLike());

      String command = Prompt.inputString("- 삭제하시겠습니까?(y/N) ");
      if (command.equalsIgnoreCase("n") || command.isEmpty()) {
        System.out.println("- 목록으로 돌아갑니다. ");
        System.out.println();
      } else if (command.equalsIgnoreCase("y")) {

        int password = Prompt.inputInt("비밀번호? ");
        if (findByPw(removeNo, password)) {
          boardList.remove(boardList.get(removeNo));

          for (int i = removeNo; i <= boardList.size(); i++) {
            Board board = boardList.get(i);
            board.setNo(board.getNo() - 1);
          }
          System.out.println("- <삭제완료>");
          System.out.println();
        } else {
          System.out.println("- 비밀번호가 일치하지 않습니다. ");
          System.out.println();
        }
      } else {
        System.out.println("- 잘못 입력하셨습니다. ");
        System.out.println();
      }
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }
}