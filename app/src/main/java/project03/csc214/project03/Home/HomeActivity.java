package project03.csc214.project03.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import project03.csc214.project03.AddFriend.AddFriendActivity;
import project03.csc214.project03.Chat.ChatMasterDetailActivity;
import project03.csc214.project03.Main.MainActivity;
import project03.csc214.project03.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        boolean handled;
        switch (item.getItemId()){

            case R.id.addFriendItem:

                Intent intentAdd = new Intent(this, AddFriendActivity.class);//adds user
                startActivity(intentAdd);
                handled = true;
                break;

            case R.id.chatItem:

                Intent intentChat = new Intent(this, ChatMasterDetailActivity.class);
                startActivity(intentChat);
                handled = true;
                break;

            case R.id.logItem:
                Intent intentLog = new Intent(this, MainActivity.class);//logout
                FirebaseAuth.getInstance().signOut();
                intentLog.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(this, "Securely Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(intentLog);
                handled = true;
                break;

            default:
                handled = super.onOptionsItemSelected(item);
        }
        return handled;
    }
}
