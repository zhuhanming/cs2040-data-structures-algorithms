
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Amazing {
  public static Stack<String> stack = new Stack<String>();
  static boolean solved = false;
  static int[][] maze = new int[201][201];
  static Kattio sc = new Kattio(System.in, System.out);
  static int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

  // enum Action {
  // UP, DOWN, LEFT, RIGHT, NONE;
  // }

  public static void main(String[] args) {
    for (int i=0; i<201; i++){
      for (int x=0; x<201; x++){
        maze[i][x] = 0;
      }
    }
    DFS(100,100);
    if (!solved) {
      sc.println("no way out");
      sc.flush();
    }
    sc.close();
  }

  public static void DFS(int x, int y){
    maze[x][y] = 1;
    if(!solved&&x>0&&maze[x-1][y]!=1){
      sc.println("up");
      sc.flush();
      String result = sc.getWord();
      switch(result){
        case "wall":
          // maze[x-1][y] = 1;
          break;
        case "solved":
          solved = true;
          System.exit(0);
          break;
        case "ok":
          DFS(x-1, y);
          sc.println("down");
          sc.flush();
          if(sc.getWord().equals("wrong")) System.exit(0);
          break;
        case "wrong":
          System.exit(0);
          break;
      }
    }
    if(!solved&&y<200&&maze[x][y+1]!=1){
      sc.println("right");
      sc.flush();
      String result = sc.getWord();
      switch(result){
        case "wall":
          // maze[x][y+1] = 1;
          break;
        case "solved":
          solved = true;
          System.exit(0);
          break;
        case "ok":
          DFS(x, y+1);
          sc.println("left");
          sc.flush();
          if(sc.getWord().equals("wrong")) System.exit(0);
          break;
        case "wrong":
          System.exit(0);
          break;
      }
    }
    if(!solved&&y>0&&maze[x][y-1]!=1){
      sc.println("left");
      sc.flush();
      String result = sc.getWord();
      switch(result){
        case "wall":
          // maze[x][y-1] = 1;
          break;
        case "solved":
          solved = true;
          System.exit(0);
          break;
        case "ok":
          DFS(x, y-1);
          sc.println("right");
          sc.flush();
          if(sc.getWord().equals("wrong")) System.exit(0);
          break;
        case "wrong":
          System.exit(0);
          break;
      }
    }
    if(!solved&&x<200&&maze[x+1][y]!=1){
      sc.println("down");
      sc.flush();
      String result = sc.getWord();
      switch(result){
        case "wall":
          // maze[x+1][y] = 1;
          break;
        case "solved":
          solved = true;
          System.exit(0);
          break;
        case "ok":
          DFS(x+1, y);
          sc.println("up");
          sc.flush();
          if(sc.getWord().equals("wrong")) System.exit(0);
          break;
        case "wrong":
          System.exit(0);
          break;
      }
    }
  }

  // public static void DFS(Kattio sc) {

    // while (!stack.isEmpty()) {
    //   if (solved)
    //     break;
    //   String cmd = stack.pop();
    //   if (cmd.charAt(0) == 'X') {
    //     sc.println(cmd.substring(1, cmd.length()));
    //     sc.flush();
    //     sc.getWord();
    //     continue;
    //   } else {
    //     sc.println(cmd);
    //     sc.flush();
    //     String response = sc.getWord();
    //     switch (response) {
    //     case "ok":
    //       switch (cmd) {
    //       case "up":
    //         stack.push("Xdown");
    //         stack.push("right");
    //         stack.push("left");
    //         stack.push("up");
    //         break;
    //       case "down":
    //         stack.push("Xup");
    //         stack.push("right");
    //         stack.push("left");
    //         stack.push("down");
    //         break;
    //       case "right":
    //         stack.push("Xleft");
    //         stack.push("up");
    //         stack.push("down");
    //         stack.push("right");
    //         break;
    //       case "left":
    //         stack.push("Xright");
    //         stack.push("up");
    //         stack.push("down");
    //         stack.push("left");
    //         break;
    //       }
    //       break;
    //     case "solved":
    //       solved = true;
    //       break;
    //     case "wrong":
    //       System.exit(0);
    //       break;
    //     }
    //   }
    // }
  // }

  // public static void DFSup(Kattio sc) {
  // sc.println("up");
  // sc.flush();
  // String reply = sc.getWord();
  // switch (reply) {
  // case "ok":
  // DFS(sc);
  // break;
  // case "solved":
  // solved = true;
  // break;
  // case "wrong":
  // System.exit(0);
  // break;
  // }
  // }

  // public static void DFSdown(Kattio sc) {
  // sc.println("down");
  // sc.flush();
  // String reply = sc.getWord();
  // switch (reply) {
  // case "ok":
  // DFS(sc);
  // break;
  // case "solved":
  // solved = true;
  // break;
  // case "wrong":
  // System.exit(0);
  // break;
  // }
  // }

  // public static void DFSright(Kattio sc) {
  // sc.println("right");
  // sc.flush();
  // String reply = sc.getWord();
  // switch (reply) {
  // case "ok":
  // DFS(sc);
  // break;
  // case "solved":
  // solved = true;
  // break;
  // case "wrong":
  // System.exit(0);
  // break;
  // }
  // }

  // public static void DFSleft(Kattio sc) {
  // sc.println("left");
  // sc.flush();
  // String reply = sc.getWord();
  // switch (reply) {
  // case "ok":
  // DFS(sc);
  // break;
  // case "solved":
  // solved = true;
  // break;
  // case "wrong":
  // System.exit(0);
  // break;
  // }
  // }
}

class Kattio extends PrintWriter {
  public Kattio(InputStream i) {
    super(new BufferedOutputStream(System.out));
    r = new BufferedReader(new InputStreamReader(i));
  }

  public Kattio(InputStream i, OutputStream o) {
    super(new BufferedOutputStream(o));
    r = new BufferedReader(new InputStreamReader(i));
  }

  public boolean hasMoreTokens() {
    return peekToken() != null;
  }

  public int getInt() {
    return Integer.parseInt(nextToken());
  }

  public double getDouble() {
    return Double.parseDouble(nextToken());
  }

  public long getLong() {
    return Long.parseLong(nextToken());
  }

  public String getWord() {
    return nextToken();
  }

  private BufferedReader r;
  private String line;
  private StringTokenizer st;
  private String token;

  private String peekToken() {
    if (token == null)
      try {
        while (st == null || !st.hasMoreTokens()) {
          line = r.readLine();
          if (line == null)
            return null;
          st = new StringTokenizer(line);
        }
        token = st.nextToken();
      } catch (IOException e) {
      }
    return token;
  }

  private String nextToken() {
    String ans = peekToken();
    token = null;
    return ans;
  }
}
