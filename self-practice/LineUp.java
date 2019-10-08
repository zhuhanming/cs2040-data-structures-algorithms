import java.util.Scanner;

public class LineUp{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    int prevDiff = 0;
    int currDiff;
    String prevString = "";
    String currString;
    boolean neither = false;

    sc.nextLine();
    for(int i=0; i<num; i++){
      currString = sc.nextLine();
      if (i==0){
        prevString = currString;
        continue;
      }else if (i==1){
        if (currString.equals(prevString)){
          prevString = currString;
          continue;
        }
        currDiff = currString.compareTo(prevString);
        prevDiff = currDiff;
        prevString = currString;
        continue;
      }else{
        if (currString.equals(prevString)){
          prevString = currString;
          continue;
        }
        currDiff = currString.compareTo(prevString);
        if (currDiff<0 && prevDiff>0){
          System.out.println("NEITHER");
          neither = true;
          break;
        }else if(currDiff>0 && prevDiff<0){
          System.out.println("NEITHER");
          neither = true;
          break;
        }else{
          prevDiff = currDiff;
          prevString = currString;
        }
      }
    }
    if (!neither){
      if (prevDiff<0){
        System.out.println("DECREASING");
      }else if(prevDiff>0){
        System.out.println("INCREASING");
      }
    }

  }
}