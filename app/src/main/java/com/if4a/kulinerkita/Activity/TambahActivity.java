package com.if4a.kulinerkita.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.if4a.kulinerkita.API.APIRequestData;
import com.if4a.kulinerkita.API.RetroServer;
import com.if4a.kulinerkita.Model.ModelResponse;
import com.if4a.kulinerkita.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {
    private EditText etNama, etAsal, etDeskripsisingkat;
    private Button btntambah;
    private String nama,asal, deskripsiSingkat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_nama);
        etAsal = findViewById(R.id.et_asal);
        etDeskripsisingkat = findViewById(R.id.et_deskripsi_singkat);
        btntambah = findViewById(R.id.btn_tambah);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                asal = etAsal.getText().toString();
                deskripsiSingkat = etDeskripsisingkat.getText().toString();

                if(nama.trim().equals("")){
                    etNama.setError("nama harus diisi!");
                }
                if(asal.trim().equals("")){
                    etNama.setError("asal harus diisi!");
                }
                if(deskripsiSingkat.trim().equals("")){
                    etNama.setError("deskripsi singkat harus diisi!");
                }
                else{
                    tambahKuliner();
                }
            }
        });
    }
    private void tambahKuliner(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(nama, asal, deskripsiSingkat);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : "+ kode + ", Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}