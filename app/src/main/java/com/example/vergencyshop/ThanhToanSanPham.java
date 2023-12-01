package com.example.vergencyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vergencyshop.Adapter.ThanhToanAdapter;
import com.example.vergencyshop.models.HoaDon;
import com.example.vergencyshop.models.HoaDonChiTiet;
import com.example.vergencyshop.models.NguoiDung;
import com.example.vergencyshop.models.SanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class ThanhToanSanPham extends AppCompatActivity {
    TextView tvTenSDtThanhToan , tvDiaChiThanhToan , tvTongThanhToanHoaDon ,btnMuaHang;
    RecyclerView rcSanPhamThanhToan ;
    RadioButton rdNhanHangThanhToan,  rdBankingThanhToan ;
    ThanhToanAdapter thanhToanAdapter;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ArrayList<HoaDonChiTiet> list = new ArrayList<>();

    int indexhoadon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_san_pham);
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        Currency currency = Currency.getInstance(locale);
        anhXa();
        rdBankingThanhToan.setChecked(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        indexhoadon = bundle.getInt("indexhoadon");
        setList();
        setThongTin();

        rcSanPhamThanhToan.setLayoutManager(new LinearLayoutManager(this ));
        thanhToanAdapter = new ThanhToanAdapter(list,this);

        rcSanPhamThanhToan.setAdapter(thanhToanAdapter);

        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMuaHang();
            }
        });

    }



    private int tinhThanhTien (){
        int soHang = 0 ;

        for (HoaDonChiTiet i : list
             ) {

           soHang +=  Integer.parseInt(i.getTongTien());

        }
        return soHang ;
    }

    private void setMuaHang() {

// Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

// Lấy ngày hiện tại và định dạng
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);

        HoaDon hoaDon = new HoaDon();

        DatabaseReference hdRef = FirebaseDatabase.getInstance().getReference();

        hdRef.child("HoaDon").push().setValue(hoaDon);

    }

    private  void anhXa(){

        tvTenSDtThanhToan = findViewById(R.id.tvTenSDtThanhToan);
        tvDiaChiThanhToan = findViewById(R.id.tvDiaChiThanhToan);
        tvTongThanhToanHoaDon = findViewById(R.id.tvTongThanhToanHoaDon);
        btnMuaHang = findViewById(R.id.btnMuaHang);
        rcSanPhamThanhToan = findViewById(R.id.rcSanPhamThanhToan);
        rdBankingThanhToan = findViewById(R.id.rdBankingThanhToan);
        rdNhanHangThanhToan = findViewById(R.id.rdNhanHangThanhToan);
    }

    private String setPhuongThucThanhToan (){

        if (rdBankingThanhToan.isChecked()){
            return "bank";
        }else {
            return "tien";
        }
    }
    private void setThongTin (){




        reference.child("NguoiDung").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NguoiDung nguoiDung = snapshot.getValue(NguoiDung.class);

                tvDiaChiThanhToan.setText(nguoiDung.getDiaChi());
                tvTenSDtThanhToan.setText(nguoiDung.getTen() +"-"+nguoiDung.getDiaChi());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private void setList(){
        ArrayList<HoaDon> listHD = new ArrayList<>();


        DatabaseReference hoadonRef = FirebaseDatabase.getInstance().getReference("HoaDon");
        Query query = hoadonRef.orderByChild("idND").equalTo(user.getUid());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren()
                ) {
                    listHD.add(data.getValue(HoaDon.class));

                }

//                Query queryHoaDon =

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DatabaseReference hoaDonChiTietRef = FirebaseDatabase.getInstance().getReference("HoaDonChiTiet");

        hoaDonChiTietRef.removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Xóa dữ liệu thành công
                        Log.d("XoaDuLieu", "Đã xóa dữ liệu thành công trên Firebase Realtime Database.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xảy ra lỗi khi xóa dữ liệu
                        Log.e("XoaDuLieu", "Lỗi khi xóa dữ liệu trên Firebase Realtime Database: " + e.getMessage());
                    }
                });



    }
}