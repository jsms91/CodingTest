package BaekJoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//백준 11724(실버2, 연결요소의 개수)
public class P1 {

    static boolean[] visit; //방문 배열
    static ArrayList<Integer>[] A; //인접리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정점의 개수
        int M = Integer.parseInt(st.nextToken()); //간선의 개수
        int count = 0; //연결 요소의 개수

        visit = new boolean[N+1]; //방문배열
        A = new ArrayList[N+1]; //인접리스트

        for(int i=1; i<A.length; i++) {
            A[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            //양방향이므로 서로의 리스트에 넣기
            A[n1].add(n2);
            A[n2].add(n1);
        }

        for(int i=1; i<=N; i++) {
            if(!visit[i]) { //방문하지 않은곳이면 방문 후 DFS 실행
                DFS(i);
                count++; //연결 요소의 개수 증가
            }
        }

        System.out.println(count);

    }

    private static void DFS(int index) {
        visit[index] = true; //방문 체크
        for(int node : A[index]) {
            if (!visit[node]) { //인접 항목에 방문하지 않았을 경우 DFS 실행
                DFS(node);
            }
        }
    }
}