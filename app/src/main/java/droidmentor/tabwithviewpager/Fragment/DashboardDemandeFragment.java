package droidmentor.tabwithviewpager.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import droidmentor.tabwithviewpager.Activity.DemandeActivity;
import droidmentor.tabwithviewpager.Activity.PaypalActivity;
import droidmentor.tabwithviewpager.Adapter.AlbumsAdapter;
import droidmentor.tabwithviewpager.Model.Album;
import droidmentor.tabwithviewpager.R;

/**
 * Created by bootnet on 27/11/2017.
 */

public class DashboardDemandeFragment extends Fragment {

    ImageView btn_file;
    ImageView btn_send;
    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Album> albumList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = inflater.getContext();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dashboard_demande_fragment, container, false);

        /*btn_continuer = (Button) view.findViewById(R.id.continuer);
        btn_continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayementDemandeFragment payementDemandeFragment = new PayementDemandeFragment();
                FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.replace(R.id.container,payementDemandeFragment );
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
            }
        });*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumsAdapter(mContext, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        /*try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //recover a btn_file
        btn_file = (ImageView) view.findViewById(R.id.btn_file);
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "click btn file");
            }
        });

        //recover a btn_send
        btn_send = (ImageView) view.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PaypalActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Album a = new Album("True Romance", 13, covers[0]);
        albumList.add(a);

        a = new Album("Xscpae", 8, covers[1]);
        albumList.add(a);

        a = new Album("Maroon 5", 11, covers[2]);
        albumList.add(a);

        a = new Album("Born to Die", 12, covers[3]);
        albumList.add(a);

        a = new Album("Honeymoon", 14, covers[4]);
        albumList.add(a);

        a = new Album("I Need a Doctor", 1, covers[5]);
        albumList.add(a);

        a = new Album("Loud", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
