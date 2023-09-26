package dolmatov.models;

import jakarta.validation.constraints.*;

public class Person {
    // Перегруженные пустые методы для того, чтобы можно было создавать ModelAttribute с 0 значениями + id значение = 0
    private int id;
    @NotNull(message = "Type a year")
    @Min(value = 1900, message = "You can't be so old!")
    @Max(value = 2023, message = "This year hasn't passed")
    private int year;
    @NotEmpty(message = "This book should contain its name")
    @Pattern(regexp = "^\\w+ \\w+$", message = "You should have added your name according to that pattern: 'Name Surname' ")
    private String name;

    public Person() {
    }

    public Person(int id, int year, String name) {
        this.id = id;
        this.year = year;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
