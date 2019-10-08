/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Workstations{
  public static void main(String[] args){
    Kattio sc = new Kattio(System.in, System.out);
    int numResearchers = sc.getInt();
    int inactivity = sc.getInt();
    int result = 0;
    Researcher[] res = new Researcher[numResearchers];
    for (int i=0; i<numResearchers; i++){
      res[i] = new Researcher(sc.getInt(), sc.getInt());
    }
    Arrays.sort(res, new Comparator<Researcher>(){
      public int compare(Researcher a, Researcher b){
        return a.getArrTime() - b.getArrTime();
      }
    });
    
    PriorityQueue<Station> unlockedStations = new PriorityQueue<>(new Comparator<Station>(){
      public int compare(Station a, Station b){
        return a.getLockTime() - b.getLockTime();
      }
    });
    PriorityQueue<Station> lockedStations = new PriorityQueue<>(new Comparator<Station>(){
      public int compare(Station a, Station b){
        return a.getLockTime() - b.getLockTime();
      }
    });

    for (int i=0; i<numResearchers; i++){
      boolean isAssigned = false;
      if (unlockedStations.isEmpty()&&lockedStations.isEmpty()){
        unlockedStations.offer(new Station(res[i].arrTime, res[i].useTime, inactivity));
        isAssigned = true;
      }else if (unlockedStations.isEmpty()&&!lockedStations.isEmpty()){
        Station curr = lockedStations.poll();
        curr.use(res[i].arrTime, res[i].useTime, inactivity);
        unlockedStations.offer(curr);
        isAssigned = true;
      } else {
        while(!unlockedStations.isEmpty()){
          Station curr = unlockedStations.poll();
          if (curr.lockTime<res[i].arrTime){
            curr.isLocked = true;
            lockedStations.offer(curr);
          }else if (curr.endUse>res[i].arrTime){
            unlockedStations.offer(curr);
            break;
          }else {
            curr.use(res[i].arrTime, res[i].useTime, inactivity);
            result++;
            isAssigned = true;
            unlockedStations.offer(curr);
            break;
          }
        }
        if(!isAssigned){
          if (!lockedStations.isEmpty()){
            Station curr = lockedStations.poll();
            curr.use(res[i].arrTime, res[i].useTime, inactivity);
            unlockedStations.offer(curr);
          }else{
            unlockedStations.offer(new Station(res[i].arrTime, res[i].useTime, inactivity));
          }
        }
      }
    }
    sc.println(result);
    sc.close();

  }

  
}

class Station{
  public boolean isLocked;
  // boolean isUsed;
  public int endUse;
  public int lockTime;

  public Station(int arrTime, int useTime, int inactivity){
    this.isLocked = false;
    this.endUse = arrTime + useTime;
    this.lockTime = this.endUse+inactivity;
  }

  public void use(int arrTime, int useTime, int inactivity){
    this.isLocked = false;
    this.endUse = arrTime + useTime;
    this.lockTime = this.endUse+inactivity;
  }

  public int getLockTime(){
    return this.lockTime;
  }
}

class Researcher{
  int arrTime;
  int useTime;

  public Researcher(int a, int u){
    arrTime = a;
    useTime = u;
  }

  public int getArrTime(){
    return this.arrTime;
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
