package dolmatov.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "You name should have consisted of >0 characters")
    private String name;
    @Column(name = "author")
    @NotEmpty(message = "You name is empty")
    private String author;
    @Column(name = "year")
    @NotNull(message = "Type a year")
    @Max(value = 2023, message = "This book can't be made in the future")
    private int year;
    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person personId;
    @Column(name = "days_taken")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeAt;
    @Transient
    private boolean expired;

    public Book(){
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
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

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Date getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(int daysTaken) {
        new Date();
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
