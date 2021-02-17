package com.leeheejin.myproject.handler;

import java.sql.Date;
import java.util.LinkedList;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardHandler {
  LinkedList<Board> boardList = new LinkedList<>();

  public void add(String menuName, String name) {
    System.out.printf("[ 홈 > %s > 게시판 > %s > 글쓰기* ]\n", menuName, name);
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
  }

  public void list(String menuName, String name) {
    System.out.printf("[ 홈 > %s > 게시판 > %s > 글목록* ]\n", menuName, name);
    Object[] list = boardList.toArray();
    for (Object obj : list) {
      Board b = (Board) obj;
      System.out.printf("[%d] %s |%s| %s |%d|%d|\n", 
          b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
          b.getViewCount(), b.getLike());
    }

    int command = Prompt.inputInt("1: 수정 | 2: 삭제 | 3: 뒤로가기\n>>");
    switch (command) {
      case 1:
        update();
        break;
      case 2:
        remove();
        break;
      default:
        break;
    }
  }

  public void update() {
    int updateNo = Prompt.inputInt("<수정>\n게시글 번호? ");
    if (updateNo <= boardList.size()) {
      int password = Prompt.inputInt("비밀번호? ");
      if (findByPw(updateNo, password)) {
        System.out.println("-");
        Board b = boardList.get(updateNo);
        b.setName(Prompt.inputString("이름>> "));
        b.setPassword(Prompt.inputInt("비밀번호>> "));
        b.setTitle(Prompt.inputString("제목>> "));
        b.setContent(Prompt.inputString("내용>> "));
        b.setRegisteredDate(new Date(System.currentTimeMillis()));
        System.out.println("- <수정완료>");
        System.out.println();
      } else {
        System.out.println("- 비밀번호가 일치하지 않습니다. ");
        System.out.println();
      }
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }


  public void remove() {
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

  private boolean findByPw(int index, int password) {
    Board b = boardList.get(index);
    if (b.getPassword() == password) {
      return true;
    }
    return false;
  }
}