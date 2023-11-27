package com.example.vergencyshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vergencyshop.R;
import com.example.vergencyshop.ThanhToanSanPham;
import com.example.vergencyshop.models.SanPham;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.HolderThanhToan> {

    ArrayList<SanPham> list ;
    Context context ;

    String  soLuong , size ;
    int tongTien;

    public ThanhToanAdapter(ArrayList<SanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ThanhToanAdapter(ArrayList<SanPham> list, Context context,String soLuong , String size) {
        this.list = list;
        this.context = context;
        this.soLuong = soLuong ;
        this.size = size ;
    }

    @NonNull
    @Override
    public HolderThanhToan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thanh_toan_item,parent,false);
        return new HolderThanhToan(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderThanhToan holder, int position) {

        holder.tvItemTenSanPhamThanhToan.setText(list.get(position).getTenSP());

        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        Currency currency = Currency.getInstance(locale);

        String formattedGiaSP = currencyFormat.format(Double.parseDouble(list.get(position).getGiaSP()));
        holder.tvItemGiaSanPhamThanhToan.setText(formattedGiaSP);

        tongTien = Integer.parseInt(soLuong) * Integer.parseInt(list.get(position).getGiaSP());
        String formattedTongTien = currencyFormat.format(tongTien);
        holder.tvItemTongTienSanPhamThanhToan.setText(formattedTongTien);
        holder.tvItemSizeSanPhamThanhToan.setText(size);
        holder.tvItemSoLuongSanPhamThanhToan.setText(soLuong);

        Glide .with(context).load(list.get(position).getAnhSP()).into(holder.imgSanPhamThanhToan);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    class HolderThanhToan extends RecyclerView.ViewHolder {
        TextView tvItemTenSanPhamThanhToan,
                tvItemSizeSanPhamThanhToan,
                tvItemGiaSanPhamThanhToan ,
                tvItemSoLuongSanPhamThanhToan,
                 tvItemTongTienSanPhamThanhToan;

        ImageView imgSanPhamThanhToan;
        public HolderThanhToan(@NonNull View itemView) {
            super(itemView);

            imgSanPhamThanhToan = itemView.findViewById(R.id.imgSanPhamThanhToan);
            tvItemGiaSanPhamThanhToan = itemView.findViewById(R.id.tvItemGiaSanPhamThanhToan);
            tvItemTenSanPhamThanhToan   = itemView.findViewById(R.id.tvItemTenSanPhamThanhToan);
            tvItemSizeSanPhamThanhToan     = itemView.findViewById(R.id.tvItemSizeSanPhamThanhToan);
            tvItemSoLuongSanPhamThanhToan  = itemView.findViewById(R.id.tvItemSoLuongSanPhamThanhToan);
            tvItemTongTienSanPhamThanhToan    = itemView.findViewById(R.id.tvItemTongTienSanPhamThanhToan);

        }
    }



}
