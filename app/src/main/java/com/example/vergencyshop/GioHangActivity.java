package com.example.vergencyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vergencyshop.Adapter.GioHangAdapter;

import com.example.vergencyshop.models.GioHang;
import com.example.vergencyshop.models.SanPham;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity{

    RecyclerView rc_giohang;

    TextView tv_tongtien, tv_muahang;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    ArrayList<GioHang> list = new ArrayList<>();
    GioHang gioHang = new GioHang();

    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        rc_giohang = findViewById(R.id.rcGioHang);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        tv_muahang = findViewById(R.id.btnMuaHang);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            Toast.makeText(this, "Không có giá trị gì", Toast.LENGTH_SHORT).show();
            return;
        }

        rc_giohang .setLayoutManager(new LinearLayoutManager(this));

        gioHangAdapter = new GioHangAdapter(this,list);

        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("GioHang");

        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();

                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    GioHang item = itemSnapshot.getValue(GioHang.class);
                    list.add(item);
                }

                gioHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu từ Firebase Database
                Log.e("GioHangActivity", "Lỗi đọc dữ liệu từ Firebase Database: " + databaseError.getMessage());
                gioHangAdapter.notifyDataSetChanged();
            }
        });

        rc_giohang.setAdapter(gioHangAdapter);
        // Inflate the layout for this fragment


        tv_muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}