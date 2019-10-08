import java.util.*;

public class Comparing {
    public static void main(String[] args) {
        // base array used for populating all examples in this program
        int[] baseArr = {3, 7, 4, 3, 5, 6};
        
        
        // examples for MyInteger1 (implemented Comparable)
        MyInteger1[] arr1 = new MyInteger1[6];
        MyInteger1[] arr2 = new MyInteger1[6];
        
        ArrayList<MyInteger1> list1 = new ArrayList<MyInteger1>();
        ArrayList<MyInteger1> list2 = new ArrayList<MyInteger1>();
        
        for (int i = 0; i < 6; i++) {
            arr1[i] = new MyInteger1(baseArr[i]);
            arr2[i] = new MyInteger1(baseArr[i]);
            
            list1.add(new MyInteger1(baseArr[i]));
            list2.add(new MyInteger1(baseArr[i]));
        }
        
        // sort by default ordering (ascending order)
        Arrays.sort(arr1);
        System.out.println("Array 1: " + Arrays.toString(arr1));
        Collections.sort(list1);
        System.out.println("List 1: " + list1);
        
        // sort by reverse ordering
        Arrays.sort(arr2, Comparator.reverseOrder());
        System.out.println("Array 2: " + Arrays.toString(arr2));
        Collections.sort(list2);
        System.out.println("List 2: " + list2);
        
        
        // examples for MyInteger2 (Comparable not implemented)
        MyInteger2[] arr3 = new MyInteger2[6];
        MyInteger2[] arr4 = new MyInteger2[6];
        
        ArrayList<MyInteger2> list3 = new ArrayList<MyInteger2>();
        ArrayList<MyInteger2> list4 = new ArrayList<MyInteger2>();
        
        for (int i = 0; i < 6; i++) {
            arr3[i] = new MyInteger2(baseArr[i]);
            arr4[i] = new MyInteger2(baseArr[i]);
            
            list3.add(new MyInteger2(baseArr[i]));
            list4.add(new MyInteger2(baseArr[i]));
        }
        
        // lines below commented out: will cause an error if run
        //Arrays.sort(arr3);
        //Collections.sort(list3);
        
        // use custom comparator
        // (a, b) -> a.value - b.value means: given two objects a and b,
        // compare them using the function (a.value - b.value) (must return an integer)
        // a negative integer means a < b, positive means a > b, zero means a == b
        // as the result of the comparison
        Arrays.sort(arr3, (a, b) -> a.value - b.value);
        System.out.println("Array 3: " + Arrays.toString(arr3));
        
        Collections.sort(list3, (a, b) -> a.value - b.value);
        System.out.println("List 3: " + list3);
        
        // more verbose version of custom comparator
        // used for comparison methods that span multiple lines
        // also swapped the values around to sort in reverse order for this example
        Arrays.sort(arr4, (a, b) -> {
            int aValue = a.value;
            int bValue = b.value;
            return bValue - aValue;
        });
        System.out.println("Array 4: " + Arrays.toString(arr4));
        
        Collections.sort(list4, (a, b) -> {
            int aValue = a.value;
            int bValue = b.value;
            return bValue - aValue;
        });
        System.out.println("List 4: " + list4);
    }
}

// MyInteger1 implements the Comparable interface, so
// it can be directly used for sorting without using
// a Comparator
class MyInteger1 implements Comparable<MyInteger1> {
    int value;
    
    public MyInteger1(int value) {
        this.value = value;
    }
    
    // a negative result means this < other,
    // a positive result means this > other,
    // a result of zero means this == other
    // as the result of the comparison
    public int compareTo(MyInteger1 other) {
        return value - other.value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
}

// MyInteger2 does not implement the Comparable interface,
// so it requires a Comparator to sort
class MyInteger2 {
    int value;
    
    public MyInteger2(int value) {
        this.value = value;
    }
    
    public String toString() {
        return String.valueOf(value);
    }
}