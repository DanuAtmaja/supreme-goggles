package com.laugh.membangunrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class SupplierData {

    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "address is required")
    private String address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
}
