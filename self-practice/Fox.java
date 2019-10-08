import java.util.*;

public class Fox{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);  
    int num = sc.nextInt();
    sc.nextLine();
    for (int i=0; i<num; i++){
      HashMap<String, Boolean> sounds = new HashMap<>();
      String input = sc.nextLine();
      ArrayList<String> words =  new ArrayList<String>(Arrays.asList(input.split(" ")));
      String x = sc.nextLine();
      while (!x.equals("what does the fox say?")){
        String[] noises = x.split(" ");
        sounds.put(noises[2], true);
        x = sc.nextLine();
      }
      String result = "";
      for (int j=0, n=words.size(); j<n; j++){
        if (!sounds.containsKey(words.get(j))){
          result += words.get(j);
          if (j!=n-1){
            result += " ";
          }
        }
        
      }
      System.out.println(result);
    }
  }
}

