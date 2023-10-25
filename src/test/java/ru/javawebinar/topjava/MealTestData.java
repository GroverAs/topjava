package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int MEAL_ID = START_SEQ + 3;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Meal meal_1 = new Meal(MEAL_ID, of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal meal_2 = new Meal(MEAL_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal meal_3 = new Meal(MEAL_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal meal_4 = new Meal(MEAL_ID + 3, of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal meal_5 = new Meal(MEAL_ID + 4, of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal meal_6 = new Meal(MEAL_ID + 5, of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal adminMeal_1 = new Meal(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 30, 14, 0), "Админ ланч", 510);
    public static final Meal adminMeal_2 = new Meal(ADMIN_MEAL_ID + 1, of(2020, Month.JANUARY, 30, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> meals = Arrays.asList(meal_6, meal_5, meal_4, meal_3, meal_2, meal_1);

    public static Meal getCreated() {
        return new Meal(null, of(2023, Month.OCTOBER, 25, 14, 0), "Созданный обед", 1180);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL_ID, meal_1.getDateTime(), "Обновленный завтрак", 450);
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}


