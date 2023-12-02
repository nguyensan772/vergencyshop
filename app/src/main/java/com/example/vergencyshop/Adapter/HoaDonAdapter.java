package com.example.vergencyshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vergencyshop.R;
import com.example.vergencyshop.models.HoaDon;
import com.example.vergencyshop.models.NguoiDung;
import com.example.vergencyshop.models.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HolderHoaDonAdapter>{

    ArrayList<HoaDon> list ;
    Context context ;
    public void filterList_hoadon(ArrayList<HoaDon> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }
    public HoaDonAdapter(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderHoaDonAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoa_don,parent,false);
        return new HolderHoaDonAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderHoaDonAdapter holder, int position) {
        DatabaseReference reference = FirebaseDatabase .getInstance().getReference();
        holder.tvIdHoaDon .setText("Số hóa đơn: "+list.get(position).getIdHD()); ;
        holder. tvNgayTaoHoaDon .setText("Ngày tạo: "+list.get(position).getNgayMua());
        reference.child("NguoiDung").child(list.get(position).getIdND()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    NguoiDung nguoiDung =    snapshot.getValue(NguoiDung.class);

                    holder.tvKhachHangHoaDon.setText("Khách hàng: "+nguoiDung.getTen());
                    holder. tvSoDienThoaiHoaDon .setText("Số điện thoại: "+nguoiDung.getSoDienThoai());
                    holder.tvDiaChiHoaDon .setText("Địa chỉ: "+nguoiDung.getDiaChi());
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        holder. tvTongTienHoaDon.setText("Tổng tiền: "+list.get(position).getThanhTien()+ "VNĐ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HolderHoaDonAdapter extends  RecyclerView.ViewHolder {

        TextView tvIdHoaDon , tvNgayTaoHoaDon ,tvKhachHangHoaDon , tvSoDienThoaiHoaDon , tvDiaChiHoaDon , tvTongTienHoaDon;
        public HolderHoaDonAdapter(@NonNull View itemView) {
            super(itemView);

            tvIdHoaDon = itemView.findViewById(R.id.tvIdHoaDon);
            tvNgayTaoHoaDon = itemView.findViewById(R.id.tvNgayTaoHoaDon);
            tvKhachHangHoaDon= itemView.findViewById(R.id.tvKhachHangHoaDon);
            tvSoDienThoaiHoaDon= itemView.findViewById(R.id.tvSoDienThoaiHoaDon);
            tvDiaChiHoaDon= itemView.findViewById(R.id.tvDiaChiHoaDon);
            tvTongTienHoaDon= itemView.findViewById(R.id.tvTongTienHoaDon);

        }
    }
}
