package BaekJoon.DFS.P2644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n; //전체 사람의 수
    private static int a1; //서로 다른 사람1
    private static int a2; //서로 다른 사람2
    private static int m; //부모 자식간의 관계의 개수

    private static Map<Integer, List<Integer>> map = new HashMap<>(); // 관계 그래프
    private static boolean[] visited; // 방문 여부 확인용 배열
    private static int result = -1; // 결과 (초기값 -1: 연결되지 않음)

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
        int result = DFS(a1, 0);

        // 결과 출력
        System.out.println(result);

    }

    private static int DFS(int current, int count) {
        visited[current] = true; // 방문 처리

        // 목표 사람에 도달하면 촌수 반환
        if (current == a2) {
            return count;
        }

        // 연결된 사람들 탐색
        for (int next : map.get(current)) {
            if (!visited[next]) {
                int result = DFS(next, count + 1);
                if (result != -1) { // 목표를 찾았다면 결과 반환
                    return result;
                }
            }
        }

        // 목표를 찾지 못한 경우
        return -1;
    }

}