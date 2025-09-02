package stackQue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Backjun12789 {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

         // 첫 줄 숫자 읽기 (공백 제거)
        int N = Integer.parseInt(br.readLine().trim());

        Queue<Integer> queue = new ArrayDeque<>();
        
        // N개의 숫자 읽기
        String[] input = br.readLine().trim().split(" "); // 한 줄을 공백으로 분리
        for(String s : input) {
            queue.add(Integer.parseInt(s));
        }
        
        // 보조 공간 (스택)
        Stack<Integer> stack = new Stack<>();

        int next = 1; // 지금 간식 받아야 할 번호
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 바로 간식을 받을 수 있다면
            if(curr == next) {
                next ++;
            // 바로 간식을 받을 수 없다면    
            } else {
               // 보조 스택 확인
               while(!stack.isEmpty() && stack.peek() == next) {
                    stack.pop();
                    next++;
               } 

               // 그래도 curr이 next랑 다르면 → 스택에 넣음
               if(curr != next) {
                    stack.push(curr);
               } else {
                    next ++;
               }
            }
        }

        // 줄이 다 끝난 뒤, 스택에 남은 애들도 확인
        while (!stack.isEmpty() && stack.peek() == next) {
            stack.pop();
            next++;
        }

        // 모두 처리했으면 next == N+1
        if (next == N + 1) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }

        

    }
    
}
