package com.example.maste.diplomaaudit.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maste.diplomaaudit.Model.Enterprise;
import com.example.maste.diplomaaudit.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EnterpriseAdapter extends BaseAdapter{

    Context context;
    LayoutInflater inflater;
    ArrayList<Enterprise> enterprises;

    public EnterpriseAdapter (Context context, ArrayList<Enterprise> enterprises){
        this.context = context;
        this.enterprises = enterprises;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return enterprises.size();
    }

    @Override
    public Object getItem(int position) {
        return enterprises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.list_layout, parent, false);
        }
        Enterprise enterprise = getEnterprise(position);

        ((TextView) view.findViewById(R.id.list_item_text)).setText(enterprise.getName());

        return view;
    }

    Enterprise getEnterprise(int position) {
        return (Enterprise) getItem(position);
    }
}
