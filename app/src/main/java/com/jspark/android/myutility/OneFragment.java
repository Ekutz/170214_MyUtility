package com.jspark.android.myutility;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OneFragment extends Fragment {

    TextView txt, txtPre;

    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnP, btnM, btnMu, btnD, btnR, btnC;

    View view;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view!=null) return view;

        view = inflater.inflate(R.layout.fragment_one, container, false);

        txt = (TextView)view.findViewById(R.id.tvResult);
        txtPre = (TextView)view.findViewById(R.id.preview);

        btnOne = (Button)view.findViewById(R.id.btn1);
        btnTwo = (Button)view.findViewById(R.id.btn2);
        btnThree = (Button)view.findViewById(R.id.btn3);
        btnFour = (Button)view.findViewById(R.id.btn4);
        btnFive = (Button)view.findViewById(R.id.btn5);
        btnSix = (Button)view.findViewById(R.id.btn6);
        btnSeven = (Button)view.findViewById(R.id.btn7);
        btnEight = (Button)view.findViewById(R.id.btn8);
        btnNine = (Button)view.findViewById(R.id.btn9);
        btnZero = (Button)view.findViewById(R.id.btn0);

        btnP = (Button)view.findViewById(R.id.btnPlus);
        btnM = (Button)view.findViewById(R.id.btnMinus);
        btnMu = (Button)view.findViewById(R.id.btnMulti);
        btnD = (Button)view.findViewById(R.id.btnDiv);
        btnC = (Button)view.findViewById(R.id.btnCancle);
        btnR = (Button)view.findViewById(R.id.btnRun);

        btnOne.setOnClickListener(listener);
        btnTwo.setOnClickListener(listener);
        btnThree.setOnClickListener(listener);
        btnFour.setOnClickListener(listener);
        btnFive.setOnClickListener(listener);
        btnSix.setOnClickListener(listener);
        btnSeven.setOnClickListener(listener);
        btnEight.setOnClickListener(listener);
        btnNine.setOnClickListener(listener);
        btnZero.setOnClickListener(listener);

        btnP.setOnClickListener(listener);
        btnM.setOnClickListener(listener);
        btnMu.setOnClickListener(listener);
        btnD.setOnClickListener(listener);

        btnC.setOnClickListener(listener);
        btnR.setOnClickListener(listener);

        return view;
    }
    
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn1 :
                    txtPre.setText(txtPre.getText() + "1");
                    txt.setText(txt.getText() + "1");
                    break;
                case R.id.btn2 :
                    txtPre.setText(txtPre.getText() + "2");
                    txt.setText(txt.getText() + "2");
                    break;
                case R.id.btn3 :
                    txtPre.setText(txtPre.getText() + "3");
                    txt.setText(txt.getText() + "3");
                    break;
                case R.id.btn4 :
                    txtPre.setText(txtPre.getText() + "4");
                    txt.setText(txt.getText() + "4");
                    break;
                case R.id.btn5 :
                    txtPre.setText(txtPre.getText() + "5");
                    txt.setText(txt.getText() + "5");
                    break;
                case R.id.btn6 :
                    txtPre.setText(txtPre.getText() + "6");
                    txt.setText(txt.getText() + "6");
                    break;
                case R.id.btn7 :
                    txtPre.setText(txtPre.getText() + "7");
                    txt.setText(txt.getText() + "7");
                    break;
                case R.id.btn8 :
                    txtPre.setText(txtPre.getText() + "8");
                    txt.setText(txt.getText() + "8");
                    break;
                case R.id.btn9 :
                    txtPre.setText(txtPre.getText() + "9");
                    txt.setText(txt.getText() + "9");
                    break;
                case R.id.btn0 :
                    txtPre.setText(txtPre.getText() + "0");
                    txt.setText(txt.getText() + "0");
                    break;

                case R.id.btnPlus :
                    txtPre.setText(txtPre.getText() + "+");
                    txt.setText("");
                    break;
                case R.id.btnMinus :
                    txtPre.setText(txtPre.getText() + "-");
                    txt.setText("");
                    break;
                case R.id.btnMulti :
                    txtPre.setText(txtPre.getText() + "*");
                    txt.setText("");
                    break;
                case R.id.btnDiv :
                    txtPre.setText(txtPre.getText() + "/");
                    txt.setText("");
                    break;

                case R.id.btnCancle :
                    txtPre.setText("");
                    txt.setText("");
                    break;
                case R.id.btnRun :
                    txt.setText(runBtn(txtPre.getText().toString()));
                    break;
            }
        }
    };

    public String runBtn(String str) {
        String result = "";

        String splited[] = str.split("(?<=[*/+-])|(?=[*/+-])");

        ArrayList<String> list = new ArrayList<String>();

        for(String item : splited) {
            list.add(item);
        }

        int index=0;
        for(index=0;index<list.size();index++) {
            String getItem = list.get(index);

            double a = 0, b=0, calResult=0;
            boolean check = true;

            if(getItem.equals("*")) {
                a = Double.parseDouble(list.get(index-1));
                b = Double.parseDouble(list.get(index+1));
                calResult = a * b;
                check=true;
            } else if(getItem.equals("/")) {
                a = Double.parseDouble(list.get(index-1));
                b = Double.parseDouble(list.get(index+1));
                calResult = a / b;
                check=true;
            } else {
                check = false;
            }

            if(check) {
                list.set(index, ""+calResult);
                list.remove(index+1);
                list.remove(index-1);
                index--;
            } else {

            }
        }

        index = 0;
        for(index=0;index<list.size();index++) {
            String getItem = list.get(index);

            double a = 0, b=0, calResult=0;
            boolean check = true;

            if(getItem.equals("+")) {
                a = Double.parseDouble(list.get(index-1));
                b = Double.parseDouble(list.get(index+1));
                calResult = a + b;
                check=true;
            } else if(getItem.equals("-")) {
                a = Double.parseDouble(list.get(index-1));
                b = Double.parseDouble(list.get(index+1));
                calResult = a - b;
                check=true;
            } else {
                check = false;
            }

            if(check) {
                list.set(index, ""+calResult);
                list.remove(index+1);
                list.remove(index-1);
                index--;
            } else {

            }
        }
        result = list.get(0);
        return result;
    }

}
