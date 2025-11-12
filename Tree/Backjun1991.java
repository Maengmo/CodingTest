package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Backjun1991 {
    static Map<Character, Node> tree = new HashMap<>();

    static class Node {
        char left;
        char right;
        Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 트리 구성
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            tree.put(root, new Node(left, right));
        }

        StringBuilder sb = new StringBuilder();
        preorder('A', sb);
        sb.append('\n');
        inorder('A', sb);
        sb.append('\n');
        postorder('A', sb);
        System.out.println(sb);
    }

    // 전위 순회 (Root → Left → Right)
    static void preorder(char node, StringBuilder sb) {
        if (node == '.') return;
        sb.append(node);
        preorder(tree.get(node).left, sb);
        preorder(tree.get(node).right, sb);
    }

    // 중위 순회 (Left → Root → Right)
    static void inorder(char node, StringBuilder sb) {
        if (node == '.') return;
        inorder(tree.get(node).left, sb);
        sb.append(node);
        inorder(tree.get(node).right, sb);
    }

    // 후위 순회 (Left → Right → Root)
    static void postorder(char node, StringBuilder sb) {
        if (node == '.') return;
        postorder(tree.get(node).left, sb);
        postorder(tree.get(node).right, sb);
        sb.append(node);
    }
}
