package BaekJoon.DFS.P11725;

import java.io.BufferedWriter;

public class Memo {
    public static void main(String[] args) {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }
}
// BufferedWriter는 출력 성능 최적화를 위한 코드이다.
/*
1. System.out
- System.out은 표준 출력을 담당하는 객체, 일반적으로 콘솔에 출력할 때 사용
- System.out 자체는 PrintStream 객체, 이 객체는 기본적으로 println()과 같은 메서드를 제공하여 콘솔에 출력할 수 있다.

2. OutputStreamWriter
- OutputStreamWriter는 바이트 스트림을 문자 스트림으로 변환하는 클래스
- System.out은 바이트 스트림이므로, 이 데이터를 문자로 변환할 필요가 있을 때 OutputStreamWriter를 사용 즉, 바이트 스트림을 문자 스트림으로 변환해주는 역할
- 이 클래스는 출력 데이터를 문자로 처리할 수 있도록 변환하는 역할을 하며, 바이트 스트림을 문자 스트림으로 연결하는 데 사용

3. BufferedWriter
- BufferedWriter는 문자 데이터를 버퍼에 담아서 한번에 출력하는 기능을 제공
- 기본적으로 System.out을 사용하면 출력할 때마다 즉시 데이터를 처리하는 방식이므로 성능이 저하될 수 있다.
- BufferedWriter는 버퍼를 사용하여 일정량의 데이터를 모은 후 한 번에 출력하여 출력 성능을 향상시킨다.
- BufferedWriter는 데이터가 출력될 때마다 버퍼에 저장하고, 버퍼가 꽉 차거나, 명시적으로 flush() 메서드를 호출할 때 한 번에 출력

 */

