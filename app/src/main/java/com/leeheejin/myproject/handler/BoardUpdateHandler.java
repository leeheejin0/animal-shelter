package com.leeheejin.myproject.handler;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler {

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void service() {

  }

  public void list(String menuName, String name) {
    System.out.printf("[ 홈 > %s > 게시판 > %s > 글목록* ]\n", menuName, name);
    Board[] list = boardList.toArray(new Board[boardList.size()]);
    for (Board b : list) {
      System.out.printf("(%d) %s |%s| %s |%d|%d|\n", 
          b.getNo(), b.getTitle(), b.getRegisteredDate(), b.getName(), 
          b.getViewCount(), b.getLike());
    }

    int command = Prompt.inputInt("1: 상세보기 | 2: 검색 | 3: 뒤로가기\n>>");
    switch (command) {
      case 1:
        detail();
        break;
      case 2:
        //search();
        break;
      case 3:
        return;
      default:
        System.out.println("실행할 수 없는 명령입니다.");
        System.out.println();
        break;
    }
  }

  public void detail() {
    int detailNo = Prompt.inputInt("<상세보기>\n게시글 번호? ");
    if (detailNo <= boardList.size()) {
      Board b = boardList.get(detailNo - 1);
      b.setViewCount(b.getViewCount() + 1);
      System.out.println("==========================================================");
      System.out.printf("<  %s  >                %s | %s |\n", 
          b.getTitle(), b.getName(), b.getRegisteredDate());
      System.out.println(" --------------------------------------------------------");
      System.out.printf("  %s\n", b.getContent());
      System.out.println(" --------------------------------------------------------");
      System.out.printf("                              조회수 : %d | 좋아요 : %d |\n", 
          b.getViewCount(), b.getLike());
      System.out.println("==========================================================");
      int command = Prompt.inputInt("1: 수정 | 2: 삭제 | 3: 뒤로가기\n>> ");
      switch (command) {
        case 1:
          update(detailNo);
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
    } else {
      System.out.println("- 잘못 입력하셨습니다. ");
      System.out.println();
    }
  }

  public void search() {
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

  public void update(int updateNo) {
    try {
      int password = Prompt.inputInt("<수정>\n비밀번호? ");
      if (findByPw(updateNo, password)) {
        System.out.println("-");
        Board b = boardList.get(updateNo - 1);
        b.setName(Prompt.inputString("이름>> "));
        b.setPassword(Prompt.inputInt("비밀번호>> "));
        b.setTitle(Prompt.inputString("제목>> "));
        b.setContent(Prompt.inputString("내용>> "));
        b.setRegisteredDate(new Date(System.currentTimeMillis()));

        System.out.println("==========================================================");
        System.out.printf("<  %s  >                %s | %s |\n", 
            b.getTitle(), b.getName(), b.getRegisteredDate());
        System.out.println(" --------------------------------------------------------");
        System.out.printf("  %s\n", b.getContent());
        System.out.println(" --------------------------------------------------------");
        System.out.printf("                              조회수 : %d | 좋아요 : %d |\n", 
            b.getViewCount(), b.getLike());
        System.out.println("==========================================================");
        System.out.println("- <수정완료>");
        System.out.println();
      } else {
        System.out.println("- 비밀번호가 일치하지 않습니다. ");
        System.out.println();
      }
    } catch (Exception e) {
      System.out.println("---------------------");
      System.out.println(" 잘못된 입력입니다. ");
      System.out.println("---------------------");
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
}