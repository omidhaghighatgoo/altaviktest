package com.atlavik.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "SHOPPING_CART")
@Data
@NoArgsConstructor
public class ShoppingCart {

    public ShoppingCart(String countryCode, String currency, String created, String updated, Set<Product> products) {
        this.countryCode = countryCode;
        this.currency = currency;
        this.created = created;
        this.updated = updated;
        this.products = products;
    }

    @Id
    @GeneratedValue
    @org.hibernate.annotations.Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;


    @Column(name = "COUNTRY_CODE", nullable = false)
    private String countryCode;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "CREATED", nullable = false)
    private String created;

    @Column(name = "UPDATED")
    private String updated;

    @ManyToMany(cascade = CascadeType.PERSIST , fetch = FetchType.EAGER)
    @JoinTable(
            name = "SHOPPINGCARD_PRODUCTS",
            joinColumns = @JoinColumn(name="SHOPPING_ID"),
            inverseJoinColumns = @JoinColumn(name="PRODUCT_ID")
    )
    private Set<Product> products;


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", currency='" + currency + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", products=" + products +
                '}';
    }
}
