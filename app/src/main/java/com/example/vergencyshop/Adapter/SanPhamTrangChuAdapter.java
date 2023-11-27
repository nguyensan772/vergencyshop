package com.example.vergencyshop.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vergencyshop.ChiTietSanPham;
import com.example.vergencyshop.R;
import com.example.vergencyshop.models.SanPham;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class SanPhamTrangChuAdapter extends RecyclerView.Adapter<SanPhamTrangChuAdapter.HolderSanPhamTrangChuAdapter> {



    private final Context context;
    private final ArrayList<SanPham> list;


    public SanPhamTrangChuAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
    }






    @NonNull
    @Override
    public HolderSanPhamTrangChuAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.san_pham_item_trang_chu,parent,false);

        return new HolderSanPhamTrangChuAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderSanPhamTrangChuAdapter holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context)
                .load(Uri.decode(list.get(position).getAnhSP()))
                .placeholder(R.drawable.ic_giohang)

                .error(R.drawable.ngacnhien)
                .into(holder.imgSP);

        holder.tenSP.setText(list.get(position).getTenSP());
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        Currency currency = Currency.getInstance(locale);

        String formattedGiaSP = currencyFormat.format(Double.parseDouble(list.get(position).getGiaSP()));
        holder.giaSP.setText(formattedGiaSP);


        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class HolderSanPhamTrangChuAdapter extends RecyclerView.ViewHolder {
            ImageView imgSP;
            TextView tenSP , giaSP ;
            LinearLayout layoutItem ;
        public HolderSanPhamTrangChuAdapter(@NonNull View itemView) {
            super(itemView);
            imgSP = (ImageView) itemView.findViewById(R.id.imgSanPhamTrangChu);
            tenSP =(TextView) itemView.findViewById(R.id.tvTenItemSanPhamTrangChu);
            giaSP =(TextView) itemView.findViewById(R.id.tvGiaItemSanPhamTrangChu);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItemSanPhamTrangChu);
        }
    }

    private void onClickGoToDetail(SanPham model) {
        Intent intent = new Intent(context, ChiTietSanPham.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("SanPham",model);
        intent.putExtras(bundle);
        context.startActivity(intent);



    }
}
