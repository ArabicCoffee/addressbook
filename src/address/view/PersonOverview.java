package address.view;

import address.MainApp;
import address.model.Departmens;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class PersonOverview {
    @FXML
    private TableView<Departmens> tableViewDepartmens;
    @FXML
    private TableColumn<Departmens, String> nameDepartmentColumn;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Label labelNameDepartment;
    @FXML
    private TableView<Departmens.Person> tableViewContacts;
    @FXML
    private TableColumn<Departmens.Person, String> FIO;
    @FXML
    private TableColumn<Departmens.Person, String> position;
    @FXML
    private TableColumn<Departmens.Person, LocalDate> birthday;
    @FXML
    private TableColumn<Departmens.Person, String> phone;
    @FXML
    private TableColumn<Departmens.Person, String> mobilePhone;

    private MainApp mainApp;

    public PersonOverview() {

    }

    @FXML
    private void initialize() {
        textFieldSearch.setPromptText("Поиск");
        nameDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().nameDepartmentProperty());
        FIO.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        position.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        birthday.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        mobilePhone.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneProperty());

        tableViewDepartmens.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Departmens>() {
            @Override
            public void changed(ObservableValue<? extends Departmens> observable, Departmens oldValue, Departmens newValue) {
                showContacts(newValue);

            }
        });

    }

    public void showContacts(Departmens departmens) {
        if (departmens != null) {
            labelNameDepartment.setText(departmens.getNameDepartment());

            //FIO.setCellValueFactory();
            //FIO.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
            /*position.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
            birthday.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
            phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
            mobilePhone.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneProperty());
            tableViewContacts.setItems(mainApp.getContacts());*/

            //birthday.(DateUtil.format(person.getBirthday()));
        }
    }

    public void handleDeletePerson() {
        int selectedIndex = tableViewContacts.getSelectionModel().getSelectedIndex();
        tableViewContacts.getItems().remove(selectedIndex);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tableViewDepartmens.setItems(mainApp.getDepartmens());
        tableViewContacts.setItems(mainApp.getContacts());
    }
}
