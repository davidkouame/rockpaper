package droidmentor.tabwithviewpager.Directory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import droidmentor.tabwithviewpager.Model.Demande;
import droidmentor.tabwithviewpager.R;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bootnet on 18/11/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    public List<Demande> demandes;
    public ArrayList<Demande> selected_demande=new ArrayList<>();
    private RecyclerViewAdapterListener listener;
    private SparseBooleanArray selectedItems;

    // array used to perform multiple animation at once
    private SparseBooleanArray animationItemsIndex;
    private boolean reverseAllAnimations = false;

    // index is used to animate only the selected row
    // dirty fix, find a better solution
    private static int currentSelectedIndex = -1;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        public TextView libelle, subject, message, iconText, timestamp;
        public ImageView iconImp, imgProfile;
        public LinearLayout messageContainer;
        public RelativeLayout iconContainer, iconBack, iconFront;
        public RelativeLayout ll_listitem ;

        public MyViewHolder(View view) {
            super(view);
            libelle = (TextView) view.findViewById(R.id.libelle);
            iconText = (TextView) view.findViewById(R.id.icon_text);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            ll_listitem =(RelativeLayout)view.findViewById(R.id.ll_listitem);
            //iconBack = (RelativeLayout) view.findViewById(R.id.icon_back);
            /*iconFront = (RelativeLayout) view.findViewById(R.id.icon_front);
            iconImp = (ImageView) view.findViewById(R.id.icon_star);
            imgProfile = (ImageView) view.findViewById(R.id.icon_profile);
            messageContainer = (LinearLayout) view.findViewById(R.id.message_container);
            iconContainer = (RelativeLayout) view.findViewById(R.id.icon_container);*/
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }


    public RecyclerViewAdapter(Context mContext, List<Demande> demandes, ArrayList<Demande> selected_demande) {
        this.mContext = mContext;
        this.demandes = demandes;
        this.selected_demande = selected_demande;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.liste_demande, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //Message message = messages.get(position);

        // displaying text view data
        holder.libelle.setText("dddd");//holder.from.setText(message.getFrom());
        holder.timestamp.setText("ddd");//holder.timestamp.setText(message.getTimestamp());

        // displaying the first letter of From in icon text
        holder.iconText.setText("ddd");//holder.iconText.setText(message.getFrom().substring(0, 1));

        if(selected_demande.contains(demandes.get(position)))
            holder.ll_listitem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_selected_state));
        else
            holder.ll_listitem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.list_item_normal_state));
    }

    @Override
    public int getItemCount() {
        return demandes.size();
    }


    public interface RecyclerViewAdapterListener{
        void onIconClicked(int position);
        void onIconImportantClicked(int position);
        void onMessageRowClicked(int position);
        void onRowLongClicked(int position);
    }


}
