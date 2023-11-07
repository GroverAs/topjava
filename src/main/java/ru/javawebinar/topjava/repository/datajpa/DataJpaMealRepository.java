package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository) {
        this.crudMealRepository = crudRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        meal.setUser(crudUserRepository.getReferenceById(userId));
        if (get(meal.getId(), userId) == null) {
            if (!meal.isNew()) {
                return null;
            }
        }
        return crudMealRepository.save(meal);
}

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) !=0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudMealRepository.getReferenceById(userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudMealRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }
}
