package BaekJoon.DFS;

public class Memo {
}
//DFS(깊이우선탐색)
/*
1. 그래프나 트리에서 출발점(시작노드)에서부터 깊이(깊숙한 방향)로 노드를 탐색하는 방식
    - 탐색할 한 쪽 분기를 정하여 최대 깊이 까지 탐색을 마친 후 다른 쪽 분기로 이동하여 다시 탐색을 수행

2. DFS 특징
    - 스택을 사용하여 탐색
    - 재귀 함수로 구현

3. 시간복잡도
    - O(V+E) [V:노드수, E:에지수]

4. 한 노드의 자식 노드를 모두 방문한 후 다음 노드로 이동하는 방식으로 동작한다.
    - 한번 방문한 노드를 다시 방문 X
    - 노드 방문 여부를 체크할 배열 필요
    - 후입선출(스택 특성)

5. 동작 과정
    - DFS를 시작할 노드를 정한 후 사용할 자료구조 초기화
    - 스택에서 노들르 꺼낸 후 꺼낸 노드의 인접 노드를 다시 스택에 삽입
    - 스택 자료구조에 값이 없을 때까지 반복(방문은 스킵)
 */