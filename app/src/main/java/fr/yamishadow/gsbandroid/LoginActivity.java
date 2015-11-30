package fr.yamishadow.gsbandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cmdbtnco();
    }

    private void cmdbtnco() {

        String login = ((EditText) findViewById(R.id.txtlogin)).getText().toString();
        String mdp = ((EditText) findViewById(R.id.txtmdp)).getText().toString();

        AccesHTTP acces = new AccesHTTP() {
            protected void onPostExecute(Long result) {
                try {
                    JSONArray tJson = new JSONArray(this.ret);

                } catch (JSONException e) {
                    Log.d("log", "Pb decodage JSON " + e.toString());
                }
            }
        };
        acces.addParam("login","login");
        acces.addParam("mdp","motdepasse");
        acces.execute("http://10.20.161.5/GSBAndroid/getlogin.php");
    }
}