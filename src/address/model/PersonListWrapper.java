package address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List <Departmens> persons;
    private List <Departmens.Person> choices;
    //это необязательное имя, которое мы можем задать для элемента
    @XmlElement(name = "person")
    public List <Departmens> getPersons() {
        return persons;
    }
    public void setPersons(List<Departmens> persons) {
        this.persons = persons;
    }

    @XmlElement(name = "choices")
    public List <Departmens.Person> getChoices() {
        return choices;
    }

    public void setChoices(List<Departmens.Person> choices) {
        this.choices = choices;
    }

}