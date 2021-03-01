package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

// 값타입은 변경 불가능하게 설계해야함.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
