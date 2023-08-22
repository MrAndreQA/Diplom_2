package ru.yandex.praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.model.User;

public class GenerateUser {
    public static User getUserRandom() {
        return new User(
                RandomStringUtils.randomAlphanumeric(7) + "@yandex.ru",
                RandomStringUtils.randomAlphanumeric(12),
                RandomStringUtils.randomAlphabetic(12)
        );
    }

    public static User getUserWithoutEmail() {
        return new User(
                "",
                RandomStringUtils.randomAlphanumeric(12),
                RandomStringUtils.randomAlphabetic(12)
        );
    }

    public static User getUserWithoutName() {
        return new User(
                RandomStringUtils.randomAlphanumeric(7) + "@yandex.ru",
                "",
                RandomStringUtils.randomAlphabetic(12)
        );
    }

    public static User getUserWithoutPassword() {
        return new User(
                RandomStringUtils.randomAlphanumeric(12),
                RandomStringUtils.randomAlphabetic(12),
                ""
        );
    }

    public static User getUserWithModified() {
        return new User("usersemen@yandex.ru", "Usersema", "passwordinelvish");
    }
}
