import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Galactic2{
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int numTeams = sc.getInt();
    int numEvents = sc.getInt();

    ArrayList<Team> teams = new ArrayList<Team>();
    for (int i = 1; i <= numTeams; i++) {
      teams.add(new Team(i, 0, 0));
    }
    AVL<Team> score = new AVL<>();
    Team fave = teams.get(0);
    score.put(fave);

    for (int i = 0; i < numEvents; i++) {
      int teamNo = sc.getInt();
      int penalty = sc.getInt();
      Team curr = teams.get(teamNo - 1);
      if (score.contains(curr)) score.delete(curr);
      curr.addPenalty(penalty);
      score.put(curr);
      sc.println(score.rank(fave));
    }
    sc.close();
  }
}

class AVL<K extends Comparable<K>>{
  private Node root;

  private class Node{
    private final K key;
    private int height;
    private int size;
    private Node left;
    private Node right;
    // private Node parent;

    public Node(K key, int height, int size){
      this.key = key;
      this.size = size;
      this.height = height;
    }
  }

  public AVL(){
  }

  public boolean isEmpty(){
    return root==null;
  }

  public int size(){
    return size(root);
  }

  private int size(Node x){
    if (x==null) return 0;
    return x.size;
  }

  public int height(){
    return height(root);
  }

  private int height(Node x){
    if (x==null) return -1;
    return x.height;
  }

  private K get(K key){
    if (key == null) throw new IllegalArgumentException("argument to get() is null");
    Node x = get(root, key);
    if (x == null) return null;
    return x.key;
  }

  private Node get(Node x, K key){
    if (x==null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp<0) return get(x.left, key);
    else if (cmp>0) return get(x.right, key);
    else return x;
  }

  public boolean contains(K key){
    return get(key) != null;
  }

  public void put(K key){
    root = put(root, key);
  }

  private Node put(Node x, K key){
    if (x==null) return new Node(key, 0, 1);
    int cmp = key.compareTo(x.key);
    if (cmp<0){
      x.left = put(x.left, key);
    }else if (cmp>0){
      x.right = put(x.right, key);
    }else{
      return x;
    }
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node balance(Node x){
    if (balanceFactor(x)<-1){
      if(balanceFactor(x.right)>0){
        x.right = rotateRight(x.right);
      }
      x = rotateLeft(x);
    }else if (balanceFactor(x)>1){
      if (balanceFactor(x.left)<0){
        x.left = rotateLeft(x.left);
      }
      x = rotateRight(x);
    }
    return x;
  }

  private int balanceFactor(Node x){
    return height(x.left) - height(x.right);
  }

  private Node rotateRight(Node x){
    Node y = x.left;
    x.left = y.right;
    y.right = x;
    y.size = x.size;
    x.size = 1 + size(x.left)+ size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y;
  }

  private Node rotateLeft(Node x){
    Node y = x.right;
    x.right = y.left;
    y.left = x;
    y.size = x.size;
    x.size = 1 + size(x.left)+ size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y;
  }

  public void delete(K key){
    if (!contains(key)) return;
    root = delete(root, key);
  }

  private Node delete(Node x, K key){
    int cmp = key.compareTo(x.key);
    if (cmp<0){
      x.left = delete(x.left, key);
    }else if (cmp>0){
      x.right = delete(x.right, key);
    }else{
      if (x.left==null){
        return x.right;
      }else if  (x.right==null){
        return x.left;
      }else{
        Node y = x;
        x = min(y.right);
        x.right = deleteMin(y.right);
        x.left = y.left;
      }
    }
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private void deleteMin(){
    if (isEmpty()) throw new NoSuchElementException();
    root = deleteMin(root);
  }

  private Node deleteMin(Node x){
    if (x.left==null) return x.right;
    x.left = deleteMin(x.left);
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node min(Node x){
    if (x.left ==null) return x;
    return min(x.left); 
  }

  public int rank(K key){
    return rank(key, root);
  }

  private int rank(K key, Node x){
    if (x==null) return 0;
    int cmp = key.compareTo(x.key);
    if (cmp<0) return rank(key, x.left);
    else if (cmp>0) return 1 + size(x.left) + rank(key, x.right);
    else return size(x.left)+1;
  }
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

class Team implements Comparable<Team> {
  public int teamNo;
  public int correct;
  public int penalty;

  @Override
  public int compareTo(Team o) {
    if (this.correct != o.correct) {
      return o.correct - this.correct;
    } else if (this.penalty != o.penalty) {
      return this.penalty - o.penalty;
    } else
      return this.teamNo-o.teamNo;
  }

  public Team(int teamNo, int correct, int penalty) {
    this.teamNo = teamNo;
    this.correct = correct;
    this.penalty = penalty;
  }

  public void addPenalty(int penalty) {
    this.correct++;
    this.penalty += penalty;
  }

  public String toString(){
    String result = this.teamNo + "";
    return result;
  }
}