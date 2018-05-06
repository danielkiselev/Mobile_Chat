package project03.csc214.project03.Registration;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import project03.csc214.project03.Main.MainActivity;

/**
 * Created by Daniel on 6/25/2017.
 */

public class CreateUser {

    private FirebaseAuth mAuth;
    private ProgressDialog mRegProgress;
    private DatabaseReference mDatabase;

    public CreateUser(final String fNameS,final String lNameS, String emailS, String passS, String passCheckS, final Activity activity, final Context context) {


        if (fNameS.length() != 0 && lNameS.length() != 0 && emailS.length() != 0 && passS.length() != 0 && passCheckS.length() != 0) {
            if (passS.length() >= 6 && passCheckS.length() >= 6) {
                if (!passS.equals(passCheckS)) {
                    Log.d("registerUser", " password fail");
                    Toast.makeText(activity, "Please Make Sure Passwords Match!", Toast.LENGTH_SHORT).show();
                } else {
                    mRegProgress = new ProgressDialog(context);
                    Log.d("registerUser", " start");
                    mRegProgress.setTitle("Registering User");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(emailS, passS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(activity, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Log.d("registerUser", " password pass");
                                String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                Log.d("UID: ", UID);
                                addDetails(fNameS,lNameS,UID);
                                mRegProgress.dismiss();
                                endRegistration(context);

                            } else {
                                mRegProgress.hide();
                                Toast.makeText(activity, "Issue Processing Registration", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
            else{
                Toast.makeText(activity, "Invalid Password Length", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(activity, "One Or More Fields Are Empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addDetails(String fNameS, String lNameS, String UID){
        Log.d("addDetails: ","arrived" );
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(UID);
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("fName", fNameS);
        userMap.put("lName", lNameS);
        mDatabase.setValue(userMap);

    }

    public void endRegistration(Context c){
        Intent mainIntent = new Intent(c, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(mainIntent);
    }
}
