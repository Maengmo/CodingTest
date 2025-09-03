package stackQue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Backjun28278 {
    
    public static void main(String[] args) throws IOException {

        //입력 받는 buffer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //출력 buffer 
        StringBuilder sb = new StringBuilder();

        //명령 갯수
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        //명령 받는 for 문
        for (int i = 0; i <N;  i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            //명령어 분개 처리
            switch(cmd) {
               
                // 1 X: 정수 X를 스택에 넣는다. (1 ≤ X ≤ 100,000)
                case 1 : 
                    int num = Integer.parseInt(st.nextToken());
                    stack.push(num);
                    break;
                
                // 2: 스택에 정수가 있다면 맨 위의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
                case 2 :
                    sb.append(stack.isEmpty() ? -1 : stack.pop()). append("\n");
                    break;

                // 3: 스택에 들어있는 정수의 개수를 출력한다.
                case 3 :
                    sb.append(stack.size()).append("\n");
                    break;

                // 4: 스택이 비어있으면 1, 아니면 0을 출력한다.
                case 4 :
                    sb.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;

                // 5: 스택에 정수가 있다면 맨 위의 정수를 출력한다. 없다면 -1을 대신 출력한다.
                case 5 :
                    sb.append(stack.isEmpty()? -1 : stack.peek()).append("\n");
                    break;

            } 
        }

        System.out.print(sb);


    }
}
