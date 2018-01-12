package droidmentor.tabwithviewpager.Activity;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import droidmentor.tabwithviewpager.Fragment.DashboardDemandeFragment;
import droidmentor.tabwithviewpager.Fragment.DemandeFragment;
import droidmentor.tabwithviewpager.R;
import droidmentor.tabwithviewpager.ViewPager.CustomTabActivity;


public class PaypalActivity extends AppCompatActivity {

    Button btn_annuler ;
    Button btn_terminer ;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal);

        mContext = this;

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Mode de paiement");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //recover button annuler
        btn_annuler = (Button) findViewById(R.id.annuler);
        btn_terminer = (Button) findViewById(R.id.terminer);


        //ajout d'un ecouteur au boutton annuler
        btn_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CustomTabActivity.class);
                startActivity(intent);

            }
        });

        //ajout d'un ecouteur au boutton annuler
        btn_terminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CustomTabActivity.class);
                intent.putExtra("termined", "ok");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
