package stackQue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Backjun28279 {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 1: {
                    int num = Integer.parseInt(st.nextToken());
                    deque.addFirst(num);
                    break;
                }
                case 2: {
                    int num = Integer.parseInt(st.nextToken());
                    deque.addLast(num);
                    break;
                }
                case 3: {
                     sb.append(deque.isEmpty() ? -1 : deque.pollFirst()). append("\n");
                    break;
                }
                case 4: {
                    sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
                    break;
                }
                case 5: {
                    sb.append(deque.size()).append("\n");
                    break;
                }
                case 6: {
                    sb.append(deque.isEmpty()? 1 : 0).append("\n");
                    break;
                }
                case 7: {
                    sb.append(deque.isEmpty()? -1 : deque.peekFirst()).append("\n");
                    break;
                }
                case 8: {
                    sb.append(deque.isEmpty()? -1 : deque.peekLast()).append("\n");
                    break;
                }
                default:
                    break;
            }
        } 
        System.out.print(sb);

    }

}
