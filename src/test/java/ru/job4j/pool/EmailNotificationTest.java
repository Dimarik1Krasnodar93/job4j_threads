package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class EmailNotificationTest {

    @Test
    void emailTo() throws InterruptedException {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("Ivan", "vanya@mail.ru"));
        emailNotification.close();
        Thread.sleep(500);
        int expected = 1;
        assertThat(emailNotification.getSendedMessages().size()).isEqualTo(expected);
    }
}