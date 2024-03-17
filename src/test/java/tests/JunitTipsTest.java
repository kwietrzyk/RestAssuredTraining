package tests;

import configuration.ProvideRandomStringResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class JunitTipsTest {

    @Test
    @ExtendWith(ProvideRandomStringResolver.class)  // if we would need different types of data then we would need another Provider class
    public void shouldCreateRandomStudentNameTest(String name, String address, String city) {
        String firstName = name;
        String addressName = address;
        String cityName = city;

        System.out.println("Name: " + firstName);
        System.out.println("Address: " + addressName);
        System.out.println("City: " + cityName);
    }


}
