package com.lucask.bullseyes.domain.statement;

import com.lucask.bullseyes.BullseyesApplicationTests;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.lucask.bullseyes.domain.statement.StatementHelper.getHouseRent;
import static com.lucask.bullseyes.infrastruture.StatementResource.DEFAULT_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class StatementIntegrationTest extends BullseyesApplicationTests {

    @Autowired
    private StatementServiceImpl service;

    @Test
    public void should_create_statement() throws Exception {
        perform(post(DEFAULT_PATH, getHouseRent()))
                .andExpect(jsonPath("$.id", Matchers.notNullValue()));
    }

}
