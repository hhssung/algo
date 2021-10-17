package Heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*

priority Queue를 사용하는 문제
 
max PQ, min PQ를 만듦.
처음엔 무조건 max PQ에 넣기.
이후 맨 위의 두 원소를 비교하여 수 알맞게 조절.

1 maxQ
5 minQ

*/

public class 가운데를말해요_1655 {
	private static void print(PriorityQueue<Integer> a, PriorityQueue<Integer> b) {
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> minQ = new PriorityQueue<>();
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});

		StringBuffer ans = new StringBuffer();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			maxQ.add(num);

			if (minQ.size() == 0 || maxQ.size() - minQ.size() == 2) {
				minQ.add(maxQ.poll());
			} else {
				int max_tmp = maxQ.peek();
				int min_tmp = minQ.peek();
				if (max_tmp > min_tmp) {
					if (minQ.size() == maxQ.size()) {
						// swap
						maxQ.poll();
						minQ.poll();
						maxQ.add(min_tmp);
						minQ.add(max_tmp);
					} else {
						minQ.add(maxQ.poll());
					}
				}
			}
			//System.out.println(maxQ.peek() + " "+ minQ.peek());

			// 출력
			if (maxQ.size() == 0) {
				ans.append(minQ.peek()).append("\n");
			} else if (minQ.size() == 0) {
				ans.append(maxQ.peek()).append("\n");
			} else {
				if (maxQ.size() == minQ.size()) {
					ans.append(Math.min(maxQ.peek(), minQ.peek())).append("\n");
				} else {
					if (maxQ.size() > minQ.size()) {
						ans.append(maxQ.peek()).append("\n");
					} else {
						ans.append(minQ.peek()).append("\n");
					}
				}
			}
		}

		//System.out.println("=======================");
		System.out.println(ans.toString());
	}
}