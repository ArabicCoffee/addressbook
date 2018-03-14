package address.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Departmens {

    private final StringProperty nameDepartment;
    private  Person [] contacts;


    public Departmens() {
        this(null);
    }

    public Departmens(String nameDepartment) {
        this.nameDepartment = new SimpleStringProperty(nameDepartment);
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

    public class Person {
        private final StringProperty FIO;
        private final StringProperty position;
        private final ObjectProperty<LocalDate> Date;
        private final StringProperty phone;
        private final StringProperty mobilePhone;

        public Person() {
            this.FIO = new SimpleStringProperty("Томачинский");
            this.position = new SimpleStringProperty("Boss");
            Date = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
            this.phone = new SimpleStringProperty("777");
            this.mobilePhone = new SimpleStringProperty("89997776666");
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
