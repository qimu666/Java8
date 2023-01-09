package com.qimu;

import com.qimu.pojo.Author;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 23:45
 * @Version:1.0
 * @Description: 高级用法:基本数据类型优化
 */
public class AdvancedUsageTest {

    private static final List<Author> authorList = StreamStatic.getAuthors();

    /**
     * 运行时间：21ms
     */
    @Test
    public void test01() {
        authorList.stream()
                .map(Author::getAge)
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);
    }

    /**
     * 运行时间：5ms
     * 优化速度提升4倍
     * 优化装箱和拆箱的时间消耗,数据量越大优化越明显
     */
    @Test
    public void test02() {
        authorList.stream()
                .mapToInt(Author::getAge)
                .map(age -> age + 10)
                .filter(age -> age > 18)
                .map(age -> age + 2)
                .forEach(System.out::println);
    }
}
