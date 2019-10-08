/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.Scanner;

public class SortOfSorting{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int x = sc.nextInt();
    while (x!=0){
      sc.nextLine();
      String[] arr = new String[x];
      for (int i=0; i<x; i++){
        arr[i] = sc.nextLine();
      }
      sortAndPrint(arr);
      x = sc.nextInt();
      if(x!=0){
        System.out.println();
      }
    }
    sc.close();
  }

  public static void sortAndPrint(String[] a){
    mergeSort(a, 0, a.length-1);
    print(a);
  }

  public static void mergeSort(String[] a, int i, int j){
    if (i<j){
      int mid = (i+j)/2;
      mergeSort(a, i, mid);
      mergeSort(a, mid+1, j);
      merge(a, i, mid, j);
    }
  }

  public static void merge(String[] a, int i, int mid, int j){
    String[] temp = new String[j-i+1];
    int first = i, second = mid+1, dest = 0;
    while(first<=mid&&second<=j){
      if (compare(a[first], a[second])<=0){
        temp[dest++] = a[first++];
      }else{
        temp[dest++] = a[second++];
      }
    }
    while(first<=mid) temp[dest++] = a[first++];
    while(second<=j) temp[dest++] = a[second++];

    for (int k=0; k<temp.length; k++){
      a[i+k] = temp[k];
    }
  }

  public static int compare(String a, String b){
    // Character a1 = a.charAt(0);
    // Character a2 = a.charAt(1);
    // Character b1 = b.charAt(0);
    // Character b2 = a.charAt(1);
    // if (a1.compareTo(b1)==0){
    //   return a2.compareTo(b2);
    // }else{
    //   return a1.compareTo(b1);
    // }
    if (a.charAt(0)>b.charAt(0)){
      return 1;
    }else if (a.charAt(0)<b.charAt(0)){
      return -1;
    }else{
      if (a.charAt(1)>b.charAt(1)){
        return 1;
      }else if (a.charAt(1)<b.charAt(1)){
        return -1;
      }else{
        return 0;
      }
    }
  }

  public static void print(String[] a){
    for (int i =0, n=a.length; i<n; i++){
      System.out.println(a[i]);
    }
  }
}