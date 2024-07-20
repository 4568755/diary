package com.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String mail;
    private String userName;
    private String passWord;



}
