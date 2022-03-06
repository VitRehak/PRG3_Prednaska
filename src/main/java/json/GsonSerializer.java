package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.MySerializer;
import model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class GsonSerializer implements MySerializer {

    String path = "Gson.json";
    Gson gson;

    public GsonSerializer() {
        gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateConverter()).setPrettyPrinting().create();
    }

    @Override
    public void serialize(Person person) {
        Type targetType = new TypeToken<Person>() {
        }.getType();
        String json = gson.toJson(person, targetType);
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person deserialize() {
        Person person = null;
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                json.append(line);
            }
            Type targetType = new TypeToken<Person>() {
            }.getType();
            person = gson.fromJson(json.toString(), targetType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }
}
