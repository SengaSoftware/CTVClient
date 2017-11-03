package org.vipserv.jacwro.ctvclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Wsdl2Code.WebServices.CTVBackendService.tvOffer;

import java.util.ArrayList;

/**
 * Created by ejacwro on 2017-11-02.
 */

public class MainListAdapter extends BaseAdapter {

    Context context;
    public ArrayList<tvOffer> data;
    private LayoutInflater inflater = null;

    public MainListAdapter(Context context, ArrayList<tvOffer> data) {
        super();

        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.main_list_row, null);
        TextView text = (TextView) vi.findViewById(R.id.offerNameTextView);
        tvOffer item = data.get(position);
        text.setText(item.offerName);
        return vi;
    }
}
