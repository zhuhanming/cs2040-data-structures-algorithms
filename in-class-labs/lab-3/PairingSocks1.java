/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.Scanner;
import java.util.Stack;

public class PairingSocks1{
  public static Stack<Integer> central = new Stack<Integer>();
  public static Stack<Integer> aux = new Stack<Integer>();

  public static int process(){
    int count =0;
    while(true){
      if(central.isEmpty()&&aux.isEmpty()){
        return count;
      }
      if(central.isEmpty()&&!aux.isEmpty()){
        return -1;
      }
      if(!aux.isEmpty()){
        if (central.peek().equals(aux.peek())){
          central.pop();
          aux.pop();
          count++;
        }else{
          aux.push(central.pop());
          count++;
        }
      }else{
        aux.push(central.pop());
        count++;
      }
    }
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int numSocks = sc.nextInt();
    numSocks*=2;
    int next;
    for (int i=0; i<numSocks; i++){
      next = sc.nextInt();
      aux.push(next);
    }
    sc.close();
    for (int i=0;i<numSocks; i++){
      central.push(aux.pop());
    }
    int result = process();
    if (result==-1){
      System.out.println("impossible");
    }else{
      System.out.println(result);
    }
  }
}