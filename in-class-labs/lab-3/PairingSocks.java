import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PairingSocks{


  public static int process(Stack<Integer> cent, Stack<Integer> aux, int count){
    if (cent.isEmpty()&&aux.isEmpty()){
      return count;
    }else if(!cent.isEmpty()&&!aux.isEmpty()){
      if(cent.peek()==aux.peek()){
        Stack<Integer> newCent = (Stack<Integer>) cent.clone();
        Stack<Integer> newAux = (Stack<Integer>) aux.clone();
        newAux.pop();
        newCent.pop();
        return process(newCent, newAux, count+1);
      }else{
        Stack<Integer> newCent1 = (Stack<Integer>) cent.clone();
        Stack<Integer> newAux1 = (Stack<Integer>) aux.clone();
        newAux1.push(newCent1.pop());
        Stack<Integer> newCent2 = (Stack<Integer>) cent.clone();
        Stack<Integer> newAux2 = (Stack<Integer>) aux.clone();
        newCent2.push(newAux2.pop());
        return Math.max(process(newCent1, newAux1, count+1), process(newCent2, newAux2, count+1));
      }
    }else if(cent.isEmpty()){
      Stack<Integer> newCent2 = (Stack<Integer>) cent.clone();
      Stack<Integer> newAux2 = (Stack<Integer>) aux.clone();
      newCent2.push(newAux2.pop());
      return process(newCent2, newAux2, count+1);
    }else if(aux.isEmpty()){
      Stack<Integer> newCent2 = (Stack<Integer>) cent.clone();
      Stack<Integer> newAux2 = (Stack<Integer>) aux.clone();
      newAux2.push(newCent2.pop());
      return process(newCent2, newAux2, count+1);
    }
    return -1;
  }

  public static void main(String[] args){
    // HashMap<Integer, Integer> socksCount = new HashMap<Integer, Integer>();
    Stack<Integer> central = new Stack<Integer>();
    Stack<Integer> aux = new Stack<Integer>();
    Scanner sc = new Scanner(System.in);
    int numSocks = sc.nextInt();
    for (int i=0, n=2*numSocks; i<n; i++){
      int next = sc.nextInt();
      aux.push(next);
      // if (socksCount.containsKey(next)){
      //   socksCount.replace(next, socksCount.get(next)+1);
      // }
    }
    for (int i=0, n=2*numSocks; i<n; i++){
      // int next = sc.nextInt();
      central.push(aux.pop());
    }
    // boolean toProceed = true;
    // for(Map.Entry<Integer, Integer> entry : socksCount.entrySet()) {
    //   Integer value = entry.getValue();
    //   if(value%2==1){
    //     System.out.println("impossible");
    //     toProceed = false;
    //   }
    // }
    // if (toProceed){
      int result = process(central, aux, 0);
      if (result==-1){
        System.out.println("impossible");
      }
      System.out.println(result);
    // }

  }
}