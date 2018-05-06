package project03.csc214.project03.Main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import project03.csc214.project03.AddFriend.AddFriendActivity;
import project03.csc214.project03.Home.HomeActivity;

/**
 * Created by Daniel on 6/25/2017.
 */

public class Login {
    private ProgressDialog mLogProgress;
    private FirebaseAuth mAuth;

    public Login(String emailS, String passS, final Activity activity, final Context context) {
        if (emailS.length() != 0 && passS.length() != 0) {
            mAuth = FirebaseAuth.getInstance();
            mLogProgress = new ProgressDialog(context);
            mLogProgress.setTitle("Logging In");
            mLogProgress.setMessage("Communicating With Authorization Server");
            mLogProgress.setCanceledOnTouchOutside(false);
            mLogProgress.show();
            mAuth.signInWithEmailAndPassword(emailS,passS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(activity, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                        mLogProgress.dismiss();
                        Intent homeIntent = new Intent(context,HomeActivity.class);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(homeIntent);
                    } else {
                        mLogProgress.hide();
                        Toast.makeText(activity, "Invalid Email Or Password!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(activity, "One Or More Fields Are Empty!", Toast.LENGTH_SHORT).show();
        }
    }
}

