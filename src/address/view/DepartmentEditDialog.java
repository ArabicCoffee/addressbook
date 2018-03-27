package address.view;

import address.model.Departmens;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//

public class DepartmentEditDialog {
    @FXML
    private TextField textFieldNewDepartment;

    private Stage dialogStage;
    private Departmens department;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        textFieldNewDepartment.setPromptText("Пример: Отдел Информатизации");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDepartment(Departmens department) {
        this.department = department;

        textFieldNewDepartment.setText(department.getNameDepartment());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            department.setNameDepartment(textFieldNewDepartment.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (textFieldNewDepartment.getText() == null || textFieldNewDepartment.getText().length() == 0) {
            errorMessage += "Ошибка ввода!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Пожалуйста заполните правильно все поля");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
