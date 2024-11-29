# BFS(너비우선탐색)
> 시작 노드에서 출발해 시작노드를 기준으로 가장 가까운 노드를 먼저 방문하면서 탐색하는 알고리즘

### - 기능: 그래프 완전 탐색
### - 특징: `FIFO` 탐색, `Queue` 자료구조 이용
### - 시간 복잡도: O(V+E) [V:노드수, E:에지수]

> 너비우선 탐색은 `선입선출` 방식으로 탐색하므로 `큐`를 이용해 구현한다. 
또한 너비 우선 탐색은 탐색시작 노드와 가까운 노드를 우선하여 탐색하므로 목표 노드에 도착하는 경로가 여러 개일 때 최단경로를 보장한다.

## 너비 우선 탐색의 핵심 이론(BFS 원리 3단계)
### 1. BFS를 시작할 노드를 정한 후 사용할 자료구조 초기화
- DFS와 마찬가지로 방문했던 노드는 다시 방문하지 않기 때문에 `방문 노드를 체크할 배열`이 필요
- 그래프를 인접 리스트로 표현하는 것 또한 DFS와 동일하지만 탐색을 위해 `스택`이 아닌 `큐`를 사용한다.

### 2. 큐에서 노드를 꺼낸 후 꺼낸 노드의 인접 노드를 다시 큐에 삽입
- 큐에서 노드를 꺼내면서 인접 노드를 큐에 삽입
- 이때 방문 배열을 체크하여 이미 방문한 노드는 큐에 삽입하지 않는다.
- 또한 큐에서 꺼낸 노드를 탐색순서에 기록

### 3. 큐 자료구조에 값이 없을 때까지 반복
- 큐에 노드가 없을 때까지 앞선 과정을 반복
- 선입선출(FIFO) 방식으로 탐색하므로 탐색 순서가 DFS와 다름

### 4. 예시
#### [인접리스트]
```
1 -> 2, 3  
2 -> 5, 6  
3 -> 4  
4 -> 6  
5  
6  
```
#### [BFS 탐색]
- 큐를 사용하며, 한 단계씩 모든 노드를 방문
- 시작점 1에서 탐색
  - 1 → 2, 3 (1과 연결된 노드들 탐색)
  - 2 → 5, 6 (2와 연결된 노드들 탐색)
  - 3 → 4 (3과 연결된 노드 탐색)
  - 4에서 더 갈 곳이 없으며, 나머지 노드들은 이미 방문됨.
> `결과: 1 → 2 → 3 → 5 → 6 → 4`