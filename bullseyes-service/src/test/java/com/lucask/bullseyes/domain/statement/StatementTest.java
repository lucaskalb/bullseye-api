package com.lucask.bullseyes.domain.statement;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class StatementTest {

    @Test
    public void change_status_test() {
        Statement stat = new Statement();

        stat.paid();
        Assert.assertTrue(stat.isPaid());
        Assert.assertThat(stat.getStatus(), Matchers.equalTo(StatementStatus.PAID));

        stat.open();
        Assert.assertTrue(stat.isOpen());
        Assert.assertThat(stat.getStatus(), Matchers.equalTo(StatementStatus.OPEN));

        stat.canceled();
        Assert.assertTrue(stat.isCanceled());
        Assert.assertThat(stat.getStatus(), Matchers.equalTo(StatementStatus.CANCELED));

    }


}
