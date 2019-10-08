import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Galactic {
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
    score.insert(fave);

    for (int i = 0; i < numEvents; i++) {
      int teamNo = sc.getInt();
      int penalty = sc.getInt();
      Team curr = teams.get(teamNo - 1);
      try {
        score.delete(curr);
        curr.addPenalty(penalty);
        score.insert(curr);
        score.inorder();
        sc.println(score.rank(fave, score.root));
      } catch (Exception e) {
        curr.addPenalty(penalty);
        score.insert(curr);
        score.inorder();
        sc.println(score.rank(fave, score.root));
      }
    }
    sc.close();
  }
}

class AVLVertex<T extends Comparable<T>> {
  AVLVertex(T v) {
    key = v;
    parent = left = right = null;
    height = 0;
    size = 1;
  }

  // all these attributes remain public to slightly simplify the code
  public AVLVertex<T> parent, left, right;
  public T key;
  public int height; // will be used in lecture on AVL
  public int size;
}

// This is just a sample implementation
// There are other ways to implement AVL concepts...
class AVL<T extends Comparable<T>> {
  public AVLVertex<T> root;

  public AVL() {
    root = null;
  }

  public T search(T v) {
    AVLVertex<T> res = search(root, v);
    return res == null ? null : res.key;
  }

  // helper method to perform search
  public AVLVertex<T> search(AVLVertex<T> T, T v) {
    if (T == null)
      return null; // not found
    else if (T.key == v)
      return T; // found
    else if (T.key.compareTo(v) < 0)
      return search(T.right, v); // search to the right
    else
      return search(T.left, v); // search to the left
  }

  // public method called to find Minimum key value in AVL
  public T findMin() {
    return findMin(root);
  }

  // helper method to perform findMin
  // Question: What happens if AVL is empty?
  public T findMin(AVLVertex<T> X) {
    if (X.left == null)
      return X.key; // this is the min
    else
      return findMin(X.left); // go to the left
  }

  // public method called to find Maximum key value in AVL
  public T findMax() {
    return findMax(root);
  }

  // helper method to perform findMax
  // Question: Again, what happens if AVL is empty?
  public T findMax(AVLVertex<T> X) {
    if (X.right == null)
      return X.key; // this is the max
    else
      return findMax(X.right); // go to the right
  }

  // public method to find successor to given value v in AVL.
  public T successor(T v) {
    AVLVertex<T> vPos = search(root, v);
    return vPos == null ? null : successor(vPos);
  }

  // helper recursive method to find successor to for a given vertex T in AVL
  public T successor(AVLVertex<T> T) {
    if (T.right != null) // this subtree has right subtree
      return findMin(T.right); // the successor is the minimum of right subtree
    else {
      AVLVertex<T> par = T.parent;
      AVLVertex<T> cur = T;
      // if par(ent) is not root and cur(rent) is its right children
      while ((par != null) && (cur == par.right)) {
        cur = par; // continue moving up
        par = cur.parent;
      }
      return par == null ? null : par.key; // this is the successor of T
    }
  }

  // public method to find predecessor to given value v in AVL
  public T predecessor(T v) {
    AVLVertex<T> vPos = search(root, v);
    return vPos == null ? null : predecessor(vPos);
  }

  // helper recursive method to find predecessor to for a given vertex T in AVL
  public T predecessor(AVLVertex<T> T) {
    if (T.left != null) // this subtree has left subtree
      return findMax(T.left); // the predecessor is the maximum of left subtree
    else {
      AVLVertex<T> par = T.parent;
      AVLVertex<T> cur = T;
      // if par(ent) is not root and cur(rent) is its left children
      while ((par != null) && (cur == par.left)) {
        cur = par; // continue moving up
        par = cur.parent;
      }
      return par == null ? null : par.key; // this is the successor of T
    }
  }

  // public method called to perform inorder traversal
  public void inorder() {
    inorder(root);
    System.out.println();
  }

  // helper method to perform inorder traversal
  public void inorder(AVLVertex<T> T) {
    if (T == null)
      return;
    inorder(T.left); // recursively go to the left
    System.out.print(T.key + " "); // visit this AVL node
    inorder(T.right); // recursively go to the right
  }

  // public method called to insert a new key with value v into AVL
  public void insert(T v) {
    root = insert(root, v);
  }

  // helper recursive method to perform insertion of new vertex into AVL
  public AVLVertex<T> insert(AVLVertex<T> T, T v) {
    if (T == null)
      return new AVLVertex<T>(v); // insertion point is found
    if (T.key.compareTo(v) < 0) { // search to the right
      T.right = insert(T.right, v);
      T.right.parent = T;
    } else { // search to the left
      T.left = insert(T.left, v);
      T.left.parent = T;
    }

    if (T.left == null && T.right!=null) {
      T.size = T.right.size + 1;
      T.height = T.right.height + 1;
    } else if (T.right == null&&T.left!=null) {
      T.size = T.left.size + 1;
      T.height = T.left.height + 1;
    } else {
      T.size = T.left.size + T.right.size + 1;
      T.height = Math.max(T.right.height, T.left.height) + 1;
    }
    T = balance(T);
    return T; // return the updated AVL
  }

  // public method to delete a vertex containing key with value v from AVL
  public void delete(T v) {
    root = delete(root, v);
  }

  // helper recursive method to perform deletion
  public AVLVertex<T> delete(AVLVertex<T> T, T v) {
    if (T == null)
      return T; // cannot find the item to be deleted
    if (T.key.compareTo(v) < 0) // search to the right
      T.right = delete(T.right, v);
    else if (T.key.compareTo(v) > 0) // search to the left
      T.left = delete(T.left, v);
    else { // this is the node to be deleted
      if (T.left == null && T.right == null) // this is a leaf
        T = null; // simply erase this node
      else if (T.left == null && T.right != null) { // only one child at right
        T.right.parent = T.parent;
        T = T.right; // bypass T
      } else if (T.left != null && T.right == null) { // only one child at left
        T.left.parent = T.parent;
        T = T.left; // bypass T
      } else { // has two children, find successor
        T successorV = successor(v);
        T.key = successorV; // replace this key with the successor's key
        T.right = delete(T.right, successorV); // delete the old successorV
      }
    }
    if (T != null) {
      if (T.left == null && T.right!=null) {
        T.size = T.right.size + 1;
        T.height = T.right.height + 1;
      } else if (T.right == null&&T.left!=null) {
        T.size = T.left.size + 1;
        T.height = T.left.height + 1;
      } else {
        T.size = T.left.size + T.right.size + 1;
        T.height = Math.max(T.right.height, T.left.height) + 1;
      }
      T = balance(T);
    }
    return T; // return the updated AVL
  }

  private AVLVertex<T> balance(AVLVertex<T> T) {
    int diff;
    if (T.left != null && T.right != null) {
      diff = T.left.height - T.right.height;
    } else if (T.left == null && T.right != null) {
      diff = 0 - T.right.height;
    } else if (T.left != null && T.right == null) {
      diff = T.left.height;
    } else {
      diff = 0;
    }
    if (diff >= 2) {
      int diff2;
      if (T.left.left != null && T.left.right != null) {
        diff2 = T.left.left.height - T.left.right.height;
      } else if (T.left.left == null && T.left.right != null) {
        diff2 = 0 - T.left.right.height;
      } else if (T.left.left != null && T.left.right == null) {
        diff2 = T.left.left.height;
      } else {
        diff2 = 0;
      }
      if (diff2 >= 0) {
        AVLVertex<T> result = rotateRight(T);
        if (result.left == null) {
          result.size = result.right.size + 1;
          result.height = result.right.height + 1;
        } else if (result.right == null) {
          result.size = result.left.size + 1;
          result.height = result.left.height + 1;
        } else {
          result.size = result.left.size + result.right.size + 1;
          result.height = Math.max(result.right.height, result.left.height) + 1;
        }
        return result;
      } else {
        T.left = rotateLeft(T.left);
        AVLVertex<T> result = rotateRight(T);
        if (result.left == null) {
          result.size = result.right.size + 1;
          result.height = result.right.height + 1;
        } else if (result.right == null) {
          result.size = result.left.size + 1;
          result.height = result.left.height + 1;
        } else {
          result.size = result.left.size + result.right.size + 1;
          result.height = Math.max(result.right.height, result.left.height) + 1;
        }
        return result;
      }
    } else if (diff <= -2) {
      int diff2;
      if (T.right.left != null && T.right.right != null) {
        diff2 = T.right.left.height - T.right.right.height;
      } else if (T.right.left == null && T.right.right != null) {
        diff2 = 0 - T.right.right.height;
      } else if (T.right.left != null && T.right.right == null) {
        diff2 = T.right.left.height;
      } else {
        diff2 = 0;
      }
      if (diff2 <= 0) {
        AVLVertex<T> result = rotateLeft(T);
        if (result.left == null) {
          result.size = result.right.size + 1;
          result.height = result.right.height + 1;
        } else if (result.right == null) {
          result.size = result.left.size + 1;
          result.height = result.left.height + 1;
        } else {
          result.size = result.left.size + result.right.size + 1;
          result.height = Math.max(result.right.height, result.left.height) + 1;
        }
        return result;
      } else {
        T.right = rotateRight(T.right);
        AVLVertex<T> result = rotateLeft(T);
        if (result.left == null) {
          result.size = result.right.size + 1;
          result.height = result.right.height + 1;
        } else if (result.right == null) {
          result.size = result.left.size + 1;
          result.height = result.left.height + 1;
        } else {
          result.size = result.left.size + result.right.size + 1;
          result.height = Math.max(result.right.height, result.left.height) + 1;
        }
        return result;
      }
    } else
      return T;
  }

  private AVLVertex<T> rotateRight(AVLVertex<T> T) {
    AVLVertex<T> moving = T.left.right;
    T.left.parent = T.parent;
    T.left.right = T;
    T.parent = T.left;
    T.left = moving;
    if (T.left == null && T.right!=null) {
      T.size = T.right.size + 1;
      T.height = T.right.height + 1;
    } else if (T.right == null&&T.left!=null) {
      T.size = T.left.size + 1;
      T.height = T.left.height + 1;
    } else {
      T.size = T.left.size + T.right.size + 1;
      T.height = Math.max(T.right.height, T.left.height) + 1;
    }
    return T.parent;
  }

  private AVLVertex<T> rotateLeft(AVLVertex<T> T) {
    AVLVertex<T> moving = T.right.left;
    T.right.parent = T.parent;
    T.right.left = T;
    T.parent = T.right;
    T.right = moving;
    if (T.left == null && T.right!=null) {
      T.size = T.right.size + 1;
      T.height = T.right.height + 1;
    } else if (T.right == null&&T.left!=null) {
      T.size = T.left.size + 1;
      T.height = T.left.height + 1;
    } else {
      T.size = T.left.size + T.right.size + 1;
      T.height = Math.max(T.right.height, T.left.height) + 1;
    }
    return T.parent;
  }

  public int rank(T T, AVLVertex<T> curr) {
    if (curr.key.compareTo(T) == 0) {
      if (curr.left != null) {
        return curr.left.size + 1;
      } else {
        return 1;
      }
    } else if (curr.key.compareTo(T) < 0) {
      if (curr.left != null) {
        return curr.left.size + 1 + rank(T, curr.right);
      } else {
        return 1 + rank(T, curr.right);
      }
    } else {
      return rank(T, curr.left);
    }
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
      return o.teamNo - this.teamNo;
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