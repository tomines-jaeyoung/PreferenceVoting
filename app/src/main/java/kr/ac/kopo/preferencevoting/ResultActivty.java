package kr.ac.kopo.preferencevoting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_activty);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        int[] voteCount = intent.getIntArrayExtra("voteCount");
        String[] bossNameArr = intent.getStringArrayExtra("bossNameArr");

        TextView[] textArr = new TextView[bossNameArr.length];
        RatingBar[] ratingArr = new RatingBar[bossNameArr.length];

        int[] textIdArr = {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9};
        int[] ratingIdArr = {R.id.rating1, R.id.rating2, R.id.rating3, R.id.rating4, R.id.rating5, R.id.rating6, R.id.rating7, R.id.rating8, R.id.rating9};

        // 이미지 파일 아이디를 저장할 배열
        Integer[] imageFileId = {
                R.drawable.guardian_slime,R.drawable.black_mage, R.drawable.demian,  R.drawable.lucid, R.drawable.suu,
                R.drawable.zakum, R.drawable.papulatus, R.drawable.pinkbean, R.drawable.horntail,


        };

        for (int i = 0; i < textArr.length ; i++) {
            textArr[i] = findViewById(textIdArr[i]);
            ratingArr[i] = findViewById(ratingIdArr[i]);
        }

        for (int i = 0; i < textArr.length; i++) {
            textArr[i].setText(bossNameArr[i]);
            ratingArr[i].setRating(voteCount[i]);
        }

        // 가장 많은 표를 받은 항목의 인덱스 찾기
        int maxIndex = 0;
        for (int i = 1; i < voteCount.length; i++) {
            if (voteCount[i] > voteCount[maxIndex]) {
                maxIndex = i;
            }
        }

        TextView textTitle = findViewById(R.id.textTitle);
        ImageView imageResult = findViewById(R.id.imageResult);

        textTitle.setText(bossNameArr[maxIndex]);
        imageResult.setImageResource(imageFileId[maxIndex]);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}