package com.example.mdjahirulislam.final_project_bitm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.modelClass.Spacecraft;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Oclemy on 8/4/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class CustomAdapterMultiImage extends BaseAdapter {

    Context context;
    ArrayList<Spacecraft> spacecrafts;

    public CustomAdapterMultiImage(Context context, ArrayList<Spacecraft> spacecrafts) {
        this.context = context;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //INFLATE CUSTOM LAYOUT
            view = LayoutInflater.from(context).inflate(R.layout.single_image_show_for_select_photo, viewGroup, false);
        }

        final Spacecraft s = (Spacecraft) this.getItem(i);

//        TextView nameTxt = (TextView) view.findViewById(R.id.imageNameTV);
        final ImageView img = (ImageView) view.findViewById(R.id.spacecraftImg);
        ImageView deleteImg = (ImageView) view.findViewById(R.id.deleteImageButton);

        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spacecrafts.remove(i);
                Toast.makeText(context,"Remove "+ s.getName(), Toast.LENGTH_SHORT).show();

                if (spacecrafts.size()==0){

                    Toast.makeText(context, "array size : "+spacecrafts.size(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "else array size : "+spacecrafts.size(), Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
            }
        });

        //BIND DATA
//        nameTxt.setText(s.getName());
        Picasso.with(context).load(s.getUri()).placeholder(R.drawable.no_image).into(img);

        //VIEW ITEM CLICK
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, s.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
