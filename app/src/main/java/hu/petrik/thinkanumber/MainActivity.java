package hu.petrik.thinkanumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnPlussz;
    private Button btnMinusz;
    private Button btnTipp;
    private ImageView hp4;
    private ImageView hp3;
    private ImageView hp2;
    private ImageView hp1;
    private ImageView[] eletek;
    private TextView tippErtek;
    private int tipp;
    private Random rnd;
    private int gondolt;
    private int elet;
    private AlertDialog.Builder jatekVegeDialogBuilder;
    private Button btnkonnyu;
    private  Button btnNehez;
    private AlertDialog.Builder nehezsegDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListeners();
    }

    private void addListeners() {
        btnPlussz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipp < 10) {
                    tipp++;
                    tippErtek.setText(String.valueOf(tipp));
                } else {
                    Toast.makeText(MainActivity.this, "A gondolt szám nem lehet nagyobb mint 10!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMinusz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipp > 1) {
                    tipp--;
                    tippErtek.setText(String.valueOf(tipp));
                } else {
                    Toast.makeText(MainActivity.this, "A gondolt szám nem lehet kisebb mint 1!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipp < gondolt) {
                    // eletCsokkentSwitchCase();
                    eletCsokkent();
                    Toast.makeText(MainActivity.this, "A gondolt szám nagyobb", Toast.LENGTH_SHORT).show();
                } else if (tipp > gondolt) {
                    // eletCsokkentSwitchCase();
                    eletCsokkent();
                    Toast.makeText(MainActivity.this, "A gondolt szám kisebb", Toast.LENGTH_SHORT).show();
                } else {
                    jatekVegeDialogBuilder.setTitle("Győzelem");
                    jatekVegeDialogBuilder.create().show();
                }
            }
        });
    }

    private void eletCsokkent() {
        if (elet > 0) {
            elet--;
        }
        eletek[elet].setImageResource(R.drawable.heart1);
        if (elet == 0) {
            jatekVegeDialogBuilder.setTitle("Vereség");
            jatekVegeDialogBuilder.create().show();
        }
    }


    private void eletCsokkentSwitchCase() {
        elet--;
        switch (elet) {
            case 3:
                hp4.setImageResource(R.drawable.heart1);
                break;
            case 2:
                hp3.setImageResource(R.drawable.heart1);
                break;
            case 1:
                hp2.setImageResource(R.drawable.heart1);
                break;
            case 0:
                hp1.setImageResource(R.drawable.heart1);
                break;
        }
    }


    private void init() {
        btnkonnyu = findViewById(R.id.btnKonnyu);
        btnNehez = findViewById(R.id.btnNehez);
        btnPlussz = findViewById(R.id.btnPlussz);
        btnMinusz = findViewById(R.id.btnminusz);
        btnTipp = findViewById(R.id.btnTipp);
        hp1 = findViewById(R.id.hp1);
        hp2 = findViewById(R.id.hp2);
        hp3 = findViewById(R.id.hp3);
        hp4 = findViewById(R.id.hp4);
        tippErtek = findViewById(R.id.tippErteke);
        rnd = new Random();
        gondolt = rnd.nextInt(10) + 1;
        tipp = 1;
        elet = 4;
        eletek = new ImageView[]{hp1, hp2, hp3, hp4};
        Log.d("gondolt", String.valueOf(gondolt));
        jatekVegeDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        jatekVegeDialogBuilder.setMessage("Szeretne új játékot játszani?");
        jatekVegeDialogBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        jatekVegeDialogBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
            }
        });
        jatekVegeDialogBuilder.setCancelable(false);
        ujJatek();
    }

    private void ujJatek() {
        gondolt = rnd.nextInt(10) + 1;
        tipp = 1;
        elet = 4;
        eletek = new ImageView[]{hp1, hp2, hp3, hp4};
        Log.d("gondolt", String.valueOf(gondolt));
        tippErtek.setText(String.valueOf(tipp));
        for(ImageView iv : eletek){
            iv.setImageResource(R.drawable.heart2);
        }
    }
}