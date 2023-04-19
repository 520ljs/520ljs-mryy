package com.ss.mryy.entity;

import java.io.Serializable;

/**
 * (Projecttype)实体类
 *
 * @author makejava
 * @since 2023-04-19 22:27:55
 */
public class Projecttype implements Serializable {
    private static final long serialVersionUID = 597407535451713300L;
    /**
     * 项目类型id
     */
    private Long id;
    /**
     * 项目类型
     */
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

