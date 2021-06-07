package ac.id.atmaluhur.mhs.utsmobileprogramming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int jumlah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPesan = findViewById(R.id.btnPesan);
        ImageButton btnReset = findViewById((R.id.btnReset));
        EditText nama = findViewById(R.id.txtNama);
        TextView jumlahtxt = findViewById(R.id.txtJumlah);
        CheckBox Cheese = findViewById(R.id.cbCheese);
        CheckBox Meat = findViewById(R.id.cbMeat);
        RadioGroup rdBayar = findViewById(R.id.rdBayar);


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rdBayar.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(id);

                if(radioButton == null){
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), DetailPesanan.class);
                Bundle b = new Bundle();

                boolean stateCheese=Cheese.isChecked();//mengidentifikasi check
                boolean stateMeat=Meat.isChecked();//mengidentifikasi check

                String pesan="";

                if(stateCheese){
                    pesan="Cheese";
                }
                if(stateMeat){
                    pesan="Meat";
                }
                if(stateCheese && stateMeat){
                    pesan="Cheese and Meat";
                }

                int totalHarga = hitungHarga(stateCheese,stateMeat);//memanggil method jumlah harga

                b.putString("nama", nama.getText().toString());
                b.putString("jumlah", jumlahtxt.getText().toString());
                b.putString("pembayaran", radioButton.getText().toString());
                b.putString("harga", Integer.toString(totalHarga));
                b.putString("topping", pesan);
                intent.putExtras(b);
               // Perintah Intent Explicit pindah halaman ke activity_detail
               startActivity(intent);
            }
    });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama.setText("");
                jumlahtxt.setText("0");
                jumlah = 0;
                rdBayar.clearCheck();
                Cheese.setChecked(false);
                Meat.setChecked(false);
            }
        });
    }


    public void tambah(View view){
        if(jumlah==50){
            Toast.makeText(this,"Max 50 Box",Toast.LENGTH_SHORT).show();
            return;
        }
        jumlah = jumlah + 1 ;
        display(jumlah);
    }

    public void kurang(View view){
        if (jumlah==1){
            Toast.makeText(this,"Min 1 Box",Toast.LENGTH_SHORT).show();
            return;
        }
        jumlah = jumlah - 1;
        display(jumlah);
    }

    public void display(int number) {
        TextView jumlahtxt = (TextView) findViewById(R.id.txtJumlah);
        jumlahtxt.setText("" + number);
    }


    private int hitungHarga(boolean stateCheese,boolean stateMeat){//jumlah pesanan * harga
        int harga = 50000;

        if(stateMeat && stateCheese) {
            harga = harga + 30000;
        }
        else {
            if(stateCheese){
                harga = harga + 10000;//harga tambahan toping
            }

            if(stateMeat){
                harga = harga +20000;
            }
        }
        return jumlah * harga;
    }
}