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
    int count;
    private String currentOperation = "";
    private boolean isOperationClicked = false;

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
            result.setText(buttonText);
            isOperationClicked = false;
        } else {
           result.append(buttonText);
        }
    }

    public void operationFunction(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        if (buttonText.equals("-") && (result.getText().toString().isEmpty() || isOperationClicked)) {
            result.setText("-");
            isOperationClicked = false;
            return;
        }


        currentOperation = buttonText;

        try {
            num1 = Integer.parseInt(result.getText().toString());
        } catch (NumberFormatException e) {
            result.setText("Error");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clearFunction(view);
                }
            }, 2000);
            return;
        }

        isOperationClicked = true;
    }


    public void calculateFunction(View view) {
        if (result.getText().toString().isEmpty()) {
            result.setText("Error: No input");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clearFunction(view);
                }
            }, 2000);
            return;
        }

        try {
            num2 = Integer.parseInt(result.getText().toString());
        }  catch (NumberFormatException e) {
            result.setText("Error");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clearFunction(view);
                }
            }, 2000);
            return;
        }
        if (isOperationClicked) {
            result.setText("Error: Incomplete operation");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clearFunction(view);
                }
            }, 2000);
            return;
        }
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
            default:
                result.setText("Error: Invalid operation");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        clearFunction(view);
                    }
                }, 2000);
                return;



        }

        result.setText(String.valueOf(resultValue));
        currentOperation = "";
        isOperationClicked = false;
    }
    public void clearFunction(View view){
        result.setText("");

    }
}
