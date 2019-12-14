package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;

public class PersonController
{
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private ToggleGroup gender;

    @FXML
    private Button saveBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;

    ObservableList<Person> persons = FXCollections.<Person>observableArrayList();
    Person person;

    @FXML//
    TableView<Person> tabela = new TableView<>();//

    public PersonController()
    {
    }

    @FXML
    private void initialize()
    {
	person = new Person();

	firstName.textProperty().bindBidirectional(person.firstNameProperty());
	lastName.textProperty().bindBidirectional(person.lastNameProperty());
	email.textProperty().bindBidirectional(person.emailProperty());

	gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
	{
	    @Override
	    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
	    {
		if(newValue != null)
		{
		    ToggleButton selected = (ToggleButton) newValue;
		    switch(selected.getId())
		    {
			case "male":
			    person.genderProperty().set(Gender.MALE);
			    break;
			case "female":
			    person.genderProperty().set(Gender.FEMALE);
			    break;
		    }
		}
	    }
	});
    }

    @FXML
    private void savePerson()
    {
	if(person.isValid())
	{
	    /* SAVE + LIST */
	    persons = tabela.getItems();
	    ToggleButton selected = (ToggleButton) gender.getSelectedToggle();
	    switch(selected.getId())
	    {
		case "male":
		    persons.add(new Person(firstName.getText(), lastName.getText(), email.getText(), Gender.MALE));
		    break;
		case "female":
		    persons.add(new Person(firstName.getText(), lastName.getText(), email.getText(), Gender.FEMALE));
		    break;
	    }
	    tabela.setItems(persons);
	    /* SAVE + LIST */
	}
	else
	{
	    StringBuilder errMsg = new StringBuilder();

	    ArrayList<String> errList = person.errorsProperty().get();
	    for(String errList1 : errList)
		errMsg.append(errList1);

	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("Person can be saved!");
	    alert.setHeaderText(null);
	    alert.setContentText(errMsg.toString());
	    alert.showAndWait();
	    errList.clear();
	}
    }

    @FXML
    private void closeForm()
    {
	Platform.exit();
    }

    @FXML
    private void clearPerson()
    {
	person.firstNameProperty().set("");
	person.lastNameProperty().set("");
	person.emailProperty().set("");
	person.genderProperty().set(Gender.FEMALE);

	if(gender.getSelectedToggle() != null)
	    gender.getSelectedToggle().setSelected(false);
    }

    /* DELETE */
    @FXML
    private void deletePerson()
    {
	persons = tabela.getItems();
	int index = tabela.selectionModelProperty().getValue().getSelectedIndex();
	persons.remove(index);
	tabela.setItems(persons);
    }
    /* DELETE */
}
