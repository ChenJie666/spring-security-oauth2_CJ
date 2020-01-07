package com.cj.oauth2.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_content")
public class TbContent implements Serializable {

    private static final long serialVersionUID = 1L;    //TODO 区分同一类的不同版本，防止传输完成后还原错误

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "title_desc")
    private String titleDesc;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "url")
    private String url;

    @Column(name = "pic")
    private String pic;

    @Column(name = "pic2")
    private String pic2;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;
}
