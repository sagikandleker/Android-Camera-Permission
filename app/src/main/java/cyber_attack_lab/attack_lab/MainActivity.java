package cyber_attack_lab.attack_lab;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cyber_attack_lab.attack_lab.Main2Activity;

public class MainActivity extends AppCompatActivity {
    private int STORAGE_PERMISSION_CODE = 1;
    String name;
    EditText nameInput;
    TextView helloText;
    Button submitButton;
    Button cameraButton;
    Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)  {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, STORAGE_PERMISSION_CODE);
        }

        nameInput = (EditText)findViewById(R.id.nameInput);
        helloText = (TextView)findViewById(R.id.helloText);
        submitButton = (Button)findViewById(R.id.submitButton);
        cameraButton = (Button)findViewById(R.id.cameraButton);
        exitBtn = (Button)findViewById(R.id.exitBtn);

        submitButton.setEnabled(true);
        cameraButton.setEnabled(false);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();

                if (name.length() != 0) {
                    helloText.setText("Hello " + name + ", congratulation!");
                    submitButton.setEnabled(false);
                    cameraButton.setEnabled(true);
                    nameInput.setEnabled(false);
                    nameInput.getText().clear();
                    nameInput.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    nameInput.setHint("");
                } else {
                    name = nameInput.getText().toString();
                }
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2Activity();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }

    public void openMain2Activity() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        }
    }
}