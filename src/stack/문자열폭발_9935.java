package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class 문자열폭발_9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String explode = br.readLine();
	
		char check_c = explode.charAt(explode.length() - 1);	//맨 마지막 문자
		
		Stack<Character> stack = new Stack<>();
		char[] str_to_char = str.toCharArray();
		
		ArrayList<Character> tmpList = new ArrayList<>();
		for(char c : str_to_char) {
			// 맨 끝 문자와 다를 경우 push
			if(c != check_c) {
				stack.add(c);
				continue;
			}
			// stack 사이즈 맞지 않을때 push
			if(explode.length() > stack.size()+1) {
				stack.add(c);
				continue;
			}
			// explode.length()-1 만큼 빼와서 비교하기
			boolean isValid = true;
			for(int i=explode.length()-2; i>=0; i--) {
				tmpList.add(stack.pop());
				// 문자열 같지 않음
				if(explode.charAt(i) != tmpList.get(tmpList.size()-1)) {
					isValid = false;
					break;
				}
			}
			
			// 유효하지 않을 경우 다시 stack에 전부 넣기
			if(!isValid) {
				while(tmpList.size()!=0) {
					stack.add(tmpList.get(tmpList.size()-1));
					tmpList.remove(tmpList.size()-1);
				}
				stack.add(c);
			}else {
				// 유효할 경우 list 클리어 -- 이거때문에 틀림
				tmpList.clear();
			}
			
//			Iterator<Character> itr = stack.iterator();
//	        // hasNext() returns true if the stack has more elements
//	        while (itr.hasNext())
//	        {
//	            // next() returns the next element in the iteration
//	            System.out.print(itr.next());
//	        }
//	        System.out.println();
		}
		
		StringBuffer sb = new StringBuffer();
		//스택에 있는 문자 모두 빼기
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		if(sb.length() == 0) {
			System.out.println("FRULA");
		}else {
			System.out.println(sb.reverse().toString());
		}
	}
}
