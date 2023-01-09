package com.qimu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: QiMu
 * @Date: 2023年01月09日 18:51
 * @Version:1.0
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book {
    private Long id;
    //书名
    private String name;
    //分类
    private String category;
    //详分
    private Integer score;

    private String intro;
}
