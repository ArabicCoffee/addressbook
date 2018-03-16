package address.model;

import address.MainApp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "Departments")
    public class PersonListWrapper {
    private List<Departmens> departmens;



    @XmlElement(name = "department")
    public List<Departmens> getDepartmens() {
        return departmens;
    }

    public void setDepartmens(List<Departmens> persons) {
        this.departmens = persons;
    }


}
