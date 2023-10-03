package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

//        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<Object, Integer> sumOfCalories = new HashMap<>();
        for (UserMeal userMeal : meals) {
            LocalDate mealDate = userMeal.getDateTime().toLocalDate();
            sumOfCalories.put(mealDate, sumOfCalories.getOrDefault(mealDate, 0) + userMeal.getCalories());
        }
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        for (UserMeal userMeal : meals) {
            LocalDateTime dateTime = userMeal.getDateTime();
            if (TimeUtil.isBetweenHalfOpen(dateTime.toLocalTime(), startTime, endTime)) {
                userMealWithExcesses.add(new UserMealWithExcess(dateTime, userMeal.getDescription(), userMeal.getCalories(),
                        sumOfCalories.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, List<UserMeal>> mealFilteredList = meals
                .stream()
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .sorted(Comparator.comparing(UserMeal::getDateTime))
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate()));

        List<UserMealWithExcess> listWithExcess = new ArrayList<>();
        for (Map.Entry<LocalDate, List<UserMeal>> entry : mealFilteredList.entrySet()) {
            int[] calories = {0};
            entry.getValue().forEach(s -> {
                calories[0] += s.getCalories();
                listWithExcess.add(new UserMealWithExcess(
                        s.getDateTime(),
                        s.getDescription(),
                        s.getCalories(),
                        calories[0] >= caloriesPerDay));
            });
        }
        return listWithExcess;
    }
}