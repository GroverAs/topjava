package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal actual = service.get(MEAL_ID, USER_ID);
        assertMatch(actual, meal_6);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), meal_6, meal_5, meal_4, meal_3, meal_2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(MEAL_ID, 1);
    }

    @Test
    public void getBetweenInclusive() {
        assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.JANUARY, 30),
                LocalDate.of(2015, Month.JANUARY, 30), USER_ID), meal_3, meal_2, meal_1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), meals);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() {
        service.update(meal_2, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal created = MealTestData.getCreated();
        service.create(created, USER_ID);
        assertMatch(service.getAll(USER_ID), created, meal_6, meal_5, meal_4, meal_3, meal_2, meal_1);
    }
}