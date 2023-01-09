package com.qimu;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 23:59
 * @Version:1.0
 * @Description: parallel()方法可以把串行流转换成并行流
 */
public class ParallelTest {
    /**
     * 串行方法 12ms
     */
    @Test
    public void test01() {
        Stream<Integer> stream =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        Integer sum = stream.peek(integer -> System.out.println("串行方法-->integer=" + integer + "___,线程名::" + Thread.currentThread().getName()))
                .filter(num -> num > 5)
                .reduce(Integer::sum)
                .get();
        System.out.println(sum);
    }

    /**
     * 并行方法 4ms
     */
    @Test
    public void test02() {
        Stream<Integer> stream =
                Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        Integer sum = stream.parallel()
                .peek(integer -> System.out.println("并行方法-->integer=" + integer + "___,线程名::" + Thread.currentThread().getName()))
                .filter(num -> num > 5)
                .reduce(Integer::sum)
                .get();
        System.out.println(sum);
    }
}
