package com.ss.mryy.entity;

import java.io.Serializable;

/**
 * (Tecpro)实体类
 *
 * @author makejava
 * @since 2023-04-20 01:52:44
 */
public class Tecpro implements Serializable {
    private static final long serialVersionUID = 225973974223074453L;

    private Long id;

    private Long tecid;

    private Long proid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTecid() {
        return tecid;
    }

    public void setTecid(Long tecid) {
        this.tecid = tecid;
    }

    public Long getProid() {
        return proid;
    }

    public void setProid(Long proid) {
        this.proid = proid;
    }

}

