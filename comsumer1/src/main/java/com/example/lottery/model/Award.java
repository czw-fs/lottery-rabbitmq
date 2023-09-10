package com.example.lottery.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Award implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String describe;
    private Integer count;
}
