package com.laugh.membangunrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * buat enkapsulasi data.
 * Pembungkus object untuk ditransfer nantinya
 */
@Setter
@Getter
public class ResponseData<T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T payload;

}
