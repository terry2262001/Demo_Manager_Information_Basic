package com.example.quang.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quang.R;
import com.example.quang.model.ThongTin;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinAdapter extends  ArrayAdapter<ThongTin> {
    public ThongTinAdapter(@NonNull Context context, @NonNull List<ThongTin> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ThongTin thongTin = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.thongtin_item, parent, false);
        }
        TextView tvTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvDinhDanh = convertView.findViewById(R.id.tvDinhDanh);
        TextView tvNgaySinh = convertView.findViewById(R.id.tvNgaySinh);
        TextView tvThem = convertView.findViewById(R.id.tvThongTinThem);
        TextView tvGmail = convertView.findViewById(R.id.tvGmail);
        CircleImageView avt = convertView.findViewById(R.id.imgAvatar);

        tvTen.setText(thongTin.getHoVaTen());
        tvDinhDanh.setText(thongTin.getTenDinhDanh());
        tvNgaySinh.setText(thongTin.getSinhNhat());
        tvThem.setText(thongTin.getThongTinThem());
        tvGmail.setText(thongTin.getGmail());
        byte[] image = thongTin.getAvt();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        avt.setImageBitmap(bitmap);
        return convertView;
    }

    @Nullable
    @Override
    public ThongTin getItem(int position) {
        return super.getItem(position);
    }
}

