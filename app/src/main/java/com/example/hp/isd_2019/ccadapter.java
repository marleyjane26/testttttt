package com.example.hp.isd_2019;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
      final   ccadapter.ViewHolder holder;

        if (convertView == null) {
            holder = new ccadapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_view_cc, null, true);

holder.delete=(FloatingActionButton) convertView.findViewById(R.id.deleteBtn);
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
//        holder.requestQueue = Volley.newRequestQueue(creditcardActivity.);
//
//        holder.progressDialog = new ProgressDialog(this.context);
//holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Showing progress dialog at user registration time.
//                holder.progressDialog.setMessage("Please Wait, We are Deleting the credit card");
//                holder.progressDialog.show();
//
//
//                // Creating string request with post method.
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, holder.HttpUrl,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String ServerResponse) {
//
//                                // Hiding the progress dialog after all task complete.
//                                holder.progressDialog.dismiss();
//
//                                // Showing response message coming from server.
//                                holder.Toast.makeText(this, ServerResponse, Toast.LENGTH_LONG).show();
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        creditcardActivity.this.finish();
//                                    }
//                                }, 2000);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError volleyError) {
//
//                                // Hiding the progress dialog after all task complete.
//                                progressDialog.dismiss();
//
//                                // Showing error message if something goes wrong.
//                                Toast.makeText(creditcardActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
//
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//
//                        // Creating Map String Params.
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        // Adding All values to Params.
//                        params.put("id", paymentID);
//
//
//
//                        return params;
//                    }
//
//                };
//
//                // Creating RequestQueue.
//                RequestQueue requestQueue = Volley.newRequestQueue(creditcardActivity.this);
//
//                // Adding the StringRequest object into requestQueue.
//                requestQueue.add(stringRequest);
//            }
//        });

        return convertView;
    }

    private class ViewHolder {

        protected TextView  num,numjson,name,namejson,expire,datejson,yearjson,cvc,cvcjson;
protected FloatingActionButton delete;
protected  ProgressDialog progressDialog;
        protected   RequestQueue requestQueue;
        protected  String HttpUrl = "https://lbpower.000webhostapp.com/api/postcc.php";
    }

}
