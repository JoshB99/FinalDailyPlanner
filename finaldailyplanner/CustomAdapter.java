package com.example.finaldailyplanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finaldailyplanner.Goal;
import com.example.finaldailyplanner.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by hardik on 9/1/17.
 */
public class CustomAdapter  extends BaseAdapter {

    private Context context;
    public static ArrayList<Goal> modelArrayList;
    DatabaseHelper mDatabaseHelper;


    public CustomAdapter(Context context, ArrayList<Goal> modelArrayList) {

        this.context = context;
        this.modelArrayList = modelArrayList;
        mDatabaseHelper = new DatabaseHelper(context);
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.description);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


        holder.tvDescription.setText(modelArrayList.get(position).getDescription());

        holder.checkBox.setChecked(modelArrayList.get(position).getSelected());

        holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag( position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView tv = (TextView) tempview.findViewById(R.id.description);
                Integer pos = (Integer)  holder.checkBox.getTag();

                Cursor data = mDatabaseHelper.getItemID(modelArrayList.get(pos).getDescription());
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    if(modelArrayList.get(pos).getSelected()){
                        modelArrayList.get(pos).setSelected(false);
                        mDatabaseHelper.selectCheckbox(0, itemID, modelArrayList.get(pos).getDescription());
                        Cursor cursor = mDatabaseHelper.getData();
                        while(cursor.moveToNext()){
                            boolean b = (cursor.getInt(3) > 0 );
                            String s = cursor.getString(1);
                        }
                    }else {
                        modelArrayList.get(pos).setSelected(true);
                        mDatabaseHelper.selectCheckbox(1, itemID, modelArrayList.get(pos).getDescription());
                    }
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected CheckBox checkBox;
        private TextView tvDescription;

    }

}