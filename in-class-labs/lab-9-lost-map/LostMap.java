
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.*;
import java.io.*;

public class LostMap {
	public static ArrayList<ArrayList<IntegerPair>> AdjList;
	public static ArrayList<Boolean> taken;
	public static PriorityQueue<IntegerPair> pq;

	public static void main(String[] args) {
		Kattio sc = new Kattio(System.in, System.out);
		int vill = sc.getInt();
		AdjList = new ArrayList<ArrayList<IntegerPair>>();
		for (int i = 0; i < vill; i++) {
			ArrayList<IntegerPair> Neighbor = new ArrayList<IntegerPair>();
			AdjList.add(Neighbor);
		}
		for (int i = 0; i < vill; i++) {
			for (int j = 0; j < vill; j++) {
				AdjList.get(i).add(new IntegerPair(i, j, sc.getInt()));
			}
		}

		taken = new ArrayList<Boolean>();
		taken.addAll(Collections.nCopies(vill, false));
		pq = new PriorityQueue<IntegerPair>();
		process(0);

		while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
			IntegerPair front = pq.poll();
			if (!taken.get(front.second())) { // we have not connected this vertex yet
				sc.println((front.first() + 1) + " " + (front.second() + 1));
				process(front.second());
			}
		}
		sc.close();
	}

	public static void process(int vtx) {
		taken.set(vtx, true);
		for (int j = 0; j < AdjList.get(vtx).size(); j++) {
			IntegerPair v = AdjList.get(vtx).get(j);
			if (!taken.get(v.second())) {
				pq.offer(v);
			}
		}
	}
}

class IntegerPair implements Comparable<IntegerPair> {
	public Integer _first, _second, _third;

	public IntegerPair(Integer f, Integer s, Integer p) {
		_first = f;
		_second = s;
		_third = p;
	}

	public int compareTo(IntegerPair o) {
		if (!this.third().equals(o.third()))
			return this.third() - o.third();
		else if (!this.first().equals(o.first()))
			return this.first() - o.first();
		else
			return this.second() - o.second();
	}

	Integer first() {
		return _first;
	}

	Integer second() {
		return _second;
	}

	Integer third() {
		return _third;
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