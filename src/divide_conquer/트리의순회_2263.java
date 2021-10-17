package divide_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 트리의순회_2263 {

	static int[] left; // 노드의 왼쪽을 저장하는 배열
	static int[] right; // 노드의 오른쪽을 저장하는 배열
	static int[] inorder;
	static int[] inorder_idx; // inorder 배열의 각각의 idx를 저장하는 배열
	static int[] postorder;

	// postorder의 맨 왼쪽 : 항상 inorder의 root. 분할정복
	// [p_left, p_right], [i_left, i_right]
	private static int traversal(int p_left, int p_right, int i_left, int i_right) {
		int root = postorder[p_right];
		int ltree_size = inorder_idx[root] - i_left; // 왼쪽 트리의 개수

		if (p_left == p_right) {
			return root;
		}

		// 왼쪽 트리
		if (ltree_size != 0) {
			left[root] = traversal(p_left, p_left + ltree_size - 1, i_left, i_left + ltree_size - 1);
		}
		// 오른쪽 트리
		if (i_right - i_left - ltree_size > 0) {
			right[root] = traversal(p_left + ltree_size, p_right - 1, i_left + ltree_size + 1, i_right);
		}

		return root;
	}

	static StringBuffer sb = new StringBuffer();

	private static void makePreOrder(int root) {
		sb.append(root).append(" ");
		if (left[root] != 0)
			makePreOrder(left[root]);
		if (right[root] != 0)
			makePreOrder(right[root]);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		left = new int[n + 1];
		right = new int[n + 1];
		inorder = new int[n + 1];
		inorder_idx = new int[n + 1];
		postorder = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
			inorder_idx[inorder[i]] = i;
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}

		traversal(0, n - 1, 0, n - 1);

		makePreOrder(postorder[n - 1]);
		System.out.println(sb.toString());

//		for (int i = 1; i <= n; i++) {
//			System.out.printf("%d : (%d , %d)\n", i, left[i], right[i]);
//		}
	}
}
