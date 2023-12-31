package com.example;

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
        String json = JsonObject.toJson(customer);
        System.out.println(json);

        String json2 = JsonObject.toJson(new String[]{"one", "two", null, "three"});
        System.out.println(json2);
    }
}
