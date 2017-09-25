package com.example.admin.week4weekendhwamazonpablo;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.week4weekendhwamazonpablo.model.Response;

import java.util.List;

import static com.example.admin.week4weekendhwamazonpablo.R.id.ivBookImg;

/**
 * Created by Admin on 9/13/2017.
 */

public class RecyclerViewAdapterAmazon extends RecyclerView.Adapter<RecyclerViewAdapterAmazon.ViewHolder> {

    RecyclerView rvCelebList;
    private RecyclerViewAdapterAmazon.OnViewHolderInteractionListener mListener;
    private static final String TAG = "RecyclerViewAdapterCeleb";
    List<Response> carList = new ArrayList<>();
    Context context;

    public RecyclerViewAdapterAmazon( List<Response> carList) {

        this.carList = carList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivBookImg;
        private TextView tvTitle ;




        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivBookImg= itemView.findViewById(R.id.ivBookImg);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //    Log.d(TAG, "onCreateViewHolder: ");
        context = parent.getContext();
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rv_car_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //  Log.d(TAG, "onBindViewHolder position: "+position);
        Response car = carList.get(position);

        holder.tvTitle.setText(car.getTitle());


        Glide.with(context)
                .load(car.getImageURL())
                .error(R.mipmap.ic_launcher)
                .into(holder.ivBookImg);

/*
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null) {
                    //  Toast.makeText(context, position+"" , Toast.LENGTH_SHORT).show();
                    mListener.onViewHolderInteraction(String.valueOf(position));
                }
                //  Toast.makeText(context, "click" , Toast.LENGTH_SHORT).show();
            }
        });
*/




    }

    @Override
    public int getItemCount() {

        return carList.size();
    }

    //Dismiss
    public void dismissCar(int pos) {
        this.carList.remove(pos);
        this.notifyItemRemoved(pos);
    }


    public interface OnViewHolderInteractionListener {
        void onViewHolderInteraction(String data);
    }

    public void setListener(RecyclerViewAdapterAmazon.OnViewHolderInteractionListener listener) {
        this.mListener = listener;
    }

}
