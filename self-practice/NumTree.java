
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class NumTree {
  static int left(int i) {
    return i << 1;
  } // shortcut for 2*i

  static int right(int i) {
    return (i << 1) + 1;
  } // shortcut for 2*i + 1

  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int height = sc.getInt();
    int num = (int) Math.pow(2, height + 1) - 1;
    int start = 1;
    if (sc.hasMoreTokens()) {
      String inst = sc.getWord();
      for (int i = 0, n = inst.length(); i < n; i++) {
        char x = inst.charAt(i);
        switch (x) {
        case 'L':
          start = left(start);
          break;
        case 'R':
          start = right(start);
          break;
        }
      }
    }
    sc.println(num-start+1);
    sc.close();
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
