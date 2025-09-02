import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 빠른 출력을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 명령의 개수
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 1: // 1 X: 정수 X를 스택에 넣는다
                    int x = Integer.parseInt(st.nextToken());
                    stack.push(x);
                    break;

                case 2: // pop
                    sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
                    break;

                case 3: // size
                    sb.append(stack.size()).append("\n");
                    break;

                case 4: // empty
                    sb.append(stack.isEmpty() ? 1 : 0).append("\n");
                    break;

                case 5: // top
                    sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
                    break;
            }
        }

        // 결과 한 번에 출력
        System.out.print(sb);
    }
}
