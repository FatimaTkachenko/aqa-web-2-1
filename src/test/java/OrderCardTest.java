package ru.netology.web;

import org.junit.jupiter.api.Test;
import ru.netology.web.page.OrderCardPage;

public class OrderCardTest {
    @Test
    void shouldOrderCard() {
        OrderCardPage page = new OrderCardPage();
        page.validOrder("Москва", "Иван Иванов", "+79991234567");
        page.quit();
    }
}
