package com.festeam.win7.cryptocurrency.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.festeam.win7.cryptocurrency.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by win7 on 2/23/18.
 */

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.ViewHolder> {

    Context mainActivityContext;
    ArrayList<CCryto> list_crypto = new ArrayList<CCryto>();

    public AdapterItems(Context mainActivityContext, ArrayList<CCryto> list_crypto) {
        this.mainActivityContext = mainActivityContext;
        this.list_crypto = list_crypto;
    }

    @Override
    public AdapterItems.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Used to connect our custom UI to our recycler view
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.items, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterItems.ViewHolder holder, int position) {
        //Used to set data in each row of recycler view

        String name = list_crypto.get(position).getName();
        String price = list_crypto.get(position).getPrice();
        String change24 = list_crypto.get(position).getChange24();
        String change7 = list_crypto.get(position).getChange7();
        String symbol = list_crypto.get(position).getSymbol().toLowerCase();

        holder.tw_name.setText(name);
        holder.tw_price.setText(price);
        holder.tw_change24h.setText(change24 + "%");
        holder.tw_change7d.setText(change7 + "%");

        if (Double.parseDouble(change24) >= 0){
            holder.tw_change24h.setTextColor(Color.BLUE);
        }else {
            holder.tw_change24h.setTextColor(Color.RED);
        }

        if (Double.parseDouble(change7) >= 0){
            holder.tw_change7d.setTextColor(Color.BLUE);
        }else {
            holder.tw_change7d.setTextColor(Color.RED);
        }

        try{
            String uri = "@drawable/" + symbol;
            int imageResource = mainActivityContext.getResources().getIdentifier(uri, null, mainActivityContext.getPackageName());
            Drawable imagen = ContextCompat.getDrawable(mainActivityContext.getApplicationContext(), imageResource);
            holder.iw_symbol.setImageDrawable(imagen);
        }catch (Exception e){

        }

        // holder.iw_symbol.setImageResource(R.drawable.bcc);
        // holder.iw_symbol .setImageURI(Uri.parse("https://files.coinmarketcap.com/static/img/coins/32x32/3.png"));
        // Picasso.with(mainActivityContext).load("https://files.coinmarketcap.com/static/img/coins/128x128/"+ (position + 1) +".png").into(holder.iw_symbol);
    }

    @Override
    public int getItemCount() {
        return list_crypto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout items_crypto;

        TextView tw_name;
        TextView tw_price;
        TextView tw_change24h;
        TextView tw_change7d;
        ImageView iw_symbol;

        public ViewHolder(View itemView) {
            super(itemView);
            tw_name = itemView.findViewById(R.id.textView_name);
            tw_price = itemView.findViewById(R.id.textView_price);
            tw_change24h = itemView.findViewById(R.id.textView_change24d);
            tw_change7d = itemView.findViewById(R.id.textView_change7d);
            iw_symbol = itemView.findViewById(R.id.imageView_symbol);

            items_crypto = itemView.findViewById(R.id.items_crypto);

            items_crypto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mainActivityContext,
                            "You clicked items number: "+ getAdapterPosition(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}