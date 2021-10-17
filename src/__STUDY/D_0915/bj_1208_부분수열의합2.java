package __STUDY.D_0915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class bj_1208_부분수열의합2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		// 숫자의 절댓값은 -100000 ~ 100000. N은 최대 40개. 800만의 배열 만들기

		final int MAX_NUM = 100000;
		final int ZERO = MAX_NUM * N; // DP[] 0의 인덱스
		Long[] DP = new Long[MAX_NUM * N * 2 + 1]; // 합의 개수들을 저장하는 배열

		st = new StringTokenizer(br.readLine());
		int tmp;
		
		// SET 안쓰는 경우
		tmp = Integer.parseInt(st.nextToken());
		int min_num;
		int max_num;
		for (int i = 0; i < MAX_NUM * N * 2 + 1; i++) {
			DP[i] = 0l;
		}
		DP[ZERO] = 1l;
		// 맨 처음 값이 0일 경우 반례
		if(tmp == 0) {
			DP[ZERO]++;
		}else {
			DP[ZERO + tmp] = 1l;
		}
		if(tmp>0) {
			min_num = 0;
			max_num = tmp;
		}else {
			max_num = 0;
			min_num = tmp;
		}
		
		for (int i = 1; i < N; i++) {
			tmp = Integer.parseInt(st.nextToken());
			if(tmp>0) {
				for(int j=max_num; j>=min_num; j--) {
					if(DP[ZERO+j] != 0l) {
						DP[ZERO + j + tmp] = DP[ZERO + j + tmp] + DP[ZERO + j];
					}
				}
			}else {
				for(int j=min_num; j<=max_num; j++) {
					if(DP[ZERO+j] != 0l) {
						DP[ZERO + j + tmp] = DP[ZERO + j + tmp] + DP[ZERO + j];
					}
				}
			}
			
			if(tmp>0) {
				max_num += tmp;
			}else {
				min_num += tmp;
			}
		}

		DP[ZERO]--; // 0개짜리는 제외
		System.out.println(DP[S + ZERO]); // 정수 S의 개수
	}
}


/*  SET 쓰는 경우
Set<Integer> set = new HashSet<>();
// init
tmp = Integer.parseInt(st.nextToken());
set.add(0);
set.add(tmp);
for (int i = 0; i < MAX_NUM * N * 2 + 1; i++) {
	DP[i] = 0l;
}
DP[ZERO] = 1l;
// 맨 처음 값이 0일 경우 반례
if(tmp == 0) {
	DP[ZERO]++;
}else {
	DP[ZERO + tmp] = 1l;
}
ArrayList<Integer> list = new ArrayList<>();
for (int i = 1; i < N; i++) {
	tmp = Integer.parseInt(st.nextToken());
	list.clear();
	for (int idx : set) {
		// System.out.print(idx+" ");
		list.add(idx);
	}
	// DP 꼬이지 않게 정렬하기
	if (tmp > 0) {
		Collections.sort(list, Collections.reverseOrder());
	} else {
		Collections.sort(list);
	}
	// System.out.println();
	for (int idx : list) {
		DP[ZERO + idx + tmp] = DP[ZERO + idx + tmp] + DP[ZERO + idx];
		set.add(idx + tmp);
	}
}
*/



/*

Sample Output

40 4802
-99999 -99799 -99399 -98799 -97999 -96999 -95799 -94399 -92799 -90999 -88999 -86799 -84399 -81799 -78999 -75999 -72799 -69399 -65799 -61999 -57999 -53799 -49399 -44799 -39999 -34999 -29799 -24399 -18799 -12999 -6999 -799 5601 12201 19001 26001 33201 40601 48201 56001
1

40 0
100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000 -100000
137846528819

*/
