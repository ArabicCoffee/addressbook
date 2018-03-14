package address;

import address.model.Departmens;
import address.view.DepartmentEditDialog;
import address.view.PersonEditDialog;
import address.view.PersonOverview;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        primaryStage.show();
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

    public static void main(String[] args) {
        launch(args);
    }
}
