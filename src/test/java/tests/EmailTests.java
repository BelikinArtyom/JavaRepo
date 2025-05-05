package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для работы с junit")

public class EmailTests {

    @Test
    @DisplayName("Валидный адрес принят формой")
    void validEmail() {
        System.out.println("Hello World!");
    }

    @Test
    @DisplayName("Неверный адрес почты не пропускается валидацией")
    void invalidEmail() {
        System.out.println("Hello World!");
    }

    @Disabled ("tra-333")
    @Test
    @DisplayName("Пустой адрес почты не пропускается валидацей")
    void emptyEmail() {
        System.out.println("Hello World!");
        throw new AssertionError("Падение");
    }
}
