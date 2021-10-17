package Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 나무재테크_16235 {
	private static class tree implements Comparable<tree> {
		int y;
		int x;
		int age;

		public tree(int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}

		@Override
		public int compareTo(tree o) {
			if (this.y != o.y) {
				return this.y - o.y;
			}
			if (this.x != o.x) {
				return this.x - o.x;
			}
			if (this.age != o.age) {
				return this.age - o.age;
			}
			return 0;
		}

		@Override
		public String toString() {
			return "( " + y + " , " + x + " ) " + age;
		}
	}

	private static ArrayList<tree> springAndSummer(int[][] field, ArrayList<tree> trees) {
		// spring. 나이 먹음. 나무 죽을수도 있음
		Collections.sort(trees);
		ArrayList<tree> new_trees = new ArrayList<>();
		ArrayList<tree> dead_trees = new ArrayList<>();
		for (tree t : trees) {
			if (field[t.y][t.x] < t.age) {
				dead_trees.add(t);
			} else {
				field[t.y][t.x] -= t.age;
				t.age++;
				new_trees.add(t);
			}
		}

		// summer. 죽은 나무 양분으로
		for (tree t : dead_trees) {
			field[t.y][t.x] += (t.age / 2);
		}
		
		return new_trees;
	}

	private static void fall(ArrayList<tree> trees, int N) {
		int len = trees.size();

		int tmpY, tmpX;
		int[] dy = { 1, 1, 1, 0, 0, -1, -1, -1 };
		int[] dx = { 1, 0, -1, 1, -1, 1, 0, -1 };
		// 5의 배수 나무일 경우 주변에 나이 1인 나무 생기게 하기
		for (int i = 0; i < len; i++) {
			tree t = trees.get(i);
			if (t.age % 5 == 0) {
				for(int j=0; j<8; j++) {
					tmpY = t.y + dy[j];
					tmpX = t.x + dx[j];
					if (isValid(tmpY, tmpX, N)) {
						trees.add(new tree(tmpY, tmpX, 1));
					}
				}
			}
		}
	}

	private static boolean isValid(int y, int x, int N) {
		if (y < 1 || x < 1 || y > N || x > N) {
			return false;
		}
		return true;
	}

	private static void winter(int[][] field, int[][] tree_food, int N) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				field[i][j] += tree_food[i][j];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] tree_food = new int[N + 1][N + 1]; // 비료
		int[][] field = new int[N + 1][N + 1]; // 밭의 영양분 상태

		// init
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				field[i][j] = 5;
			}
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				tree_food[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<tree> trees = new ArrayList<>();
		int x, y, z;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			trees.add(new tree(x, y, z));	// x,y 조심
		}

		// k년
		for (int i = 1; i <= K; i++) {
			// simulation
			trees = springAndSummer(field, trees);
			fall(trees, N);
			winter(field, tree_food, N);
		}
		
		System.out.println(trees.size());
	}
}
