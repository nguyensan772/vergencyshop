package com.example.vergencyshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vergencyshop.models.SanPham;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ChiTietSanPham extends AppCompatActivity {
    TextView tvTenSanPham,tvGiaSanPham,tvThongTinChiTietSanPham;
    ImageView imgChiTietSanPham;

    //Cụm tăng chỉnh sô lượng
    TextView btnTruSoLuong , btnTangSoLuong, tvSoLuong;

    //Đặt hàng và giỏ hàng
    Button btnThemGioHang , btnDatHang ;

    //Chọn size
    RadioButton rdSizeM , rdSizeL , rdSizeXL ;
    SanPham sanPham;
    int index;
    String size ;
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
        //Set dữ liệu
        setChiTietSanPham();
        hienSoLuong();
        chonSoLuong();
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonSize();

            }
        });





    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(ChiTietSanPham.this,MainActivity.class));

    }

    private  void anhXa (){
        tvTenSanPham = findViewById(R.id.tvTenChiTietSanPham);
        tvGiaSanPham = findViewById(R.id.tvGiaChiTietSanPham);
        tvThongTinChiTietSanPham = findViewById(R.id.tvThongTinChiTietSanPham);
        imgChiTietSanPham = findViewById(R.id.imgChiTietSanPhamAct);
        // Cụm tăng số lượng
        btnTangSoLuong = findViewById(R.id.btnCongSoLuong);
        btnTruSoLuong = findViewById(R.id.btnTruSoLuong);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        //Chọn size
        rdSizeM = findViewById(R.id.rdSizeM);
        rdSizeL = findViewById(R.id.rdSizeL);
        rdSizeXL = findViewById(R.id.rdSizeXL);
        //Đặt hàng và giỏ hàng
        btnThemGioHang = findViewById(R.id.btnThemVaoGioHangChiTietSanPham);
        btnDatHang = findViewById(R.id.btnThanhToanChiTietSanPham);


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

    private  void hienSoLuong (){
        tvSoLuong.setText(String.valueOf(index));
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

    private void chonSize (){
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


        Intent intent = new Intent(ChiTietSanPham.this , ThanhToanSanPham.class);
        Bundle bundle = new Bundle();


        bundle.putSerializable("thanhtoancainay",sanPham);
        bundle.putString("soluongthanhtoan",String.valueOf(index));
        bundle.putString("sizethanhtoan",size);

        intent.putExtras(bundle);
        this.startActivity(intent);

    }

}