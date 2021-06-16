package com.laugh.membangunrestapi.models.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

/**
 * This class is for conecting into databasess
 */

@Entity
@Table(name = "tbl_product")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id"
//)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name is required")
    @Column(name = "product_name", length = 100)
    private String name;

    @NotEmpty(message = "description is required")
    @Column(name = "product_description", length = 500)
    private String description;

    private Double price;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "tbl_product_supplier",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    @JsonManagedReference
    private Set<Supplier> suppliers;
}
