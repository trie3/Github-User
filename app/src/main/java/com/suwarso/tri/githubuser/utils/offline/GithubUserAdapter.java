package com.suwarso.tri.githubuser.utils.offline;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.suwarso.tri.githubuser.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.GithubUserViewHolder> {

    private Context context;
    private List<GithubUser> listUser;
    private View.OnClickListener mOnItemClickListener;

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public void setList(List<GithubUser> list){
        this.listUser = list;
        notifyDataSetChanged();
    }
//    public GithubUserAdapter(@NonNull DiffUtil.ItemCallback<GithubUser> diffCallback, Context context) {
//        super(diffCallback);
//        this.context = context;
//    }




//
//    public GithubUserAdapter(@NonNull DiffUtil.ItemCallback<GithubUser> diffCallback) {
//        super(diffCallback);
//    }

    public GithubUserAdapter(Context context, List<GithubUser> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public GithubUserAdapter.GithubUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new GithubUserAdapter.GithubUserViewHolder(inflater.inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(GithubUserViewHolder holder, int position) {
//        if((position+1) % 4 == 0){
//            holder.bindDataInvert(listUser.get(position));
//        }else{
//        }
        holder.bindData(listUser.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class GithubUserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_bg) ConstraintLayout background;
        @BindView(R.id.item_user_img_profile) CircleImageView imageProfile;
        @BindView(R.id.itemUser_tv_name) TextView tvNama;
        @BindView(R.id.itemUser_tv_company) TextView tvCompany;
        @BindView(R.id.itemUser_notes) ImageView imgNote;

        public GithubUserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        void bindData(GithubUser model, int position){
            if(model.isSeen()){
                background.setBackgroundColor(context.getResources().getColor(R.color.window_background_seen));
            }else{
                background.setBackgroundColor(context.getResources().getColor(R.color.window_background));
            }
            if(position % 4 == 0){
                Glide.with(context)
                        .asBitmap()
                        .load(model.getAvatarUrl())
                        .error(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                imageProfile.setImageBitmap(invertImage(resource));
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {

                            }
                        });
            }else {
                Glide.with(context)
                        .load(model.getAvatarUrl())
                        .error(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageProfile);
            }
            tvNama.setText(model.getLogin());
            if(model.getNote().isEmpty() || model.getNote().equals("")){
                imgNote.setVisibility(View.GONE);
            }else{
                imgNote.setVisibility(View.VISIBLE);
            }
        }

        public Bitmap invertImage(Bitmap src) {
            // create new bitmap with the same attributes(width,height)
            // as source bitmap
            Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
            // color info
            int A, R, G, B;
            int pixelColor;
            // image size
            int height = src.getHeight();
            int width = src.getWidth();

            // scan through every pixel
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++)
                {
                    // get one pixel
                    pixelColor = src.getPixel(x, y);
                    // saving alpha channel
                    A = Color.alpha(pixelColor);
                    // inverting byte for each R/G/B channel
                    R = 255 - Color.red(pixelColor);
                    G = 255 - Color.green(pixelColor);
                    B = 255 - Color.blue(pixelColor);
                    // set newly-inverted pixel to output image
                    bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }

            // return final bitmap
            return bmOut;
        }

    }
}
