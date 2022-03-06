package XML;

import model.MySerializer;
import model.Person;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class DOM4JSerializer implements MySerializer {

    private final String path = "DOM4J.xml";
    SAXReader saxReader;

    public DOM4JSerializer() {
        saxReader = new SAXReader();
    }

    @Override
    public void serialize(Person person) {
        Document document = DocumentHelper.createDocument();
        Element root = DocumentHelper.createElement("person");
        document.add(procSer(root, person));

        OutputFormat format = OutputFormat.createPrettyPrint();
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(path), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Element procSer(Element parent, Person person) {
        parent.addAttribute("name", person.getName());
        parent.addAttribute("age", String.valueOf(person.getAge()));
        parent.addAttribute("date_of_birth", person.getDateOfBirth().toString());
        person.getChildren().forEach(ch -> {
            Element newEl = DocumentHelper.createElement("person");
            parent.add(procSer(newEl, ch));
        });
        return parent;
    }


    @Override
    public Person deserialize() {
        Person person = null;
        try {
            Document document = saxReader.read(path);
            Element el = (Element) document.selectSingleNode("person");
            person = proDeser(el);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return person;
    }

    private Person proDeser(Element parent) {
        Person person = new Person();
        person.setName(parent.attributeValue("name"));
        person.setAge(Integer.parseInt(parent.attributeValue("age")));
        String dateOfBirth = parent.attributeValue("date_of_birth");
        String[] timeData = dateOfBirth.split("-");
        int year = Integer.parseInt(timeData[0]);
        int month = Integer.parseInt(timeData[1]);
        int day = Integer.parseInt(timeData[2]);
        person.setDateOfBirth(LocalDate.of(year, month, day));

        List<Element> els = parent.selectNodes("person");
        els.forEach(e -> person.addChildren(proDeser(e)));
        return person;
    }
}
