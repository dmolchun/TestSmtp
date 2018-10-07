package com.dibragimov.test.testsmtp;

import com.dibragimov.test.testsmtp.telegram.TelegramService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSmtpApplicationTests {
    @Autowired
    TelegramService telegramService;

    @Test
    public void testRegisterDeregister() {
        Assert.assertEquals("Successfully registered", telegramService.register("test@mail.ru", 12L));
        Assert.assertEquals("Successfully deregistered", telegramService.deregister("test@mail.ru", 12L));
    }

}
