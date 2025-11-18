package UnionFind;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Backjun4195 {
    
    static Map<String, String> parent;
    static Map<String, Integer> size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            parent = new HashMap<>();
            size = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!parent.containsKey(a)) {
                    parent.put(a, a);
                    size.put(a, 1);
                }
                if (!parent.containsKey(b)) {
                    parent.put(b, b);
                    size.put(b, 1);
                }

                sb.append(union(a, b)).append("\n");
            }
        }

        System.out.print(sb);
    }

    // 부모 찾기
    static String find(String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));  // 경로 압축
        }
        return parent.get(x);
    }

    // 합치기
    static int union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            parent.put(rootB, rootA);
            size.put(rootA, size.get(rootA) + size.get(rootB));
        }

        return size.get(rootA);
    }
}
