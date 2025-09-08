package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Backjun11399 {
    
   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        // 오름차순 정렬
        Arrays.sort(P);
        
        int sum = 0;   // 최종 합
        int prefix = 0; // 누적합
        for (int i = 0; i < N; i++) {
            prefix += P[i];
            sum += prefix;
        }
        
        System.out.println(sum);
    }
}
