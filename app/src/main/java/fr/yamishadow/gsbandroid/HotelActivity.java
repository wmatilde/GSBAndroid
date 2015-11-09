package fr.yamishadow.gsbandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class HotelActivity extends AppCompatActivity {

    // informations affichées dans l'activitéjbhiuefhuirhughurghiuorghorhgiuhZer
    private int annee ;
    private int mois ;
    private int qte ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
    }

    /**
     * Sur la selection de l'image : retour au menu principal
     */
    private void imgReturn_clic() {
        ((ImageView)findViewById(R.id.imgKmReturn)).setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                retourActivityPrincipale();
            }
        }) ;
    }

    /**
     * Retour à l'activité principale (le menu)
     */
    private void retourActivityPrincipale() {
        Intent intent = new Intent(HotelActivity.this, MainActivity.class) ;
        startActivity(intent) ;
    }

    /**
     * Sur le clic du bouton plus : ajout de 10 dans la quantité
     */
    private void cmdPlus_clic() {
        ((Button)findViewById(R.id.cmdKmPlus)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                qte += 10;
                enregNewQte();
            }
        }) ;
    }

    /**
     * Sur le clic du bouton moins : enléve 10 dans la quantité si c'est possible
     */
    private void cmdMoins_clic() {
        ((Button)findViewById(R.id.cmdKmMoins)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                qte = Math.max(0, qte - 10); // suppression de 10 si possible
                enregNewQte();
            }
        }) ;
    }

    /**
     * Enregistrement dans la zone de texte et dans la liste de la nouvelle qte, à la date choisie
     */
    private void enregNewQte() {
        // enregistrement dans la zone de texte
        ((EditText)findViewById(R.id.txtNuits)).setText(String.valueOf(qte)) ;
        // enregistrement dans la liste
        int key = annee*100+mois ;
        if (!Global.listFraisMois.containsKey(key)) {
            // creation du mois et de l'annee s'ils n'existent pas déjà
            Global.listFraisMois.put(key, new FraisMois(annee, mois)) ;
        }
        Global.listFraisMois.get(key).setKm(qte) ;
    }
}
