package address.view;

import address.MainApp;
import address.model.Departmens;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML
    private ButtonBar buttonBarContacts;
    @FXML
    private Label labelBirthday;
    @FXML
    private Label labelHolidays;
    @FXML
    private Label choices;

    @FXML
    private TableView<Departmens.Person> tableViewBirthday;
    @FXML
    private TableColumn<Departmens.Person, String> FIOb;
    @FXML
    private TableColumn<Departmens.Person, String> positionb;
    @FXML
    private TableColumn<Departmens.Person, LocalDate> birthdayb;
    @FXML
    private TableColumn<Departmens.Person, String> phoneb;
    @FXML
    private TableColumn<Departmens.Person, String> mobilePhoneb;
    @FXML
    private TableView<Departmens> tableViewHolidays;
    @FXML
    private TableColumn<Departmens, String> nameHoliday;
    @FXML
    private TableColumn<Departmens, String> dateHoliday;
    @FXML
    private TableView<Departmens.Person> tableViewChoices;
    @FXML
    private TableColumn<Departmens.Person, String> FIOc;
    @FXML
    private TableColumn<Departmens.Person, String> positionc;
    @FXML
    private TableColumn<Departmens.Person, LocalDate> birthdayc;
    @FXML
    private TableColumn<Departmens.Person, String> phonec;
    @FXML
    private TableColumn<Departmens.Person, String> mobilePhonec;



    private MainApp mainApp;

    public PersonOverview() {
    }

    @FXML
    private void initialize() {
        textFieldSearch.setPromptText("Поиск");
        visibleMain();
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

    @FXML
    private void handleMainPage() {
        visibleMain();
    }



    public void visibleMain() {

        labelNameDepartment.setVisible(false);
        tableViewContacts.setVisible(false);
        buttonBarContacts.setVisible(false);

        labelBirthday.setVisible(true);
        tableViewBirthday.setVisible(true);
        labelHolidays.setVisible(true);
        tableViewHolidays.setVisible(true);
        choices.setVisible(true);
        tableViewChoices.setVisible(true);
    }

    public void visiblePerson() {
        labelNameDepartment.setVisible(true);
        tableViewContacts.setVisible(true);
        buttonBarContacts.setVisible(true);

        labelBirthday.setVisible(false);
        tableViewBirthday.setVisible(false);
        labelHolidays.setVisible(false);
        tableViewHolidays.setVisible(false);
        choices.setVisible(false);
        tableViewChoices.setVisible(false);
    }

    public void showContacts(Departmens departmens) {
        if (departmens != null) {
            visiblePerson();
            labelNameDepartment.setText(departmens.getNameDepartment());
            tableViewContacts.setItems(departmens.getContactList());
        }
    }

    public void handleDeletePerson() {
        int selectedIndex = tableViewContacts.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableViewContacts.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран контакт");
            alert.setContentText("Пожалуйста выберите контакт в таблице для удаления.");
            alert.showAndWait();
        }
    }

    public void handleDeleteDepartment() {
        int selectedIndex = tableViewDepartmens.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tableViewDepartmens.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран отдел");
            alert.setContentText("Пожалуйста выберите отдел в таблице для удаления.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewDepartment() throws Exception {
        Departmens tempDepartment = new Departmens();
        boolean okClicked = mainApp.showDepartmentEditDialog(tempDepartment);
        if (okClicked) {
            mainApp.getDepartmens().add(tempDepartment);
        }
    }

    @FXML
    private void handleEditDepartment() throws Exception {
        Departmens selectedDepartment = tableViewDepartmens.getSelectionModel().getSelectedItem();
        if (selectedDepartment != null) {
            boolean okClicked = mainApp.showDepartmentEditDialog(selectedDepartment);
            if (okClicked) {
                showContacts(selectedDepartment);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран отдел");
            alert.setContentText("Пожалуйста, выберите отдел из списка.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() throws Exception{
        Departmens.Person tempPerson = new Departmens.Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            Departmens selectedPerson = tableViewDepartmens.getSelectionModel().getSelectedItem();
            selectedPerson.getContactList().add(tempPerson);
        }
    }

    @FXML
    private void handleEditPerson() throws Exception {
        Departmens.Person selectedPerson = tableViewContacts.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                Departmens selectedPerson2 = tableViewDepartmens.getSelectionModel().getSelectedItem();
                showContacts(selectedPerson2);
            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран контакт");
            alert.setContentText("Пожалуйста выберите контакт для редактирования.");

            alert.showAndWait();
        }
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tableViewDepartmens.setItems(mainApp.getDepartmens());
    }
}
