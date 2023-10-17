package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealService_I {
    Meal get(int id, int userId) throws NotFoundException;

    Collection<Meal> getAll(int userId);

    void delete(int id, int userId) throws NotFoundException;

    Meal create(Meal meal, int userId);

    Meal update(Meal meal, int userId) throws NotFoundException;

    default List<Meal> intervalBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return intervalBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    List<Meal> intervalBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

}

