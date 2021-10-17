package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리순회_1991 {
	static class Node {
		char name;
		Node leftNode;
		Node rightNode;

		//처음 초기화
		public Node(char name) {
			this.name = name;
			this.leftNode = null;
			this.rightNode = null;
		}
		
		public Node(char name, Node leftNode, Node rightNode) {
			this(name);
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
		
		public void setChild(Node leftNode, Node rightNode) {
			this.leftNode = leftNode;
			this.rightNode = rightNode;
		}
	}

	static class Tree {
		Node[] nodes;
		
		// init
		public Tree(int N) {
			nodes = new Node[N];	//노드 초기화
			for(int i=0; i<N; i++){
				nodes[i] = new Node((char)(i+'A'));	//비어있는 노드 우선 삽임
			}
		}

		public void setNode(char me, char left, char right) {
			int me_idx = me-'A';
			Node left_node;
			Node right_node;
			if(left == '.') {
				left_node = null;
			}else {
				left_node = nodes[left-'A'];
			}
			if(right == '.') {
				right_node = null;
			}else {
				right_node = nodes[right-'A'];
			}
			
			nodes[me_idx].setChild(left_node, right_node);
		}
		
		StringBuffer sb = new StringBuffer();
		
		//전위
		public void preOrder(Node n) {
			if(n != null) {
				sb.append(n.name);
				preOrder(n.leftNode);
				preOrder(n.rightNode);
			}
		}
		
		//중위
		public void inOrder(Node n) {
			if(n != null) {
				inOrder(n.leftNode);
				sb.append(n.name);
				inOrder(n.rightNode);
			}
		}
		
		//후위
		public void postOrder(Node n) {
			if(n != null) {
				postOrder(n.leftNode);
				postOrder(n.rightNode);
				sb.append(n.name);
			}
		}
		
		//루트노드
		public Node getRoot() {
			return nodes[0];
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Tree tree = new Tree(N);
		
		for (int cnt = 0; cnt < N; cnt++) {
			st = new StringTokenizer(br.readLine());
			char me = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			tree.setNode(me, left, right);
		}

		tree.preOrder(tree.getRoot());
		tree.sb.append("\n");
		tree.inOrder(tree.getRoot());
		tree.sb.append("\n");
		tree.postOrder(tree.getRoot());
		System.out.println(tree.sb.toString());
	}
}
