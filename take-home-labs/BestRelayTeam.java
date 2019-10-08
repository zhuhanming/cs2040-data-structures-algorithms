import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BestRelayTeam{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    String name;
    double first, rest;
    Runner[] arr = new Runner[num];
    Runner[] holdFirst = new Runner[4];
    Runner[] holdRest = new Runner[4];

    for (int i=0; i<num; i++){
      name = sc.next();
      first = sc.nextDouble();
      rest = sc.nextDouble();
      sc.nextLine(); // to read away the \n.
      arr[i] = new Runner(name, first, rest);
    }
    FirstComparator firstComp = new FirstComparator();
    RestComparator restComp = new RestComparator();
    Arrays.sort(arr, firstComp);
    for (int i=0; i<4; i++){
      holdFirst[i] = arr[i];
    }
    Arrays.sort(arr, restComp);
    for (int i=0; i<4; i++){
      holdRest[i] = arr[i];
    }
    double bestTime = 9999, currTime = 0;
    int numAdded = 0;
    Runner[] finalTeam = new Runner[4];
    Runner[] currTeam = new Runner[4];
    for (Runner x : holdFirst){
      numAdded = 0;
      currTime = x.getFirst();
      currTeam[numAdded++] = x;
      for (Runner y : holdRest){
        if (numAdded==4){
          break;
        }
        if (x!=y){
          currTime += y.getRest();
          currTeam[numAdded++] = y;
        }
      }
      if (currTime<bestTime){
        bestTime= currTime;
        for (int i=0; i<4; i++){
          finalTeam[i] = currTeam[i];
        }
      }
    }
    System.out.println(bestTime);
    for (Runner x : finalTeam){
      System.out.println(x.getName());
    }
    sc.close();
  }
}

class Runner{
  public String name;
  public double first;
  public double rest;

  public Runner(String n, double f, double r){
    this.name = n;
    this.first = f;
    this.rest = r;
  }

  public String getName(){
    return this.name;
  }

  public double getFirst(){
    return this.first;
  }

  public double getRest(){
    return this.rest;
  }
}

class FirstComparator implements Comparator<Runner>{
  public int compare(Runner a, Runner b){
    if (a.getFirst()<b.getFirst()) return -1;
    if (a.getFirst()>b.getFirst()) return 1;
    return 0;
  }
  public boolean equals(Runner a, Runner b){
    return a==b;
  }
}

class RestComparator implements Comparator<Runner>{
  public int compare(Runner a, Runner b){
    if (a.getRest()<b.getRest()) return -1;
    if (a.getRest()>b.getRest()) return 1;
    return 0;
  }
  public boolean equals(Runner a, Runner b){
    return a==b;
  }
}