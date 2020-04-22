package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {

    private TextView text_home;
    private TextView text_away;
    private ImageView logo_home;
    private ImageView logo_away;
    private Integer home_skor;
    private Integer away_skor;
    private TextView skor_home;
    private TextView skor_away;

    private TextView sc_home;

    private static final String RESULT_KEY = "result";
    private static final String TAG = MatchActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        text_home = findViewById(R.id.txt_home);
        text_away = findViewById(R.id.txt_away);
        logo_home = findViewById(R.id.home_logo);
        logo_away = findViewById(R.id.away_logo);
        skor_home = findViewById(R.id.score_home);
        skor_away = findViewById(R.id.score_away);
        sc_home = findViewById(R.id.sc_home);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            //1.Menampilkan detail match sesuai data dari main activity
            Bundle bundle = getIntent().getExtras();
            Bitmap home = bundle.getParcelable("home_logo");
            Bitmap away = bundle.getParcelable("away_logo");

            logo_home.setImageBitmap(home);
            logo_away.setImageBitmap(away);

            text_home.setText(extra.getString("home_team"));
            text_away.setText(extra.getString("away_team"));
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            home_skor++;
            skor_home.setText(String.valueOf(home_skor));
            String nama = data.getStringExtra("nama");
            sc_home.setText(nama);
        }
    }

    public void handleAddHome(View view) {
        Intent intent = new Intent(MatchActivity.this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }

    public void handleAddAway(View view) {
        away_skor++;
        skor_away.setText(String.valueOf(away_skor));
    }

    public void handleResult(View view) {
        String result;

        if (home_skor > away_skor){
            result = text_home.getText().toString();
        } else if (home_skor < away_skor){
            result = text_away.getText().toString();
        } else {
            result = "DRAW!";
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
