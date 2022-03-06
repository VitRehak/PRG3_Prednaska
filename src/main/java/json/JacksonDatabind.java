package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import model.MySerializer;
import model.Person;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonDatabind implements MySerializer {

    String path = "JacksonDatabind.json";
    ObjectMapper objectMapper;

    public JacksonDatabind() {
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JSR310Module());
    }

    @Override
    public void serialize(Person person) {
        try {
            objectMapper.writeValue(new FileWriter(path), person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person deserialize() {
        try {
            return objectMapper.readValue(new FileReader(path),Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
