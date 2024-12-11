package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private StringBuilder currentInput;
    private double firstOperand;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Inicializa los elementos de la interfaz
        display = findViewById(R.id.display);
        currentInput = new StringBuilder();

        setupButtons();
    }

    private void setupButtons() {
        // Inicializa los botones numéricos
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(this::handleNumberClick);
        }

        // Inicializa los botones de operación
        findViewById(R.id.buttonAdd).setOnClickListener(this::handleOperatorClick);
        findViewById(R.id.buttonSubtract).setOnClickListener(this::handleOperatorClick);
        findViewById(R.id.buttonMultiply).setOnClickListener(this::handleOperatorClick);
        findViewById(R.id.buttonDivide).setOnClickListener(this::handleOperatorClick);

        // Inicializa los botones especiales
        findViewById(R.id.buttonEquals).setOnClickListener(this::handleEqualsClick);
        findViewById(R.id.buttonClear).setOnClickListener(this::handleClearClick);
    }

    private void handleNumberClick(View view) {
        Button button = (Button) view;
        currentInput.append(button.getText().toString());
        display.setText(currentInput.toString());
    }

    private void handleOperatorClick(View view) {
        if (currentInput.length() > 0) {
            firstOperand = Double.parseDouble(currentInput.toString());
            currentInput.setLength(0); // Limpia la entrada actual
            Button button = (Button) view;
            operator = button.getText().toString(); // Guarda el operador seleccionado
        }
    }

    private void handleEqualsClick(View view) {
        if (currentInput.length() > 0 && operator != null) {
            double secondOperand = Double.parseDouble(currentInput.toString());
            double result = 0;

            // Realiza la operación seleccionada
            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result)); // Muestra el resultado
            currentInput.setLength(0); // Limpia la entrada para nuevos cálculos
            operator = null; // Reinicia el operador
        }
    }

    private void handleClearClick(View view) {
        currentInput.setLength(0); // Limpia la entrada actual
        firstOperand = 0; // Reinicia el primer operando
        operator = null; // Limpia el operador
        display.setText(""); // Limpia el display
    }
}