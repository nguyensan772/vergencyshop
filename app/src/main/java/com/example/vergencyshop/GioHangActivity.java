package com.example.vergencyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vergencyshop.Adapter.GioHangAdapter;

import com.example.vergencyshop.models.GioHang;
import com.example.vergencyshop.models.HoaDon;
import com.example.vergencyshop.models.HoaDonChiTiet;
import com.example.vergencyshop.models.SanPham;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GioHangActivity extends AppCompatActivity {

    RecyclerView rc_giohang;

    TextView tv_tongtien, tv_muahang;
    ImageView btn_backToMain;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    ArrayList<GioHang> list = new ArrayList<>();
    GioHang gioHang = new GioHang();

    GioHangAdapter gioHangAdapter;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        rc_giohang = findViewById(R.id.rcGioHang);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        tv_muahang = findViewById(R.id.btnMuaHang);

        btn_backToMain = findViewById(R.id.img_backToMain);
        btn_backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GioHangActivity.this, MainActivity.class));
            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        rc_giohang.setLayoutManager(new LinearLayoutManager(this));

        gioHangAdapter = new GioHangAdapter(this, list);

        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("GioHang");

        rc_giohang.setAdapter(gioHangAdapter);

        Query query = cartRef.orderByChild("idNguoiDung").equalTo(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        list.add(snapshot1.getValue(GioHang.class));

                    }


                    tv_tongtien.setText(tinhtongtien());
                }
                gioHangAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        tv_muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHoaDon();
            }
        });
    }

    private void setHoaDon() {

        ArrayList<HoaDon> listHD = new ArrayList<>();


        DatabaseReference hoadonRef = FirebaseDatabase.getInstance().getReference("HoaDon");
        Query query = hoadonRef.orderByChild("idND").equalTo(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot data:snapshot.getChildren()
                         ) {
                        listHD.add(data.getValue(HoaDon.class));

                    }



                    DatabaseReference hoadonchitietRef = FirebaseDatabase.getInstance().getReference("HoaDonChiTiet");
                    if (list.size() < 0) {

                        return;
                    }
                    HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                    for (GioHang hang : list) {

                        hoaDonChiTiet.setIdHD(hang.getIdNguoiDung() + listHD.size());
                        hoaDonChiTiet.setIdSP(hang.getIdSP());

                        hoaDonChiTiet.setSoLuong(hang.getSoluongSP());
                        hoadonchitietRef.push().setValue(hoaDonChiTiet);

                    }
                    startActivity(new Intent(GioHangActivity.this, ThanhToanSanPham.class));




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }

    private String tinhtongtien() {
        int tongTien = 0;

        for (GioHang gioHang1 : list) {

            int giatri = Integer.parseInt(gioHang1.getGiaSP());
            tongTien = giatri + tongTien;
        }

        return String.valueOf(tongTien);
    }


}