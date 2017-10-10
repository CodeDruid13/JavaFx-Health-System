package Registration;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Obare on 3/13/2017.
 */
public class PatientDetails {

    private StringProperty PatientID;
    private StringProperty PatientFName;
    private StringProperty PatientLName;
    private StringProperty NextOfKin;
    private StringProperty IDNumber;
    private StringProperty Gender;
    private StringProperty ContactNo;
    private StringProperty Email;
    private StringProperty BloodGroup;
    private StringProperty Remarks;

    public PatientDetails (String PatientID, String PatientFName, String PatientLName, String NextOfKin,
    String IDNumber, String Gender, String ContactNo, String Email, String BloodGroup, String Remarks
    )
    {

        this.PatientID = new SimpleStringProperty(PatientID);
        this.PatientFName = new SimpleStringProperty(PatientFName);
        this.PatientLName = new SimpleStringProperty(PatientLName);
        this.NextOfKin = new SimpleStringProperty(NextOfKin);
        this.IDNumber = new SimpleStringProperty(IDNumber);
        this.Gender = new SimpleStringProperty(Gender);
        this.ContactNo = new SimpleStringProperty(ContactNo);
        this.Email = new SimpleStringProperty(Email);
        this.BloodGroup = new SimpleStringProperty(BloodGroup);
        this.Remarks = new SimpleStringProperty(Remarks);
    }

    public String getPatientID()
    {
        return PatientID.get();
    }
    public String getPatientFName()
    {
        return PatientFName.get();
    }
    public String getPatientLName()
    {
        return PatientLName.get();
    }
    public String getNextOfKin()
    {
        return NextOfKin.get();
    }
    public String getIDNumber()
    {
        return IDNumber.get();
    }
    public String getGender()
    {
        return Gender.get();
    }
    public String getContactNo()
    {
        return ContactNo.get();
    }
    public String getEmail()
    {
        return Email.get();
    }
    public String getBloodGroup()
    {
        return BloodGroup.get();
    }
    public String getRemarks()
    {
        return Remarks.get();
    }

    ///setters

    public void setPatientID(String patientID)
    {
        PatientID.set(patientID);
    }
    public void setPatientFName(String patientFName)
    {
        PatientFName.set(patientFName);
    }
    public void setPatientLName(String patientLName)
    {
        PatientLName.set(patientLName);
    }
    public void setNextOfKin(String nextOfKin)
    {
        NextOfKin.set(nextOfKin);
    }
    public void setIDNumber(String idNumber)
    {
        IDNumber.set(idNumber);
    }
    public void setGender(String gender)
    {
        Gender.set(gender);
    }
    public void setContactNo(String contactNo)
    {
        ContactNo.set(contactNo);
    }
    public void setEmail(String email)
    {
        Email.set(email);
    }
    public void setBloodGroup(String bloodGroup)
    {
        BloodGroup.set(bloodGroup);
    }
    public void setRemarks(String remarks)
    {
        Remarks.set(remarks);
    }

    //property

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
    public StringProperty NextOfKinproperty()
    {
        return NextOfKin;
    }
    public StringProperty IDNumberproperty()
    {
        return IDNumber;
    }
    public StringProperty Genderproperty()
    {
        return Gender;
    }
    public StringProperty ContactNoproperty()
    {
        return ContactNo;
    }
    public StringProperty Emailproperty()
    {
        return Email;
    }
    public StringProperty BloodGroupproperty()
    {
        return BloodGroup;
    }
    public StringProperty Remarksproperty()
    {
        return Remarks;
    }
}

