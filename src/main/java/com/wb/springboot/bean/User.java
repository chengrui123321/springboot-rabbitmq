package com.wb.springboot.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User
 */
@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String username;

    private String password;

}
