package main;

import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person
{
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty email = new SimpleStringProperty(this, "email", "");
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty(this, "gender", Gender.FEMALE);

    public Person()
    {

    }

    public Person(String firstName, String lastName, String email, Gender gender)
    {
	this.firstName.set(firstName);
	this.lastName.set(lastName);
	this.email.set(email);
	this.gender.set(gender);
    }

    public Person(String firstName)
    {
	this.firstName.set(firstName);
    }

    public Person(String firstName, String lastName)
    {
	this.firstName.set(firstName);
	this.lastName.set(lastName);
    }

    public String getFirstName()
    {
	return firstName.get();
    }

    public void setFirstName(String firstName)
    {
	this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty()
    {
	return firstName;
    }

    public String getLastName()
    {
	return lastName.get();
    }

    public void setLastName(String lastName)
    {
	this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty()
    {
	return lastName;
    }

    public String getEmail()
    {
	return email.get();
    }

    public void setEmail(String email)
    {
	this.email.set(email);
    }

    public StringProperty emailProperty()
    {
	return email;
    }

    public Gender getGender()
    {
	return gender.get();
    }

    public void setGender(Gender gender)
    {
	this.gender.set(gender);
    }

    public ObjectProperty<Gender> genderProperty()
    {
	return gender;
    }

    private final ObjectProperty<ArrayList<String>> errorList = new SimpleObjectProperty<>(this, "errorList", new ArrayList<>());

    public ObjectProperty<ArrayList<String>> errorsProperty()
    {
	return errorList;
    }

    public boolean isValid()
    {
	boolean isValid = true;
	if(firstName.get() != null && firstName.get().equals(""))
	{
	    errorList.getValue().add("First name can't be empty!");
	    isValid = false;
	}
	if(lastName.get().equals(""))
	{
	    errorList.getValue().add("Last name can't be empty!");
	    isValid = false;
	}
	if(email.get().equals(""))
	{
	    errorList.getValue().add("Email can't be empty!");
	    isValid = false;
	}
	return isValid;
    }
}
