package com.example.quang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quang.R;
import com.example.quang.dbHelper.DBHelper;
import com.example.quang.model.ThongTin;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThemThongTinActivity extends AppCompatActivity {
    private static final int IMAGE_REQUEST = 1;
    EditText etTen,etTDD,etNgaySinh,etTTT,etGmail;
    Button btAdd,btSua;
    DBHelper dbHelper;
    CircleImageView imgAvatar;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_them_thong_tin);
        etTen = findViewById(R.id.etTen);
        etTDD = findViewById(R.id.etTDD);
        etNgaySinh = findViewById(R.id.etNgaySinh);
        etTTT = findViewById(R.id.etTTT);
        etGmail = findViewById(R.id.etGmail);
        btAdd = findViewById(R.id.btAdd);
        btSua = findViewById(R.id.btSua);
        imgAvatar = findViewById(R.id.imgAvatar);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etTen.getText().toString().trim().isEmpty() && !etTDD.getText().toString().trim().isEmpty() && !etNgaySinh.getText().toString().trim().isEmpty() && !etTTT.getText().toString().trim().isEmpty()&& !etGmail.getText().toString().trim().isEmpty()){
                    String ten = etTen.getText().toString();
                    String tenDinhDanh = etTDD.getText().toString();
                    String sinhNhat = etNgaySinh.getText().toString();
                    String thongTinThem = etTTT.getText().toString();
                    String gmail = etGmail.getText().toString();
                    ThongTin thongTin = new ThongTin(0,ten,tenDinhDanh,sinhNhat, thongTinThem, gmail,ImagetoByte(imgAvatar));
                    Intent intent = getIntent();
                    intent.putExtra("newTT", thongTin);
                    setResult(RESULT_OK, intent);
                    dbHelper.insertThongTin(thongTin);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Không được để trống các trường !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgAvatar.setOnClickListener(view -> {
            openImage();
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ThongTin thongtin = (ThongTin) bundle.getSerializable("thongTinSua");
            etTen.setText(thongtin.getHoVaTen());
            etTDD.setText(thongtin.getTenDinhDanh());
            etNgaySinh.setText(thongtin.getSinhNhat());
            etTTT.setText(thongtin.getThongTinThem());
            etGmail.setText(thongtin.getGmail());
            btSua.setVisibility(View.VISIBLE);
            byte[] image = thongtin.getAvt();
            Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imgAvatar.setImageBitmap(bitmap);
            btSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String ten = etTen.getText().toString();
                    String tenDinhDanh = etTDD.getText().toString();
                    String ngaySinh = etNgaySinh.getText().toString();
                    String thongTinThem = etTTT.getText().toString();
                    String gmail = etGmail.getText().toString();
                    thongtin.setHoVaTen(ten);
                    thongtin.setTenDinhDanh(tenDinhDanh);
                    thongtin.setSinhNhat(ngaySinh);
                    thongtin.setThongTinThem(thongTinThem);
                    thongtin.setGmail(gmail);
                    thongtin.setAvt(ImagetoByte(imgAvatar));

                    if (dbHelper.updateThongTin(thongtin) > 0){
                        Toast.makeText(ThemThongTinActivity.this, "Cập nhật thành công ", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(ThemThongTinActivity.this, "Khong thể Cập Nhật", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else{
            btAdd.setVisibility(View.VISIBLE);
        }
    }
    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgAvatar.setImageURI(imageUri);
        }
    }
    private byte[] ImagetoByte(ImageView imgProduct) {
        Bitmap bitmap = ((BitmapDrawable)imgProduct.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
}