package address.view;

import address.model.Departmens;
import address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    @FXML
    private TextField comments;

    private Stage dialogStage;
    private Departmens.Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        textFieldFIO.setPromptText("Пример: Иванов Иван Иванович");
        textFieldPosition.setPromptText("Пример: Начальник");
        textFieldBirthday.setPromptText("Пример: 24-11-1990");
        textFieldPhone.setPromptText("Пример: 777");
        textFieldMobilePhone.setPromptText("Пример: 8-981-456-77-77 или 89814567777");
        comments.setPromptText("Пример: любой текст");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Departmens.Person person) {
        this.person = person;

        textFieldFIO.setText(person.getFIO());
        textFieldPosition.setText(person.getPosition());
        textFieldBirthday.setText(DateUtil.format(person.getDate()));
        textFieldPhone.setText(person.getPhone());
        textFieldMobilePhone.setText(person.getMobilePhone());
        comments.setText(person.getComments());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOK() {
        if (isInputValid()) {
            person.setFIO(textFieldFIO.getText());
            person.setPosition(textFieldPosition.getText());
            person.setDate(DateUtil.parse(textFieldBirthday.getText()));
            person.setPhone(textFieldPhone.getText());
            person.setMobilePhone(textFieldMobilePhone.getText());
            person.setComments(comments.getText());

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

        if (textFieldFIO.getText() == null || textFieldFIO.getText().length() == 0) {
            errorMessage += "Ошибка ввода ФИО!\n";
        }
        /*if (textFieldPosition.getText() == null || textFieldPosition.getText().length() == 0) {
            errorMessage += "Ошибка ввода Должности!\n";
        }*/
        /*if (textFieldBirthday.getText() == null || textFieldBirthday.getText().length() == 0) {
            errorMessage += "Ошибка ввода Даты рождения!\n";
        } else {*/
            /*if (!DateUtil.validDate(textFieldBirthday.getText())) {
                errorMessage += "Неправильный формат ввода Даты рождения. Используйте: ГГГГ-ММ-ДД";
            }*/
        //}
        /*if (textFieldPhone.getText() == null || textFieldPhone.getText().length() == 0) {
            errorMessage += "Ошибка ввода Телефона!\n";
        }
        if (textFieldMobilePhone.getText() == null || textFieldMobilePhone.getText().length() == 0) {
            errorMessage += "Ошибка ввода мобильно телефона!\n";
        }*/

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Пожалуйста заполните все поля");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
