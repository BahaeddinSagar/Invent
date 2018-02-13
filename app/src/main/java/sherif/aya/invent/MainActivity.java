package sherif.aya.invent;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    private FirebaseAnalytics mFirebaseAnalytics;
    ArrayList<Item> items;
    DatabaseReference myRef;
    FirebaseDatabase database;
    static boolean calledAlready = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        editText = findViewById(R.id.editText2);


         database = FirebaseDatabase.getInstance();
        if (!calledAlready)
        {
            database.setPersistenceEnabled(true);
            calledAlready = true;
        }



        //DatabaseReference myRef = database.getReference("message");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        myRef = database.getReference("invent");

        items = new ArrayList<Item>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e("Count " ,""+snapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    int id = Integer.parseInt(postSnapshot.getKey());

                    String location = postSnapshot.child("Location").getValue().toString();
                    String Family_Type = postSnapshot.child("Family Type").getValue().toString();
                    String Description = postSnapshot.child("Description").getValue().toString();
                    String Specifications = postSnapshot.child("Specifications").getValue().toString();
                    String Part_number = postSnapshot.child("Part number").getValue().toString();
                    int Quantity = Integer.parseInt(String.valueOf(postSnapshot.child("Quantity").getValue()));

                    String Status = postSnapshot.child("Status").getValue().toString();

                    //Log.e("data", location);
                    Item post = new Item(id ,location,Family_Type,Description,Specifications,Part_number,Quantity,Status);

                    items.add(post);

                    //Log.e("Get Data", post.<YourMethod>());
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: " ,firebaseError.getMessage());
            }


        });



        /**




                DatabaseHandler db = new DatabaseHandler(this);



        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Item(1,"BH1", "Adapter","AD   0.5-7/8   NM","AD-  0.5-7/8  NM","AD-NF-0.5",90,"New"));
        db.addContact(new Item(2,"BH1", "Adapter","AD   0.5-7/8   NM","AD-  0.5-7/8  NM","AD-NF-0.5",90,"New"));
        db.addContact(new Item(3,"AK3","Amplifier","Linear Power Amplifier LPA2100","2100 Mhz Band","1469538",1,"New"));
        db.addContact(new Item(4,"BF1-2","Antenna","Omni antenna","800-2500 Mhz","IXD-360V03N0-05",3,"New"));
        db.addContact(new Item(5,"AH2/AH3-2","Antenna","Log Periodic Antenna","800-2700 Mhz 11db","LA-ANT-11",10," "));
        db.addContact(new Item(6,"C5","Antenna Accessory","Style Decorative Antenna_Pole","800-500 Mhz Ntype female" ,"TDJ-0825GYHA-POLE",1," "));
        db.addContact(new Item(7,"AN2","Attenuator","100W 35dB NF-NF Attenuator, N Female (input),N Female (output)","35dB-NF/NF","AT100-35dB-NF/NF",12,"BRAND NEW"));
        db.addContact(new Item(8,"BH3","Attenuator","Attenuator 10dB  NM/NF","10dB  NM/NF","GKX-ATT5-NMNF10",19,"New"));
        db.addContact(new Item(9,"AH4","CABELS","JUMPER OMNI ANTENNA  RG400","MEAL TIO MEAL","  ",4," "));
        db.addContact(new Item(10,"C2","Cables","Coaxial Cable","1/2 Cable","LDF4-50A",1 ," "));
        db.addContact(new Item(11,"C4","Cables","IF Cable Coaxial Cable","75 ohm","RG11S60",915," "));
        db.addContact(new Item(12,"AL2","Coupler","Power Tapper, 10dB","800-2500 Mhz","GKX-CO10-NF0825",3,"2USED"));
        db.addContact(new Item(13,"BG2","Coupler","Power Tapper, 20dB","800-2500 Mhz","GKX-COU20-NF0825",30," "));
        db.addContact(new Item(14,"AL4","Combiner","Dualband compiner Unit","RA-7800DC","RA7800-DC21DF06DB05ED",2," "));
        db.addContact(new Item(15,"BB2-2","Combiner","Combiner NF77","NF77","GKX-COM-NF77",7," "));
        db.addContact(new Item(16,"BH1","Connector","1/2 Connector","male Connector","CO-0.5INCH-M",214,"New"));
        db.addContact(new Item(17,"BH1","Connector","1/2 Connector","Female Connector","CO-0.5INCH-F",30," "));
        db.addContact(new Item(18,"AA2","Fiber Optics","Duplex Patchcord/Multi mode","FC/PC-FC/PC 20m","FC/PC-FC/PC 20m",2,"New"));
        db.addContact(new Item(19,"AA2","Fiber Optics","Duplex Patchcord/Single mode" ,"LC/PC-FC/PC 20m","LC-FC-20",5,"New"));
        db.addContact(new Item(20,"PMO office","Tools & Equipment","ترابنو هلتي  كهربائي 1050 وات كراون صيني","ترابنو هلتي كهربائي 1050 وات كراون صيني","F295",1," "));
        db.addContact(new Item(21,"PMO office","Tools & Equipment","ترابنو هلتي كهربائي 1050 وات كراون صيني", "ترابنو هلتي كهربائي 1050 وات كراون صيني","F253",1," "));
        db.addContact(new Item(22,"PMO office","Tools & Equipment","بوصلة-truArs 20","بوصلة","F258",1," "));
        db.addContact(new Item(23,"PMO office","Tools & Equipment","بوصلة-tru Ars 20","بوصلة","F245",1," "));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Item> items = db.getAllitems();

        for (Item cn : items) {
            String log = "Id: "+cn.getId()+" ,Location: " + cn.getLocation() + " ,Family Type: " + cn.getFamily_Type() +" ,Description: " + cn.getDescription() +" ,Specifications: " + cn.getSpecifications() + " , Quantitiy: " + cn.getQuantity() + " ,Status: " + cn.getStatus();
            // Writing Contacts to log
            Log.d("Name: ", log);
            editText.setText(editText.getText() +"\n" + log);
        }


        **/







    }

    void scan (View view) {
        editText = (EditText) findViewById(R.id.editText2);
        String v = String.valueOf(editText.getText());
       if (!editText.getText().toString().equals("") ) {
           IntentIntegrator integrator = new IntentIntegrator(this);


           integrator.setOrientationLocked(false);
           integrator.initiateScan();
       }else {
           Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();

       }
        return;
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        DatabaseHandler db = new DatabaseHandler(this);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Item requestedItem = null;
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                    for (Item item : items) {
                        if (item.getPart_number().equals(result.getContents())  ){
                            requestedItem = item;

                        }

                }


                if (requestedItem != null) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);

                    View mView = getLayoutInflater().inflate(R.layout.dialog , null);
                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();


                    TextView location = (TextView) mView.findViewById(R.id.Location);
                    TextView family_Type = (TextView) mView.findViewById(R.id.Family_Type);
                    TextView description = (TextView) mView.findViewById(R.id.Description);
                    TextView part_Number = (TextView) mView.findViewById(R.id.Part_Number);
                    TextView quantity = (TextView) mView.findViewById(R.id.Quantity);
                    TextView status = (TextView) mView.findViewById(R.id.Status);
                    final Button add = (Button) mView.findViewById(R.id.Add);
                    Button withdraw = (Button) mView.findViewById(R.id.Withdraw);
                    Button plus = (Button) mView.findViewById(R.id.plus);
                    Button minus = (Button) mView.findViewById(R.id.minus);
                    final TextView q = (TextView) mView.findViewById(R.id.q);

                    plus.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {

                            q.setText(String.valueOf(1+Integer.parseInt((String) q.getText())));

                        }
                    });

                    minus.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            if (Integer.parseInt(String.valueOf(q.getText())) != 1) {
                                q.setText(String.valueOf(1 + Integer.parseInt((String) q.getText())));
                            }
                        }
                    });




                    location.setText(requestedItem.getLocation());
                    family_Type.setText(requestedItem.getFamily_Type());
                    description.setText(requestedItem.getDescription());
                    part_Number.setText(requestedItem.getPart_number());
                    quantity.setText(String.valueOf(requestedItem.getQuantity()));
                    status.setText(requestedItem.getStatus());

                    final Item finalRequestedItem = requestedItem;
                    add.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            finalRequestedItem.setQuantity(finalRequestedItem.getQuantity() + Integer.parseInt(q.getText().toString()));
                            updateQ(finalRequestedItem , "add" , Integer.parseInt(q.getText().toString()));
                            Toast.makeText(MainActivity.this, String.valueOf(finalRequestedItem.getQuantity()), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });


                    withdraw.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            if (finalRequestedItem.getQuantity() - Integer.parseInt(q.getText().toString()) < 0){
                                Toast.makeText(MainActivity.this,"Cannot withdraw more than avilable Quantity", Toast.LENGTH_LONG).show();
                            }else {

                                finalRequestedItem.setQuantity(finalRequestedItem.getQuantity() - Integer.parseInt(q.getText().toString()));
                                updateQ(finalRequestedItem, "withdraw", Integer.parseInt(q.getText().toString()));
                                Toast.makeText(MainActivity.this, String.valueOf(finalRequestedItem.getQuantity()), Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });


                    }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void updateQ(Item item, String addOrwithdraw , int q){
        editText = findViewById(R.id.editText2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message")
        DatabaseReference myRef = database.getReference("invent");
        DatabaseReference myRef1 = database.getReference("movement");
        myRef.child(String.valueOf(item.id)).child("Quantity").setValue(item.getQuantity());


        Map<String, String> userData = new HashMap<String, String>();

        String person = editText.getText().toString();
        String partNumber = item.getPart_number();
        String datetime = new Date().toString();


        userData.put("person", person);
        userData.put("part_number", partNumber);
        userData.put("AddOrWithdraw", addOrwithdraw);
        userData.put("Quantity", String.valueOf(q));
        userData.put("date&Time", datetime);
        myRef1.push().setValue(userData);


    }

    public void commingSoon(View view) {
        Toast.makeText(MainActivity.this,"In Progress", Toast.LENGTH_SHORT).show();
    }
}
