package com.example;

import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import com.example.pojo.Address;
import com.example.pojo.Customer;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception{
        Customer customer = new Customer(1L, "jack", "15122199876",
                false, LocalDate.now(), Date.valueOf(LocalDate.now()),
                new Address(123L, "lalala apartment", new String[] { "a", null, "c" }),
                22);
        String json = JsonObject.toJson(customer, Customer.class);
        System.out.println(json);
    }
}
