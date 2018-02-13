package sherif.aya.invent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bahaeddin on 13-Jan-18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Alwadi";

    // Contacts table name
    private static final String TABLE_Inventory = "Inventory";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_Family_Type = "Family_Type";
    private static final String KEY_Description = "Description";
    private static final String KEY_Specifications = "Specifications";
    private static final String KEY_Part_number = "Part_number";
    private static final String KEY_Quantity = "Quantity";
    private static final String KEY_Status = "Status";
    private static final String KEY_Location = "Location";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Inventory);
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Inventory + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +  KEY_Location + "TEXT" + KEY_Family_Type + " TEXT,"
                + KEY_Description + " TEXT" +KEY_Specifications + " TEXT,"
                + KEY_Part_number + " TEXT" +KEY_Quantity + " TEXT,"
                + KEY_Status + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Inventory);

        // Create tables again
        onCreate(sqLiteDatabase);


    }

    // Adding new contact
    public void addContact(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Description, item.getDescription()); // Contact Name
        values.put(KEY_Location, item.getDescription()); // Contact Name
        values.put(KEY_Family_Type, item.getFamily_Type()); // Contact Phone Number
        values.put(KEY_ID, item.getId()); // Contact Name
        values.put(KEY_Part_number, item.getPart_number()); // Contact Phone Number
        values.put(KEY_Quantity, item.getQuantity()); // Contact Name
        values.put(KEY_Status, item.getStatus()); // Contact Phone Number
        values.put(KEY_Specifications, item.getSpecifications()); // Contact Phone Number
        // Inserting Row
        db.insert(TABLE_Inventory, null, values);
        db.close(); // Closing database connection

    }

    // Getting single contact
    public Item getitem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Inventory, new String[] { KEY_ID,
                        KEY_Location , KEY_Family_Type , KEY_Description , KEY_Specifications , KEY_Part_number , KEY_Quantity , KEY_Status }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7));
        // return contact
        return item;


    }

    public Item getid(String Part_number) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Inventory, new String[] { KEY_ID,
                        KEY_Location , KEY_Family_Type , KEY_Description , KEY_Specifications , KEY_Part_number , KEY_Quantity , KEY_Status }, KEY_Part_number + "=?",
                new String[] { String.valueOf(Part_number) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7));
        // return contact
        return item;


    }


    // Getting All Contacts
    public List<Item> getAllitems() {
        List<Item> itemList = new ArrayList<Item>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Inventory;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setLocation(cursor.getString(1));
                item.setFamily_Type(cursor.getString(2));
                item.setDescription(cursor.getString(3));
                item.setSpecifications(cursor.getString(4));
                item.setPart_number(cursor.getString(5));
                item.setQuantity(cursor.getInt(6));
                item.setStatus(cursor.getString(7));

                // Adding contact to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return contact list
        return itemList;


    }

    // Getting contacts Count

        public int getContactsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_Inventory;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

            // return count
            return cursor.getCount();

    }
    // Updating single contact
    public int updateContact(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Description, item.getDescription()); // Contact Name
        values.put(KEY_Location, item.getDescription()); // Contact Name
        values.put(KEY_Family_Type, item.getFamily_Type()); // Contact Phone Number
        values.put(KEY_ID, item.getId()); // Contact Name
        values.put(KEY_Part_number, item.getPart_number()); // Contact Phone Number
        values.put(KEY_Quantity, item.getQuantity()); // Contact Name
        values.put(KEY_Status, item.getStatus()); // Contact Phone Number
        values.put(KEY_Specifications, item.getSpecifications()); // Contact Phone Number

        // updating row
        return db.update(TABLE_Inventory, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });

    }

    // Deleting single contact
    public void deleteContact(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Inventory, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        db.close();

    }

}
