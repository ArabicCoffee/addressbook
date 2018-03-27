package address.view;

import address.MainApp;
import address.model.Departmens;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
    private TableColumn<Departmens.Person, String> comments;
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
    private TableColumn<Departmens.Person, String> commentsb;
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
    @FXML
    private TableColumn<Departmens.Person, String> commentsc;
    @FXML
    private Button buttonMainLink;

    private  ObservableList<Departmens> tempDepartamens =  FXCollections.observableArrayList();
    private Departmens temp = new Departmens();
    private ObservableList<Departmens.Person> tempList = FXCollections.observableArrayList();

    private ObservableList<Departmens.Person> tempListChoices = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Departmens.Person, String> FIOs;
    @FXML
    private TableColumn<Departmens.Person, String> positions;
    @FXML
    private TableColumn<Departmens.Person, LocalDate> birthdays;
    @FXML
    private TableColumn<Departmens.Person, String> phones;
    @FXML
    private TableColumn<Departmens.Person, String> mobilePhones;
    @FXML
    private TableColumn<Departmens.Person, String> commentss;
    @FXML
    private TableView<Departmens.Person> tableViewSearch;

    @FXML
    private TableColumn<Departmens, String> nameDepartmentColumnBirthday;
    @FXML
    private TableColumn<Departmens, String> nameDepartmentColumnChoice;
    @FXML
    private TableColumn<Departmens, String> nameDepartmentColumnSearch;

    private ObservableList<Departmens.Person> masterData = FXCollections.observableArrayList();

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private MainApp mainApp;

    public PersonOverview() {
    }

    public ObservableList<Departmens> getTempDepartamens() {
        return tempDepartamens;
    }

    public void setTempDepartamens() {
        this.tempDepartamens = mainApp.getDepartmens();
    }

    @FXML
    private void initialize() {
        buttonMainLink.toFront();
        textFieldSearch.setPromptText("Поиск");
        visibleMain();
        //visiblePerson();

        tableViewContacts.setRowFactory( tv -> {
            TableRow<Departmens.Person> row = new TableRow<>();
            row.setOnDragDetected(event -> {
                        if (! row.isEmpty()) {
                            Integer index = row.getIndex();
                            Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                            db.setDragView(row.snapshot(null, null));
                            ClipboardContent cc = new ClipboardContent();
                            cc.put(SERIALIZED_MIME_TYPE, index);
                            db.setContent(cc);
                            event.consume();
                        }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Departmens.Person draggedPerson = tableViewContacts.getItems().remove(draggedIndex);

                    int dropIndex ;

                    if (row.isEmpty()) {
                        dropIndex = tableViewContacts.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }

                    tableViewContacts.getItems().add(dropIndex, draggedPerson);

                    event.setDropCompleted(true);
                    tableViewContacts.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });

        tableViewDepartmens.setRowFactory( tv -> {
            TableRow<Departmens> row = new TableRow<>();
            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Departmens draggedPerson = tableViewDepartmens.getItems().remove(draggedIndex);

                    int dropIndex ;

                    if (row.isEmpty()) {
                        dropIndex = tableViewDepartmens.getItems().size() ;
                    } else {
                        dropIndex = row.getIndex();
                    }

                    tableViewDepartmens.getItems().add(dropIndex, draggedPerson);

                    event.setDropCompleted(true);
                    tableViewDepartmens.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });




        nameDepartmentColumn.setCellValueFactory(cellData -> cellData.getValue().nameDepartmentProperty());
        FIO.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        position.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        birthday.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        phone.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        mobilePhone.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneProperty());
        comments.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());

        //////

        FIOb.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        positionb.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        birthdayb.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        phoneb.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        mobilePhoneb.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneProperty());
        commentsb.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());



        FIOc.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        positionc.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        birthdayc.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        phonec.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        mobilePhonec.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneProperty());
        commentsc.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());



        FIOs.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        positions.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        birthdays.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        phones.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        mobilePhones.setCellValueFactory(cellData -> cellData.getValue().mobilePhoneProperty());
        commentss.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());


        FilteredList<Departmens.Person> filteredData = new FilteredList<>(masterData, p -> true);

        textFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                visibleSearchTableView();
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getFIO().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }


                /*for (int i = 0; i < person.getContactList().size(); i++) {
                if (person.getContactList().get(i).getFIO().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } }*/
                return false; // Does not match.
            });
        });

        SortedList<Departmens.Person> sortedData = new SortedList<>(filteredData);
        //sortedData.comparatorProperty().bind(tableViewSearch.comparatorProperty());
        tableViewSearch.setItems(sortedData);


        tableViewDepartmens.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Departmens>() {
            @Override
            public void changed(ObservableValue<? extends Departmens> observable, Departmens oldValue, Departmens newValue) {
                showContacts(newValue);
            }
        });

        //textFieldSearch.

        /*tableViewContacts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Departmens.Person>() {
            @Override
            public void changed(ObservableValue<? extends Departmens.Person> observable, Departmens.Person oldValue, Departmens.Person newValue) {
                tempListChoices.add(newValue);
                tableViewChoices.setItems(tempListChoices);
            }
        });*/

    }



    public void initTableviewBirthday() {
        for (int i = 0; i < tempDepartamens.size(); i++) {
            for (int j = 0; j < tempDepartamens.get(i).getContactList().size(); j++) {
                ////
                if (tempDepartamens.get(i).getContactList().get(j).getDate().getMonth().getValue() == LocalDate.now().getMonth().getValue()) {
                    /*if (tempDepartamens.get(i).getContactList().get(j).getDate().getDayOfMonth() < LocalDate.now().getDayOfMonth()) {
                        if ((tempDepartamens.get(i).getContactList().get(j).getDate().getDayOfMonth() - LocalDate.now().getDayOfMonth()) <= 0
                                && (tempDepartamens.get(i).getContactList().get(j).getDate().getDayOfMonth() - LocalDate.now().getDayOfMonth()) <= -7) {
                            tempList.add(tempDepartamens.get(i).getContactList().get(j));
                        }
                    } else {
                        int tempDate = tempDepartamens.get(i).getContactList().get(j).getDate().getDayOfMonth() - LocalDate.now().getDayOfMonth();
                        tempDate -= 30;
                        if (tempDate <= 0 && tempDate <= -7) {
                            tempList.add(tempDepartamens.get(i).getContactList().get(j));
                        }
                    }*/
                    if (tempDepartamens.get(i).getContactList().get(j).getDate().getDayOfMonth() == LocalDate.now().getDayOfMonth()) {
                        tempList.add(tempDepartamens.get(i).getContactList().get(j));
                    }
                }


                ////
            }
        }
        temp.setContactList(tempList);
        tableViewBirthday.setItems(temp.getContactList());
    }

    @FXML
    private void handleAddToChoice() {
        int selectedIndex = tableViewContacts.getSelectionModel().getSelectedIndex();
        //tempListChoices.add(tableViewContacts.getItems().get(selectedIndex));
        mainApp.getContacts().add(tableViewContacts.getItems().get(selectedIndex));
        tableViewChoices.setItems(mainApp.getContacts());
        //tableViewChoices.setItems(tempListChoices);
        //temp.setTempListChoices(tempListChoices);
        //mainApp.getContacts().add(tempListChoices);

    }

    public List<Departmens.Person> choiceInMain() {
        return  tempListChoices;
    }

    @FXML
    private void handleDeleteFromChoice() {
        int selectedIndex = tableViewChoices.getSelectionModel().getSelectedIndex();
        tableViewChoices.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleMainPage() {
        visibleMain();
    }


    public void visibleSearchTableView() {
        labelNameDepartment.setVisible(false);
        tableViewContacts.setVisible(false);
        buttonBarContacts.setVisible(false);
        labelBirthday.setVisible(false);
        tableViewBirthday.setVisible(false);
        choices.setVisible(false);
        tableViewChoices.setVisible(false);

        tableViewSearch.setVisible(true);
        tableViewSearch.toFront();

    }

    public void visibleMain() {
        //tableViewChoices.setItems(tempListChoices);
        labelNameDepartment.setVisible(false);
        tableViewContacts.setVisible(false);
        buttonBarContacts.setVisible(false);
        tableViewSearch.setVisible(false);

        labelBirthday.setVisible(true);
        tableViewBirthday.setVisible(true);

        choices.setVisible(true);
        tableViewChoices.setVisible(true);
        labelBirthday.toFront();
        tableViewBirthday.toFront();


        choices.toFront();
        tableViewChoices.toFront();
    }

    public void visiblePerson() {
        labelNameDepartment.setVisible(true);
        tableViewContacts.setVisible(true);
        buttonBarContacts.setVisible(true);
        labelNameDepartment.toFront();
        tableViewContacts.toFront();
        buttonBarContacts.toFront();
        buttonMainLink.toFront();

        tableViewSearch.setVisible(false);
        labelBirthday.setVisible(false);
        tableViewBirthday.setVisible(false);
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

        setTempDepartamens();
        initTableviewBirthday();
        tableViewChoices.setItems(mainApp.getContacts());

        for (int i = 0; i < mainApp.getDepartmens().size(); i++) {
            for (int j = 0; j < mainApp.getDepartmens().get(i).getContactList().size(); j++) {
                masterData.add(mainApp.getDepartmens().get(i).getContactList().get(j));
            }
        }
        //masterData.addAll(mainApp.getDepartmens());
    }
}
