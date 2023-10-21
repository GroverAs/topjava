package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.inmemory.InMemoryUserRepository.*;
@Repository
public class InMemoryMealRepository implements MealRepository {
//    public static final Comparator<Meal> MEAL_COMPARATOR = (meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime());
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, USER_ID));

        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Админ завтрак", 500), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 14, 0), "Админ обед", 900), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Админ ужин", 610), ADMIN_ID);
    }

        @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, initialCapacity -> new ConcurrentHashMap<Integer, Meal>(initialCapacity));
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            return meals.put(meal.getId(), meal);
        }
        // handle case: update, but not present in storage
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getFilteredByStream(userId, meal -> true);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getFilteredByStream(userId, meal ->
                Util.isBetweenHalfOpen(meal.getDateTime(), startDateTime, endDateTime));
    }
    private List<Meal> getFilteredByStream(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals.values()
                .stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());

    }
}

