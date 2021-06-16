package com.laugh.membangunrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class CategoryData {

    @NotEmpty(message = "Name is required")
    private String name;
}
