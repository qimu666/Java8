package com.qimu;

import com.qimu.pojo.Author;
import com.qimu.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 18:54
 * @Version:1.0
 * @Description: 中间操作
 */
@SpringBootTest
public class MiddleTest {
    private static final List<Author> authorList = StreamStatic.getAuthors();

    @Test
    public void test01() {
        // 打印所有姓名长度大于1的作家姓名
        authorList.stream()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void test02() {
        // 打印所有作家的姓名(String类型)
        authorList.stream()
                // Author转换为String类型
                .map(Author::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test03() {
        // 年龄加10岁
        authorList.stream()
                .map(Author::getAge)
                .map(age -> age + 10)
                .forEach(System.out::println);
    }

    @Test
    public void test04() {
        // 打印所有作家,并且其中的元素不能重复
        authorList.stream()
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test05() {
        // 通过年龄进行排序，不能重复
        authorList.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    @Test
    public void test06() {
        // 付流中的元素按照年龄进行降序排序，并且要求不能有复的元素，然后打印其中年龄最人的两个作家的健名。
        authorList.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(2)
                .forEach(author -> System.out.println(author.getName() + author.getAge()));
    }

    @Test
    public void test07() {
        // 打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按年龄降序排序。
        authorList.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .skip(1)
                .forEach(author -> System.out.println(author.getName() + author.getAge()));
    }

    @Test
    public void test08() {
        // 打印所有书的名字,并且去重
        authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

        /*===========================================================*/

        authorList.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    @Test
    public void test09() {
        // 打印现有分类,对分类去重，不能出现 "哲学,爱情" 这种格式
        authorList.stream()
                .flatMap(author -> author.getBooks().stream())
                // 对书去重
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                // 对分类去重
                .distinct()
                .forEach(System.out::println);
    }
}

