package ac.id.atmaluhur.mhs.utsmobileprogramming;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPesanan extends AppCompatActivity{
    private Button btnKembali;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pesanan);

        Bundle b = getIntent().getExtras();

        //nilai
        TextView nama = findViewById(R.id.tvNama);
        TextView jumlahOrder = findViewById(R.id.tvJumlah);
        TextView totalHarga = findViewById(R.id.tvTotalHarga);
        TextView method = findViewById(R.id.tvMethod);
        TextView topping = findViewById(R.id.tvTopping);

        //setNilai
        nama.setText("Dear, " + b.getCharSequence("nama"));
        jumlahOrder.setText(b.getCharSequence("jumlah") + " Box");
        totalHarga.setText("Total Harga : Rp " + b.getCharSequence("harga"));
        method.setText("Metode Bayar : " + b.getCharSequence("pembayaran"));
        topping.setText(b.getCharSequence("topping") + " Pizza");

        btnKembali = findViewById(R.id.btnKembali);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(kembali);
                finish();
            }
        });
    }





}