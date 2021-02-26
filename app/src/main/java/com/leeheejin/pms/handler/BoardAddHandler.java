package com.leeheejin.pms.handler;

import java.sql.Date;
import java.util.List;
import com.leeheejin.pms.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardAddHandler extends AbstractBoardHandler {

  public BoardAddHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {

  }

  public void add(String menuName, String name) {
    System.out.printf("[ 홈 > %s > 게시판 > %s > 글쓰기* ]\n", menuName, name);
    try {
      Board b = new Board();
      b.setNo(boardList.size() + 1);
      b.setName(Prompt.inputString("이름>> "));
      b.setPassword(Prompt.inputInt("비밀번호>> "));
      b.setTitle(Prompt.inputString("제목>> "));
      b.setContent(Prompt.inputString("내용>> "));
      b.setRegisteredDate(new Date(System.currentTimeMillis()));

      boardList.add(b);

      System.out.println("- 게시글 등록이 완료되었습니다. ");
      System.out.println();
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
    }
  }
}