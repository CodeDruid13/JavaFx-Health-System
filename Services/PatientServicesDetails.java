package Services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Obare on 3/28/2017.
 */
public class PatientServicesDetails {

    private StringProperty PatientID;
    private  StringProperty PatientLName;
    private  StringProperty PatientFName;

    public PatientServicesDetails(String PatientID, String PatientFName, String PatientLName){

        this.PatientID = new SimpleStringProperty(PatientID);
        this.PatientFName = new SimpleStringProperty(PatientFName);
        this.PatientLName = new SimpleStringProperty(PatientLName);
    }

    public String getPatientID()
    {

        return PatientID.get();
    }
    public String getPatientLName()
    {

        return PatientFName.get();
    }
    public String getPatientFName()
    {

        return PatientLName.get();
    }

    public void setPatientID(String value)
    {

        PatientID.set(value);
    }
    public void setPatientFName(String value)
    {

        PatientFName.set(value);
    }
    public void setPatientLName(String value)
    {

        PatientLName.set(value);
    }

    public StringProperty PatientIDproperty()
    {
        return PatientID;
    }
    public StringProperty PatientFNameproperty()
    {
        return PatientFName;
    }
    public StringProperty PatientLNameproperty()
    {
        return PatientLName;
    }
}
