package Inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Obare on 3/21/2017.
 */
public class SupplierDataDetails {

    private final StringProperty SupplierID;
    private final StringProperty SupplierFName;
    private final StringProperty SupplierLName;
    private final StringProperty SupplierPhoneNumber;
    private final StringProperty SupplierEmail;
    private final StringProperty SupplierAddress;

    public SupplierDataDetails(String SupplierID, String SupplierFname, String SupplierLname,
                               String SupplierPhoneNumber, String SupplierEmail, String SupplierAddress) {

        this.SupplierID = new SimpleStringProperty(SupplierID);
        this.SupplierFName = new SimpleStringProperty(SupplierFname);
        this.SupplierLName = new SimpleStringProperty(SupplierLname);
        this.SupplierPhoneNumber = new SimpleStringProperty(SupplierPhoneNumber);
        this.SupplierEmail = new SimpleStringProperty(SupplierEmail);
        this.SupplierAddress = new SimpleStringProperty(SupplierAddress);
    }

    //getters
    public String getSupplierID(){
        return SupplierID.get();
    }

    public String getSupplierFName(){
        return SupplierFName.get();
    }

    public String getSupplierLName(){
        return SupplierLName.get();
    }

    public String getSupplierPhoneNumber(){
        return SupplierPhoneNumber.get();
    }

    public String getSupplierEmail(){
        return SupplierEmail.get();
    }

    public String getSupplierAddress(){
        return SupplierAddress.get();
    }

    //setters

    public void setSupplierID(String value)
    {

        SupplierID.set(value);
    }

    public void setSupplierFName(String value)
    {

        SupplierFName.set(value);
    }

    public void setSupplierLName(String value)
    {

        SupplierLName.set(value);
    }

    public void setSupplierPhoneNumber(String value)
    {

        SupplierPhoneNumber.set(value);
    }

    public void setSupplierEmail(String value)
    {

        SupplierEmail.set(value);
    }

    public void setSupplierAddress(String value)
    {

        SupplierAddress.set(value);
    }


    //PROPERTY
    public StringProperty SupplierIDProperty() {

        return SupplierID;
    }

    public StringProperty SupplierFNameProperty() {

        return SupplierFName;
    }

    public StringProperty SupplierLNameProperty() {

        return SupplierLName;
    }

    public StringProperty SupplierPhoneNumberProperty() {

        return SupplierPhoneNumber;
    }

    public StringProperty SupplierEmailProperty() {

        return SupplierEmail;
    }

    public StringProperty SupplierAddressProperty() {

        return SupplierAddress;
    }

}
