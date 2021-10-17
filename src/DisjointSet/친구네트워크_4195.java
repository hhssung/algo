package DisjointSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 친구네트워크_4195 {
	static class DisjointSet{
		HashMap<String, Integer> map;
		ArrayList<Integer> rep;	//대표자 저장해놓은 리스트
		ArrayList<Integer> sum;	//친구수 저장해놓은 리스트 - 대표자냐 아니냐에 따라 갱신 안될수도 있음
		int idx = 0;
		
		public DisjointSet() {
			map = new HashMap<>();
			rep  =new ArrayList<>();
			sum = new ArrayList<>();
		}
		
		public int union(String a, String b) {
			if(!map.containsKey(a)) {
				map.put(a, idx);
				rep.add(idx);
				sum.add(1);	//처음엔 친구 한명
				idx++;
			}
			if(!map.containsKey(b)) {
				map.put(b,idx);
				rep.add(idx);
				sum.add(1);
				idx++;
			}
			int a_num = map.get(a);
			int b_num = map.get(b);
			int a_rep = getRep(a_num);	//대표자들 가져오기
			int b_rep = getRep(b_num);
			
			// 틀린 부분 1)      합 갱신. 이미 같은 집합일 경우 합치지 않음
			if(a_rep != b_rep) {
				sum.set(a_rep, sum.get(a_rep)+sum.get(b_rep));
				sum.set(b_rep,  sum.get(a_rep));
			}
			
			// 큰 대표자로 갱신
			if(a_rep < b_rep) {
				a_rep = b_rep;
			}
			
			update(a_num, a_rep);
			update(b_num, a_rep);
			
			return sum.get(a_rep);
		}
		
		//대표자 구하기
		private int getRep(int node) {
			while(node != rep.get(node)) {
				node = rep.get(node);
			}
			return node;
		}
		
		//가지치기
		private void update(int node, int new_rep) {
			int before = node;
			while(node != rep.get(node)) {
				node = rep.get(node);
				rep.set(before, new_rep);
				before = node;
			}
			rep.set(node, new_rep);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//File file = new File("src/data.txt");
		//BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuffer sb = new StringBuffer();
		String a,b;
		for(int test_case = 1; test_case<=T; test_case++) {
			DisjointSet dset = new DisjointSet();
			int m = Integer.parseInt(br.readLine());
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				a = st.nextToken();
				b = st.nextToken();
				//union & 친구 구하기
				sb.append(dset.union(a, b)).append("\n");
//				if(i%30==0) {
//					sb.append("\n");
//				}
			}
			
		}
		System.out.println(sb.toString());
	}
}
