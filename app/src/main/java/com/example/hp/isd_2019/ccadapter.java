package com.example.hp.isd_2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ccadapter extends BaseAdapter {

    private Context context;
    private ArrayList<creditcard> dataModelArrayList;

    public ccadapter(Context context, ArrayList<creditcard> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        if(getCount() > 0){
            return getCount();
        }else{
            return super.getViewTypeCount();
        }
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ccadapter.ViewHolder holder;

        if (convertView == null) {
            holder = new ccadapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_view_cc, null, true);


            holder.numjson = (TextView) convertView.findViewById(R.id.numberjson);
            holder.namejson = (TextView) convertView.findViewById(R.id.namejson);
            holder.datejson = (TextView) convertView.findViewById(R.id.monthjson);
            holder.yearjson = (TextView) convertView.findViewById(R.id.yearjson);
            holder.cvcjson = (TextView) convertView.findViewById(R.id.digitjson);
            holder.num = (TextView) convertView.findViewById(R.id.cnum);
            holder.name = (TextView) convertView.findViewById(R.id.cNameHolder);
            holder.expire = (TextView) convertView.findViewById(R.id.expirationDate);
            holder.cvc = (TextView) convertView.findViewById(R.id.cvv);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ccadapter.ViewHolder)convertView.getTag();
        }


        holder.numjson.setText(dataModelArrayList.get(position).getCc_number());
        holder.namejson.setText(dataModelArrayList.get(position).getName_holder());
        holder.datejson.setText(dataModelArrayList.get(position).getmonth());
        holder.yearjson.setText(dataModelArrayList.get(position).getyear());
        holder.cvcjson.setText(dataModelArrayList.get(position).getCvc());


        return convertView;
    }

    private class ViewHolder {

        protected TextView  num,numjson,name,namejson,expire,datejson,yearjson,cvc,cvcjson;

    }

}
