import java.util.Scanner;

public class Mjehuric{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int[] arr = new int[5];
    for (int i=0; i<5; i++){
      arr[i] = sc.nextInt();
    }  
    sc.close();
    printBubbleSort(arr);
  }

  public static void printBubbleSort(int[] a){
    for (int i=1; i<5; i++){
      boolean sorted = true;
      for (int k=0; k<5-i; k++){
        if (a[k]>a[k+1]){
          sorted = false;
          int temp = a[k];
          a[k] = a[k+1];
          a[k+1] = temp;
          print(a);
        }
      }
      if (sorted) break;
    }
  }

  public static void print(int[] a){
    for (int i=0; i<5; i++){
      if (i!=4){
        System.out.print(a[i]+" ");
      }
      else{
        System.out.print(a[i]);
        System.out.println();
      }
    }
  }
}