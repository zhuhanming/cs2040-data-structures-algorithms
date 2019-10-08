import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RoomBooking{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int total = sc.nextInt();
    int booked = sc.nextInt();
    Integer[] rooms = new Integer[booked];
    for (int i=0; i<booked; i++){
      int x = sc.nextInt();
      rooms[i] = x;
    }
    List<Integer> allBooked = Arrays.asList(rooms);
    if(total==booked){
      System.out.println("too late");
    }else{
      for (int i=1; i<=total; i++){
        if (!allBooked.contains(i)){
          System.out.println(i);
          break;
        }
      }
    }
  }
}