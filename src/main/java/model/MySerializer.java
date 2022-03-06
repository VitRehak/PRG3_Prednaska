package model;

public interface MySerializer {

    void serialize(Person person);

    Person deserialize();
}
