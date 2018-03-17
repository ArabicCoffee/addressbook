package address;

import address.model.Departmens;
import address.model.PersonListWrapper;
import address.view.DepartmentEditDialog;
import address.view.PersonEditDialog;
import address.view.PersonOverview;
import address.view.RootLayout;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Departmens> departmens = FXCollections.observableArrayList();
    //private ObservableList<Departmens.Person> contacts = FXCollections.observableArrayList();



    //initialize left tableview
    public MainApp() {
        departmens.add(new Departmens("Отдел № 1"));
        departmens.add(new Departmens("Отдел № 2"));
        departmens.add(new Departmens("Отдел № 3"));
        departmens.add(new Departmens("Отдел № 4"));
        departmens.add(new Departmens("Отдел № 5"));
        departmens.add(new Departmens("Отдел № 6"));
        departmens.add(new Departmens("Отдел № 7"));
        departmens.add(new Departmens("Отдел № 8"));
        departmens.add(new Departmens("Отдел № 9"));
        departmens.add(new Departmens("Отдел № 10"));

        //initialize right tableview
        /*contacts.add(new Departmens().new Person());
        contacts.add(new Departmens().new Person());
        contacts.add(new Departmens().new Person());*/
    }

    public ObservableList<Departmens> getDepartmens() {
        return departmens;
    }

    /*public ObservableList<Departmens.Person> getContacts() {
        return contacts;
    }*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Телефонный справочник");

        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        RootLayout controller = loader.getController();
        controller.setMainApp(this);



        primaryStage.show();

        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }

    }

    public void showPersonOverview() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/PersonOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        rootLayout.setCenter(personOverview);

        PersonOverview controller = loader.getController();
        controller.setMainApp(this);
    }

    public boolean showDepartmentEditDialog(Departmens departmens) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/DepartmentEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование отдела");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        DepartmentEditDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setDepartment(departmens);

        dialogStage.showAndWait();

        return controller.isOkClicked();
    }

    public boolean showPersonEditDialog(Departmens.Person person) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Редактирование контакта");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        PersonEditDialog controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        dialogStage.showAndWait();

        return controller.isOkClicked();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public File getPersonFilePath() {

        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file
     *            the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences p = Preferences.userRoot();
        Preferences p2 = Preferences.systemNodeForPackage(MainApp.class);
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Телефонный справочник - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Телефонный справочник");
        }
    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            departmens.clear();
            departmens.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка загрузки данных");
            alert.setContentText("Невозможно загрузить данные из БД:\n" + file.getPath());

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(departmens);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка сохранения данных");
            alert.setContentText("Невозможно сохранить данные в БД:\n" + file.getPath());

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
