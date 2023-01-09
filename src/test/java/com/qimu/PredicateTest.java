package com.qimu;

import com.qimu.pojo.Author;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 23:13
 * @Version:1.0
 * @Description: 函数式接口常用默认方法
 */
public class PredicateTest {
    private static final List<Author> authorList = StreamStatic.getAuthors();

    /**
     * 打印作家中年龄大于17并且姓名的长度大于1的作家。
     */
    @Test
    public void testAnd() {
        authorList.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17)
                        .and(author -> author.getName().length() > 1))
                .forEach(System.out::println);
    }

    /**
     * 打印作家中年龄大于17并且姓名的长度大于1的作家。
     */
    @Test
    public void testAnd02() {
        authorList.stream()
                .filter(author -> author.getAge() > 17 && author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getAge()+"::"+author.getName()));
    }
}
