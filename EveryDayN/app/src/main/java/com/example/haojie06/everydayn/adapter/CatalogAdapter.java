package com.example.haojie06.everydayn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.haojie06.everydayn.R;
import com.example.haojie06.everydayn.object.CatalogIt;

import java.util.List;

/**
 * Created by haojie06 on 2018/4/1.
 */

public class CatalogAdapter extends ArrayAdapter<CatalogIt> {
    private int resourceId;

    public CatalogAdapter(Context context, int resource, List<CatalogIt> objects)
    {
        super(context,resource,objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CatalogIt catalogIt = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView name = (TextView) view.findViewById(R.id.catText);
        name.setText(catalogIt.getName());
        return view;
    }
}
