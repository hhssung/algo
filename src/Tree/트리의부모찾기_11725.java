package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리의부모찾기_11725 {
	static ArrayList<ArrayList<Integer>> list;
	
	static class pair{
		int baby;
		int mother;
		public pair(int baby, int mother) {
			super();
			this.baby = baby;
			this.mother = mother;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] mother_node = new int[N+1];
		list = new ArrayList<ArrayList<Integer>> ();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		int first;
		int second;
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			first = Integer.parseInt(st.nextToken());
			second = Integer.parseInt(st.nextToken());
			//일단 양방향
			list.get(first).add(second);
			list.get(second).add(first);
		}

		//BFS init
		mother_node[1] = -1;	
		Queue<pair> q = new LinkedList<>();
		for(int i : list.get(1)) {
			q.add(new pair(i,1));
		}
		while(!q.isEmpty()) {
			int baby = q.peek().baby;
			int mother = q.poll().mother;
			mother_node[baby] = mother;
			for(int i : list.get(baby)) {
				if(i==mother) {	//부모노드 제외
					continue;
				}
				q.add(new pair(i,baby));
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i=2; i<=N; i++) {
			sb.append(mother_node[i]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
