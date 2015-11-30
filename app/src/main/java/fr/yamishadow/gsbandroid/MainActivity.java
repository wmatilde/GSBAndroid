package fr.yamishadow.gsbandroid;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // récupération des informations sérialisées
        recupSerialize() ;
        // chargement des méthodes événementielles
        cmdMenu_clic(((Button)findViewById(R.id.cmdKm)), KmActivity.class) ;
        cmdMenu_clic(((Button)findViewById(R.id.cmdNuitee)), HotelActivity.class) ;
        cmdMenu_clic(((Button)findViewById(R.id.cmdEtape)), EtapeActivity.class);
        cmdMenu_clic(((Button)findViewById(R.id.cmdRepas)), RepasActivity.class);
        cmdMenu_clic(((Button)findViewById(R.id.cmdHf)), HfActivity.class) ;
        cmdMenu_clic(((Button)findViewById(R.id.cmdHfRecap)), HfRecapActivity.class);
        cmdMenu_clic(((Button)findViewById(R.id.cmdlogin)), LoginActivity.class);
        //cmdTransfert_clic();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Récupére la sérialisation si elle existe
     */

    private void recupSerialize() {
    	Global.listFraisMois = (Hashtable<Integer, FraisMois>) Serializer.deSerialize(Global.filename, MainActivity.this) ;
    	// si rien n'a été récupéré, il faut créer la liste
    	if (Global.listFraisMois==null) {
    		Global.listFraisMois = new Hashtable<Integer, FraisMois>() ;
    	}
    }

    /**
     * Sur la sélection d'un bouton dans l'activité principale ouverture de l'activité correspondante
     */
    private void cmdMenu_clic(Button button, final Class classe) {
    	button.setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
    			// ouvre l'activité
    			Intent intent = new Intent(MainActivity.this, classe) ;
    			startActivity(intent) ;
                finish();
    		}
    	}) ;
    }
    
    /**
     * Cas particulier du bouton pour le transfert d'informations vers le serveur
     */
    private void cmdTransfert_clic() {
    	((Button)findViewById(R.id.cmdlogin)).setOnClickListener(new Button.OnClickListener() {
    		public void onClick(View v) {
    			// envoi les informations sérialisées vers le serveur
    			// en construction
    		}
    	}) ;    	
    }
}
