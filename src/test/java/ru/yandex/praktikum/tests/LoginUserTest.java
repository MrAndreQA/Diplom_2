package ru.yandex.praktikum.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.BasicScript;
import ru.yandex.praktikum.UserSteps;
import ru.yandex.praktikum.GenerateUser;
import ru.yandex.praktikum.model.UserCredentials;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.is;
public class LoginUserTest extends BasicScript {
    private UserCredentials userCredentials;

    @Before
    public void tearUp() {
        user = GenerateUser.getUserRandom();
        token = UserSteps.create(user).then().extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (token != null) UserSteps.delete(token);
    }

    @Test
    @DisplayName("Логин пользователя")
    @Description("Логин под уже существующим пользователем")
    public void loginCorrectUserTest() {
        userCredentials = UserCredentials.from(user);
        UserSteps.login(userCredentials)
                .then()
                .assertThat()
                .statusCode(HTTP_OK)
                .and()
                .body("success", is(true));
    }

    @Test
    @DisplayName("Логин пользователя c неверным логином")
    @Description("Логин неправильной учетной записи")
    public void loginUserWithWrongEmailTest() {
        userCredentials = UserCredentials.withIncorrectEmail(user);
        UserSteps.login(userCredentials)
                .then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and()
                .body("success", is(false))
                .body("message", is("email or password are incorrect"));
    }

    @Test
    @DisplayName("Логин пользователя c неверным паролем")
    @Description("Логин неправильной учетной записи")
    public void loginUserWithWrongPasswordTest() {
        userCredentials = UserCredentials.withIncorrectPassword(user);
        UserSteps.login(userCredentials)
                .then()
                .assertThat()
                .statusCode(HTTP_UNAUTHORIZED)
                .and()
                .body("success", is(false))
                .body("message", is("email or password are incorrect"));
    }
}
