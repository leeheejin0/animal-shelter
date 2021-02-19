package com.leeheejin.myproject.handler;

import java.util.List;
import com.leeheejin.myproject.domain.Board;

public abstract class AbstractBoardHandler {

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  protected boolean findByPw(int index, int password) {
    Board b = boardList.get(index - 1);
    if (b.getPassword() == password) {
      return true;
    }
    return false;
  }
}