package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Backjun5639 {
     static int[] preorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            list.add(Integer.parseInt(line));
        }

        preorder = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            preorder[i] = list.get(i);
        }

        postorder(0, preorder.length - 1);

        System.out.print(sb.toString());
    }

    static void postorder(int start, int end) {
        if (start > end) return;

        int root = preorder[start];
        int idx = start + 1;

        // 오른쪽 서브트리 시작점 찾기
        while (idx <= end && preorder[idx] < root) {
            idx++;
        }

        // 왼쪽 서브트리
        postorder(start + 1, idx - 1);
        // 오른쪽 서브트리
        postorder(idx, end);
        // 루트 출력
        sb.append(root).append('\n');
    }
}
