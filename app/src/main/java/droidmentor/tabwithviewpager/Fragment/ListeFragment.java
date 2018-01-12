package droidmentor.tabwithviewpager.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import droidmentor.tabwithviewpager.Directory.RecyclerViewAdapter;
import droidmentor.tabwithviewpager.Model.Demande;
import droidmentor.tabwithviewpager.Model.SampleModel;
import droidmentor.tabwithviewpager.R;
import droidmentor.tabwithviewpager.Utils.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListeFragment extends Fragment{

    ActionMode mActionMode;
    Menu context_menu;
    List<Demande> demande;
    private RecyclerView recycler;
    private ViewGroup container;
    private Context mContext;
    RecyclerViewAdapter adapter;
    boolean isMultiSelect = false;

    ArrayList<Demande> user_list = new ArrayList<>();
    ArrayList<Demande> multiselect_list = new ArrayList<>();

    public ListeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Set Context
        mContext = container.getContext();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calls, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager linear = new LinearLayoutManager(container.getContext());
        recycler.setLayoutManager(linear);

        demande = this.generateDemandeListe();
        adapter = new RecyclerViewAdapter(container.getContext(), demande, multiselect_list);
        recycler.setAdapter(adapter);

        recycler.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, recycler, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                if (isMultiSelect)
                    multi_select(position);
                else
                    Toast.makeText(mContext, "Details Page", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                if (!isMultiSelect) {
                    multiselect_list = new ArrayList<Demande>();
                    isMultiSelect = true;

                    if (mActionMode == null) {
                        mActionMode = ((Activity) mContext).startActionMode(mActionModeCallback);
                    }
                }

                multi_select(position);
            }
        }));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calls_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //generate list fruits
    private List<Demande> generateDemandeListe(){
        List<Demande> demandes = new ArrayList<Demande>();

        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        demandes.add(new Demande("dsdjnsjd"));
        return demandes;
    }

    public void multi_select(int position) {
        Log.d("position", "la position selectionnée est :"+Integer.toString(position));

        if (mActionMode != null) {
            if (multiselect_list.contains(demande.get(position)))
                multiselect_list.remove(demande.get(position));
            else
                multiselect_list.add(demande.get(position));

            if (multiselect_list.size() > 0)
                mActionMode.setTitle("" + multiselect_list.size());
            else
                mActionMode.setTitle("");

            refreshAdapter();

        }
    }

    public void refreshAdapter() {
        adapter.selected_demande=multiselect_list;
        adapter.demandes=demande;
        adapter.notifyDataSetChanged();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_multi_select, menu);
            context_menu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    int count_list = multiselect_list.size();
                    demande.removeAll(multiselect_list);
                    refreshAdapter();
                    mode.finish();
                    if(count_list> 1){
                        Toast.makeText(mContext, count_list+" demandes viennent d'être supprimées", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "1 demande vient d'être supprimé", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
            isMultiSelect = false;
            multiselect_list = new ArrayList<Demande>();
            refreshAdapter();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id) {
            case android.R.id.home:
               Log.d("item","home");
                return true;
            case R.id.action_settings:
                //Toast.makeText(getApplicationContext(),"Settings Click",Toast.LENGTH_SHORT).show();
                Log.d("item","settings");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
