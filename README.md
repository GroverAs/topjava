[Java Enterprise Online Project](https://javaops.ru/view/topjava)
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), DataTables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/04ef4925e8ba44449cb7d576413285ec)](https://app.codacy.com/gh/GroverAs/topjava/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

- Администратор может создавать / редактировать / удалять пользователей, пользователи - управлять своим профилем и данными (питание) через пользовательский интерфейс (AJAX), а остальные - с обычной авторизацией;
- Фильтрация блюд по дате и времени;
- Цвет записи о приеме пищи зависит от того, превышает ли дневная сумма калорий "Дневной лимит калорий" (редактируемые параметры профиля пользователя).;
- Все REST-интерфейсы прошли покрыты unit - тестами (JUnit).

Стек приложения:
Spring MVC, Spring Data JPA, Spring Security, Spring Security Test, Hibernate ORM, Hibernate Validator, SLF4J, Json Jackson, JSP, JSTL, Apache Tomcat, WebJars, плагин DataTables, EHCACHE, PostgreSQL, JUnit, Hamcrest, jQuery, Bootstrap.

Выполнен деплой на VDS сервер. Результат можно посмотреть здесь http://myjavapro.ru/topjava