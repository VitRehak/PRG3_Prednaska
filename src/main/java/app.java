import XML.DOM4JSerializer;
import XML.JaxbApiSerializer;
import json.GsonSerializer;
import json.JacksonDatabind;
import model.Person;

import java.time.LocalDate;

public class app {


    public static void main(String[] args) {
        Person p = new Person("Honza", 27, LocalDate.of(1995, 5, 26));
        Person ch1 = new Person("Pepa", 6, LocalDate.of(2016, 7, 2));
        Person ch2 = new Person("Aneta", 8, LocalDate.of(2014, 1, 15));
        p.addChildren(ch1);
        p.addChildren(ch2);

        gson(p);
        jacksonCore(p);
        jaxbApi(p);
        dOM4J(p);
    }

    public static void gson(Person p) {
        GsonSerializer gsonSerializer = new GsonSerializer();
        gsonSerializer.serialize(p);
        Person newP = gsonSerializer.deserialize();
        System.out.println(newP);
    }

    public static void jacksonCore(Person p) {
        JacksonDatabind jacksonCoreSerializer = new JacksonDatabind();
        jacksonCoreSerializer.serialize(p);
        Person newP = jacksonCoreSerializer.deserialize();
        System.out.println(newP);
    }

    public static void jaxbApi(Person p) {
        JaxbApiSerializer jaxbApiSerializer = new JaxbApiSerializer();
        jaxbApiSerializer.serialize(p);
        Person newP = jaxbApiSerializer.deserialize();
        System.out.println(newP);
    }

    public static void dOM4J(Person p) {
        DOM4JSerializer dom4JSerializer = new DOM4JSerializer();
        dom4JSerializer.serialize(p);
        Person newP = dom4JSerializer.deserialize();
        System.out.println(newP);
    }
}
