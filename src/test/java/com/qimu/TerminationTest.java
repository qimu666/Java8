package com.qimu;

import com.qimu.pojo.Author;
import com.qimu.pojo.Book;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 20:32
 * @Version:1.0
 * @Description: 终止操作
 */
public class TerminationTest {
    private static final List<Author> authorList = StreamStatic.getAuthors();

    @Test
    public void test01() {
        // 输出所有作家的名字
//        authorList.forEach(author -> System.out.println(author.getName()));
//
//        authorList.stream().forEach(author -> System.out.println(author.getName()));

        authorList.stream()
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 打印这些作家的所有书记的数目，并去重
        long count = authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);
    }

    @Test
    public void test03() {
        // 分别获取这些作家的所出书籍的最高分和最低分并打印。
        Optional<Integer> max = authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .max((o1, o2) -> o1 - o2);
        System.out.println(max.get());

        Optional<Integer> min = authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .min((o1, o2) -> o1 - o2);
        System.out.println(min.get());
    }

    @Test
    public void test04() {
        // 把一个流转换为一个集合
        // 获取一个存放所有作者名字的Lst集合。
        List<String> collectList = authorList.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        System.out.println(collectList);
    }

    @Test
    public void test05() {
        // 获取一个存放所有书名的Set集合。
        Set<String> collectSet = authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getName)
                .collect(Collectors.toSet());
        System.out.println(collectSet);
    }


    @Test
    public void test06() {
        // 获取一个map集合，map的key为作者名，value为解List<Book>
        Map<String, List<Book>> collectMap = authorList.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
        System.out.println(collectMap);
    }

    @Test
    public void test07() {
        // 判断是否有年龄在29以上的作家
        boolean flag = authorList.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(flag);
    }

    @Test
    public void test08() {
        // 判断是否所有的作家都是成年人
        boolean flag = authorList.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(flag);
    }

    @Test
    public void test09() {
        // 判断是否所有的作家都不满100岁
        boolean flag = authorList.stream()
                .noneMatch(author -> author.getAge() >= 100);
        System.out.println(flag);
    }

    @Test
    public void test10() {
        // 获取任合一个年龄大于18的作家，如果存在就翰出他的名字
        Optional<Author> any = authorList.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        any.ifPresent(author -> System.out.println(author.getName()));
    }

    @Test
    public void test11() {
        // 获取一个年龄最小的作家，并输出他的姓名。
        Optional<Author> first = authorList.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));
    }

    @Test
    public void test12() {
        // 使用reduce.求所有作者年龄的和
        Integer sum = authorList.stream()
                .map(Author::getAge)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    @Test
    public void test13() {
        // 使用reduce.求所有作者中年龄的最大值
        Integer ageMax = authorList.stream()
                .map(Author::getAge)
                .reduce(Integer.MIN_VALUE, (identity, integer2) -> {
                    if (identity < integer2) {
                        identity = integer2;
                    }
                    return identity;
                });
        System.out.println(ageMax);
    }

    @Test
    public void test14() {
        // 使阳reduce.求所有作者中年龄的最小值
        Optional<Integer> reduce = authorList.stream()
                .map(Author::getAge)
                .reduce(Integer::min);
        reduce.ifPresent(System.out::println);
    }
}
