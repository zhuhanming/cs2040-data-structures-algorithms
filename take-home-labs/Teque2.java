import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Deque {
  public int[] arr;
  public int front;
  public int back;
  public int size;
  public int maxSize;

  public Deque(int size) {
    maxSize = size;
    arr = new int[size];
    front = 0;
    back = 0;
    this.size = 0;
  }

  public boolean isEmpty() {
    return front == back;
  }

  public void addFront(int x) {
    if (isEmpty()) {
      arr[front] = x;
      size++;
      back++;
    } else {
      front = (front - 1 + maxSize) % maxSize;
      arr[front] = x;
      size++;
    }
  }

  public void addBack(int x) {
    arr[back] = x;
    back = (back + 1) % maxSize;
    size++;
  }

  public int get(int index) {
    return arr[(front + index) % maxSize];
  }

  public int removeFront() {
    int result = arr[front];
    front = (front + 1) % maxSize;
    size--;
    return result;
  }

  public int removeBack() {
    back=(back-1+maxSize)%maxSize;
    size--;
    return arr[back];
  }
}

public class Teque2 {
  public Deque first;
  public Deque second;
  public int size;

  public Teque2(int size) {
    first = new Deque(size);
    second = new Deque(size);
  }

  public void addFront(int x) {
    first.addFront(x);
    size++;
    balance();
  }

  public void addBack(int x) {
    second.addBack(x);
    size++;
    balance();
  }

  public int getValue(int index) {
    if (index < first.size) {
      return first.get(index);
    } else {
      return second.get((index - first.size));
    }
  }

  public void addMiddle(int x) {
    if (first.size == second.size) {
      first.addBack(x);
    } else {
      second.addFront(x);
    }
    size++;
  }

  public void balance() {
    if ((first.size + second.size) % 2 == 0) {
      if (first.size > second.size) {
        while (first.size != second.size) {
          second.addFront(first.removeBack());
        }
      } else if (second.size > first.size) {
        while (first.size != second.size) {
          first.addBack(second.removeFront());
        }
      }
    } else {
      if (first.size > second.size) {
        while (first.size != second.size + 1) {
          second.addFront(first.removeBack());
        }
      } else if (second.size > first.size) {
        while (first.size != second.size + 1) {
          first.addBack(second.removeFront());
        }
      }
    }
  }

  public void print(){
    for (int i=0; i<first.size; i++){
      System.out.print(first.get(i) + " ");
    }
    System.out.print("| ");
    for (int i=0; i<second.size; i++){
      System.out.print(second.get(i) + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) throws IOException {
    Kattio io = new Kattio(System.in, System.out);
    int numOps = io.getInt();
    Teque2 arr = new Teque2(numOps);
    for (int i = 0; i < numOps; i++) {
      String cmd = io.getWord();
      int num = io.getInt();
      switch (cmd) {
      case "push_back":
        arr.addBack(num);
        // arr.print();
        break;
      case "push_front":
        arr.addFront(num);
        // arr.print();
        break;
      case "push_middle":
        arr.addMiddle(num);
        // arr.print();
        break;
      case "get":
        io.println(arr.getValue(num));
        break;
      }
    }
    io.close();
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