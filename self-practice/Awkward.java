import java.util.HashMap;
import java.util.Scanner;

public class Awkward{
  public static HashMap<Integer, Integer> languages = new HashMap<Integer, Integer>();
  public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
    int numGuests = sc.nextInt();
    int awkward = numGuests;
    int language;
    for (int i=0; i<numGuests; i++){
      language = sc.nextInt();
      if (languages.containsKey(language)){
        int x = languages.get(language);
        if (i-x<awkward){
          awkward = (i-x);
        }
        languages.replace(language, i);
      }else{
        languages.put(language, i);
      }
    }
    sc.close();
    System.out.println(awkward);
  }  
}