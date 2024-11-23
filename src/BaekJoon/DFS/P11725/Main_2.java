package BaekJoon.DFS.P11725;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//트리의 부모 찾기(실버2)
public class Main_2 {
    private static int N; //노드 길이
    private static ArrayList<Integer>[] tree; //인접 노드들이 담길 리스트 배열
    private static int[] ans; //부모 노드 번호 담길 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; //각 노드번호의 인접한 노드번호가 담길 배열
        ans = new int[N+1]; //각 노드번호의 부모노드 번호만 담길 배열(0번은 당연히 없으니 0이고 최상단 부모노드인 1번은 밑에서 -1로 초기화)

        //트리 초기화
        for(int i=0; i<tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        //각 노드 리스트에 인접 노드 번호 넣기
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        ans[1] = -1; //1은 부모노드가 없다.
        DFS(1); //탐색 시작

        for(int i=2; i<ans.length; i++) {
            bw.write(ans[i]+"\n");
        }
        bw.flush();
    }
    private static void DFS(int node) {
        for(int i=0; i<tree[node].size(); i++) {
            int nextNode = tree[node].get(i);
            if(ans[nextNode] == 0) { //현재 노드에 인접한 노드번호가 부모가 없으면
                ans[nextNode] = node; //현재 노드번호를 부모로 설정
                DFS(nextNode); //해당 노드에 인접한 노드들 탐색
            }
        }
    }
}