package com.jspark.android.myutility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jspark.android.myutility.FiveFragment.OnListFragmentInteractionListener;
import com.jspark.android.myutility.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<String> data = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
    private final Context context;

    public MyItemRecyclerViewAdapter(Context context, List<String> data, OnListFragmentInteractionListener listener) {
        this.context = context;

        // 폰에서 이미지 가져와서 data에 세팅
//        ContentResolver resolver = context.getContentResolver();
//        Uri target = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//
//        String projections[] = {
//                MediaStore.Images.Media.DATA
//        };
//
//        Cursor cursor = resolver.query(target, projections, null, null, null);
//
//        if(cursor.moveToFirst()) {
//            while (cursor.moveToNext()) {
//                String uriStr = cursor.getString(cursor.getColumnIndex(projections[0]));
//                data.add(uriStr);
//            }
//        }
//        Log.w("adapter", "cursor moves");
//        cursor.close();
        this.data = data;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imageUri = data.get(position);
        Glide.with(context).load(holder.imageUri).into(holder.mContentView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mContentView;
        public String imageUri;

        public ViewHolder(View view) {
            super(view);
            mContentView = (ImageView) view.findViewById(R.id.content);
            imageUri = null;

            mContentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent i = new Intent(context, DetailActivity.class);
//                    context.startActivity(i);
                }
            });
        }
    }
}
