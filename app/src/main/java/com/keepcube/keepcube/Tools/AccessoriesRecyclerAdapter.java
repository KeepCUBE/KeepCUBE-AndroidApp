package com.keepcube.keepcube.Tools;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private int currentRoom = -1;


    // TODO: 18.04.2017 updatnout layout a vkladani hodnot do taxtviewu

    public AccessoriesRecyclerAdapter(int roomIndex) {
//        currentRoom = Home.getRoomIDByIndex(roomIndex);
        currentRoom = roomIndex;

        // Content Updater, zatím prasácký
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.shouldUpdateRecyclerViews()) {
                    notifyDataSetChanged();
                    MainActivity.recyclerViewsUpdated();
                }
                handler.postDelayed(this, 1);
            }
        }, 1);

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
        // default
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_room, parent, false);

        if (viewType == ITEM_TYPE_CLASSIC) {
            View normalView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_led, parent, false);
            return new ClassicViewHolder(normalView);
        } else if (viewType == ITEM_TYPE_SPACE) {
            View headerRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_space, parent, false);
            return new SpaceViewHolder(headerRow);
        }

        // default
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

                classicViewHolder.name.setText(Home.room(currentRoom).getDeviceNameByIndex(position - 1));
                classicViewHolder.device_id.setText(Home.room(currentRoom).getDeviceIDByIndex(position - 1));
                classicViewHolder.type_id.setText(Home.room(currentRoom).getDeviceTypeByIndex(position - 1));
                classicViewHolder.mesh_addr.setText(Home.room(currentRoom).getDeviceMeshAddrByIndex(position - 1));

                break;

            case ITEM_TYPE_LED:
                break;

            case ITEM_TYPE_SENSE:
                break;
        }
    }

    @Override
    public int getItemCount() {

        Log.d("getItemCount room", String.valueOf(currentRoom));
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
        TextView name;
        TextView device_id;
        TextView type_id;
        TextView mesh_addr;

        ClassicViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            device_id = (TextView) v.findViewById(R.id.device_id);
            type_id = (TextView) v.findViewById(R.id.type_id);
            mesh_addr = (TextView) v.findViewById(R.id.mesh_addr);
        }
    }

}