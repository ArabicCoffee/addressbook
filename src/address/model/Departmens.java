package address.model;

import address.util.LocalDateAdapter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Departmens {

    private final StringProperty nameDepartment;
    private  Person [] contacts;
    private ObservableList<Departmens.Person> contactList = FXCollections.observableArrayList();
    private ObservableList<Departmens.Person> tempListChoices = FXCollections.observableArrayList();

    public void setContacts(Person[] contacts) {
        this.contacts = contacts;
    }

    public ObservableList<Person> getTempListChoices() {
        return tempListChoices;
    }

    public void setTempListChoices(ObservableList<Person> tempListChoices) {
        this.tempListChoices = tempListChoices;
    }

    public void setContactList(ObservableList<Departmens.Person> contactList) {
        this.contactList = contactList;
    }

    public ObservableList<Departmens.Person> getContactList() {
        return contactList;
    }




    public Departmens() {
        this(null);
    }

    public Departmens(String nameDepartment) {
        this.nameDepartment = new SimpleStringProperty(nameDepartment);

        /*contactList.add(new Person());
        contactList.add(new Person());
        contactList.add(new Person());
        contactList.add(new Person());*/
        //this.contacts = new Person[]{new Person()};
    }

    public String getNameDepartment() {
        return nameDepartment.get();
    }

    public StringProperty nameDepartmentProperty() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment.set(nameDepartment);
    }

    public Person[] getContacts() {
        return contacts;
    }

    public static class Person {
        private final StringProperty FIO;
        private final StringProperty position;
        private final ObjectProperty<LocalDate> Date;
        private final StringProperty phone;
        private final StringProperty mobilePhone;
        private final StringProperty comments;

        public Person() {
            this.FIO = new SimpleStringProperty("");
            this.position = new SimpleStringProperty("");
            Date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1980, 11, 24));
            this.phone = new SimpleStringProperty("");
            this.mobilePhone = new SimpleStringProperty("");
            this.comments = new SimpleStringProperty("");
        }

        public String getComments() {
            return comments.get();
        }

        public StringProperty commentsProperty() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments.set(comments);
        }

        public String getFIO() {
            return FIO.get();
        }

        public StringProperty FIOProperty() {
            return FIO;
        }

        public void setFIO(String FIO) {
            this.FIO.set(FIO);
        }

        public String getPosition() {
            return position.get();
        }

        public StringProperty positionProperty() {
            return position;
        }

        public void setPosition(String position) {
            this.position.set(position);
        }

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        public LocalDate getDate() {
            return Date.get();
        }

        public ObjectProperty<LocalDate> dateProperty() {
            return Date;
        }

        public void setDate(LocalDate date) {
            this.Date.set(date);
        }

        public String getPhone() {
            return phone.get();
        }

        public StringProperty phoneProperty() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone.set(phone);
        }

        public String getMobilePhone() {
            return mobilePhone.get();
        }

        public StringProperty mobilePhoneProperty() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone.set(mobilePhone);
        }
    }

}
