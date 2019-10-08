import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Teque {
  public ListNode head;
  public ListNode back;
  public ListNode middle;
  public int num_nodes;

  public Teque() {
  }

  public void pushBack(int number) {
    if (num_nodes == 0) {
      back = new ListNode(number);
      head = back;
      middle = back;
      num_nodes++;
    } else {
      back.setNext(new ListNode(number));
      back = back.getNext();
      num_nodes++;
      if (num_nodes % 2 == 1) {
        middle.setPrev(middle);
        middle = middle.getNext();
      }
    }
  }

  public void pushFront(int number) {
    if (num_nodes == 0) {
      head = new ListNode(number);
      back = head;
      middle = head;
      num_nodes++;
    } else if (num_nodes == 1) {
      ListNode newHead = new ListNode(number);
      newHead.setNext(head);
      head = newHead;
      num_nodes++;
      middle.setPrev(head);
      middle = middle.getPrev();
    } else {
      ListNode newHead = new ListNode(number);
      newHead.setNext(head);
      head = newHead;
      num_nodes++;
      if (head.getNext() == middle) {
        middle.setPrev(head);
      }
      if (num_nodes % 2 == 0) {
        middle = middle.getPrev();
      }
    }
  }

  public void pushMiddle(int number) {
    if (num_nodes == 0) {
      head = new ListNode(number);
      middle = head;
      back = head;
      num_nodes++;
    } else {
      ListNode newMiddle = new ListNode(number);
      newMiddle.setPrev(middle);
      if (middle.getNext() != null) {
        middle.getNext().setPrev(newMiddle);
        newMiddle.setNext(middle.getNext());
      } else {
        back = newMiddle;
      }
      middle.setNext(newMiddle);
      num_nodes++;
      if (num_nodes % 2 == 1) {
        middle = newMiddle;
      }
    }
  }

  public void get(int index) {
    int counter = 0;
    ListNode node = null;
    for (ListNode cur = head; cur != null; cur = cur.getNext()) {
      if (counter == index) {
        node = cur;
        break;
      }
      counter++;
    }
    System.out.println(node.getItem());
  }

  public static void main(String[] args) throws IOException {
    Kattio io = new Kattio(System.in, System.out);
    int numOps = io.getInt();
    Teque arr = new Teque();
    for (int i = 0; i < numOps; i++) {
      String cmd = io.getWord();
      int num = io.getInt();
      switch (cmd) {
      case "push_back":
        arr.pushBack(num);
        break;
      case "push_front":
        arr.pushFront(num);
        break;
      case "push_middle":
        arr.pushMiddle(num);
        break;
      case "get":
        arr.get(num);
        break;
      }
    }
    io.close();
  }

}

class ListNode {
  /* attributes */
  public int item;
  public ListNode next;
  public ListNode prev;

  /* constructors */
  public ListNode(int val) {
    this(val, null);
  }

  public ListNode(int val, ListNode n) {
    item = val;
    next = n;
  }

  /* get the next ListNode */
  public ListNode getNext() {
    return next;
  }

  public ListNode getPrev() {
    return prev;
  }

  public void setPrev(ListNode n) {
    prev = n;
  }

  /* get the item of the ListNode */
  public int getItem() {
    return item;
  }

  /* set the item of the ListNode */
  public void setItem(int val) {
    item = val;
  }

  /* set the next reference */
  public void setNext(ListNode n) {
    next = n;
  }
}

class Kattio extends PrintWriter {
  public Kattio(InputStream i) {
    super(new BufferedOutputStream(System.out));
    r = new BufferedReader(new InputStreamReader(i));
  }

  public Kattio(InputStream i, OutputStream o) {
    super(new BufferedOutputStream(o));
    r = new BufferedReader(new InputStreamReader(i));
  }

  public boolean hasMoreTokens() {
    return peekToken() != null;
  }

  public int getInt() {
    return Integer.parseInt(nextToken());
  }

  public double getDouble() {
    return Double.parseDouble(nextToken());
  }

  public long getLong() {
    return Long.parseLong(nextToken());
  }

  public String getWord() {
    return nextToken();
  }

  private BufferedReader r;
  private String line;
  private StringTokenizer st;
  private String token;

  private String peekToken() {
    if (token == null)
      try {
        while (st == null || !st.hasMoreTokens()) {
          line = r.readLine();
          if (line == null)
            return null;
          st = new StringTokenizer(line);
        }
        token = st.nextToken();
      } catch (IOException e) {
      }
    return token;
  }

  private String nextToken() {
    String ans = peekToken();
    token = null;
    return ans;
  }
}
