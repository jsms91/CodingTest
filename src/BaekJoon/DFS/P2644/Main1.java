package BaekJoon.DFS.P2644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main1{
    private static int n; //전체 사람의 수
    private static int a1; //서로 다른 사람1
    private static int a2; //서로 다른 사람2
    private static int m; //부모 자식간의 관계의 개수

    private static Map<Integer, List<Integer>> map = new HashMap<>(); // 관계 그래프
    private static boolean[] visited; // 방문 여부 확인용 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //전체 사람의 수
        visited = new boolean[n + 1]; // 사람 번호가 1부터 시작하므로 n+1 크기

        StringTokenizer st = new StringTokenizer(br.readLine());
        a1 = Integer.parseInt(st.nextToken());
        a2 = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        // 관계 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 양방향 연결
            map.get(x).add(y);
            map.get(y).add(x);
        }

        // DFS 호출
        int result = DFS(a1, a2);

        // 결과 출력
        System.out.println(result);

    }

    private static int DFS(int start, int target) {
        Stack<int[]> stack = new Stack<>();
        boolean[] visited = new boolean[n + 1]; // 방문 체크 배열
        stack.push(new int[]{start, 0}); // 시작 노드와 현재 촌수 (0부터 시작)
        visited[start] = true; // 시작 노드 방문 처리

        while (!stack.isEmpty()) {
            int[] current = stack.pop(); // 스택에서 노드와 촌수를 꺼냄
            int currentNode = current[0];
            int count = current[1]; // 현재 촌수

            // 목표 사람에 도달하면 촌수 반환
            if (currentNode == target) {
                return count;
            }

            // 현재 사람과 연결된 사람들 탐색
            if (map.containsKey(currentNode)) {
                for (int next : map.get(currentNode)) {
                    if (!visited[next]) { // 아직 방문하지 않은 사람이라면
                        visited[next] = true; // 방문 처리
                        stack.push(new int[]{next, count + 1}); // 스택에 새로운 노드와 촌수를 추가
                    }
                }
            }
        }

        // 목표에 도달할 수 없으면 -1 반환
        return -1;
    }

}