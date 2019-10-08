import java.util.Scanner;

public class TreasureHunt{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int rows = sc.nextInt();
    int cols = sc.nextInt();
    sc.nextLine();
    char[][] maze = new char[rows][cols];
    for (int i=0; i<rows; i++){
      String curr = sc.nextLine();
      for (int k=0; k<cols; k++){
        maze[i][k] = curr.charAt(k);
      }
    }
    sc.close();
    int i = 0, k = 0, moves = 0;
    boolean notFound = true;
    while(i<rows && k<cols && i>=0 && k>=0 && moves<=(rows*cols) && notFound){
      switch(maze[i][k]){
        case 'N':
          i-=1;
          moves +=1;
          break;
        case 'S':
          i+=1;
          moves +=1;
          break;
        case 'W':
          k-= 1;
          moves += 1;
          break;
        case 'E':
          k += 1;
          moves += 1;
          break;
        case 'T':
          notFound = false;
          break;
      }
    }
    if (!notFound){
      System.out.println(moves);
    }else if (moves>(rows*cols)){
      System.out.println("Lost");
    }else{
      System.out.println("Out");
    }
  }
}