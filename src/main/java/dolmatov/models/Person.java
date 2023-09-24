package dolmatov.models;

public class Person {
    // Перегруженные пустые методы для того, чтобы можно было создавать ModelAttribute с 0 значениями + id значение = 0
    private int id;
    private int year;
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
