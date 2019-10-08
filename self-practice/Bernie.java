import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;


public class Bernie{
  public static HashMap<String, ArrayList<Integer>> countries = new HashMap<String, ArrayList<Integer>>();

  public static void main(String[] args){
    Kattio sc = new Kattio(System.in, System.out);
    int trips = sc.getInt();
    for (int i=0;i<trips;i++){
      String country = sc.getWord();
      int year = sc.getInt();
      if (countries.containsKey(country)){
        countries.get(country).add(year);
      }else{
        ArrayList<Integer> curr = new ArrayList<Integer>();
        curr.add(year);
        countries.put(country, curr);
      }
    }
    for (Map.Entry<String, ArrayList<Integer>> entry : countries.entrySet()){
      Collections.sort(entry.getValue());
    }
    int numQueries = sc.getInt();
    for (int i=0; i<numQueries; i++){
      String key = sc.getWord();
      int index =  sc.getInt();
      sc.println(countries.get(key).get(index-1));
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
