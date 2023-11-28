package com.example.vergencyshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vergencyshop.Adapter.SanPhamTrangChuAdapter;
import com.example.vergencyshop.models.SanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanhMuc extends AppCompatActivity {

    int CODE_CHOSSE;

    RecyclerView rcSanPhamDanhMuc ;

    EditText edtTimKiem ;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    ArrayList<SanPham> list = new ArrayList<>();
    SanPham sanPham = new SanPham();

    SanPhamTrangChuAdapter sanPhamTrangChuAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        anhXa();
        

        Bundle bundle = getIntent().getExtras();


        if (bundle == null){
            Toast.makeText(this, "Không có giá trị gì", Toast.LENGTH_SHORT).show();
            return;
        }
        CODE_CHOSSE =  bundle.getInt("CODE");
        Toast.makeText(this, String.valueOf(CODE_CHOSSE), Toast.LENGTH_SHORT).show();

        rcSanPhamDanhMuc .setLayoutManager(new GridLayoutManager(this,2));

        sanPhamTrangChuAdapter = new SanPhamTrangChuAdapter(this,list);


        if (CODE_CHOSSE == 1 ){

            reference.child("SanPham").orderByChild("danhmuc").equalTo("SHIRT").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        list.clear();
                        for (DataSnapshot dataChild : snapshot.getChildren()){

                            list.add(dataChild.getValue(SanPham.class));

                        }
                        sanPhamTrangChuAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if (CODE_CHOSSE == 2 ){

            reference.child("SanPham").orderByChild("danhmuc").equalTo("TSHIRT").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        list.clear();
                        for (DataSnapshot dataChild : snapshot.getChildren()){

                            list.add(dataChild.getValue(SanPham.class));

                        }
                        sanPhamTrangChuAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if (CODE_CHOSSE == 3 ){

            reference.child("SanPham").orderByChild("danhmuc").equalTo("SWEATER").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        list.clear();
                        for (DataSnapshot dataChild : snapshot.getChildren()){

                            list.add(dataChild.getValue(SanPham.class));

                        }
                        sanPhamTrangChuAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if (CODE_CHOSSE == 4 ){

            reference.child("SanPham").orderByChild("danhmuc").equalTo("HOODIES").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        list.clear();
                        for (DataSnapshot dataChild : snapshot.getChildren()){

                            list.add(dataChild.getValue(SanPham.class));

                        }
                        sanPhamTrangChuAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if (CODE_CHOSSE == 5 ){

            reference.child("SanPham").orderByChild("danhmuc").equalTo("SHORT").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        list.clear();
                        for (DataSnapshot dataChild : snapshot.getChildren()){

                            list.add(dataChild.getValue(SanPham.class));

                        }
                        sanPhamTrangChuAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if (CODE_CHOSSE == 6 ){

            reference.child("SanPham").orderByChild("danhmuc").equalTo("PANTS").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        list.clear();
                        for (DataSnapshot dataChild : snapshot.getChildren()){

                            list.add(dataChild.getValue(SanPham.class));

                        }
                        sanPhamTrangChuAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


        rcSanPhamDanhMuc.setAdapter(sanPhamTrangChuAdapter);



    }


    private  void  anhXa (){
        edtTimKiem = findViewById(R.id.edtTimKiemSanPhamTheoTen);
        rcSanPhamDanhMuc = findViewById(R.id.rcSanPhamDanhMuc);
    }
}