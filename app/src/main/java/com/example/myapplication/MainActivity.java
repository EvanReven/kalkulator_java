package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView  solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutionTv = findViewById(R.id.hasil);

        assignId(buttonC, R.id.delete);
        assignId(buttonBrackOpen, R.id.tanda_kurung_awal);
        assignId(buttonBrackClose, R.id.tanda_kurung_akhir);
        assignId(buttonDivide, R.id.bagi);
        assignId(buttonMultiply, R.id.kali);
        assignId(buttonPlus, R.id.tambah);
        assignId(buttonMinus, R.id.kurang);
        assignId(buttonEquals, R.id.sama_dengan);
        assignId(button0, R.id.angka_0);
        assignId(button1, R.id.angka_1);
        assignId(button2, R.id.angka_2);
        assignId(button3, R.id.angka_3);
        assignId(button4, R.id.angka_4);
        assignId(button5, R.id.angka_5);
        assignId(button6, R.id.angka_6);
        assignId(button7, R.id.angka_7);
        assignId(button8, R.id.angka_8);
        assignId(button9, R.id.angka_9);
        assignId(buttonC, R.id.delete);
        assignId(buttonDot, R.id.titik);
        assignId(buttonAC, R.id.hapus_semua);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            return;
        }

        if (buttonText.equals("=")) {
            try {
                String finalResult = getResult(dataToCalculate);
                double numericResult = Double.parseDouble(finalResult);
                solutionTv.setText(finalResult);
            } catch (Exception e) {
                solutionTv.setText("Err");

            }
            return;
        }

        if (buttonText.equals("DEL")) {
            if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        } else {
            if (buttonText.equals("X")) {
                buttonText = "*";
            }
            dataToCalculate = dataToCalculate + buttonText;
        }


        solutionTv.setText(dataToCalculate);

        try {
            String finalResult = getResult(dataToCalculate);
            double numericResult = Double.parseDouble(finalResult);
            if (!finalResult.equals("0")) {

            }
        } catch (Exception e) {

        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
