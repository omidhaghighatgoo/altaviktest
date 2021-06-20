package com.atlavik.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
public class Product {

    public Product(String description, String category, BigDecimal price, String created, String updated) {
        this.description = description;
        this.category = category;
        this.price = price;
        this.created = created;
        this.updated = updated;
    }

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)")
    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID id;


    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Column(name = "PRICE" , nullable = false)
    private BigDecimal price;

    @Column(name = "CREATED", nullable = false)
    private String created;

    @Column(name = "UPDATED")
    private String updated;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
