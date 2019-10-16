package com.otus.otusbasicnetwork;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTv;
    private ImageView imageIv, imageIvGlide;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);

        titleTv = itemView.findViewById(R.id.titleTv);
        imageIv = itemView.findViewById(R.id.imageIv);
        imageIvGlide = itemView.findViewById(R.id.imageIvGlide);
    }

    public void bind(MovieItem movieItem) {
        this.titleTv.setText(movieItem.title);

        Picasso.get()
                .load(movieItem.imageUrl)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageIv);

        Glide.with(itemView.getContext())
                .load(movieItem.imageUrl)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_error_black_24dp)
                .into(imageIvGlide);
    }
}
