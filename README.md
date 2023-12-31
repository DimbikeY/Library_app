# Библиотечное веб-приложение  

Добро пожаловать в репозиторий библиотечного веб-приложения! Это приложение разработано для местной библиотеки, которая хочет перейти на цифровой учет книг и читателей. Проект предполагает создание веб-приложения, позволяющего библиотекарям регистрировать читателей, выдавать книги и освобождать их после возврата, а также возможность следить, кто не принес обратно выданную книгу после 10-ти полных дней

## Описание проекта

Сущности, с которыми работает приложение:
- **Человек** (поля: ФИО, год рождения)
- **Книга** (поля: название, автор, год)

Отношение между сущностями: Один ко Многим. У человека может быть множество книг, но каждая книга может принадлежать только одному человеку. В базе данных проекта существуют две таблицы - `Person` и `Book`. Для всех таблиц настроена автоматическая генерация идентификаторов (id).
## Версия 3.0 (17.10.23): Перевод приложения на spring-boot c spring-security
#### ....в процессе....

## Версия 2.0 (08.10.23): Перевод приложения с jdbcTemplate на Hibernate и java-data-jpa
### Обновленная схемы 
#### **Схема БД** 
![Схема_БД](https://github.com/DimbikeY/Library_app/blob/main/schemes/%D0%91%D0%94.png)
#### **Основная композиция** ####
![Схема_основных_компонентов](https://github.com/DimbikeY/Library_app/blob/main/schemes/Components.png)
#### **Поля с частичной валидацией** ####
![Поля](https://github.com/DimbikeY/Library_app/blob/main/schemes/Fields_with_valid.png)
#### Обновленный функционал
1. **Пагинация и сотрировка** - задание в параметрах запроса о выводе определенного количества книг на определенной странице с одновременной возможностью сортировки по полю
2. **Регистронезависимый поиск по книгам** - возможность поиска книг по начальным буквам с выводом информации, кому она принадлежит на данный момент/свободная
3. **Проверка на просроченный возврат** - на странице читателя красным выведены те имена, которые не принесли назад книгу после 10-го дня

#### Описание веток
- **main** - основная для релиза
- **develop** - основная для разработки
- **release N.Mv** - для релизов после реализации фичи
- **hotfix** - для минорных исправлений после слияния в main
- **feature** - для реализации фич

## 1.0 версия
### Схемы
- ** Схема БД ** 
  ![Схема_БД](https://github.com/DimbikeY/Library_app/blob/main/schemes/DB_scheme.png)

- ** Основная композиция
  ![Схема_основных компонентов](https://github.com/DimbikeY/Library_app/blob/main/schemes/Main_scheme.png)

#### Функциональность

Проект включает следующие функциональные элементы:

1. **Страницы добавления, изменения и удаления человека**.
2. **Страницы добавления, изменения и удаления книги**.
3. **Страница со списком всех людей**. Люди на этой странице кликабельны, и при клике осуществляется переход на страницу человека.
4. **Страница со списком всех книг**. Книги также кликабельны, и при клике осуществляется переход на страницу книги.
5. **Страница человека**. На этой странице отображаются значения полей человека и список книг, которые он взял. Если человек не взял ни одной книги, вместо списка выводится текст "Человек пока не взял ни одной книги".
6. **Страница книги**. Здесь показаны значения полей книги и имя человека, который взял эту книгу. Если книга не была взята никем, выводится текст "Эта книга свободна".
7. **Освобождение книги**. На странице книги, если книга взята человеком, рядом с его именем появляется кнопка "Освободить книгу". Эта кнопка позволяет библиотекарю освободить книгу после ее возврата, и она снова становится доступной для других читателей.
8. **Назначение книги**. На странице книги, если книга свободна, предоставляется выпадающий список с возможностью выбора человека и кнопка "Назначить книгу". Эта кнопка позволяет библиотекарю назначить книгу выбранному человеку, и она появляется в списке книг этого человека.
9. **Валидация полей**. Все поля должны проходить валидацию с помощью аннотации `@Valid` и Spring Validator.

#### Технологии

Проект разработан с использованием следующих технологий:

- Java
- Spring API
- Thymeleaf (для шаблонов)
- jdbcTemplate (для работы с базой данных)
- HTML/CSS (для визуального интерфейса)

#### Установка и запуск

Чтобы установить и запустить это веб-приложение, выполните следующие шаги:

1. Клонируйте репозиторий на свой компьютер
2. Настройте подключение к базе данных в файле resources/database.properties.origin (для этого удалите постфикс .origin) и заполните URL, username, password своими значениями. console.sql содержит данные для вашей БД
3. Запустите приложение с помощью TOMCAT.
4. К сожалению, jdbcTemplate плохо работает с Docker. В будущем Hibernate в сочетании с JPA будет размещаться в отдельном контейнере для удобства развертывания программы. 

