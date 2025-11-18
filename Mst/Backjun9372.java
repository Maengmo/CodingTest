package Mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun9372 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            // 비행기 연결 정보는 실제 계산에는 필요 없음
            for (int i = 0; i < M; i++) {
                br.readLine(); // 입력만 소모
            }
            
            // 항상 N - 1
            System.out.println(N - 1);
        }
    }
}
