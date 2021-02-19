package com.leeheejin.myproject;

import java.util.LinkedList;
import com.leeheejin.myproject.domain.Board;
import com.leeheejin.myproject.domain.Cat;
import com.leeheejin.myproject.domain.Dog;
import com.leeheejin.myproject.domain.Member;
import com.leeheejin.myproject.domain.Other;
import com.leeheejin.myproject.handler.BoardAddHandler;
import com.leeheejin.myproject.handler.BoardListHandler;
import com.leeheejin.myproject.handler.CatAddHandler;
import com.leeheejin.myproject.handler.CatListHandler;
import com.leeheejin.myproject.handler.DogAddHandler;
import com.leeheejin.myproject.handler.DogListHandler;
import com.leeheejin.myproject.handler.MemberAddHandler;
import com.leeheejin.myproject.handler.MemberAccountHandler;
import com.leeheejin.myproject.handler.MemberUpdateHandler;
import com.leeheejin.myproject.handler.OtherAddHandler;
import com.leeheejin.myproject.handler.OtherListHandler;
import com.leeheejin.util.Prompt;

public class App2 {
  public static void main(String[] args) {
    LinkedList<Member> memberList = new LinkedList();
    MemberAddHandler memberAddHandler = new MemberAddHandler(memberList);
    MemberAccountHandler memberLogInHandler = new MemberAccountHandler(memberList);
    MemberUpdateHandler memberUpdateHandler = new MemberUpdateHandler(memberList);

    LinkedList<Board> boardList = new LinkedList();
    BoardAddHandler boardAddHandler = new BoardAddHandler(boardList);
    BoardListHandler boardListHandler = new BoardListHandler(boardList);
    BoardAddHandler boardAddHandler2 = new BoardAddHandler(boardList);
    BoardListHandler boardListHandler2 = new BoardListHandler(boardList);

    LinkedList<Cat> catList = new LinkedList();
    CatAddHandler catAddHandler = new CatAddHandler(catList);
    CatListHandler catListHandler = new CatListHandler(catList);

    LinkedList<Dog> dogList = new LinkedList();
    DogAddHandler dogAddHandler = new DogAddHandler(dogList);
    DogListHandler dogListHandler = new DogListHandler(dogList);

    LinkedList<Other> otherList = new LinkedList();
    OtherAddHandler otherAddHandler = new OtherAddHandler(otherList);
    OtherListHandler otherListHandler = new OtherListHandler(otherList);

    loop:
      while (true) {
        System.out.println("< 동물 보호소 관리 시스템 >");
        System.out.println("[ 홈* ]");
        System.out.println("(1) 회원가입 / 로그인");
        System.out.println("(2) 비회원 둘러보기"); 
        System.out.println("(3) 시스템 종료"); 
        int input = Prompt.inputInt(">> ");
        try {
          switch (input) {
            case 1:
              System.out.println("[ 홈 > 회원가입/로그인* ]");
              System.out.println("(1) 회원가입");
              System.out.println("(2) 로그인"); 
              System.out.println("(3) 뒤로가기"); 
              int memberInput = Prompt.inputInt(">> ");
              if (memberInput == 1) {
                memberAddHandler.signUp();
              } else if (memberInput == 2) {
                int menuNo = memberLogInHandler.logIn();
                if (menuNo == 1) {
                  System.out.println("[ 홈 > 관리자 메뉴* ]");
                  System.out.println("(1) 회원정보수정");
                  System.out.println("(2) 로그아웃"); 
                  System.out.println("(3) 구조 동물 목록");
                  System.out.println("(4) 게시판");
                  int mCommand = Prompt.inputInt(">> ");
                  try {
                    if (mCommand == 1) {
                      memberUpdateHandler.updateInfo();
                    } else if (mCommand == 2) {
                      System.out.println("- 로그아웃 되었습니다. \n");
                      return;
                    } else if (mCommand == 3) {
                      System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록* ]");
                      System.out.println("(1) 신규 등록");
                      System.out.println("(2) 고양이 목록 보기");
                      System.out.println("(3) 개 목록 보기"); 
                      System.out.println("(4) 기타 동물 목록 보기");
                      System.out.println("(5) 뒤로가기"); 
                      int command = Prompt.inputInt(">> ");
                      try {
                        switch (command) {
                          case 1: 
                            System.out.println("[ 홈 > 관리자 메뉴 > 구조동물목록 > 신규등록* ]");
                            System.out.println("(1) 신규 고양이 등록");
                            System.out.println("(2) 신규 개 등록"); 
                            System.out.println("(3) 신규 기타동물 등록");
                            System.out.println("(4) 뒤로가기"); 
                            int managerAddCommand = Prompt.inputInt(" >> ");
                            try {
                              switch (managerAddCommand) {
                                case 1:
                                  catAddHandler.add();
                                  break;
                                case 2:
                                  dogAddHandler.add();
                                  break;
                                case 3:
                                  otherAddHandler.add();
                                  break;
                                default:
                                  break;
                              }
                            } catch (Exception e) {
                              System.out.println("---------------------");
                              System.out.println(" 잘못된 입력입니다. ");
                              System.out.println("---------------------");
                            }
                            System.out.println();
                            break;
                          case 2:
                            catListHandler.managerList();
                            break;
                          case 3:
                            dogListHandler.managerList();
                            break;
                          case 4:
                            otherListHandler.managerList();
                            break;
                          default:
                            break;
                        }
                      } catch (Exception e) {
                        System.out.println("---------------------");
                        System.out.println(" 잘못된 입력입니다. ");
                        System.out.println("---------------------");
                      }
                      System.out.println();
                    } else if (mCommand == 4) {
                      System.out.println("[ 홈 > 관리자 메뉴 > 게시판* ]");
                      System.out.println("(1) 입양이야기");
                      System.out.println("(2) 구조이야기");
                      System.out.println("(3) 뒤로가기");
                      int command = Prompt.inputInt(">> ");
                      try {
                        switch (command) {
                          case 1:
                            System.out.println("[ 홈 > 관리자 메뉴 > 게시판 > 입양이야기* ]\n");
                            System.out.println("(1) 게시글 등록");
                            System.out.println("(2) 게시글 목록");
                            System.out.println("(3) 뒤로가기");
                            int boardCommandMA = Prompt.inputInt(">> ");
                            try {
                              switch (boardCommandMA) {
                                case 1:
                                  boardAddHandler.add("관리자 메뉴", "입양이야기");
                                  break;
                                case 2:
                                  boardListHandler.list("관리자 메뉴", "입양이야기");
                                  break;
                                default:
                                  break;
                              }
                            } catch (Exception e) {
                              System.out.println("---------------------");
                              System.out.println(" 잘못된 입력입니다. ");
                              System.out.println("---------------------");
                            }
                            System.out.println();

                            break;
                          case 2:
                            System.out.println("[ 홈 > 관리자 메뉴 > 게시판 > 구조이야기* ]\n");
                            System.out.println("(1) 게시글 등록");
                            System.out.println("(2) 게시글 목록");
                            System.out.println("(3) 뒤로가기");
                            int boardCommandMR = Prompt.inputInt(">> ");
                            try {
                              switch (boardCommandMR) {
                                case 1:
                                  boardAddHandler2.add("관리자 메뉴", "구조이야기");
                                  break;
                                case 2:
                                  boardListHandler2.list("관리자 메뉴", "구조이야기");
                                  break;
                                default:
                                  break;
                              }
                            } catch (Exception e) {
                              System.out.println("---------------------");
                              System.out.println(" 잘못된 입력입니다. ");
                              System.out.println("---------------------");
                            }
                            System.out.println();
                            break;
                          default:
                            break;
                        }
                      } catch (Exception e) {
                        System.out.println("---------------------");
                        System.out.println(" 잘못된 입력입니다. ");
                        System.out.println("---------------------");
                      }
                      System.out.println();
                    } else {
                      System.out.println("- 잘못 입력하셨습니다. ");
                      System.out.println();
                    }
                  } catch (Exception e) {
                    System.out.println("---------------------");
                    System.out.println(" 잘못된 입력입니다. ");
                    System.out.println("---------------------");
                  }
                  System.out.println();
                }
              } else if (memberInput == 3) {
                break;
              } else {
                System.out.println("- 잘못 입력하셨습니다. ");
              }
              break;
            case 2:
              System.out.println("[ 홈 > 메뉴* ]");
              System.out.println("(1) 구조 동물 목록");
              System.out.println("(2) 게시판");
              System.out.println("(3) 뒤로가기");
              int command = Prompt.inputInt(">> ");
              try {
                if (command == 1) {
                  System.out.println("[ 홈 > 메뉴 > 구조동물목록* ]");
                  System.out.println("(1) 고양이 목록 보기");
                  System.out.println("(2) 개 목록 보기"); 
                  System.out.println("(3) 기타 동물 목록 보기");
                  System.out.println("(4) 뒤로가기"); 
                  int listCommand = Prompt.inputInt(">> ");
                  try {
                    switch (listCommand) {
                      case 1:
                        catListHandler.generalList();
                        break;
                      case 2:
                        dogListHandler.generalList();
                        break;
                      case 3:
                        otherListHandler.generalList();
                        break;
                      default:
                        break;
                    }
                  } catch (Exception e) {
                    System.out.println("---------------------");
                    System.out.println(" 잘못된 입력입니다. ");
                    System.out.println("---------------------");
                  }
                  System.out.println();
                } else if (command == 2) {
                  System.out.println("[ 홈 > 메뉴 > 게시판* ]");
                  System.out.println("(1) 입양이야기");
                  System.out.println("(2) 구조이야기");
                  System.out.println("(3) 뒤로가기");
                  int boardCommand = Prompt.inputInt(">> ");
                  try {
                    switch (boardCommand) {
                      case 1:
                        System.out.println("[ 홈 > 메뉴 > 게시판 > 입양이야기* ]\n");
                        System.out.println("(1) 게시글 등록");
                        System.out.println("(2) 게시글 목록");
                        System.out.println("(3) 뒤로가기");
                        int boardCommandA = Prompt.inputInt(">> ");
                        try {
                          switch (boardCommandA) {
                            case 1:
                              boardAddHandler.add("메뉴", "입양이야기");
                              break;
                            case 2:
                              boardListHandler.list("메뉴", "입양이야기");
                              break;
                            default:
                              break;
                          }
                        } catch (Exception e) {
                          System.out.println("---------------------");
                          System.out.println(" 잘못된 입력입니다. ");
                          System.out.println("---------------------");
                        }
                        System.out.println();
                        break;
                      case 2:
                        System.out.println("[ 홈 > 메뉴 > 게시판 > 구조이야기* ]\n");
                        System.out.println("(1) 게시글 등록");
                        System.out.println("(2) 게시글 목록");
                        System.out.println("(3) 뒤로가기");
                        int boardCommandR = Prompt.inputInt(">> ");
                        try {
                          switch (boardCommandR) {
                            case 1:
                              boardAddHandler2.add("메뉴", "구조이야기");
                              break;
                            case 2:
                              boardListHandler2.list("메뉴", "구조이야기");
                              break;
                            default:
                              break;
                          }
                        } catch (Exception e) {
                          System.out.println("---------------------");
                          System.out.println(" 잘못된 입력입니다. ");
                          System.out.println("---------------------");
                        }
                        System.out.println();
                      default:
                        break;
                    }
                  } catch (Exception e) {
                    System.out.println("---------------------");
                    System.out.println(" 잘못된 입력입니다. ");
                    System.out.println("---------------------");
                  }
                  System.out.println();
                } else if (command == 3) {
                  //뒤로가기
                  return;
                } else {
                  System.out.println("- 잘못 입력하셨습니다. ");
                  System.out.println();
                }
              } catch (Exception e) {
                System.out.println("---------------------");
                System.out.println(" 잘못된 입력입니다. ");
                System.out.println("---------------------");
              }
              System.out.println();
              break;
            case 3:
              System.out.println("- 종료합니다. ");
              break loop;
            default:
              System.out.println("- 잘못 입력하셨습니다. ");
              System.out.println();
              break;
          }
        } catch (Exception e) {
          System.out.println("---------------------");
          System.out.println(" 잘못된 입력입니다. ");
          System.out.println("---------------------");
        }
        System.out.println();
      }
  }
}



