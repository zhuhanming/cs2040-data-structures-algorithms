
/*
Name: Zhu Hanming
Matric No: A0196737L
Collaborators: None
*/

import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Islands {
	public static int rows;
	public static int cols;
	public static void main(String[] args) {
		Kattio sc = new Kattio(System.in, System.out);
		Islands.rows = sc.getInt();
		Islands.cols = sc.getInt();
		char[][] inputs = new char[rows][cols];
		for (int i=0;i<rows;i++){
			String word = sc.getWord();
			for (int x=0; x<cols; x++){
				inputs[i][x] = word.charAt(x);
			}
		}
		ArrayDeque<IntegerPair> arr = new ArrayDeque<IntegerPair>();
		int answer = 0;
		boolean[][] visited = new boolean[rows][cols];
		for (int i=0;i<rows;i++){
			for (int x=0; x<cols; x++){
				visited[i][x] = false;
			}
		}
		for (int i=0;i<rows;i++){
			for (int x=0; x<cols; x++){
				if (inputs[i][x]=='L'&&visited[i][x]==false){
					visited[i][x] = true;
					arr.offer(new IntegerPair(i, x));
					BFS(inputs, arr, visited);
					answer++;
				}
			}
		}
		sc.println(answer);
		sc.close();
	}

	public static void BFS(char[][] inputs, ArrayDeque<IntegerPair> arr, boolean[][] visited){
		while (!arr.isEmpty()){
			IntegerPair curr = arr.poll();
			int i = curr.row;
			int x = curr.col;
			if(i>0){
				if((inputs[i-1][x]=='L'||inputs[i-1][x]=='C')&&(visited[i-1][x]==false)){
					visited[i-1][x] = true;
					arr.offer(new IntegerPair(i-1, x));
				}
			}
			if (i<rows-1){
				if((inputs[i+1][x]=='L'||inputs[i+1][x]=='C')&&visited[i+1][x]==false){
					visited[i+1][x] = true;
					arr.offer(new IntegerPair(i+1, x));
				}
			}
			if(x>0){
				if((inputs[i][x-1]=='L'||inputs[i][x-1]=='C')&&visited[i][x-1]==false){
					visited[i][x-1] = true;
					arr.offer(new IntegerPair(i, x-1));
				}
			}
			if (x<cols-1){
				if((inputs[i][x+1]=='L'||inputs[i][x+1]=='C')&&visited[i][x+1]==false){
					visited[i][x+1] = true;
					arr.offer(new IntegerPair(i, x+1));
				}
			}
		}
	}
}

class IntegerPair{
	public int row;
	public int col;
	IntegerPair(int row, int col){
		this.row = row;
		this.col = col;
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
