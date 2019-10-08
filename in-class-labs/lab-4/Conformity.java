/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Conformity{
  public static HashMap<String, Integer> combis = new HashMap<>();

  public static void main(String[] args){
    Kattio sc = new Kattio(System.in, System.out);
    int numStudents = sc.getInt();
    int[] arr = new int[5];
    for (int i=0; i<numStudents; i++){
      for (int x=0; x<5; x++){
        arr[x] = sc.getInt();
      }
      Arrays.sort(arr);
      String input = "";
      for (int x=0; x<5; x++){
        input += Integer.toString(arr[x]);
      }
      if (combis.containsKey(input)){
        combis.put(input, combis.get(input)+1);
      }else{
        combis.put(input, 1);
      }
    }
    int x = Collections.max(combis.values());
    int result = 0;
    for (Integer value:combis.values()){
      if(value==x){
        result += x;
      }
    }
    System.out.println(result);
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
