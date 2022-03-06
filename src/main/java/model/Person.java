package model;

import XML.LocalDateConverter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateConverter.class)
    private LocalDate dateOfBirth;

    @XmlElement(name = "person")
    private final List<Person> children;

    public Person(String name, int age, LocalDate dateOfBirth) {
        this.children = new ArrayList<>();
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
        this.children = new ArrayList<>();
    }


    public void addChildren(Person child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Person> getChildren() {
        return children;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "name " + name + "\n" +
                "age " + age + "\n" +
                "date of birth " + dateOfBirth + "\n" +
                "children count " + children.size();
    }
}
