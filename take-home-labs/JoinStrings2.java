import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class JoinStrings2 {
  public static void main(String[] args) throws IOException {
    Kattio io = new Kattio(System.in, System.out);
    int n = io.getInt();
    TailedLinkedList[] inputs = new TailedLinkedList[n];
    for (int i = 0; i < n; i++) {
      inputs[i] = new TailedLinkedList(io.getWord());
    }
    if (n>1){
      for (int i = 0; i < n - 2; i++) {
        int a = io.getInt()-1;
        int b = io.getInt()-1;
        inputs[a].append(inputs[b]);
      }
      int a = io.getInt()-1;
      int b = io.getInt()-1;
      inputs[a].append(inputs[b]);
      for (ListNode x = inputs[a].head; x!=null; x=x.next){
        io.print(x.item);
      }
      io.println();
      io.close();
    }else{
      io.println(inputs[0].head.item);
      io.close();
    }
    
  }
}

class TailedLinkedList {
  // attributes
  public ListNode head;
  public ListNode tail;
  public TailedLinkedList(String val){
    this.head = new ListNode(val);
    this.tail = this.head;
  }
  public void append(TailedLinkedList n) {
    this.tail.next = n.head;
    this.tail = n.tail;
  }

}
class ListNode {
	/* attributes */
	public String item;
	public ListNode next;
	/* constructors */
	public ListNode(String val) { this.item= val; }

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
                  if (line == null) return null;
                  st = new StringTokenizer(line);
              }
              token = st.nextToken();
          } catch (IOException e) { }
      return token;
  }

  private String nextToken() {
      String ans = peekToken();
      token = null;
      return ans;
  }
}
