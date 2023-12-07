package com.example.quang.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.quang.Adapter.ThongTinAdapter;
import com.example.quang.R;
import com.example.quang.dbHelper.DBHelper;
import com.example.quang.model.ThongTin;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
 //   DatabaseHelper db;
    Button btnThem;
    ListView listViewThem;
    ArrayList<ThongTin> listTT;
    ThongTinAdapter thongTinAdapter;
    static final int ADD_THONG_TIN = 1;
    //1
    DBHelper dbHelper;
    ThongTin thongTinTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2
        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_home);
        listViewThem=findViewById(R.id.lvDanhSach);
        listTT = new ArrayList<>();
        listTT.addAll(dbHelper.getAllThongTin());
        thongTinAdapter = new ThongTinAdapter(this,listTT);
        listViewThem.setAdapter(thongTinAdapter);
        registerForContextMenu(listViewThem);


        listViewThem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this,ChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thongtin", listTT.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listViewThem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                thongTinTemp = listTT.get(i);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_THONG_TIN && resultCode == RESULT_OK) {
            if (data != null) {
                ThongTin newTT = (ThongTin) data.getSerializableExtra("newTT");
                int sizeTemp = listTT.size();
                listTT.add(newTT);
                if (listTT.size() > sizeTemp){
                    Toast.makeText(getApplicationContext(), "Thêm thành công thông tin "+newTT.getHoVaTen()+" vào danh sách. ", Toast.LENGTH_SHORT).show();
                }
                thongTinAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_sua:
                Intent intent = new Intent(HomeActivity.this,ThemThongTinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thongTinSua", thongTinTemp);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.mnu_xoa:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bạn có muốn xóa ");
                builder.setMessage("ID = " + thongTinTemp.getId() + "?");
                builder.setPositiveButton("Vâng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dbHelper.deleteThongTin(thongTinTemp.getId()) > 0) {
                            Toast.makeText(getApplication().getApplicationContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                            init();
                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
    private void init() {
        listTT.clear();
        listTT.addAll(dbHelper.getAllThongTin());
        thongTinAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_them:
                startActivity(new Intent(HomeActivity.this, ThemThongTinActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}