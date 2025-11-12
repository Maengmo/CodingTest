package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjun2263 {
    static int n;
    static int[] inorder, postorder;
    static int[] inorderIndex; // inorder 값 -> 인덱스 매핑
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        inorder = new int[n];
        postorder = new int[n];
        inorderIndex = new int[n + 1]; // 1-based 값 매핑

        // inorder 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        // postorder 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        // inorder 인덱스 매핑 (값 -> 인덱스)
        for (int i = 0; i < n; i++) {
            inorderIndex[inorder[i]] = i;
        }

        // 전위 순회 생성
        buildPreorder(0, n - 1, 0, n - 1);

        // 결과 출력
        System.out.println(sb);
    }

    // (inStart ~ inEnd, postStart ~ postEnd)
    static void buildPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        // 1️⃣ 후위순회의 마지막 원소 = 루트
        int root = postorder[postEnd];
        sb.append(root).append(' ');

        // 2️⃣ inorder에서 루트 기준으로 왼쪽/오른쪽 분리
        int rootIndex = inorderIndex[root];
        int leftSize = rootIndex - inStart;

        // 3️⃣ 왼쪽 서브트리
        buildPreorder(inStart, rootIndex - 1, postStart, postStart + leftSize - 1);
        // 4️⃣ 오른쪽 서브트리
        buildPreorder(rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1);
    }
}
