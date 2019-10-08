import java.util.ArrayList;
import java.util.Scanner;
public class Throwns{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int children = sc.nextInt();
    int turns = sc.nextInt();
    int curr = 0;
    ArrayList<Integer> previous = new ArrayList<Integer>();
    sc.nextLine();
    for (int i=0; i<turns; i++){
      previous.add(curr);
      String input = sc.next();
      if (input.equals("undo")){
        int undos = sc.nextInt();
        for (int x=0; x<undos; x++){
          previous.remove(previous.size()-1);
        }
        curr = previous.get(previous.size()-1);
        previous.remove(previous.size()-1);
      }else{
        Integer now = Integer.parseInt(input);
        curr += now;
        while(curr<0){
          curr += children;
        }
        curr%=children;
      }
    }
    System.out.println(curr);
    sc.close();
  }
}