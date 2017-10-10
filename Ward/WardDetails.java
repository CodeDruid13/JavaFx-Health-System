/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ward;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Obare
 */
 public class WardDetails {

    private StringProperty wardname;
    private StringProperty wardtype;
    private StringProperty beds;
    private StringProperty charges;

    //Default constructor
    public WardDetails(String wardname, String wardtype, String beds, String charges) {
        this.wardname = new SimpleStringProperty(wardname);
        this.wardtype = new SimpleStringProperty(wardtype);
        this.beds = new SimpleStringProperty(beds);
        this.charges = new SimpleStringProperty(charges);
    }

    //Getters
    public String getwardname()
    {
        return wardname.get();
    }

    public String getwardtype()
    {
        return wardtype.get();
    }

    public String getbeds()
    {
        return beds.get();
    }

    public String getCharges()
    {
        return charges.get();
    }

    //Setters
    public void setWardname(String value)
    {

        wardname.set(value);
    }

    public void setWardtype(String value)

    {

        wardtype.set(value);
    }

    public void setBeds(String value)

    {

        beds.set(value);
    }

    public void setCharges(String value)
    {
        charges.set(value);
    }

    //Property values
    public StringProperty wardnameProperty()
    {

        return wardname;
    }

    public StringProperty wardtypeProperty()
    {

        return wardtype;
    }

    public StringProperty bedsProperty()
    {

        return beds;
    }

    public StringProperty chargesProperty()
    {
        return charges;
    }
}

