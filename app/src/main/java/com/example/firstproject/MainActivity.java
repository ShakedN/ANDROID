package com.example.firstproject;

import static kotlinx.coroutines.DelayKt.delay;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView result;
    int num1,num2;
    private String currentOperation = ""; // שמירת הפעולה הנבחרת
    private boolean isOperationClicked = false; // עוקב אם נבחרה פעולה

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

        result = findViewById(R.id.textView);
        result.setText("");
    }




    public void numFunction(View view) {

        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if (isOperationClicked) {
            // התחלת מספר חדש לאחר פעולה
            result.setText(buttonText);
            isOperationClicked = false;
        } else {
            // הוספת מספר לתצוגה
            // comment
            result.append(buttonText);
        }
    }

    public void operationFunction(View view) {
        Button button = (Button) view;
        currentOperation = button.getText().toString();
        num1 = Integer.parseInt(result.getText().toString());
        isOperationClicked = true; // מאפשר להתחיל מספר חדש
    }
    public void calculateFunction(View view) {
        num2 = Integer.parseInt(result.getText().toString());
        int resultValue = 0;

        switch (currentOperation) {
            case "+":
                resultValue = num1 + num2;
                break;
            case "-":
                resultValue = num1 - num2;
                break;
            case "X":
                resultValue = num1 * num2;
                break;
            case ":":
                if (num2 != 0) {
                    resultValue = num1 / num2;
                } else {


                        result.setText("Error");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clearFunction(view);
                        }
                    }, 2000);

                    return;
                }
                break;



        }

        result.setText(String.valueOf(resultValue));
        currentOperation = ""; // איפוס הפעולה
        isOperationClicked = false;
    }
    public void clearFunction(View view){
        result.setText("");

    }
}
