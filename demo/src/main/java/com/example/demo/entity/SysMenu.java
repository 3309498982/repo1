package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class SysMenu {
    private Integer id;

    private Integer parentId;

    private String menuName;

    private String href;

    private String permission;

    private List<SysMenu> children;

}