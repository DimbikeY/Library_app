package dolmatov.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "year")
    @NotNull(message = "Type a year")
    @Min(value = 1900, message = "You can't be so old!")
    @Max(value = 2023, message = "This year hasn't passed")
    private int year;
    @Column(name = "name")
    @NotEmpty(message = "This book should contain its name")
    @Pattern(regexp = "^\\w+ \\w+$", message = "You should have added your name according to that pattern: 'Name Surname' ")
    private String name;
    @OneToMany(mappedBy = "personId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> bookList;

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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
