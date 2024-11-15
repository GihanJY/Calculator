package com.example.firstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button_one;
    Button button_two;
    Button button_three;
    Button button_four;
    Button button_five;
    Button button_six;
    Button button_seven;
    Button button_eight;
    Button button_nine;
    Button button_zero;
    Button period_button;
    Button plus_button;
    Button minus_button;
    Button multiply_button;
    Button divide_button;
    Button plus_minus_button;
    Button equal_button;
    Button clear_button;
    Button delete_button;

    TextView result_text;
    TextView operation_text;

    StringBuilder num = new StringBuilder();
    StringBuilder operation = new StringBuilder();
    double operand = 0;
    double result = 0;
    char operator = ' ';
    boolean is_first = true;

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

        result_text = findViewById(R.id.display_result_text);
        operation_text = findViewById(R.id.display_calculation_text);

        button_one = findViewById(R.id.button_one);
        button_two = findViewById(R.id.button_two);
        button_three = findViewById(R.id.button_three);
        button_four = findViewById(R.id.button_four);
        button_five = findViewById(R.id.button_five);
        button_six = findViewById(R.id.button_six);
        button_seven = findViewById(R.id.button_seven);
        button_eight = findViewById(R.id.button_eight);
        button_nine = findViewById(R.id.button_nine);
        button_zero = findViewById(R.id.button_zero);

        clear_button = findViewById(R.id.clear_button);
        delete_button = findViewById(R.id.delete_button);
        period_button = findViewById(R.id.period_button);
        plus_minus_button = findViewById(R.id.plus_minus_button);

        plus_button = findViewById(R.id.plus_button);
        minus_button = findViewById(R.id.minus_button);
        multiply_button = findViewById(R.id.multiply_button);
        divide_button = findViewById(R.id.divide_button);
        equal_button = findViewById(R.id.equal_button);

        List<Button> number_buttons = Arrays.asList(
                button_one, button_two, button_three,
                button_four, button_five, button_six,
                button_seven, button_eight, button_nine,
                button_zero
        );

        numberInput(number_buttons);
        operatorInput();
        deleteInput();
        clearInput();
    }

    private void numberInput(List<Button> number_buttons) {
        for (Button button : number_buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int input_number = getNumber(button);
                    num.append(input_number);

                    System.out.println(num);
                    operand = Double.parseDouble(String.valueOf(num));


                    operation_text.setText("");
                    operation.append(num);
                    operation_text.setText(String.valueOf(operation));
                }
            });
        }
    }

    private void operatorInput() {
        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator('+');
            }
        });

        minus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator('-');
            }
        });

        multiply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator('*');
            }
        });

        divide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator('/');
            }
        });

        equal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
                result_text.setText(String.valueOf(result));
            }
        });
    }

    private void clearInput() {
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.setLength(0);
                result = 0;
                operand = 0;
                operator = ' ';
                is_first = true;

                result_text.setText("");
                operation_text.setText("");
            }
        });
    }

    private void deleteInput() {
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num.length() > 0) {
                    num.deleteCharAt(num.length() - 1);

                    result_text.setText(num.toString());
                }
            }
        });
    }

    private int getNumber(Button btn){
        int number = 0;
        if (btn.equals(button_zero)) {
            number = 0;
        } else if (btn.equals(button_one)) {
            number = 1;
        } else if (btn.equals(button_two)) {
            number = 2;
        } else if (btn.equals(button_three)) {
            number = 3;
        } else if (btn.equals(button_four)) {
            number = 4;
        } else if (btn.equals(button_five)) {
            number = 5;
        } else if (btn.equals(button_six)) {
            number = 6;
        } else if (btn.equals(button_seven)) {
            number = 7;
        } else if (btn.equals(button_eight)) {
            number = 8;
        } else if (btn.equals(button_nine)) {
            number = 9;
        }

        return number;
    }

    private void handleOperator(char op) {
        operator = op;
        if (is_first) {
            result = operand;
        }
        is_first = false;
        num.setLength(0);

        operation.append(String.valueOf(operation));
    }

    private void calculateResult() {
        System.out.println(result + " " + operator + " " + operand + " = ");
        switch (operator) {
            case '+':
                result = result + operand;
                break;
            case '-':
                result = result - operand;
                break;
            case '*':
                result = result * operand;
                break;
            case '/':
                result = result / operand;
                break;
        }
        System.out.println(operator);
        num.setLength(0);
    }
}