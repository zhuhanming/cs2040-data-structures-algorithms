
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/
import java.util.*;
import java.io.*;

public class IslandHopping {
  public static void main(String[] args) {
    Kattio sc = new Kattio(System.in, System.out);
    int testCases = sc.getInt();
    for (int i = 0; i < testCases; i++) {
      int lines = sc.getInt();
      ArrayList<DoublePair> coords = new ArrayList<DoublePair>();
      for (int x = 0; x < lines; x++) {
        coords.add(new DoublePair(sc.getDouble(), sc.getDouble()));
      }
      ArrayList<IntegerTriple> EdgeList = new ArrayList<IntegerTriple>();
      for (int x = 0; x < lines; x++) {
        for (int j = x + 1; j < lines; j++) {
          EdgeList.add(new IntegerTriple(distance(coords.get(x), coords.get(j)), x, j));
        }
      }
      Collections.sort(EdgeList);

      UnionFind UF = new UnionFind(lines); // all V are disjoint sets at the beginning
      double mst_cost = 0;
      for (int x = 0, n = EdgeList.size(); x<n; x++) { // process all edges, O(E)
        IntegerTriple e = EdgeList.get(x);
        int u = e.second(), v = e.third();
        double w = e.first(); // note that we have re-ordered the integer triple
        if (!UF.isSameSet(u, v)) { // if no cycle
          mst_cost += w; // add weight w of e to MST
          UF.unionSet(u, v); // link these two vertices
        }
      }
      sc.printf("%.3f\n",mst_cost);
    }
    sc.close();
  }

  public static double distance(DoublePair a, DoublePair b){
    return Math.sqrt(((a.x-b.x)*(a.x-b.x))+((a.y-b.y)*(a.y-b.y)));
  }
}

class DoublePair {
  public double x;
  public double y;

  public DoublePair(double x, double y) {
    this.x = x;
    this.y = y;
  }
}


class IntegerTriple implements Comparable<IntegerTriple> {
  public Integer _second, _third;
  public Double _first;

  public IntegerTriple(Double f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first().compareTo(o.first());
    else if (!this.second().equals(o.second()))
      return this.second() - o.second();
    else
      return this.third() - o.third();
  }

  Double first() {
    return _first;
  }

  Integer second() {
    return _second;
  }

  Integer third() {
    return _third;
  }

  public String toString() {
    return first() + " " + second() + " " + third();
  }
}

// Union-Find Disjoint Sets Library, using both path compression and union by
// rank heuristics
class UnionFind {
  public ArrayList<Integer> p, rank, setSize;
  public int numSets;

  public UnionFind(int N) {
    p = new ArrayList<Integer>(N);
    rank = new ArrayList<Integer>(N);
    setSize = new ArrayList<Integer>(N);
    numSets = N;
    for (int i = 0; i < N; i++) {
      p.add(i);
      rank.add(0);
      setSize.add(1);
    }
  }

  public int findSet(int i) {
    if (p.get(i) == i)
      return i;
    else {
      int ret = findSet(p.get(i));
      p.set(i, ret);
      return ret;
    }
  }

  public Boolean isSameSet(int i, int j) {
    return findSet(i) == findSet(j);
  }

  public void unionSet(int i, int j) {
    if (!isSameSet(i, j)) {
      numSets--;
      int x = findSet(i), y = findSet(j);
      // rank is used to keep the tree short
      if (rank.get(x) > rank.get(y)) {
        p.set(y, x);
        setSize.set(x, setSize.get(x) + setSize.get(y));
      } else {
        p.set(x, y);
        setSize.set(y, setSize.get(y) + setSize.get(x));
        if (rank.get(x) == rank.get(y))
          rank.set(y, rank.get(y) + 1);
      }
    }
  }

  public int numDisjointSets() {
    return numSets;
  }

  public int sizeOfSet(int i) {
    return setSize.get(findSet(i));
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
