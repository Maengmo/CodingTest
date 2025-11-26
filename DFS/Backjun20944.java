package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjun20944 {
      public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        // 어떤 문자든 상관 없지만, 'a'로 채우는 것이 가장 간단
        char[] arr = new char[N];
        for (int i = 0; i < N; i++) {
            arr[i] = 'a';
        }
        System.out.println(new String(arr));
    }
}
