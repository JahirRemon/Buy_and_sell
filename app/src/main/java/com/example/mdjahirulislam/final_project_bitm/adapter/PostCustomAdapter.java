package com.example.mdjahirulislam.final_project_bitm.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.interfaces.ConnectionApi;
import com.example.mdjahirulislam.final_project_bitm.modelClass.AdPostModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mdjahirulislam on 05/05/17.
 */

public class PostCustomAdapter extends RecyclerView.Adapter<PostCustomAdapter.PostViewHolder> {

    private static final String TAG = PostCustomAdapter.class.getSimpleName();


    private ArrayList<AdPostModel> adPostModelArrayList;


    public PostCustomAdapter(ArrayList<AdPostModel> adPostModelArrayList) {
        this.adPostModelArrayList = adPostModelArrayList;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_post_design,parent,false);
        PostCustomAdapter.PostViewHolder postViewHolder = new PostCustomAdapter.PostViewHolder(view);

        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        AdPostModel postModel = adPostModelArrayList.get(position);

        holder.postTitle.setText(postModel.getPost_title());
        holder.postCategory.setText(postModel.getPost_location()+", "+postModel.getPost_category());
        holder.postPrice.setText("Price : "+postModel.getPost_price()+" Tk");
        holder.postCreate.setText(postModel.getCreated_at());

        String imageUrl = postModel.getPost_image_url();

        Log.d(TAG+ " post image url from adapter : ",imageUrl);
        Context context  = holder.postImage.getContext();
        Uri imageUri = Uri.parse(imageUrl);
        Picasso.with(context).load(imageUri)
                .resize(400, 300)
                .into(holder.postImage);

    }

    @Override
    public int getItemCount() {
        return adPostModelArrayList.size();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postTitle;
        TextView postLocation;
        TextView postCategory;
        TextView postPrice;
        TextView postCreate;

        public PostViewHolder(View itemView) {
            super(itemView);

            postImage = (ImageView) itemView.findViewById(R.id.postListAdImageIV);
            postTitle = (TextView) itemView.findViewById(R.id.postListAdTitleTV);
            postCategory = (TextView) itemView.findViewById(R.id.postListAdCategoryTV);
            postPrice = (TextView) itemView.findViewById(R.id.postListAdPriceTV);
            postCreate = (TextView) itemView.findViewById(R.id.postListAdCreatedAtTV);


        }
    }
}

