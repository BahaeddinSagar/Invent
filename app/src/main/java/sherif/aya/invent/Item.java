package sherif.aya.invent;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.SumPathEffect;

import java.util.List;

/**
 * Created by Bahaeddin on 13-Jan-18.
 */

public class Item {
    //private variables
    int id;
    String Location;
    String Family_Type;
    String Description;
    String Specifications;
    String Part_number;
    int Quantity;
    String Status;


    // Empty constructor
    public Item()
    {

    }
    // constructor
    public Item(int id, String Location, String Family_Type, String Description, String Specifications, String Part_number, int Quatntity, String Status)
    {
        this.id = id;
        this.Location = Location;
        this.Family_Type = Family_Type;
        this.Description = Description;
        this.Specifications = Specifications;
        this.Part_number = Part_number;
        this.Quantity = Quatntity;
        this.Status = Status;

    }

    public Item( String Location, String Family_Type, String Description, String Specifications, String Part_number, int Quatntity, String Status)
    {

        this.Location = Location;
        this.Family_Type = Family_Type;
        this.Description = Description;
        this.Specifications = Specifications;
        this.Part_number = Part_number;
        this.Quantity = Quatntity;
        this.Status = Status;

    }


    public int getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }

    public String getFamily_Type() {
        return Family_Type;
    }

    public String getLocation() {
        return Location;
    }

    public String getPart_number() {
        return Part_number;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getSpecifications() {
        return Specifications;
    }

    public String getStatus() {
        return Status;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setFamily_Type(String Family_Type) {
        this.Family_Type = Family_Type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setPart_number(String Part_number) {
        this.Part_number = Part_number;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setSpecifications(String Specifications) {
        this.Specifications = Specifications;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }








}


