package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2_17472 {
	static int[][] map;
	static int N;
	static int M;
	static int count = 1; // 섬의 개수는 count-1 개, 6개보다 작음, 섬 번호는 2부터 시작 ( ex. 2,3,4 )
	static int[][] graph;
	private final static int INF = 9999999;

	// 섬 만들기. DFS
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	private static void makeIsland(int num_island, int y, int x) {
		map[y][x] = num_island;
		int newY;
		int newX;
		for (int i = 0; i < 4; i++) {
			newY = y + dy[i];
			newX = x + dx[i];
			if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
				continue;
			}
			if (map[newY][newX] == 1) {
				makeIsland(num_island, newY, newX);
			}
		}
	}

	// 섬 파악하기
	private static void makeMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = count + 1;
					makeIsland(count + 1, i, j);
					count++;
				}
			}
		}
	}

	// 그래프에 다리 정보 추가
	private static void addGraph(int y, int x) {
		int newY;
		int newX;
		for (int i = 0; i < 4; i++) {
			newY = y;
			newX = x;
			int bridge_len = -1;
			boolean isFind = false;
			while (true) {
				newY += dy[i];
				newX += dx[i];
				bridge_len++;
				if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
					break;
				}
				// 같은 땅일 경우 다리 불가능
				if (map[newY][newX] == map[y][x]) {
					break;
				}
				// 땅 찾음
				if (map[newY][newX] != 0) {
					// 찾았으나 다리 길이 2 미만
					if (bridge_len < 2) {
						break;
					}
					isFind = true;
					break;
				}
			}
			if (isFind) {
				int src = map[y][x];
				int des = map[newY][newX];
				// 더 짧은 거리가 있을 경우 update
				if (graph[src - 2][des - 2] > bridge_len) {
					graph[src - 2][des - 2] = bridge_len;
					graph[des - 2][src - 2] = bridge_len;
				}
			}
		}
	}

	// 다리 찾기
	private static void findBridge() {
		graph = new int[count - 1][count - 1];
		for (int i = 0; i < count - 1; i++) {
			for (int j = 0; j < count - 1; j++) {
				graph[i][j] = INF;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					addGraph(i, j);
				}
			}
		}
	}

	static int minSum;

	private static void findMinimumBridgeSum() {
		minSum = 0;

		// 프림. MST
		boolean[] visited = new boolean[count - 1];
		visited[0] = true;

		// 노드의 수-1만큼 반복
		int node;
		int weight;
		for (int k = 0; k < count - 2; k++) {
			// 방문한 정점들을 기준으로 최솟값 찾기
			node = -1;
			weight = Integer.MAX_VALUE;
			// i: 시작 노드
			for (int i = 0; i < count - 1; i++) {
				if (visited[i]) {
					// j: 도착 노드
					for (int j = 0; j < count - 1; j++) {
						if (i == j) {// 시작과 도착이 같을 수 없음
							continue;
						}
						if (graph[i][j] != INF && !visited[j]) {
							// 더 짧을 경우 갱신
							if (weight > graph[i][j]) {
								node = j;
								weight = graph[i][j];
							}
						}
					}
				}
			}
			if (node != -1) {
				minSum += weight;
				visited[node] = true;
			} else {
				minSum = -1; // 방문 못하는 노드가 있음
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solve
		makeMap();
		findBridge();
		findMinimumBridgeSum();
		System.out.println(minSum);

//		printAll(graph);
//		printAll(map);
//		System.out.println(count);
	}

	private static void printAll(int[][] arr) {
		for (int[] y : arr) {
			for (int x : y) {
				if (x == INF) {
					System.out.print(0);
				} else {
					System.out.print(x);
				}
			}
			System.out.println();
		}
	}
}
