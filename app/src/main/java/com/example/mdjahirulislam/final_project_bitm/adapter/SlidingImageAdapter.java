package com.example.mdjahirulislam.final_project_bitm.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mdjahirulislam.final_project_bitm.R;
import com.example.mdjahirulislam.final_project_bitm.activity.ShowPostListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mdjahirulislam on 07/06/17.
 */

public class SlidingImageAdapter extends PagerAdapter{

    private static final String TAG = SlidingImageAdapter.class.getSimpleName();

    private ArrayList<String> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImageAdapter(Context context,ArrayList<String> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
        Log.d(TAG,"image url :  "+IMAGES.get(0));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.sliding_images_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.image);


//        imageView.setImageResource(IMAGES.get(position));

        String imageUrl = IMAGES.get(position);

        Log.d(TAG+"  image url from adapter : ",imageUrl);
        Context context  = imageView.getContext();
        Uri imageUri = Uri.parse(imageUrl);
        Picasso.with(context).load(imageUri)
                .resize(400, 300)
                .into(imageView);




        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
