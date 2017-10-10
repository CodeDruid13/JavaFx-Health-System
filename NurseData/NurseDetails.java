package NurseData;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Obare on 3/6/2017.
 */
public class NurseDetails {

    private StringProperty NurseTag;
    private StringProperty NurseFName;
    private StringProperty NurseLName;
    private StringProperty NurseAddress;
    private StringProperty NurseIDNumber;
    private StringProperty NurseGender;
    private StringProperty NursePhoneNumber;
    private StringProperty NurseEmail;
    private StringProperty NurseQualifications;


    public NurseDetails
            (String NurseTag, String NurseFName, String NurseLName, String NurseAddress, String NurseIDNumber,
             String NurseGender, String NursePhoneNumber, String NurseEmail, String NurseQualifications) {

        this.NurseFName = new SimpleStringProperty(NurseFName);
        this.NurseLName = new SimpleStringProperty(NurseLName);
        this.NurseAddress = new SimpleStringProperty(NurseAddress);
        this.NurseIDNumber = new SimpleStringProperty(NurseIDNumber);
        this.NurseGender = new SimpleStringProperty(NurseGender);
        this.NursePhoneNumber = new SimpleStringProperty(NursePhoneNumber);
        this.NurseEmail = new SimpleStringProperty(NurseEmail);
        this.NurseQualifications = new SimpleStringProperty(NurseQualifications);
        this.NurseTag = new SimpleStringProperty(NurseTag);
    }

    public String getNurseTag()
    {

        return NurseTag.get();
    }
    public String getNurseLName()
    {

        return NurseLName.get();
    }
    public String getNurseFName()
    {

        return NurseFName.get();
    }
    public String getNurseAddress()
    {

        return NurseAddress.get();
    }
    public String getNurseIDNumber()
    {

        return NurseIDNumber.get();
    }
    public String getNurseEmail()
    {

        return NurseEmail.get();
    }
    public String getNurseGender()
    {

        return NurseGender.get();
    }
    public String getNursePhoneNumber()
    {

        return NursePhoneNumber.get();
    }
    public String getNurseQualifications()
    {

        return NurseQualifications.get();
    }

    //Setters
    public void setNurseTag(String value)
    {

        NurseTag.set(value);
    }
    public void setNurseLName(String value)
    {

        NurseLName.set(value);
    }
    public void setNurseFName(String  value)
    {

        NurseFName.set(value);
    }
    public void setNurseAddress(String value)
    {

        NurseAddress.set(value);
    }
    public void setNurseIDNumber(String value)
    {

        NurseIDNumber.set(value);
    }
    public void setNurseEmail(String value)
    {

        NurseEmail.set(value);
    }
    public void setNurseGender(String value)
    {

        NurseGender.set(value);
    }
    public void setNursePhoneNumber(String value)
    {

        NursePhoneNumber.set(value);
    }
    public void setNurseQualifications(String value)
    {

        NurseQualifications.set(value);
    }

    //property
    public StringProperty NurseTagproperty()
    {
        return NurseTag;
    }
    public StringProperty NurseLNameproperty()
    {
        return NurseLName;
    }
    public StringProperty NurseAddressproperty()
    {
        return NurseAddress;
    }
    public StringProperty NurseFNameproperty()
    {
        return NurseFName;
    }
    public StringProperty NurseIDNumberproperty()
    {
        return NurseIDNumber;
    }
    public StringProperty NurseGenderproperty()
    {
        return NurseGender;
    }
    public StringProperty NursePhoneNumberproperty()
    {
        return NursePhoneNumber;
    }
    public StringProperty NurseEmailproperty()
    {
        return NurseEmail;
    }
    public StringProperty NurseQualificationsproperty()
    {
        return NurseQualifications;
    }

}




