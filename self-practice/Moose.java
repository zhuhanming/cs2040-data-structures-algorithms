import java.util.Scanner;

public class Moose{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int left = sc.nextInt();
    int right = sc.nextInt();
    if (left==0&&right==0){
      System.out.println("Not a moose");
    }else if (left==right){
      System.out.println("Even "+(left+right));
    }else{
      System.out.print("Odd ");
      if (left>right){
        System.out.println(left*2);
      }else{
        System.out.println(right*2);
      }
    }
    sc.close();
  }
}