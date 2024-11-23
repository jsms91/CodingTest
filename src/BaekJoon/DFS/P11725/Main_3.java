package BaekJoon.DFS.P11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main_3 {
    private static int N; // 노드 수
    private static List<Integer>[] tree; // 인접 노드들이 담길 리스트 배열
    private static int[] ans; // 부모 노드 번호 담길 배열

    public static void main(String[] args) throws IOException {
        // 입출력 최적화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        ans = new int[N + 1]; // 부모 노드 번호 배열

        // 트리 초기화
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        // 각 노드 리스트에 인접 노드 번호 넣기
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        // 부모 노드 찾기 DFS 탐색
        ans[1] = -1; // 1번 노드는 부모가 없으므로 -1로 설정
        StackMethod(1); //스택방식으로 풀이

        // 출력 (BufferedWriter를 이용해 성능 최적화)
        for (int i = 2; i <= N; i++) {
            bw.write(ans[i] + "\n");
        }
        bw.flush(); // 버퍼에 남은 내용 출력
    }

    private static void StackMethod(int node) {
        // 스택을 이용한 DFS 반복문으로 구현
        Stack<Integer> stack = new Stack<>();
        stack.push(node); // 스택에 시작 노드(node) 넣기

        while (!stack.isEmpty()) { // 스택이 비지 않으면 계속 탐색
            int currentNode = stack.pop(); // 스택에서 맨 위에 있는 노드 꺼내기

            for (int nextNode : tree[currentNode]) { // 현재 노드의 인접 노드들 탐색
                if (ans[nextNode] == 0) { // 아직 부모 노드가 설정되지 않은 자식 노드를 만났을 때
                    ans[nextNode] = currentNode; // 현재 노드를 자식 노드의 부모로 설정
                    stack.push(nextNode); // 자식 노드를 스택에 넣어 다음에 탐색할 수 있게 함
                }
            }
        }
    }

}