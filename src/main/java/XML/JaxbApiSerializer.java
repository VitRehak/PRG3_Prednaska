package XML;

import model.MySerializer;
import model.Person;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;


public class JaxbApiSerializer implements MySerializer {

    JAXBContext jaxbContext;
    String path = "JaxbApi.xml";


    public JaxbApiSerializer() {
        try {
            jaxbContext = JAXBContext.newInstance(Person.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serialize(Person person) {
        try {
            Marshaller marshallerObj = jaxbContext.createMarshaller();
            marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallerObj.marshal(person, new FileOutputStream(path));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person deserialize() {
        Person person = null;
        try {
            Unmarshaller Unmarshaller = jaxbContext.createUnmarshaller();
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileReader(path));
            person = (Person) Unmarshaller.unmarshal(xmlStreamReader);
        } catch (JAXBException | XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

}
