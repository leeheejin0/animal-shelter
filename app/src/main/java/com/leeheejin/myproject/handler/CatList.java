package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.domain.Cat;

public class CatList {
  Cat c = new Cat();

  private Node first;
  private Node last;

  int size = 0;  

  public void add(Cat c) {
    Node node = new Node(c);

    if (last == null) {
      first = node;
      last = node;
    } else {
      node.prev = last;
      last.next = node;
      last = node;
    }
    size++;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node cursor = this.first;
    int i = 0;
    while (cursor != null) {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }
    return arr;
  }



  static class Node {
    Object obj;
    Node next;
    Node prev;
    Node (Object obj){
      this.obj = obj;
    }
  }
}
