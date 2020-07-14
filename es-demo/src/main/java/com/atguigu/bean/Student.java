package com.atguigu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wx
 * @create 2020-07-11 9:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String stu_id;
    private String name;
    private String sex;
    private String favo;
}
