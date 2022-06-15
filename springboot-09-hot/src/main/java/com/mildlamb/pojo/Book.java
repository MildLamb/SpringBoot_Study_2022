package com.mildlamb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;

    public Book(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
    }
}
