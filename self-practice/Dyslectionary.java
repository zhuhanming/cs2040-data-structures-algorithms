import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Dyslectionary {
  public static final int MAX = 100;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int countGroup = 0;
    String x;
    StringComparator strComp = new StringComparator();

    while(sc.hasNextLine()&&countGroup<MAX){
      if (countGroup!=0){
        System.out.println();
      }
      countGroup++;
      ArrayList<String> container = new ArrayList<String>();
      do{
        if(!sc.hasNextLine()){break;}
        x = sc.nextLine();
        if (x.equals("")){
          break;
        }
        container.add(x);
      }while(!x.equals(""));
      Collections.sort(container, strComp);
      print(container);
    }
    sc.close();
  }

  public static void print(ArrayList<String> a) {
    int maxlen = 0;
    for (String x: a){
      if (x.length()>maxlen){
        maxlen = x.length();
      }
    }
    for (String x: a){
      for (int i = x.length(); i<maxlen; i++){
        System.out.print(" ");
      }
      System.out.println(x);
    }
  }

}

class StringComparator implements Comparator<String> {

  @Override
  public int compare(String o1, String o2) {
    String rev1 = "";
    String rev2 = "";
    for (int i = o1.length()-1; i>=0; i--){
      rev1 += o1.charAt(i);
    }
    for (int i = o2.length()-1; i>=0; i--){
      rev2 += o2.charAt(i);
    }
    if (rev1.compareTo(rev2)<0){
      return -1;
    }else if (rev1.compareTo(rev2)>0){
      return 1;
    }else return 0;
  }
  
}