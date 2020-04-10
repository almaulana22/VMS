package com.pegadaian.vms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class VisitorAdapter extends RecyclerView.Adapter <VisitorViewHolder> {

    private Context context;
    private List<VisitorData> visitorDataList;
    private int lastPosition = -1;

    public VisitorAdapter(Context context, List<VisitorData> visitorDataList) {
        this.context = context;
        this.visitorDataList = visitorDataList;
    }

    @Override
    public VisitorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_visitor,viewGroup,false);

        return new VisitorViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull final VisitorViewHolder visitorViewHolder, int i) {


        Glide.with(context)
                .load(visitorDataList.get(i).getItemFoto())
                .into(visitorViewHolder.imgVisitor);

        visitorViewHolder.tvNama.setText(visitorDataList.get(i).getItemNama());
        visitorViewHolder.tvPerusahaan.setText(visitorDataList.get(i).getItemPerusahaan());
        visitorViewHolder.tvTelp.setText(visitorDataList.get(i).getItemTelp());

        visitorViewHolder.cardVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,DetailVisitorActivity.class);
                intent.putExtra("Image",visitorDataList.get(visitorViewHolder.getAdapterPosition()).getItemFoto());
                intent.putExtra("Nama",visitorDataList.get(visitorViewHolder.getAdapterPosition()).getItemNama());
                intent.putExtra("Perusahaan",visitorDataList.get(visitorViewHolder.getAdapterPosition()).getItemPerusahaan());
                intent.putExtra("Telp",visitorDataList.get(visitorViewHolder.getAdapterPosition()).getItemTelp());
                intent.putExtra("keyValue",visitorDataList.get(visitorViewHolder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });

        setAnimation(visitorViewHolder.itemView,i);

    }

    public void setAnimation(View viewToAnimate, int position ){

        if(position> lastPosition){

            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() { return visitorDataList.size(); }

    public void filteredList(ArrayList<VisitorData> filterList) {

        visitorDataList = filterList;
        notifyDataSetChanged();
    }
}

class VisitorViewHolder extends RecyclerView.ViewHolder {

    ImageView imgVisitor;
    TextView tvNama, tvPerusahaan, tvTelp, tvCheckin;
    CardView cardVisitor;

    public VisitorViewHolder(@NonNull View itemView) {
        super(itemView);

        imgVisitor = itemView.findViewById(R.id.imgVisitor);
        tvNama = itemView.findViewById(R.id.tvNama);
        tvPerusahaan = itemView.findViewById(R.id.tvPerusahaan);
        tvTelp = itemView.findViewById(R.id.tvTelp);
        tvCheckin = itemView.findViewById(R.id.tvCheckin);
        cardVisitor = itemView.findViewById(R.id.cvVisitor);
    }
}
