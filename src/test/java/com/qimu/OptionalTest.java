package com.qimu;

import com.qimu.pojo.Author;
import com.qimu.pojo.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 22:32
 * @Version:1.0
 * @Description: 安全消费 避免空指针异常
 */
public class OptionalTest {

    public static Optional<Author> optional() {
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        List<Book> books1 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "刀的两侧", "哲学,爱情", 80, "用一把刀划"));
        author.setBooks(books1);
        return Optional.ofNullable(author);
    }

    /**
     * 安全的消费值 optional.ifPresent()
     */
    @Test
    public void test01() {
        Optional<Author> optional = optional();
        optional.ifPresent(System.out::println);
    }


    /**
     * 安全的获取值
     */
    @Test
    public void test02() {
        Optional<Author> optional = optional();
        // 获取数据并且为数据赋一个默认值,获取的数据为存在返回数据，为null时返回默认值
        Author author = optional.orElseGet(() -> new Author(3L, "易", 14, "是这个世界在限削他的思维", null));
        System.out.println(author.getName());
    }

    /**
     * 安全的获取值,抛出异常
     */
    @Test
    public void test03() throws Throwable {
        Optional<Author> optional = optional();
        // 获取的数据为存在返回数据,不存在为null则抛出异常
        Author author = optional.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new RuntimeException("数据为null");
            }
        });
        System.out.println(author.getName());
    }

    /**
     * 过滤
     * 使用filter方法对数据进行过滤,如果原本是有数据的，但不满足条件，将返回一个空的Optional对象
     */
    @Test
    public void test04(){
        Optional<Author> optional = optional();
        optional.filter(author1 -> author1.getAge() > 30)
                .ifPresent(author1 -> System.out.println(author1.getName()));
    }

    /**
     * 类型转换
     * Optional还提供了map可以让我们的对数据进行转换，并且转换得到的数据也还是被Optional包装好的，保证了我们的使用安全。
     */
    @Test
    public void test05(){
        Optional<Author> optional = optional();
        optional.map(Author::getBooks).ifPresent(books -> books.forEach(book -> System.out.println(book.getName())));
    }
}
