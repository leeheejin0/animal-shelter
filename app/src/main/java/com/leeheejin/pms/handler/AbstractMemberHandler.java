package com.leeheejin.pms.handler;

import java.util.List;
import com.leeheejin.pms.domain.Member;

public abstract class AbstractMemberHandler implements Command {

  protected List<Member> memberList;
  protected int nowLogIn = -1;

  public AbstractMemberHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void service() {}

  @Override
  public void service(int no) {}

  @Override
  public void service(String str1, String str2) {}

  protected boolean exist(String inputId, String inputPw) {
    Member[] arr = memberList.toArray(new Member[0]);
    for (int i = 0; i < memberList.size(); i++) {
      Member m = arr[i];
      if(inputId.equals(m.getId()) &&
          inputPw.equals(m.getPassword())) {
        nowLogIn = m.getNo();
        return true;
      }
    }
    return false;
  }
}
