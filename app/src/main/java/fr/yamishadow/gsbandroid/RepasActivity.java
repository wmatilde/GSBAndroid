package fr.yamishadow.gsbandroid;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;

public class RepasActivity extends AppCompatActivity {

    // informations affichées dans l'activité
    private int annee ;
    private int mois ;
    private int qte ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);
        // modification de l'affichage du DatePicker
        Global.changeAfficheDate((DatePicker) findViewById(R.id.datRepas)) ;
        imgReturn_clic() ;
        dat_clic();
        cmdPlus_clic() ;
        cmdMoins_clic() ;
        cmdValider_clic();
        valoriseProprietes();
    }

    /**
     * Sur la selection de l'image : retour au menu principal
     */
    private void imgReturn_clic() {
        ((ImageView)findViewById(R.id.imgRepasReturn)).setOnClickListener(new ImageView.OnClickListener() {
            public void onClick(View v) {
                retourActivityPrincipale();
            }
        }) ;
    }

    /**
     * Retour à l'activité principale (le menu)
     */
    private void retourActivityPrincipale() {
        Intent intent = new Intent(RepasActivity.this, MainActivity.class) ;
        startActivity(intent) ;
        finish();
    }

    /**
     * Sur le clic du bouton plus : ajout de 10 dans la quantité
     */
    private void cmdPlus_clic() {
        ((Button)findViewById(R.id.cmdRepasPlus)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                qte += 1;
                enregNewQte();
            }
        }) ;
    }

    /**
     * Sur le clic du bouton moins : enléve 10 dans la quantité si c'est possible
     */
    private void cmdMoins_clic() {
        ((Button)findViewById(R.id.cmdRepasMoins)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                qte = Math.max(0, qte - 1); // suppression de 10 si possible
                enregNewQte();
            }
        }) ;
    }

    /**
     * Enregistrement dans la zone de texte et dans la liste de la nouvelle qte, à la date choisie
     */
    private void enregNewQte() {
        // enregistrement dans la zone de texte
        ((EditText)findViewById(R.id.txtRepas)).setText(String.valueOf(qte)) ;
        // enregistrement dans la liste
        int key = annee*100+mois ;
        if (!Global.listFraisMois.containsKey(key)) {
            // creation du mois et de l'annee s'ils n'existent pas déjà
            Global.listFraisMois.put(key, new FraisMois(annee, mois)) ;
        }
        Global.listFraisMois.get(key).setRepas(qte); ;
    }

    private void dat_clic() {
        final DatePicker uneDate = (DatePicker)findViewById(R.id.datRepas) ;
        uneDate.init(uneDate.getYear(), uneDate.getMonth(), uneDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                valoriseProprietes();
            }
        });
    }

    private void cmdValider_clic() {
        ((Button)findViewById(R.id.cmdRepasValider)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Serializer.serialize(Global.filename, Global.listFraisMois, RepasActivity.this);
                retourActivityPrincipale();
            }
        }) ;
    }

    private void valoriseProprietes() {
        annee = ((DatePicker)findViewById(R.id.datRepas)).getYear() ;
        mois = ((DatePicker)findViewById(R.id.datRepas)).getMonth() + 1 ;
        // récupération de la qte correspondant au mois actuel
        qte = 0 ;
        int key = annee*100+mois ;
        if (Global.listFraisMois.containsKey(key)) {
            qte = Global.listFraisMois.get(key).getRepas() ;
        }
        ((EditText)findViewById(R.id.txtRepas)).setText(String.valueOf(qte)) ;
    }



}
