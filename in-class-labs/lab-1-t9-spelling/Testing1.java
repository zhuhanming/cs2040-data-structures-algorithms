import java.util.Scanner;

public class Testing1{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int i, j, len, num = sc.nextInt();
    String input, result = "";
    char prev, c;

    sc.nextLine(); //Error due to Scanner leaving \n behind.
    for (i=0; i<num; i++){
      input = sc.nextLine();
      len = input.length();
      for (j=0; j<len;j++){
        c = input.charAt(j);
        prev = 'a';
        if (!result.equals("")) prev = result.charAt(result.length()-1);
        switch (c){
          case 'a':
            if (prev=='2') result+= " 2";
            else result+="2";
            break;
          case 'b':
            if (prev=='2') result+= " 22";
            else result+="22";
            break;
          case 'c':
            if (prev=='2') result+= " 222";
            else result+="222";
            break;
          case 'd':
            if (prev=='3') result+= " 3";
            else result+="3";
            break;
          case 'e':
            if (prev=='3') result+= " 33";
            else result+="33";
            break;
          case 'f':
            if (prev=='3') result+= " 333";
            else result+="333";
            break;
          case 'g':
            if (prev=='4') result+= " 4";
            else result+="4";
            break;
          case 'h':
            if (prev=='4') result+= " 44";
            else result+="44";
            break;
          case 'i':
            if (prev=='4') result+= " 444";
            else result+="444";
            break;
          case 'j':
            if (prev=='5') result+= " 5";
            else result+="5";
            break;
          case 'k':
            if (prev=='5') result+= " 55";
            else result+="55";
            break;
          case 'l':
            if (prev=='5') result+= " 555";
            else result+="555";
            break;
          case 'm':
            if (prev=='6') result+= " 6";
            else result+="6";
            break;
          case 'n':
            if (prev=='6') result+= " 66";
            else result+="66";
            break;
          case 'o':
            if (prev=='6') result+= " 666";
            else result+="666";
            break;
          case 'p':
            if (prev=='7') result+= " 7";
            else result+="7";
            break;
          case 'q':
            if (prev=='7') result+= " 77";
            else result+="77";
            break;
          case 'r':
            if (prev=='7') result+= " 777";
            else result+="777";
            break;
          case 's':
            if (prev=='7') result+= " 7777";
            else result+="7777";
            break;
          case 't':
            if (prev=='8') result+= " 8";
            else result+="8";
            break;
          case 'u':
            if (prev=='8') result+= " 88";
            else result+="88";
            break;
          case 'v':
            if (prev=='8') result+= " 888";
            else result+="888";
            break;
          case 'w':
            if (prev=='9') result+= " 9";
            else result+="9";
            break;
          case 'x':
            if (prev=='9') result+= " 99";
            else result+="99";
            break;
          case 'y':
            if (prev=='9') result+= " 999";
            else result+="999";
            break;
          case 'z':
            if (prev=='9') result+= " 9999";
            else result+="9999";
            break;
          case ' ':
            if (prev=='0') result+= " 0";
            else result+="0";
            break;
        }
      }
      System.out.println("Case #"+(i+1)+": " + result);
      result = "";
    }
    sc.close();
  }
}