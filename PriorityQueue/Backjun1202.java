package PriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjun1202 {

    static class Jewel {
        int weight, value;
        Jewel(int w, int v) {
            this.weight = w;
            this.value = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 1. 보석 무게 기준 오름차순 정렬
        Arrays.sort(jewels, Comparator.comparingInt(j -> j.weight));
        // 2. 가방 무게 오름차순 정렬
        Arrays.sort(bags);

        // 3. 가격 기준 최대 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long total = 0;
        int idx = 0;

        // 가방 하나씩 확인
        for (int c : bags) {
            // 현재 가방에 넣을 수 있는 모든 보석 pq에 삽입
            while (idx < N && jewels[idx].weight <= c) {
                pq.offer(jewels[idx].value);
                idx++;
            }

            // pq에서 가장 비싼 보석 꺼내서 해당 가방에 넣음
            if (!pq.isEmpty()) {
                total += pq.poll();
            }
        }

        System.out.println(total);
    }
    
}
