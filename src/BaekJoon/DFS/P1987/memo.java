package BaekJoon.DFS.P1987;

public class memo {
    public static void main(String[] args) {
        int mask = 0;
        mask |= (1<<0); //01
        //mask 0
        //1<<0 1
        //------
        //1
        System.out.println(mask);

        int mask0 = 0;
        mask0 |= (1<<1); //10
        System.out.println(mask0);

        int mask2=0;
        mask2 |= (1<<9); //1000000000
        System.out.println(mask2);

        mask |= (1<<2); //100
        //1<<2(1을 왼쪽으로 2번이동 -> 100)
        //mask(001)
        //--------
        //101(10진수 = 5)
        System.out.println("mask : "+mask);


        //현재 mask = 101
        int x = mask & (1 << 2); //1을 왼쪽으로 2번 이동 후 mask(101)과 &연산
        //(1<<2) -> 1을 왼쪽으로 2번 이동 10 -> 100
        //100
        //101
        //---
        //100(정답) 4
        System.out.println(x); //4
        int y = mask & (2<<2); //2를 왼쪽으로 2번 이동후 &연산
        //10 -> 100 -> 1000
        //1000
        //0101
        //-----
        //0000(정답)
        System.out.println(y);//0
        int ToggleMask = 0; //10
        ToggleMask |= (1<<3); //1000
        ToggleMask ^= (1<<1); //1010(10진수 10)
        System.out.println(ToggleMask);

        ToggleMask = ~ToggleMask;
        System.out.println("NotMask = " + ToggleMask);

    }
}
/*
ArrayList contain 사용시  리스트의 크기가 커질수록 시간이 오래 걸리므로, 탐색 속도가 느려진다.

1. ArrayList:
- 순서가 중요한 경우 (예: 삽입된 순서를 유지해야 하는 경우).
- 중복된 값을 허용해야 할 경우.
- contains의 성능이 중요한 상황이 아니라면 사용 가능

2. HashSet:
- 빠른 검색이 필요한 경우 (예: 탐색 속도가 중요한 경우).
- 중복을 허용하지 않음 (중복된 값을 자동으로 거름).
- contains가 자주 호출되는 경우나, 빠른 탐색이 필요한 경우에는 HashSet이 유리

3. 비트마스크(***) 사용시 속도가 빠르다
: 비트 연산을 사용하여 여러 개의 값을 효율적으로 처리하는 기법으로 보통 0과 1로 이루어진 비트(Bit)를 사용하여 값을 표현하고,
  이를 통해 여러 개의 상태나 정보를 하나의 숫자로 묶어서 다룰 수 있다.

> 비트 연산: AND (&), OR (|), XOR (^), NOT (~) 등의 연산자를 사용하여 비트를 조작
> 비트마스크 기본 예
: 8개의 상태를 표현하려면 8개의 비트를 사용합니다. 00000000은 모든 상태가 꺼져 있는 상태, 11111111은 모든 상태가 켜져 있는 상태
- 1 << i: i번째 비트를 1로 설정
- mask | (1 << i): i번째 비트를 1로 설정
- mask & (1 << i): i번째 비트가 1인지 확인
- mask & ~(1 << i): i번째 비트를 0으로 설정
- mask ^ (1 << i): i번째 비트를 반전 (Toggle)
- ~mask: 모든 비트 반전 (NOT) --> 32비트이기때문에 위에 다른 연산들과는 다르게 예를 들어 8이면 -9가 되기때문에 8을 1000 2진수로 변경 후
~1000이 되게 해야 한다.
 */