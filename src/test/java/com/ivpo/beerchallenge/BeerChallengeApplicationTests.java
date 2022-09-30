package com.ivpo.beerchallenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest
class BeerChallengeApplicationTests {

	@Test
    void contextLoads(ApplicationContext context) {
        assertThat(context,is(notNullValue()));
    }

}
