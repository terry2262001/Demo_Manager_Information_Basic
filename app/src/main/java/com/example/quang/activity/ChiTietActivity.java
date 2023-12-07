package com.example.quang.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.quang.R;
import com.example.quang.model.ThongTin;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChiTietActivity extends AppCompatActivity {
    TextView tvTen,tvDinhDanh,tvNgaySinh,tvThem,tvGmail;
    CircleImageView imgAvt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        tvTen = findViewById(R.id.tvHoTen);
        tvDinhDanh = findViewById(R.id.tvDinhDanh);
        tvNgaySinh = findViewById(R.id.tvNgaySinh);
        tvThem = findViewById(R.id.tvThongTinThem);
        tvGmail = findViewById(R.id.tvGmail);
        imgAvt = findViewById(R.id.imgAvt);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ThongTin thongTin = (ThongTin) bundle.getSerializable("thongtin");
            tvTen.setText(thongTin.getHoVaTen());
            tvDinhDanh.setText(thongTin.getTenDinhDanh());
            tvNgaySinh.setText(thongTin.getSinhNhat());
            tvThem.setText(thongTin.getThongTinThem());
            tvGmail.setText(thongTin.getGmail());
            byte[] image = thongTin.getAvt();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgAvt.setImageBitmap(bitmap);
        }
    }
}