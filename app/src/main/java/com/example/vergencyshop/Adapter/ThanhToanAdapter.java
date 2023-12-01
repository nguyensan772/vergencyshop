package com.example.vergencyshop.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vergencyshop.R;
import com.example.vergencyshop.ThanhToanSanPham;
import com.example.vergencyshop.models.HoaDonChiTiet;
import com.example.vergencyshop.models.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.HolderThanhToan> {

    ArrayList<HoaDonChiTiet> list ;
    Context context ;
    String  soLuong , size ;
    int tongTien;

    public ThanhToanAdapter(ArrayList<HoaDonChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public ThanhToanAdapter(ArrayList<HoaDonChiTiet> list, Context context,String soLuong , String size) {
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

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SanPham").child(list.get(position).getIdSP());

            databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SanPham sanPham = new SanPham();
                sanPham =  snapshot.getValue(SanPham.class);

                Glide.with(context)
                        .load(Uri.decode(list.get(position).getAnhSP()))
                        .placeholder(R.drawable.ic_giohang)
                        .error(R.drawable.ngacnhien)
                        .into(holder.imgSanPhamThanhToan);

                holder.tvItemTenSanPhamThanhToan.setText(sanPham.getTenSP());
                holder.tvItemSizeSanPhamThanhToan.setText(list.get(position).getSizeSP());
                holder.tvItemTongTienSanPhamThanhToan.setText(list.get(position).getTongTien());
                holder.tvItemSoLuongSanPhamThanhToan.setText(list.get(position).getSoLuong());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        String formattedGiaSP = currencyFormat.format(Double.parseDouble(list.get(position).getTongTien()));
//        holder.tvItemGiaSanPhamThanhToan.setText(formattedGiaSP.toString());

//        Toast.makeText(context, ""+list.get(position).getTongTien(), Toast.LENGTH_SHORT).show();



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
