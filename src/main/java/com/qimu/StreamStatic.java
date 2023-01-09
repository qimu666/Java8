package com.qimu;

import com.qimu.pojo.Author;
import com.qimu.pojo.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author QiMu
 * @Date 2023年01月09日 1858
 * @Version1.0
 * @Description
 */
public class StreamStatic {
    public static void main(String[] args) {
        List<Author> authorList = getAuthors();
        // 打印所有作家,并且其中的元素不能重复
        authorList.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));

    }

    public static List<Author> getAuthors() {

        //    数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);

        Author author2 = new Author(2L, "业拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限削他的思维", null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "刀的两侧", "哲学,爱情", 80, "用一把刀划"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "风吹不到", "爱情", 56, "风吹不到"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣"));
        books3.add(new Book(6L, "我的剑", "个人专辑", 100, "领略世界的尽头"));
        books3.add(new Book(6L, "我的剑", "个人专辑", 100, "领略世界的尽头"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
//        author4.setBooks(books3);

        return new ArrayList<>(Arrays.asList(author, author2, author3));

    }
}
