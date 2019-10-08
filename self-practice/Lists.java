import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Lists{
  public static void main(String[] args){
    Kattio sc = new Kattio(System.in, System.out);
    int numTestCases = sc.getInt();
    for (int i=0; i<numTestCases;i++){
      String commands = sc.getWord();
      int sizeList = sc.getInt();
      String input = sc.getWord();
      input = input.substring(1, input.length()-1);
      // String[] array = input.split(",");
      ArrayDeque<String> inputs = new ArrayDeque<String>(Arrays.asList(input.split(",")));
      boolean toProceed = true;
      boolean atHead = true;
      for (int x=0, j=commands.length(); x<j; x++){
        switch(commands.charAt(x)){
          case 'R':
            atHead = !atHead;
            break;
          case 'D':
            if(sizeList==0){
              sc.println("error");
              toProceed = false;
            }else{
              if (atHead){
                inputs.pollFirst();
                sizeList--;
              }else{
                inputs.pollLast();
                sizeList--;
              }
            }
            break;
        }
        if (!toProceed){
          break;
        }
      }
      if(!toProceed){
        continue;
      }else{
        sc.print("[");
        if(atHead){
          for(int y=0, n=inputs.size(); y<n; y++){
            sc.print(inputs.pollFirst());
            if(y!=n-1){
              sc.print(",");
            }
          }
        }else{
          for(int n=inputs.size()-1, y=n; y>=0; y--){
            sc.print(inputs.pollLast());
            if(y!=0){
              sc.print(",");
            }
          }
        }
        sc.println("]");
      }
    }
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
