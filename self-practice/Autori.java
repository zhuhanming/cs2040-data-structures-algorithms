import java.util.Scanner;

public class Autori{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    String result = "";
    result += input.charAt(0);
    for (int i=1, n=input.length(); i<n; i++){
      if (input.charAt(i)=='-'){
        result+=input.charAt(i+1);
      }
    }
    sc.close();
    System.out.println(result);
  }
}