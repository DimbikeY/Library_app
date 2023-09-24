package dolmatov.models;

public class Book {
    // Перегруженные пустые методы для того, чтобы можно было создавать ModelAttribute с 0 значениями + id значение = 0
    private int id;
    private String name;
    private String author;
    private int year;
    private int personId;

    public Book(){

    }

    public Book(int id, String name, String author, int year, int personId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
