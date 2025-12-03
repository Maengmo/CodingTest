package DFS;

public class Backjun15311 {
     public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append(2000).append('\n');

        // 1000개의 1000
        for (int i = 0; i < 1000; i++) sb.append(1000).append(' ');

        // 1000개의 1
        for (int i = 0; i < 1000; i++) {
            sb.append(1);
            if (i < 999) sb.append(' ');
        }

        System.out.println(sb);
    }
}
