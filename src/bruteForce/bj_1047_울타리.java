package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// (최대 x, 최소 x, 최대 y, 최소 y를 무조건 포함한 직사각형 모양의 울타리를 생각하기)

public class bj_1047_울타리 {
	static int answer;

	static class tree {
		int x;
		int y;
		int len;

		public tree(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}

		@Override
		public String toString() {
			return "tree [x=" + x + ", y=" + y + ", len=" + len + "]";
		}
	}

	// 전부 내림차순
	static class x_Comparator implements Comparator<tree> {
		@Override
		public int compare(tree o1, tree o2) {
			return o2.x - o1.x;
		}
	}

	static class y_Comparator implements Comparator<tree> {
		@Override
		public int compare(tree o1, tree o2) {
			return o2.y - o1.y;
		}
	}

	static class len_Comparator implements Comparator<tree> {
		@Override
		public int compare(tree o1, tree o2) {
			return o2.len - o1.len;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		answer = N - 1;

		ArrayList<tree> sortByXpos = new ArrayList<>();
		ArrayList<tree> sortByYpos = new ArrayList<>();
		int x, y, len;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			len = Integer.parseInt(st.nextToken());
			sortByXpos.add(new tree(x, y, len));
			sortByYpos.add(new tree(x, y, len));
		}

		x_Comparator xComparator = new x_Comparator();
		y_Comparator yComparator = new y_Comparator();
		Collections.sort(sortByXpos, xComparator);
		Collections.sort(sortByYpos, yComparator);

		Solution(sortByXpos, sortByYpos, N);

		System.out.println(answer);
	}

	private static void Solution(ArrayList<tree> sortByXpos, ArrayList<tree> sortByYpos, int N) {
		int xIdx_max, xIdx_min;
		int yIdx_max, yIdx_min;
		int x_max, x_min;
		int y_max, y_min;
		int fence_len;
		int felled_trees; // 벌목한 나무 개수
		ArrayList<tree> treesInside = new ArrayList<>();
		len_Comparator lenComparator = new len_Comparator();

		for (xIdx_max = 0; xIdx_max < N - 1; xIdx_max++) {
			for (xIdx_min = xIdx_max + 1; xIdx_min < N; xIdx_min++) {
				for (yIdx_max = 0; yIdx_max < N - 1; yIdx_max++) {
					for (yIdx_min = yIdx_max + 1; yIdx_min < N; yIdx_min++) {
						treesInside.clear();
						fence_len = 0;
						felled_trees = 0;
						x_max = sortByXpos.get(xIdx_max).x;
						x_min = sortByXpos.get(xIdx_min).x;
						y_max = sortByYpos.get(yIdx_max).y;
						y_min = sortByYpos.get(yIdx_min).y;
						// 유효성 검사. 불가능할 시 continue
						if (sortByYpos.get(yIdx_max).x < x_min || sortByYpos.get(yIdx_max).x > x_max) {
							continue;
						}
						if (sortByYpos.get(yIdx_min).x < x_min || sortByYpos.get(yIdx_min).x > x_max) {
							continue;
						}
						if (sortByXpos.get(xIdx_max).y < y_min || sortByXpos.get(xIdx_max).y > y_max) {
							continue;
						}
						if (sortByXpos.get(xIdx_min).y < y_min || sortByXpos.get(xIdx_min).y > y_max) {
							continue;
						}

						// 안에 있는 나무 구하기. 없을 경우 울타리 길이로 추가
						for (tree t : sortByXpos) {
							if (t.x < x_max && t.x > x_min && t.y < y_max && t.y > y_min) {
								treesInside.add(new tree(t.x, t.y, t.len));
								continue;
							}
							if (t.x != x_max && t.x != x_min && t.y != y_max && t.y != y_min) {
								fence_len += t.len;
								felled_trees++;
							}
						}
						calcTree(x_max, x_min, y_max, y_min, treesInside, fence_len, felled_trees, lenComparator);
					}
				}
			}
		}

	}

	private static void calcTree(int x_max, int x_min, int y_max, int y_min, ArrayList<tree> treesInside, int fence_len,
			int felled_trees, len_Comparator lenComparator) {
		int round = ((x_max - x_min) << 1) + ((y_max - y_min) << 1); // 둘레
		Collections.sort(treesInside, lenComparator);

//		System.out.println(round + " " + fence_len + " " + felled_trees);
//		System.out.print(x_max + " " + x_min + " " + y_max + " " + y_min + " ");
//		for (tree t : treesInside) {
//			System.out.print(t + " , ");
//		}
//		System.out.println();
//		System.out.println();

		for (tree t : treesInside) {
			// 울타리를 충분히 만들 수 있음
			if (round <= fence_len) {
				if (answer > felled_trees) {
					answer = felled_trees;
				}
				return;
			}
			// 울타리 길이 부족. 나무 베기
			felled_trees++;
			fence_len += t.len;
		}
		if (round <= fence_len) {
			if (answer > felled_trees) {
				answer = felled_trees;
			}
			return;
		}
	}
}
