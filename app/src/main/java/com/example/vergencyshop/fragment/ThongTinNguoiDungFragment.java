package com.example.vergencyshop.fragment;




import static com.example.vergencyshop.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.app.ProgressDialog;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.vergencyshop.MainActivity;
import com.example.vergencyshop.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongTinNguoiDungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongTinNguoiDungFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThongTinNguoiDungFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThongTinNguoiDungFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThongTinNguoiDungFragment newInstance(String param1, String param2) {
        ThongTinNguoiDungFragment fragment = new ThongTinNguoiDungFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    View view ;
    EditText edtTen,edtNgaySinh,edtSoDienThoai,edtDiaChi;
    RadioButton rdNam ,rdNu ;
    Button btnLuu ;
    ImageView imgAvt ;
    ProgressDialog progressDialog ;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference reference = database.getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    private  Uri uri ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_tin_nguoi_dung, container, false);
        anhXa();
        setThongTin();
        String anh = reference.child("NguoiDung").child(user.getUid()).child("anh").toString();

        btnLuu.setOnClickListener(v -> {

           capNhatThongTin();

        });



        // Inflate the layout for this fragment
        return view;
    }




    private void anhXa (){
      //  progressDialog = new ProgressDialog(getContext());
        edtTen = view.findViewById(R.id.edtTenKH);

        edtNgaySinh = view.findViewById(R.id.edtNgaySinhKH);
        edtSoDienThoai = view.findViewById(R.id.edtSDTKH);
        edtDiaChi = view.findViewById(R.id.edtDiaChiKH);
        rdNam = view.findViewById(R.id.rdNam);
        rdNu = view.findViewById(R.id.rdNu);
        btnLuu = view.findViewById(R.id.btnSuaHoSo);
        imgAvt = view.findViewById(R.id.imgAVTedit);
    }

    public  void  setThongTin(){

        reference.child("NguoiDung").child(user.getUid()).child("ten").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if ( snapshot.getValue()== null){
                   edtTen.setText(null);

               }else {
                   edtTen.setText(snapshot.getValue().toString());
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Giới tính
        reference.child("NguoiDung").child(user.getUid()).child("gioiTinh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.getValue() == null){
                    rdNam.setChecked(false);
                    rdNu.setChecked(false);

                }else {
                        if (snapshot.getValue().toString().equals("nam")){
                            rdNam.setChecked(true);
                        }
                        if (snapshot.getValue().toString().equals("nữ")){
                            rdNu.setChecked(true);
                        }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        //Ngày sinh
        reference.child("NguoiDung").child(user.getUid()).child("ngaySinh").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.getValue()== null){
                    edtNgaySinh.setText(null);

                }else {
                    edtNgaySinh.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Số điện thoại
        reference.child("NguoiDung").child(user.getUid()).child("soDienThoai").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.getValue()== null){
                    edtSoDienThoai.setText(null);

                }else {
                    edtSoDienThoai.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Địa chỉ

        reference.child("NguoiDung").child(user.getUid()).child("diaChi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.getValue()== null){
                    edtDiaChi.setText(null);

                }else {
                    edtDiaChi.setText(snapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



    private  void capNhatThongTin(){

        if (edtTen.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            reference.child("NguoiDung").child(user.getUid()).child("ten").setValue(edtTen.getText().toString());
        }
    //Giới tính

        if (rdNu.isChecked()){

            reference.child("NguoiDung").child(user.getUid()).child("gioiTinh").setValue("nữ");
        }else if (rdNam.isChecked()){
            reference.child("NguoiDung").child(user.getUid()).child("gioiTinh").setValue("nam");
        }else {
            reference.child("NguoiDung").child(user.getUid()).child("gioiTinh").setValue(null);

        }

        //Ngày sinh

        if (edtNgaySinh.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            reference.child("NguoiDung").child(user.getUid()).child("ngaySinh").setValue(edtNgaySinh.getText().toString());
        }


        //Số điện thoại

        Pattern pattern = Pattern.compile("^0[0-9]{9}$");
        Matcher matcher = pattern.matcher(edtSoDienThoai.getText().toString().trim());

        if (edtSoDienThoai.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else if (!matcher.matches()){
            Toast.makeText(getContext(), "Số sai", Toast.LENGTH_SHORT).show();
        }
        else {
            reference.child("NguoiDung").child(user.getUid()).child("soDienThoai").setValue(edtSoDienThoai.getText().toString());
        }

        //Địa chỉ
        if (edtDiaChi.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        }else {
            reference.child("NguoiDung").child(user.getUid()).child("diaChi").setValue(edtDiaChi.getText().toString());
        }


    }









}