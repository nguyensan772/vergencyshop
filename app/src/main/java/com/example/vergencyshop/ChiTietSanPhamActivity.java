package com.example.vergencyshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.vergencyshop.Adapter.GioHangAdapter;
import com.example.vergencyshop.models.GioHang;
import com.example.vergencyshop.models.SanPham;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    TextView tvTenSanPham,tvGiaSanPham,tvThongTinChiTietSanPham;
    ImageView imgChiTietSanPham,imgBackToMain;

    LinearLayout btnThemVaoGio,btnMuaNgay;
    //Cụm tăng chỉnh sô lượng
    TextView btnTruSoLuong , btnTangSoLuong, tvSoLuong;
    //Đặt hàng và giỏ hàng
    Button btnThemGioHang , btnDatHang ;
    //Chọn size
    RadioButton rdSizeM , rdSizeL , rdSizeXL ;
    SanPham sanPham;
    int index;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cartRef = database.getReference("GioHang");

    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    String size = null;
    int position ;
    GioHangAdapter gioHangAdapter;
    List<GioHang> listGH;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);


        index = 1;
        anhXa();
        //Lấy dữ liệu từ trang chủ
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        sanPham = (SanPham) bundle.get("SanPham");
        position = bundle.getInt("viTriSanPham");
        //Set dữ liệu
        setChiTietSanPham();
        hienSoLuong();
        chonSoLuong();


        if ( sanPham.getTrangthaiSP() == null || sanPham.getTrangthaiSP().equals("Còn Hàng")){

            btnMuaNgay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chonSize(1);
                }
            });

            btnThemVaoGio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chonSize(2);
                }
            });


        }else {

            btnDatHang.setEnabled(false);
            btnThemGioHang.setEnabled(false);
            tvGiaSanPham.setText("Hết hàng");

            btnThemGioHang.setBackgroundColor(R.color.black);
            btnDatHang.setBackgroundColor(R.color.black);

            tvSoLuong.setEnabled(false);
            btnTangSoLuong.setEnabled(false);
            btnTruSoLuong.setEnabled(false);
        }









        imgBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietSanPhamActivity.this, MainActivity.class));
            }
        });

    }

    private void themVaoGio(){

        String anhSP =  sanPham.getAnhSP();
        String tenSP = sanPham.getTenSP();
        String sizeSP = size;
        String soluongSP = String.valueOf(index);
        String giaSP = sanPham.getGiaSP();

        String tongtien = String.valueOf(Integer.parseInt(soluongSP)*Integer.parseInt(giaSP));

//<<<<<<< HEAD
//
//        String idND = user.getUid();
//        Toast.makeText(this, ""+giaSP, Toast.LENGTH_SHORT).show();
//        GioHang newItem = new GioHang(anhSP,tenSP,sizeSP,tongtien,soluongSP,new String(),idND);
//        cartRef.push().setValue(newItem);

        String idSP = "S"+(position + 1);
        String idND = user.getUid();

        GioHang newItem = new GioHang(anhSP,tenSP,sizeSP,tongtien,soluongSP,idSP,idND);
        cartRef.child(idSP+""+idND).setValue(newItem);

        Toast.makeText(this, "Đã thêm sản phẩm vào giỏ hàng !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(ChiTietSanPhamActivity.this,MainActivity.class));

    }

    private  void anhXa (){
        tvTenSanPham = findViewById(R.id.tvTenChiTietSanPham);
        tvGiaSanPham = findViewById(R.id.tvGiaChiTietSanPham);
        tvThongTinChiTietSanPham = findViewById(R.id.tvThongTinChiTietSanPham);
        imgChiTietSanPham = findViewById(R.id.imgChiTietSanPhamAct);
        imgBackToMain = findViewById(R.id.img_backToMainMenu);
        // Cụm tăng số lượng
        btnTangSoLuong = findViewById(R.id.btnCongSoLuong);
        btnTruSoLuong = findViewById(R.id.btnTruSoLuong);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        //Chọn size
        rdSizeM = findViewById(R.id.rdSizeM);
        rdSizeL = findViewById(R.id.rdSizeL);
        rdSizeXL = findViewById(R.id.rdSizeXL);
        //Đặt hàng và giỏ hàng
        btnMuaNgay = findViewById(R.id.btn_muangay);
        btnThemVaoGio = findViewById(R.id.btn_themvaogio);


    }

    private void setChiTietSanPham (){
        Glide.with(this).load(sanPham.getAnhSP()).error(R.drawable.logo).placeholder(R.drawable.logo).into(imgChiTietSanPham);
        tvTenSanPham.setText(sanPham.getTenSP());
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        Currency currency = Currency.getInstance(locale);

        String formattedGiaSanPham = currencyFormat.format(Double.parseDouble(sanPham.getGiaSP()));
        tvGiaSanPham.setText(formattedGiaSanPham);
        tvThongTinChiTietSanPham.setText(sanPham.getMotaSP());
    }



    private  int hienSoLuong (){
        tvSoLuong.setText(String.valueOf(index));
        return index;
    }
    private  void chonSoLuong (){
        btnTangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                hienSoLuong();
            }
        });

        btnTruSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 1){
                    index--;
                    hienSoLuong();
                }else {
                    index =1;
                    hienSoLuong();
                }
            }
        });
    }

    private void chonSize (int check){
        if (!rdSizeXL.isChecked() && !rdSizeL.isChecked() && !rdSizeM.isChecked()){
            Toast.makeText(this, "Bạn chưa chọn size", Toast.LENGTH_SHORT).show();
            return;
        }

        if (rdSizeXL.isChecked()){
            size = "XL";
        }else if (rdSizeL.isChecked()){
            size = "L";

        }else if (rdSizeM.isChecked()){
            size = "M";
        }
        Intent intent;
        if(check == 1){
         intent = new Intent(ChiTietSanPhamActivity.this , ThanhToanSanPham.class);}
        else {
            themVaoGio();

            intent = new Intent(ChiTietSanPhamActivity.this , GioHangActivity.class);}

            Bundle bundle = new Bundle();


            bundle.putSerializable("thanhtoancainay", sanPham);
            bundle.putString("soluongthanhtoan", String.valueOf(index));
            bundle.putString("sizethanhtoan", size);

            intent.putExtras(bundle);
            this.startActivity(intent);


    }

}