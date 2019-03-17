package project03.csc214.project03.AddFriend;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import project03.csc214.project03.Home.HomeActivity;
import project03.csc214.project03.R;
import project03.csc214.project03.Sound.SoundManager;

public class AddFriendActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{


    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    ImageView addFriendIV;
    DatabaseReference mDatabase;
    String UID;
    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        addFriendIV = (ImageView) findViewById(R.id.addFriendIV);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("create qr UID", UID);
        setQR(UID);
        new SoundManager(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean checkPerm(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                return false;
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    return true;
                }
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void addFriendBut(View view){
        if (checkPerm()){
            zXingScannerView = new ZXingScannerView(getApplicationContext());
            setContentView(zXingScannerView);
            zXingScannerView.setResultHandler(this);
            zXingScannerView.startCamera();
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    public void handleResult(Result result){
        String friendCode = result.getText();
        Log.d("friend code received:", friendCode);
        zXingScannerView.stopCamera();
        //

        Log.d("AddFriend Data","arrived" );
        Log.d("UID code", UID);
        Log.d("friend code", friendCode);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(UID).child(friendCode);
        mDatabase.setValue(friendCode);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(friendCode).child(UID);
        mDatabase.setValue(UID);
        SoundManager.getInstance.playSound(SoundManager.sound_add);
        Toast.makeText(this, "You Are Both Now Friends", Toast.LENGTH_SHORT).show();
        Log.d("AddFriend Data", "Pass");

        Intent i = new Intent(this, AddFriendActivity.class);
        startActivity(i);
    }

    public void setQR(String UID){
        MultiFormatWriter mFWriter = new MultiFormatWriter();
        try {
            Log.d("setQR UID ", UID);
            BitMatrix bitMatrix = mFWriter.encode(UID, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barEncoder = new BarcodeEncoder();
            Bitmap qrIMG = barEncoder.createBitmap(bitMatrix);
            addFriendIV.setImageBitmap(qrIMG);
        }
        catch (WriterException e){
            e.printStackTrace();
            Log.d("error", "setQR: ");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentHome = new Intent(this, HomeActivity.class);//logout
        intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentHome);
    }
}
