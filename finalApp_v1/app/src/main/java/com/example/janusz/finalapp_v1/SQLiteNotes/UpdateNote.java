package com.example.janusz.finalapp_v1.SQLiteNotes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.janusz.finalapp_v1.R;
/**
 * Created by janusz on 11.04.2018.
 */

public class UpdateNote extends AppCompatActivity {

    DbNotes db;

    EditText editNote, editDate;
    Button btnUpdate;

    int id;
    byte[] image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

         id = getIntent().getIntExtra ("id",0);

        db = new DbNotes(this);

        editNote = (EditText) findViewById(R.id.editNote);
        editDate = (EditText) findViewById(R.id.editTitle);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        Notes contact=db.getContactById2(id);

        editNote.setText(contact.getNote());
        editDate.setText(contact.getDate()+" " );

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editNote.getText().toString();
                String phone = editDate.getText().toString();

               Notes newContact = new Notes(id, name, phone);

               db.updateContact(newContact );

                Toast.makeText(UpdateNote.this,  "contact updated with succes", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_delete:

                showAlert();

            break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Confirmation")
                    .setMessage("Are You Sure? ")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        //delete contact
                            db.deleteContact(id);
                            finish();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        }
                    });
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }
}
