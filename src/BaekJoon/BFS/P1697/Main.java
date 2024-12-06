package BaekJoon.BFS.P1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static Queue<int[]> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int ans = BFS(N,K);
        System.out.println(ans);
    }

    private static int BFS(int current, int target) {
        int max = 100_001; //100,001에서 ,처럼 자리수로 가독성 좋게 하기 위해 표시 없어도 된다.
        boolean[] visited = new boolean[max];

        int[] now = {current, 0}; //현재 위치, 시간
        que.offer(now);

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int position = cur[0]; //현재 위치
            int sec = cur[1]; //찾는데 걸린 시간

            if(position == target) { //현재위치가 목표와 같으면 찾은 시간을 반환하고 종료
                return sec;
            }

            int[] nextPosition = {position+1,position-1,position*2}; //다음 위치

            for(int next : nextPosition) {
                if (next >= 0 && next < max && !visited[next]) { //범위내에서 방문하지 않은 경우
                    visited[next] = true; //방문 체크
                    que.offer(new int[]{next, sec + 1}); //다음위치와 현재시간에서 1초 증가 시킨 후 큐에 넣고 다시 탐색
                }
            }
        }

        return -1; //찾지 못했을 경우 -1을 반환
    }

}