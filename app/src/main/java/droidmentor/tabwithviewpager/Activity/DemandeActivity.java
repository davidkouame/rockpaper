package droidmentor.tabwithviewpager.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import droidmentor.tabwithviewpager.Adapter.AlbumsAdapter;
import droidmentor.tabwithviewpager.Fragment.DashboardDemandeFragment;
import droidmentor.tabwithviewpager.Model.Album;
import droidmentor.tabwithviewpager.R;

/**
 * Created by bootnet on 26/11/2017.
 */

public class DemandeActivity extends AppCompatActivity {

    Button btn_continuer;
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demande);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Files Liste");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        /*btn_continuer = (Button) findViewById(R.id.continuer);
        btn_continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            DashboardDemandeFragment dashboardDemandeFragment = new DashboardDemandeFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, dashboardDemandeFragment).commit();
        }
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
