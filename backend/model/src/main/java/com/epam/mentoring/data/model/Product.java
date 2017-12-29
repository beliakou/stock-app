package com.epam.mentoring.data.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

//@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private Integer id;

    @Column(name = "name")
	private String name;

    @Column(name = "price")
	private BigDecimal price;

    @Transient
	private ProductType type;

    @Transient
	public ProductType getType(){
        return type;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (price != null ? !(price.compareTo(product.price) == 0) : product.price != null) return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (price != null ? price.hashCode() : 0);
		return result;
	}
}
