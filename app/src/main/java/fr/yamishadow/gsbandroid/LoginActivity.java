package fr.yamishadow.gsbandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void cmdbtnco(){

        String login = ((EditText)findViewById(R.id.txtlogin)).getText().toString();
        String mdp = ((EditText)findViewById(R.id.txtmdp)).getText().toString();

    }
}
