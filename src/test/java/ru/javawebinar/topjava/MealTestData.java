package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL1_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL_1 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_3 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_4 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL_5 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_6 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 30, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 30, 21, 0), "Админ ужин", 1500);

    public static final List<Meal> MEALS = Arrays.asList(MEAL_6, MEAL_5, MEAL_4, MEAL_3, MEAL_2, MEAL_1);

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL_2.getDateTime(), "Новый завтрак", 150);
    }
}

