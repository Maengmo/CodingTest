package PriorityQueue;

import java.io.*;
import java.util.*;

public class Backjun2075 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소 힙

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                pq.add(num);
                if (pq.size() > N) { // 힙 크기가 N 초과하면 제거
                    pq.poll();
                }
            }
        }

        System.out.println(pq.peek()); // N번째 큰 수
    }

}
