package com.example.vergencyshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vergencyshop.Adapter.HoaDonChiTietAdapter;
import com.example.vergencyshop.Adapter.SanPhamTrangChuAdapter;
import com.example.vergencyshop.R;
import com.example.vergencyshop.models.HoaDon;
import com.example.vergencyshop.models.HoaDonChiTiet;
import com.example.vergencyshop.models.SanPham;
import com.example.vergencyshop.models.TopSanPham;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopSanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopSanPhamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TopSanPhamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChiTietSanPhamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopSanPhamFragment newInstance(String param1, String param2) {
        TopSanPhamFragment fragment = new TopSanPhamFragment();
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



    View view;
    RecyclerView rcTopSanPham ;
    ArrayList<HoaDonChiTiet> list = new ArrayList<>();
    HoaDonChiTietAdapter sanPhamTrangChuAdapter ;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    ArrayList<TopSanPham> listTongTien = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view  = inflater.inflate(R.layout.frag_thanhtoan, container, false);
        anhXa ();
        setTop();
        rcTopSanPham.setLayoutManager(new LinearLayoutManager(getActivity()));
        sanPhamTrangChuAdapter = new HoaDonChiTietAdapter( list,getActivity());

        rcTopSanPham.setAdapter(sanPhamTrangChuAdapter);



        // Inflate the layout for this fragment
        return  view;
    }

    private void anhXa() {
        rcTopSanPham  = view.findViewById(R.id.rcTopSanPham);
    }

    private void setTop (){
      reference.child("SanPham").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              if (snapshot.exists()){
                  ArrayList<SanPham> sanPhams = new ArrayList<>();
                  for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                      SanPham sanPham = dataSnapshot.getValue(SanPham.class);
                      sanPhams.add(sanPham);

                  }
                  Toast.makeText(getActivity(), ""+sanPhams.size(), Toast.LENGTH_SHORT).show();

              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });
    }


}