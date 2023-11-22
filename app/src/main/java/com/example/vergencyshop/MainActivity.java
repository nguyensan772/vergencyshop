package com.example.vergencyshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.vergencyshop.Adapter.BannerAdapter;
import com.example.vergencyshop.fragment.DanhMucFragment;
import com.example.vergencyshop.fragment.DoiMatKhauFragment;
import com.example.vergencyshop.fragment.GioHangFragment;
import com.example.vergencyshop.fragment.HoaDonFragment;
import com.example.vergencyshop.fragment.LichSuMuaHangFragment;
import com.example.vergencyshop.fragment.ThongTinNguoiDungFragment;
import com.example.vergencyshop.fragment.TopSanPhamFragment;
import com.example.vergencyshop.fragment.TrangChuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {



    DrawerLayout drawerLayout ;
    Toolbar toolbar ;
    NavigationView navigationView ;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//ỳvuyjfujg
// cn1

        drawerLayout = findViewById(R.id.layout_all);
        toolbar = findViewById(R.id.jl_toolbar);
        navigationView = findViewById(R.id.main_navigation_view01);
        bottomNavigationView = findViewById(R.id.jl_btton_nav);


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang chủ");
        ActionBarDrawerToggle  toggle = new ActionBarDrawerToggle(MainActivity.this , drawerLayout , toolbar , R.string.open,R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        callFragment(new TrangChuFragment());


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_trangchu){

                    callFragment(new TrangChuFragment());
                    toolbar.setTitle("Trang chủ");



                }
                if (item.getItemId() == R.id.nav_danhmuc){
                    callFragment(new DanhMucFragment());
                    toolbar.setTitle("Danh mục");
                }

                if (item.getItemId() == R.id.nav_giohang){
                    callFragment(new GioHangFragment());
                    toolbar.setTitle("Giỏ hàng");
                }
                if (item.getItemId() == R.id.sub_Top){
                    callFragment(new TopSanPhamFragment());
                    toolbar.setTitle("Sản phẩm bán chạy");
                }
                if (item.getItemId() == R.id.nav_HoSo){
                    callFragment(new ThongTinNguoiDungFragment());
                    toolbar.setTitle("Thông tin khách hàng");
                }
                if (item.getItemId() == R.id.nav_HoaDon){
                    callFragment(new HoaDonFragment());
                    toolbar.setTitle("Hóa đơn");
                }
                if (item.getItemId() == R.id.nav_lichsumuahang){
                    callFragment(new LichSuMuaHangFragment());
                    toolbar.setTitle("Lịch sử mua hàng");
                }

                if (item.getItemId() == R.id.sub_Logout){

                }
                if (item.getItemId() == R.id.sub_Pass){
                    callFragment(new DoiMatKhauFragment());
                    toolbar.setTitle("Đổi mật khẩu");
                }

                return false;
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bt_giohang){
                    callFragment(new GioHangFragment());
                    toolbar.setTitle("Giỏ hàng");
                }

                if (item.getItemId() == R.id.bt_topsp){
                    callFragment(new TopSanPhamFragment());
                    toolbar.setTitle("Sản phẩm bán chạy");
                }
                if (item.getItemId() == R.id.bt_nguoidung){
                    callFragment(new ThongTinNguoiDungFragment());
                    toolbar.setTitle("Thông tin khách hàng");
                }
                return false;
            }
        });

    }

    private void callFragment (Fragment fragment){
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.jl_fragment,fragment).commit();
        drawerLayout.close();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isOpen()){

            drawerLayout.close();

        }else {
            super.onBackPressed();
        }
    }
}
