package com.keepcube.keepcube.Tools;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keepcube.keepcube.R;

import java.util.ArrayList;

/**
 * Created by Ondrej on 07.04.2017.
 */


public class RoomsRecyclerAdapter extends RecyclerView.Adapter<RoomsRecyclerAdapter.ViewHolder> {
    public static final int ITEM_TYPE_CLASSIC = 0;
    public static final int ITEM_TYPE_SPACE = 1;

    private ArrayList<String> mNames;
    private ArrayList<Integer> mNumberOfDevices;


    public RoomsRecyclerAdapter(ArrayList<String> names, ArrayList<Integer> numberOfDevices) {
        mNames = names;
        mNumberOfDevices = numberOfDevices;

        // Adding some helping items
        mNames.add(0, "null");
        mNumberOfDevices.add(0, 0);


    }

//    @Deprecated
//    public void add(int position, String item) {
//        mDataset.add(position, item);
//        notifyItemInserted(position);
//    }
//
//    @Deprecated
//    public void remove(String item) {
//        int position = mDataset.indexOf(item);
//        mDataset.remove(position);
//        notifyItemRemoved(position);
//    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE_SPACE : ITEM_TYPE_CLASSIC;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RoomsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_room, parent, false);

        if (viewType == ITEM_TYPE_CLASSIC) {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_room, parent, false);
            return new ClassicViewHolder(normalView); // view holder for normal items
        } else if (viewType == ITEM_TYPE_SPACE) {
            View headerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_space, parent, false);
            return new SpaceViewHolder(headerRow); // view holder for header items
        }

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ITEM_TYPE_CLASSIC:
                ClassicViewHolder classicViewHolder = (ClassicViewHolder) holder;
                classicViewHolder.roomName.setText(mNames.get(position));
                classicViewHolder.numberOfDevices.setText(String.valueOf(mNumberOfDevices.get(position)));
                break;

            case ITEM_TYPE_SPACE:
                SpaceViewHolder spaceViewHolder = (SpaceViewHolder) holder;
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mNames.size();
    }


    // Nemazat, ani nevím k čemu to je, bez toho to nefunguje
    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View v) {
            super(v);
        }
    }

    class SpaceViewHolder extends ViewHolder {
        SpaceViewHolder(View v) {
            super(v);
        }
    }

    class ClassicViewHolder extends ViewHolder {
        TextView numberOfDevices;
        TextView roomName;

        ClassicViewHolder(View v) {
            super(v);
            numberOfDevices = (TextView) v.findViewById(R.id.numberOfDevices);
            roomName = (TextView) v.findViewById(R.id.roomName);
        }
    }

}