package com.lambdaschool.veganmichianaguide.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lambdaschool.veganmichianaguide.R;
import com.lambdaschool.veganmichianaguide.activities.ListFragment.OnListFragmentInteractionListener;
import com.lambdaschool.veganmichianaguide.models.ApiObject;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyListRecyclerViewAdapter extends RecyclerView.Adapter<MyListRecyclerViewAdapter.ViewHolder> {

    private final List<ApiObject> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyListRecyclerViewAdapter(List<ApiObject> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void addItem(ApiObject item) {
        mValues.add(item);
        this.notifyItemChanged(mValues.size() - 1);
    }

    public void addItem(List<ApiObject> items) {
        for(ApiObject item: items) {
            addItem(item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).getName());
//        holder.mContentView.setText(mValues.get(position).getContent());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, holder.mTitleView);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mContentView;
        public ApiObject mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.name);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
