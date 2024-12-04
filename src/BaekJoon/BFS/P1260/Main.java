package BaekJoon.BFS.P1260;

import java.io.*;
import java.util.*;

public class Main {

    private static boolean[] visited;
    private static Set<Integer>[] Arr;
    private static int N;
    private static int M;
    private static int V;
    private static final BufferedWriter ANS = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        Arr = new TreeSet[N+1];


        for(int i=0; i<=N; i++) {
            Arr[i] = new TreeSet<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            Arr[n1].add(n2);
            Arr[n2].add(n1);
        }

        DFS(V);
        ANS.write("\n");
        ANS.flush();
        visited = new boolean[N+1]; //초기화
        BFS(V);
        ANS.flush();

    }
    private static void DFS(int current) throws IOException {
        if(visited[current]) { return; } //방문한 곳이면 종료
        visited[current] = true; //방문 체크
        ANS.write(current + " "); //정답 번호 넣기
        for(int num : Arr[current]) { //현재 번호 인접 노드번호 순서대로 호출
            DFS(num); //앞에 인접노드 번호부터 다시 DFS 탐색 시작
        }
    }

//    stack로 푼 DFS방식 ** stack로 풀 때는 앞에서 TreeSet으로 오름차순 정렬로 저장했지만 다시 내림차순으로 설정하여 탐색해야 한다.(후입선출이기 때문에)
//    private static void DFS(int start) throws IOException {
//        Stack<Integer> stack = new Stack<>(); // 스택 생성
//        stack.push(start); // 시작 노드를 스택에 넣기
//
//        while (!stack.isEmpty()) { // 스택이 비어있지 않으면 계속 실행
//            int current = stack.pop(); // 스택에서 하나 꺼내기
//            if (!visited[current]) { // 방문하지 않았다면
//                visited[current] = true; // 방문 처리
//                ANS.write(current + " "); // 정답 출력
//
//                // 현재 노드의 인접 노드를 내림차순으로 스택에 넣기
//                // TreeSet에서 인접 노드는 이미 오름차순으로 정렬되므로, 내림차순으로 스택에 넣기 위해 reverse 함수를 사용합니다.
//                List<Integer> neighbors = new ArrayList<>(Arr[current]);
//                Collections.reverse(neighbors); // 내림차순으로 스택에 넣기 위해 reverse
//                for (int num : neighbors) {
//                    if (!visited[num]) {
//                        stack.push(num); // 아직 방문하지 않은 인접 노드를 스택에 넣음
//                    }
//                }
//            }
//        }
//    }

    private static void BFS(int start) throws IOException {
        Queue<Integer> que = new LinkedList<>(); //큐생성 큐는 선입선출 방식
        que.add(start); //가장 처음 시작점인 노드번호를 큐에 넣기
        while(!que.isEmpty()) { //큐에 데이터가 없을때까지 실행
            int current = que.poll(); //가장 먼저 넣은 번호부터 꺼내기
            if(!visited[current]) { //해당 번호를 방문한 적 없다면
                visited[current] = true; //방문 표시
                ANS.write(current + " "); //정답에 넣기
                for(int num : Arr[current]) { //해당 번호의 인접 노드를 방문하지 않은 노드만 계속해서 탐색
                    if(!visited[num]) {
                        que.add(num);
                    }
                }
            }
        }


    }

}