package address.view;

import address.model.Departmens;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialog {
    @FXML
    private TextField textFieldFIO;
    @FXML
    private TextField textFieldPosition;
    @FXML
    private TextField textFieldBirthday;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private TextField textFieldMobilePhone;

    private Stage dialogStage;
    private Departmens.Person person;
    private boolean okClicked = false;
}
