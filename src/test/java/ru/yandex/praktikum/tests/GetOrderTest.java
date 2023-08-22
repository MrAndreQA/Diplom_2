package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.BasicScript;
import ru.yandex.praktikum.OrderSteps;
import ru.yandex.praktikum.UserSteps;
import ru.yandex.praktikum.GenerateUser;
import ru.yandex.praktikum.model.Order;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.is;

public class GetOrderTest extends BasicScript {
    @Before
    public void tearUp() {
        user = GenerateUser.getUserRandom();
        token = UserSteps.create(user).then().extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (token!=null) UserSteps.delete(token);
    }

    @Test
    @DisplayName("Получение заказа пользователя с авторизацией")
    @Description("Успешное получение заказа авторизованного пользователя")
    public void getOrderWithAuthUser() {
        ingredients = Order.makeIngredients();
        OrderSteps.create(ingredients, token);
        OrderSteps.getOrdersWithAuth(token)
                .then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and()
                .body("success", is(true));
    }

    @Test
    @DisplayName("Получение заказа пользователя без авторизации")
    @Description("Ошибка получения заказа пользователя без авторизации")
    public void getOrderWithoutAuthUser() {
        OrderSteps.getOrdersWithoutAuth()
                .then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and()
                .body("success", is(false))
                .body("message", is("You should be authorised"));
    }
}
