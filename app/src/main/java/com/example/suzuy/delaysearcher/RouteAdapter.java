package com.example.suzuy.delaysearcher;

/**
 * Created by suzuy on 2017/01/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class RouteAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<RailwayRoute> myList;
    public RouteAdapter(Context c, ArrayList<RailwayRoute> l)
    {
        context = c;
        myList = l;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return myList.size();
    }
    @Override
    public Object getItem(int i) {
        return myList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.route_view, viewGroup, false);
        ((TextView)view.findViewById(R.id.name)).setText(myList.get(i).getName());
        ((TextView)view.findViewById(R.id.company)).setText(myList.get(i).getCompany());
        return view;
    }
}
