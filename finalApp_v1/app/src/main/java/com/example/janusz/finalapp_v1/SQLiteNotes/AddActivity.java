package com.example.janusz.finalapp_v1.SQLiteNotes;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janusz.finalapp_v1.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * Created by janusz on 11.04.2018.
 */

public class AddActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    EditText addNote, addDate;
    TextView txtMsg;
    Button btnConfirm;
    ImageView pickImg;
    ImageButton btnPhoto;
    Bitmap selectedImage;

    final Activity activity = this;

    DbNotes db;
    private byte[] image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Log.d( "Create: ", "Creating Database ");

        addNote = (EditText) findViewById(R.id.addNote);
        addDate = (EditText) findViewById(R.id.addTitle);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnPhoto = (ImageButton) findViewById(R.id.btnPhoto);
        txtMsg = findViewById(R.id.txtMsg);


        verifyStoragePermission(this);

        db = new DbNotes(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Boolean hasWritePermission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
                Boolean sdCardExists = isExternalStorageAvailable();
                Boolean writeToSd = hasWritePermission && sdCardExists;
                System.out.println(writeToSd);

                String txtPath = writeTxt(writeToSd);
                String imgPath = writeImg(writeToSd);

                db.addContact(new Notes(addNote.getText().toString(), addDate.getText().toString(), txtPath, imgPath));

                Toast.makeText(AddActivity.this, "contact added", Toast.LENGTH_SHORT).show();

            }
        });

        try {
            PlayWithRawFiles();
        } catch (IOException e) {
            txtMsg.setText("Problems: " + e.getMessage());
        }
    }



    public void openGalerie(View view) {
        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);
    }
    public void PlayWithRawFiles() throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();
        int fileResourceId = R.raw.my_text_file;
        InputStream is = this.getResources().openRawResource(fileResourceId);
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(is) );
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        reader.close();
        is.close();
        txtMsg.setText( buf.toString() );
    }

@Override
protected void onActivityResult(int reqCode, int resultCode, Intent data) {
    super.onActivityResult(reqCode, resultCode, data);


    if (resultCode == RESULT_OK) {
        try {
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            selectedImage = BitmapFactory.decodeStream(imageStream);
            btnPhoto.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
    private Boolean verifyStoragePermission(Activity activity) {
        final int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }

    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , stream);
        return  stream.toByteArray();
    }
private boolean isExternalStorageAvailable() {

    String state = Environment.getExternalStorageState();
    boolean mExternalStorageAvailable = false;
    boolean mExternalStorageWriteable = false;

    if (Environment.MEDIA_MOUNTED.equals(state)) {
        mExternalStorageAvailable = mExternalStorageWriteable = true;
    } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
        mExternalStorageAvailable = true;
        mExternalStorageWriteable = false;
    } else {

        mExternalStorageAvailable = mExternalStorageWriteable = false;
    }

    if (mExternalStorageAvailable == true
            && mExternalStorageWriteable == true) {
        return true;
    } else {
        return false;
    }
}
    private String writeTxt(Boolean toSd) {

        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }

            root = toSd ? root : activity.getFilesDir();

            String filename = addNote.getText().toString()+"."+ addDate.getText().toString();
            File gpxfile = new File(root, filename);
            FileWriter writer = new FileWriter(gpxfile);
            writer.flush();
            writer.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            return gpxfile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    private String writeImg(Boolean toSd) {

        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }

            root = toSd ? root : activity.getFilesDir();

            String filename = addNote.getText().toString()+"."+ addDate.getText().toString()+".jpg";
            File gpxfile = new File(root, filename);
            FileWriter writer = new FileWriter(gpxfile);

            if (gpxfile.exists())
                gpxfile.delete();
            try {
                FileOutputStream out = new FileOutputStream(gpxfile);
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
            return gpxfile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}