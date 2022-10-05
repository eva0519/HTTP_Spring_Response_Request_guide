package com.sparta.thymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter     // Model을 사용하려면 Setter를 반드시 달아줘야한다
public class Star {
    String name;
    int age;
}