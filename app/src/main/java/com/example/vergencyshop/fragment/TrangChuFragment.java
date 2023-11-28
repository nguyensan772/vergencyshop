package com.example.vergencyshop.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vergencyshop.Adapter.BannerAdapter;
import com.example.vergencyshop.Adapter.SanPhamTrangChuAdapter;
import com.example.vergencyshop.R;
import com.example.vergencyshop.models.SanPham;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangChuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangChuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangChuFragment newInstance(String param1, String param2) {
        TrangChuFragment fragment = new TrangChuFragment();
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
    SanPhamTrangChuAdapter sanPhamTrangChuAdapter;
    private ViewPager viewPager;
    private Handler handler;
    private Runnable runnable;
    private int delayTime = 3000; // Thời gian chuyển đổi ảnh (3 giây)
    private int currentPage = 0;
    private int[] imageIds = {R.drawable.slide_index_1, R.drawable.slide_index_2}; // Danh sách ID ảnh
    private RecyclerView rcSanPham;
    ArrayList<SanPham> list = new ArrayList<>();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        anhXa();
        viewPager.setAdapter(new BannerAdapter(getContext(), imageIds));

        rcSanPham.setLayoutManager(new GridLayoutManager(getActivity(),2));

        sanPhamTrangChuAdapter = new SanPhamTrangChuAdapter(getActivity(),list);

        reference.child("SanPham").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham =  snapshot.getValue(SanPham.class);

                list.add(sanPham);

                sanPhamTrangChuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                sanPhamTrangChuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                SanPham sanPham =  snapshot.getValue(SanPham.class);

                sanPhamTrangChuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                sanPhamTrangChuAdapter.notifyDataSetChanged();
            }
        });

        rcSanPham.setAdapter(sanPhamTrangChuAdapter);
        // Inflate the layout for this fragment
        return view;
    }


    private void startAutoSlide() {
//        if (handler != null) {
//            handler.removeCallbacks(runnable);
//        }
    handler = new Handler();
        runnable = new Runnable() {
           public void run() {
               currentPage = viewPager.getCurrentItem();
               currentPage = (currentPage + 1) % imageIds.length;
               viewPager.setCurrentItem(currentPage, true);
               handler.postDelayed(this, delayTime);
            }
       };
      handler.postDelayed(runnable, delayTime);
    }
    @Override
    public void onResume() {
        super.onResume();
       startAutoSlide();

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void anhXa (){
        rcSanPham = view.findViewById(R.id.rcdanhSachSanPhamTC);
        viewPager = view.findViewById(R.id.viewPager);
    }

    
}