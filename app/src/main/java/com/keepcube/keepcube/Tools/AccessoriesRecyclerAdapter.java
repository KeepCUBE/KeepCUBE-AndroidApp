package com.keepcube.keepcube.Tools;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keepcube.keepcube.MainActivity;
import com.keepcube.keepcube.R;
import com.keepcube.keepcube.Tools.DataManager.Home;

/**
 * Created by Ondrej on 18.04.2017.
 */

public class AccessoriesRecyclerAdapter extends RecyclerView.Adapter<AccessoriesRecyclerAdapter.ViewHolder> {
    public static final int ITEM_TYPE_CLASSIC = 0;
    public static final int ITEM_TYPE_SPACE = 1;
    public static final int ITEM_TYPE_LED = 2;
    public static final int ITEM_TYPE_SENSE = 3;

    private String currentRoom = "";


    // TODO: 18.04.2017 updatnout layout a vkladani hodnot do taxtviewu

    public AccessoriesRecyclerAdapter(int roomPosition) {
        currentRoom = Home.getRoomNameByIndex(roomPosition);

        // Content Updater, zatím prasácký
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.updateRecyclerViews) {
                    MainActivity.updateRecyclerViews = false;
                    notifyDataSetChanged();
                }
                handler.postDelayed(this, 100);
            }
        }, 100);

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
        switch (position) {
            case 0:
                return ITEM_TYPE_SPACE;

            case 1:
                return ITEM_TYPE_CLASSIC;


            default:
                return ITEM_TYPE_CLASSIC;

        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public AccessoriesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_room, parent, false);

        if (viewType == ITEM_TYPE_CLASSIC) {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_room, parent, false);
            return new ClassicViewHolder(normalView);
        } else if (viewType == ITEM_TYPE_SPACE) {
            View headerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_space, parent, false);
            return new SpaceViewHolder(headerRow);
        }

        return new ViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case ITEM_TYPE_SPACE:
                SpaceViewHolder spaceViewHolder = (SpaceViewHolder) holder;
                break;

            case ITEM_TYPE_CLASSIC:
                ClassicViewHolder classicViewHolder = (ClassicViewHolder) holder;
                classicViewHolder.roomName.setText(Home.room(currentRoom).getDeviceNameByIndex(position - 1));
//                classicViewHolder.numberOfDevices.setText(String.valueOf(mNumberOfDevices.get(position)));
                break;

            case ITEM_TYPE_LED:
                break;

            case ITEM_TYPE_SENSE:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return Home.room(currentRoom).numberOfDevices() + 1;
    }


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