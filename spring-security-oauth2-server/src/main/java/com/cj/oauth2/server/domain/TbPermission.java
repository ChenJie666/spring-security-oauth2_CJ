package com.cj.oauth2.server.domain;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "tb_permission")
public class TbPermission implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "parent_id")
    private Long parent_id;

    @Column(name = "name")
    private String name;

    @Column(name = "enname")
    private String enname;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

}
