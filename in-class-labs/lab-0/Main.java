import java.util.Scanner;
// public class Main{
//   public static void main(String[] args){
//     Scanner sc = new Scanner(System.in);
//     String input = sc.nextLine();
//     if (input.equals("Hello World")){
//       System.out.println("Correct");
//     }else{
//       System.out.println("Incorrect");
//     }
//   }
// }

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int n, x, sum = 0;
    n = sc.nextInt();
    for (int i=0;i<n;i++){
      x = sc.nextInt();
      if ((int)(Math.log10(x)+1)>10){
        continue;
      }
      sum += x;
    }
    sc.close();
    System.out.println("Sum: " + sum);
    double avg = (double)sum/n;
    System.out.printf("Average: %.2f", avg);
    System.out.println();
  }
}