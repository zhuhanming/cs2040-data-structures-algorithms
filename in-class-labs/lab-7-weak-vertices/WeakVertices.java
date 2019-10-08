
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class WeakVertices {
	public static void main(String[] args) {
		Kattio sc = new Kattio(System.in, System.out);
		int n;
		do {
			n = sc.getInt();
			if (n == -1) break;
			int[][] adjMat = new int[n][n];
			ArrayList<Integer> results = new ArrayList<Integer>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					adjMat[i][j] = sc.getInt();
				}
			}
			for (int a = 0; a < n; a++) {
				boolean stable = false;
				for (int b = 0; b < n; b++) {
					if (adjMat[a][b] == 1) {
						for (int c = 0; c < n; c++) {
							if (adjMat[b][c] == 1) {
								if (adjMat[c][a] == 1) {
									stable = true;
								}
							}
						}
					}
				}
				if (!stable) {
					results.add(a);
				}
			}
			for (int i = 0, x = results.size(); i < x; i++) {
				if (i != x - 1) {
					sc.print(results.get(i) + " ");
				} else {
					sc.println(results.get(i));
				}
			}
		} while (n != -1);
		sc.close();
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
