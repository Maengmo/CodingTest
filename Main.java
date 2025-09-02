import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // 원래 줄 (큐)
        Queue<Integer> line = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            line.add(sc.nextInt());
        }

        // 보조 공간 (스택)
        Stack<Integer> stack = new Stack<>();

        int next = 1; // 지금 간식 받아야 할 번호

        // 줄이 빌 때까지 반복
        while (!line.isEmpty()) {
            int curr = line.poll(); // 줄에서 한 명 꺼냄

            if (curr == next) {
                // 바로 간식 받을 수 있으면
                next++;
            } else {
                // 보조 스택 확인
                while (!stack.isEmpty() && stack.peek() == next) {
                    stack.pop();
                    next++;
                }

                // 그래도 curr이 next랑 다르면 → 스택에 넣음
                if (curr != next) {
                    stack.push(curr);
                } else {
                    next++;
                }
            }
        }

        // 줄이 다 끝난 뒤, 스택에 남은 애들도 확인
        while (!stack.isEmpty() && stack.peek() == next) {
            stack.pop();
            next++;
        }

        // 모두 처리했으면 next == N+1
        if (next == N + 1) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
