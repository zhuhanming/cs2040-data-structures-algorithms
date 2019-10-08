
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.*;
import java.io.*;

public class Cannonball {
	public static final double INF = 1000000000;
	public static ArrayList<ArrayList<IntegerPair>> AdjList = new ArrayList<ArrayList<IntegerPair>>();
	public static ArrayList<Double> D = new ArrayList<Double>();
	public static ArrayList<Integer> p = new ArrayList<Integer>();
	public static int V;

	public static void initSSSP(int s) { // initialization phase
		D.clear();
		D.addAll(Collections.nCopies(V, INF)); // use 1B to represent INF
		p.clear();
		p.addAll(Collections.nCopies(V, -1)); // use -1 to represent NULL
		D.set(s, 0.0); // this is what we know so far
	}

	public static void relax(int u, int v, double w_u_v) {
		if (D.get(u) != INF && D.get(v) > D.get(u) + w_u_v) { // if SP can be shortened
			D.set(v, D.get(u) + w_u_v); // relax this edge
			p.set(v, u); // remember/update the predecessor
		}
	}

	public static void backtrack(int s, int u) {
		if (u == -1 || u == s) {
			System.out.printf("%d", u);
			return;
		}
		backtrack(s, p.get(u));
		System.out.printf(" %d", u);
	}

	public static double timeRun(Vertex a, Vertex b) {
		double dist = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
		return dist / 5;
	}

	public static double timeCannon(Vertex a, Vertex b) {
		double dist = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
		if (dist >= 50) {
			return (dist - 50) / 5 + 2;
		} else
			return Math.min((dist/5), (50-dist)/5 +2);
	}

	public static void main(String[] args) {

		Kattio sc = new Kattio(System.in, System.out);

		Vertex start = new Vertex(sc.getDouble(), sc.getDouble());
		Vertex end = new Vertex(sc.getDouble(), sc.getDouble());
		int n = sc.getInt();
		V = n + 2;
		AdjList.clear();
		for (int i = 0; i < n + 2; i++) {
			ArrayList<IntegerPair> Neighbor = new ArrayList<IntegerPair>();
			AdjList.add(Neighbor); // add neighbor list to Adjacency List
		}

		ArrayList<Vertex> cannons = new ArrayList<Vertex>();
		for (int i = 0; i < n; i++) {
			cannons.add(new Vertex(sc.getDouble(), sc.getDouble()));
		}
		ArrayList<IntegerPair> test = AdjList.get(0);
		for (int i = 0; i < n; i++) {
			test.add(new IntegerPair(timeRun(start, cannons.get(i)), i + 1));
		}
		test.add(new IntegerPair(timeRun(start, end), n + 1));

		for (int i = 1; i < n + 1; i++) {
			ArrayList<IntegerPair> temp = AdjList.get(i);
			Vertex curr = cannons.get(i - 1);
			for (int x = 0; x < n; x++) {
				temp.add(new IntegerPair(timeCannon(curr, cannons.get(x)), x + 1));
			}
			temp.add(new IntegerPair(timeCannon(curr, end), n+1));
		}

		initSSSP(0);

		// Bellman Ford's routine, implemented using AdjList (note that you can choose
		// to use EdgeList -- similar performance)
		for (int i = 0; i < V - 1; i++) // relax all E edges V-1 times, O(V)
			for (int u = 0; u < V; u++) // these two loops = O(E)
				for (int j = 0; j < AdjList.get(u).size(); j++) {
					IntegerPair v = AdjList.get(u).get(j);
					relax(u, v.second(), v.first());
				}
		sc.println(D.get(n + 1));
		sc.close();
	}
}

class Vertex {
	Double x, y;

	public Vertex(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	Double _first;
	Integer _second;

	public IntegerPair(Double f, Integer s) {
		_first = f;
		_second = s;
	}

	public int compareTo(IntegerPair o) {
		return this.first().compareTo(o.first());
	}

	Double first() {
		return _first;
	}

	Integer second() {
		return _second;
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
