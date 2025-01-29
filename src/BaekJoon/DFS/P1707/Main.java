package BaekJoon.DFS.P1707;

import java.io.*;
import java.util.*;

public class Main {

    private static int V, E;
    private static List<List<Integer>> graph;
    private static int[] colors; // 0: 미방문, 1: 색1, 2: 색2
    private static boolean check; //이분그래프인지 아닌지 체크

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int i = 0; i < k; i++) { // 테스트 케이스 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점의 개수
            E = Integer.parseInt(st.nextToken()); // 간선의 개수

            // 그래프 초기화
            graph = new ArrayList<>();
            for (int j = 0; j <= V; j++) {
                graph.add(new ArrayList<>());
            }

            colors = new int[V + 1]; // 0: 방문X, 1: 색1, 2: 색2

            // 간선 정보 입력
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                // 양방향 연결
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            check = true;

            // **모든 정점에 대해 DFS 실행 (연결 요소가 여러 개일 수 있음)**
            for (int j = 1; j <= V; j++) {
                if (colors[j] == 0) { // 방문하지 않은 정점에서 DFS 수행
                    if (!StackDfs(j)) { // 한 번이라도 false가 나오면 NO
                        check = false;
                        break;
                    }
                }
            }

            sb.append(check ? "YES\n" : "NO\n"); // 이분 그래프이면 YES, 아니면 NO
        }

        System.out.print(sb.toString()); //정답

    }

    private static boolean StackDfs(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        colors[start] = 1; // 시작 정점을 1번 색으로 칠함

        while (!stack.isEmpty()) {
            int node = stack.pop();

            for (int next : graph.get(node)) {
                if (colors[next] == 0) { // 아직 방문하지 않은 정점
                    colors[next] = 3 - colors[node]; // 현재 노드 색과 다른 색을 칠함
                    stack.push(next);
                } else if (colors[next] == colors[node]) { // 인접 정점이 같은 색이면 이분 그래프 아님
                    return false;
                }
            }
        }
        return true;
    }

}

/*
private static boolean dfs(int node, int color) {
    colors[node] = color; // 현재 정점에 색상 부여

    for (int next : graph.get(node)) { // 인접 정점 탐색
        if (colors[next] == 0) { // 방문하지 않은 경우
            if (!dfs(next, 3 - color)) return false; // 다른 색상으로 탐색
        } else if (colors[next] == color) { // 인접 정점이 같은 색이면 이분 그래프 아님
            return false;
        }
    }
    return true;
}
*/
