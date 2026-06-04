package kr.ac.kopo.preferencevoting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("아이돌 선호도 투표");

        // 투표수를 저장할 길이 9개 배열객체생성하고 0으로 초기화
        final int voteCount[] = new int[9];
        for (int i = 0; i < voteCount.length; i++) {
            voteCount[i] = 0;
        }

        // ImageView객체의 참조 저장할 길이 9개 배열객체생성
        ImageView[] imgvArr = new ImageView[9];
        int[] imgvIdArr = {R.id.imgv1, R.id.imgv2, R.id.imgv3, R.id.imgv4, R.id.imgv5, R.id.imgv6, R.id.imgv7, R.id.imgv8, R.id.imgv9};
        String[] bossNameArr = {"가디언엔젤릭슬라임", "검은마법사", "데미안", "루시드", "스우", "자쿰", "파풀라투스", "핑크빈", "혼테일"};

        for (int i = 0; i < imgvArr.length; i++) {
            final int index;
            index = i;
            imgvArr[index] = findViewById(imgvIdArr[index]);
            imgvArr[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), bossNameArr[index] + " 총 " + voteCount[index] + "표", Toast.LENGTH_SHORT).show();
                }
            });
        }
        // ResultActivity를 시작하는 intent 객체를 생성하고 값을 넣는다.
        Button btnFinish = findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ResultActivity를 시작하는 Intent객체를 생성하고 값을 넣는다.
                Intent intent = new Intent(getApplicationContext(), ResultActivty.class);
                intent.putExtra("voteCount", voteCount);
                intent.putExtra("bossNameArr", bossNameArr);
                startActivity(intent);
            }
        });
    }
}