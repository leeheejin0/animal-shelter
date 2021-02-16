package com.leeheejin.myproject.handler;

import com.leeheejin.myproject.domain.Cat;

public class CatList {
  Cat c = new Cat();

  private Node first;
  private Node last;

  private int size = 0; 

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

  public Object get(int index) {
    if (index <= 0 || index > this.size) {
      return null;
    }
    int count = 1; 
    Node cursor = first;
    while (cursor != null) {
      if (index == count++) {
        return cursor.obj;
      }
      cursor = cursor.next;
    }
    return null;
  }

  public boolean remove(Object obj) {
    Node cursor = first;
    while (cursor != null) {
      if (cursor.obj.equals(obj)) {
        this.size--;
        if (first == last) {
          first = last = null;
          return true;
        }
        if (cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        } else {
          cursor.prev.next = cursor.next;
          if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
          if (cursor == last) {
            last = cursor.prev;
          }
          return true;
        }
      }
      cursor = cursor.next;
    }
    return false;
  }

  static class Node {
    Object obj;
    Node next;
    Node prev;
    Node (Object obj){
      this.obj = obj;
    }
  }

  public int size() {
    return this.size;
  }
}
