package com.example.hypergaragesale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Taral on 3/11/2016.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    private ArrayList<BrowsePosts> mDataset;
    private OnItemClickListener clickListener;
    Bitmap theImage;
    Bitmap bitmap;

Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView mTitle;
        public TextView mPrice;
        public ImageView mImage;
        private OnItemClickListener clickListener;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) itemView.findViewById(R.id.titleView);
            mPrice = (TextView) itemView.findViewById(R.id.priceView);
            mImage=(ImageView)itemView.findViewById(R.id.image);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            // Implement view click Listener when make each row of RecycleView clickable
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());



        }
        public void setClickListener(OnItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PostsAdapter(ArrayList<BrowsePosts> myDataset,Context context) {
        mDataset = myDataset;
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get elements from your dataset at this position
        // - replace the contents of the views with that elements
        holder.mTitle.setText(mDataset.get(position).mTitle);
        holder.mPrice.setText(mDataset.get(position).mPrice);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(mDataset.get(position).mImage);
         theImage = BitmapFactory.decodeStream(imageStream);
bitmap=theImage;
        holder.mImage.setImageBitmap(theImage);

        holder.setClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent i=new Intent(context,PostDetailsActivity.class);
                i.putExtra("Name",mDataset.get(position).mTitle);
                i.putExtra("Price",mDataset.get(position).mPrice);

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();

                i.putExtra("Image", byteArray);
                context.startActivity(i);

            }


        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}