package droidmentor.tabwithviewpager.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import droidmentor.tabwithviewpager.Activity.DemandeActivity;
import droidmentor.tabwithviewpager.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemandeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Context mContext;
    Button btn_start;

    public DemandeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //recuperate a context
        mContext = container.getContext();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demande, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext, R.array.pieces_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //recuperate a btn start
        btn_start = (Button) view.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DemandeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_contacts_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("test", "un element a été chosir");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d("test", "aucun element a été choisir");
    }
}
