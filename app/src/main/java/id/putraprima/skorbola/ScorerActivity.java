package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {

    private Integer home_skor;
    private Integer away_skor;
    private EditText sc_name1;
    private Button button;

    String[] hname = new String[30];
    String[] aname = new String[30];

    private String key;

    private static final String SCORER_HOME = "scorer_home";
    private static final String SCORER_AWAY = "scorer_away";
    private static final String SKOR_HOME = "skor_home";
    private static final String SKOR_AWAY = "skor_away";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        sc_name1 = findViewById(R.id.sc_name);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = sc_name1.getText().toString();
                Bundle bundle = new Bundle();
                if (!(name).equals("")){
                    Intent intent = new Intent();
                    intent.putExtra("nama", name);
                    setResult(1, intent);
                    finish();
                }
            }
        });
    }
}
