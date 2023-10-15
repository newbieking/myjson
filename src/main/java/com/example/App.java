package com.example;

import java.sql.Date;
import java.time.LocalDate;

import com.example.pojo.Address;
import com.example.pojo.Customer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer(1L, "jack", "15122199876", 
                                false, LocalDate.now(), Date.valueOf(LocalDate.now()), 
                                    new Address(123L, "lalala apartment", new String[]{"a", null, "c"}),
                                    22);
        String json = JsonObject.toJson(customer, Customer.class);
        System.out.println(json);
    }

}
