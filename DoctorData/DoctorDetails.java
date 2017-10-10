package DoctorData;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Obare on 3/5/2017.
 */
public class DoctorDetails {

    private StringProperty DocTag;
    private  StringProperty DocFName;
    private  StringProperty DocLName;
    private StringProperty DocAddress;
    private StringProperty DocIDNumber;
    private StringProperty DocGender;
    private StringProperty DocPhoneNumber;
    private StringProperty DocEmail;
    private StringProperty DocQualifications;


    public DoctorDetails(String DocTag, String DocFName, String DocLName, String DocAddress, String DocIDNumber,
                         String DocGender, String DocPhoneNumber, String DocEmail, String DocQualifications) {

        this.DocFName = new SimpleStringProperty(DocFName);
        this.DocLName = new SimpleStringProperty(DocLName);
        this.DocAddress = new SimpleStringProperty(DocAddress);
        this.DocIDNumber = new SimpleStringProperty(DocIDNumber);
        this.DocGender = new SimpleStringProperty(DocGender);
        this.DocPhoneNumber = new SimpleStringProperty(DocPhoneNumber);
        this.DocEmail = new SimpleStringProperty(DocEmail);
        this.DocQualifications = new SimpleStringProperty(DocQualifications);
        this.DocTag = new SimpleStringProperty(DocTag);
    }

    public String getDocTag()
    {

        return DocTag.get();
    }
    public String getDocLName()
    {

        return DocLName.get();
    }
    public String getDocFName()
    {

        return DocFName.get();
    }
    public String getDocAddress()
    {

        return DocAddress.get();
    }
    public String getDocIDNumber()
    {

        return DocIDNumber.get();
    }
    public String getDocEmail()
    {

        return DocEmail.get();
    }
    public String getDocGender()
    {

        return DocGender.get();
    }
    public String getDocPhoneNumber()
    {

        return DocPhoneNumber.get();
    }
    public String getDocQualifications()
    {

        return DocQualifications.get();
    }

    //Setters
    public void setDocTag(String value)
    {

         DocTag.set(value);
    }
    public void setDocLName(String value)
    {

        DocLName.set(value);
    }
    public void setDocFName(String  value)
    {

        DocFName.set(value);
    }
    public void setDocAddress(String value)
    {

        DocAddress.set(value);
    }
    public void setDocIDNumber(String value)
    {

        DocIDNumber.set(value);
    }
    public void setDocEmail(String value)
    {

        DocEmail.set(value);
    }
    public void setDocGender(String value)
    {

        DocGender.set(value);
    }
    public void setDocPhoneNumber(String value)
    {

        DocPhoneNumber.set(value);
    }
    public void setDocQualifications(String value)
    {

        DocQualifications.set(value);
    }

    //property
    public StringProperty DocTagproperty()
    {
        return DocTag;
    }
    public StringProperty DocLNameproperty()
    {
        return DocLName;
    }
    public StringProperty DocAddressproperty()
    {
        return DocAddress;
    }
    public StringProperty DocFNameproperty()
    {
        return DocFName;
    }
    public StringProperty DocIDNumberproperty()
    {
        return DocIDNumber;
    }
    public StringProperty DocGenderproperty()
    {
        return DocGender;
    }
    public StringProperty DocPhoneNumberproperty()
    {
        return DocPhoneNumber;
    }
    public StringProperty DocEmailproperty()
    {
        return DocEmail;
    }
    public StringProperty DocQualificationsproperty()
    {
        return DocQualifications;
    }

}
