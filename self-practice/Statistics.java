import java.util.Scanner;

public class Statistics{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int caseNo = 1;
    int n, x;
    int max, min;
    while (sc.hasNextInt()){
      max = -1000001;
      min = 1000000;
      n = sc.nextInt();
      for (int i=0;i<n; i++){
        x = sc.nextInt();
        if (x>max){
          max = x;
        }
        if (x<min){
          min = x;
        }
      }
      sc.nextLine();
      System.out.println("Case " + caseNo + ": " + min + " " + max + " " + (max-min));
      caseNo++;
    }
    sc.close();
  }
}