package com.jspark.android.myutility;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    View view;

    Button btnLength, btnArea, btnWeight;
    LinearLayout loLength, loArea, loWeight;

    Spinner lengthSp1_1, lengthSp1_2;
    Spinner lengthSp2_1, lengthSp2_2;
    Spinner lengthSp3_1, lengthSp3_2;
    TextView unit1, unit2;

    ArrayList<String> dataArray = new ArrayList<String>();

    String change1 = "", change2 = "";

    EditText edit1_1, edit2_1, edit3_1;
    TextView edit1_2, edit2_2, edit3_2;
    TextView ans1_1, ans1_2, ans1_3, ans1_4, ans1_5, ans1_6, ans1_7, ans1_8;
    TextView ans2_1, ans2_2, ans2_3, ans2_4, ans2_5, ans2_6, ans2_7, ans2_8;
    TextView ans3_1, ans3_2, ans3_3, ans3_4, ans3_5, ans3_6, ans3_7, ans3_8;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view!=null) return view;

        view =inflater.inflate(R.layout.fragment_two, container, false);
        btnLength = (Button) view.findViewById(R.id.bx1);
        btnArea = (Button) view.findViewById(R.id.bx2);
        btnWeight = (Button) view.findViewById(R.id.bx3);

        loLength = (LinearLayout) view.findViewById(R.id.layoutLength);
        loArea = (LinearLayout) view.findViewById(R.id.layoutArea);
        loWeight = (LinearLayout) view.findViewById(R.id.layoutWeight);

        btnLength.setOnClickListener(clickListener);
        btnArea.setOnClickListener(clickListener);
        btnWeight.setOnClickListener(clickListener);

        setLengthSpinner(view);

        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            loLength.setVisibility(View.GONE);
            loArea.setVisibility(View.GONE);
            loWeight.setVisibility(View.GONE);
            switch (view.getId()) {
                case R.id.bx1:
                    dataArray.clear();
                    loLength.setVisibility(View.VISIBLE);
                    setLengthSpinner(view.getRootView());
                    break;
                case R.id.bx2:
                    dataArray.clear();
                    loArea.setVisibility(View.VISIBLE);
                    setAreaSpinner(view.getRootView());
                    break;
                case R.id.bx3:
                    dataArray.clear();
                    loWeight.setVisibility(View.VISIBLE);
                    setWeightSpinner(view.getRootView());
                    break;
            }
        }
    };

    private String makeFormat(double data) {
        if(String.valueOf(data).length()>10) {
            return String.format("%e", data);
        } else {
            return String.format("%.2f", data);
        }
    }

    public void setLengthSpinner(View view) {
        edit1_1 = (EditText) view.findViewById(R.id.edit1_1);
        edit1_2 = (TextView) view.findViewById(R.id.edit1_2);

        ans1_1 = (TextView) view.findViewById(R.id.answer1_1);
        ans1_2 = (TextView) view.findViewById(R.id.answer1_2);
        ans1_3 = (TextView) view.findViewById(R.id.answer1_3);
        ans1_4 = (TextView) view.findViewById(R.id.answer1_4);
        ans1_5 = (TextView) view.findViewById(R.id.answer1_5);
        ans1_6 = (TextView) view.findViewById(R.id.answer1_6);
        ans1_7 = (TextView) view.findViewById(R.id.answer1_7);
        ans1_8 = (TextView) view.findViewById(R.id.answer1_8);

        lengthSp1_1 = (Spinner) view.findViewById(R.id.sp1_1);
        lengthSp1_2 = (Spinner) view.findViewById(R.id.sp1_2);
        unit1 = (TextView) view.findViewById(R.id.unit1_1);
        unit2 = (TextView) view.findViewById(R.id.unit1_2);

        String length[] = {"밀리미터(mm)", "센치미터(cm)", "미터(m)", "킬로미터(km)", "인치(in)", "피트(ft)", "야드(yd)", "마일(mile)"};
        for (int i = 0; i < length.length; i++) {
            dataArray.add(length[i]);
        }

        ArrayAdapter<String> lengthAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dataArray);
        lengthSp1_1.setAdapter(lengthAdapter1);
        lengthSp1_2.setAdapter(lengthAdapter1);

        lengthSp1_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change1 = dataArray.get(i);
                unit1.setText(change1);
                calculateLength(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change1 = dataArray.get(0);
                unit1.setText(change1);
                calculateLength(change1, change2);
            }
        });

        lengthSp1_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change2 = dataArray.get(i);
                unit2.setText(change2);
                calculateLength(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change2 = dataArray.get(0);
                unit2.setText(change2);
                calculateLength(change1, change2);
            }
        });
    }

    public void calculateLength(String str1, String str2) {
        edit1_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateLength(change1, change2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        double get = 0;
        if (!(edit1_1.getText().toString().equals(""))) {
            get = Double.parseDouble(edit1_1.getText().toString());
        }

        if (str1.equals("밀리미터(mm)")) {
            ans1_1.setText(String.valueOf(makeFormat(get)));
            ans1_2.setText(String.valueOf(makeFormat(get * 0.1)));
            ans1_3.setText(String.valueOf(makeFormat(get * 0.001)));
            ans1_4.setText(String.valueOf(makeFormat(get * 0.0000001)));
            ans1_5.setText(String.valueOf(makeFormat(get / 25.4)));
            ans1_6.setText(String.valueOf(makeFormat(get / 304.8)));
            ans1_7.setText(String.valueOf(makeFormat(get / 914.4)));
            ans1_8.setText(String.valueOf(makeFormat(get / 1609344)));
        } else if (str1.equals("센치미터(cm)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 10)));
            ans1_2.setText(String.valueOf(makeFormat(get)));
            ans1_3.setText(String.valueOf(makeFormat(get / 100)));
            ans1_4.setText(String.valueOf(makeFormat(get / 100000)));
            ans1_5.setText(String.valueOf(makeFormat(get / 254)));
            ans1_6.setText(String.valueOf(makeFormat(get / 3048)));
            ans1_7.setText(String.valueOf(makeFormat(get / 9144)));
            ans1_8.setText(String.valueOf(makeFormat(get / 16093440)));
        } else if (str1.equals("미터(m)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 1000)));
            ans1_2.setText(String.valueOf(makeFormat(get * 100)));
            ans1_3.setText(String.valueOf(makeFormat(get)));
            ans1_4.setText(String.valueOf(makeFormat(get / 1000)));
            ans1_5.setText(String.valueOf(makeFormat(get / 0.0254)));
            ans1_6.setText(String.valueOf(makeFormat(get / 0.3048)));
            ans1_7.setText(String.valueOf(makeFormat(get / 0.9144)));
            ans1_8.setText(String.valueOf(makeFormat(get * 0.000621)));
        } else if (str1.equals("킬로미터(km)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 1000000)));
            ans1_2.setText(String.valueOf(makeFormat(get * 100000)));
            ans1_3.setText(String.valueOf(makeFormat(get * 1000)));
            ans1_4.setText(String.valueOf(makeFormat(get)));
            ans1_5.setText(String.valueOf(makeFormat(get * 39370.0787)));
            ans1_6.setText(String.valueOf(makeFormat(get * 3280.8399)));
            ans1_7.setText(String.valueOf(makeFormat(get * 1093.6133)));
            ans1_8.setText(String.valueOf(makeFormat(get * 0.621371)));
        } else if (str1.equals("인치(in)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 25.4)));
            ans1_2.setText(String.valueOf(makeFormat(get * 2.54)));
            ans1_3.setText(String.valueOf(makeFormat(get * 0.0254)));
            ans1_4.setText(String.valueOf(makeFormat(get * 0.000025)));
            ans1_5.setText(String.valueOf(makeFormat(get)));
            ans1_6.setText(String.valueOf(makeFormat(get * 0.083333)));
            ans1_7.setText(String.valueOf(makeFormat(get * 0.027778)));
            ans1_8.setText(String.valueOf(makeFormat(get * 0.000016)));
        } else if (str1.equals("피트(ft)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 304.8)));
            ans1_2.setText(String.valueOf(makeFormat(get * 30.48)));
            ans1_3.setText(String.valueOf(makeFormat(get * 0.3048)));
            ans1_4.setText(String.valueOf(makeFormat(get * 0.000305)));
            ans1_5.setText(String.valueOf(makeFormat(get * 12)));
            ans1_6.setText(String.valueOf(makeFormat(get)));
            ans1_7.setText(String.valueOf(makeFormat(get * 0.333333)));
            ans1_8.setText(String.valueOf(makeFormat(get * 0.000189)));
        } else if (str1.equals("야드(yd)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 914.4)));
            ans1_2.setText(String.valueOf(makeFormat(get * 91.44)));
            ans1_3.setText(String.valueOf(makeFormat(get * 0.9144)));
            ans1_4.setText(String.valueOf(makeFormat(get * 0.000914)));
            ans1_5.setText(String.valueOf(makeFormat(get * 36)));
            ans1_6.setText(String.valueOf(makeFormat(get * 3)));
            ans1_7.setText(String.valueOf(makeFormat(get)));
            ans1_8.setText(String.valueOf(makeFormat(get * 0.000568)));
        } else if (str1.equals("마일(mile)")) {
            ans1_1.setText(String.valueOf(makeFormat(get * 1609344)));
            ans1_2.setText(String.valueOf(makeFormat(get * 160934.4)));
            ans1_3.setText(String.valueOf(makeFormat(get * 1609.344)));
            ans1_4.setText(String.valueOf(makeFormat(get * 1.609344)));
            ans1_5.setText(String.valueOf(makeFormat(get * 63360)));
            ans1_6.setText(String.valueOf(makeFormat(get * 5280)));
            ans1_7.setText(String.valueOf(makeFormat(get * 1760)));
            ans1_8.setText(String.valueOf(makeFormat(get)));
        }

        if (str2.equals("밀리미터(mm)")) {
            edit1_2.setText(ans1_1.getText());
        } else if (str2.equals("센치미터(cm)")) {
            edit1_2.setText(ans1_2.getText());
        } else if (str2.equals("미터(m)")) {
            edit1_2.setText(ans1_3.getText());
        } else if (str2.equals("킬로미터(km)")) {
            edit1_2.setText(ans1_4.getText());
        } else if (str2.equals("인치(in)")) {
            edit1_2.setText(ans1_5.getText());
        } else if (str2.equals("피트(ft)")) {
            edit1_2.setText(ans1_6.getText());
        } else if (str2.equals("야드(yd)")) {
            edit1_2.setText(ans1_7.getText());
        } else if (str2.equals("마일(mile)")) {
            edit1_2.setText(ans1_8.getText());
        }
    }

    public void setAreaSpinner(View view) {
        edit2_1 = (EditText)view.findViewById(R.id.edit2_1);
        edit2_2 = (TextView)view.findViewById(R.id.edit2_2);

        ans2_1 = (TextView)view.findViewById(R.id.answer2_1);
        ans2_2 = (TextView)view.findViewById(R.id.answer2_2);
        ans2_3 = (TextView)view.findViewById(R.id.answer2_3);
        ans2_4 = (TextView)view.findViewById(R.id.answer2_4);
        ans2_5 = (TextView)view.findViewById(R.id.answer2_5);
        ans2_6 = (TextView)view.findViewById(R.id.answer2_6);
        ans2_7 = (TextView)view.findViewById(R.id.answer2_7);
        ans2_8 = (TextView)view.findViewById(R.id.answer2_8);

        lengthSp2_1 = (Spinner)view.findViewById(R.id.sp2_1);
        lengthSp2_2 = (Spinner)view.findViewById(R.id.sp2_2);
        unit1 = (TextView)view.findViewById(R.id.unit2_1);
        unit2 = (TextView)view.findViewById(R.id.unit2_2);

        String length[] = {"제곱미터(m^2)", "아르(a)", "헥타르(ha)", "제곱킬로미터(km^2)", "제곱피트(ft^2)", "제곱야드(yd^2)", "에이커(ac)", "평방자"};
        for(int i=0;i<length.length;i++) {
            dataArray.add(length[i]);
        }

        ArrayAdapter<String> lengthAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dataArray);
        lengthSp2_1.setAdapter(lengthAdapter1);
        lengthSp2_2.setAdapter(lengthAdapter1);

        lengthSp2_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change1 = dataArray.get(i);
                unit1.setText(change1);
                calculateArea(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change1 = dataArray.get(0);
                unit1.setText(change1);
                calculateArea(change1, change2);
            }
        });

        lengthSp2_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change2 = dataArray.get(i);
                unit2.setText(change2);
                calculateArea(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change2 = dataArray.get(0);
                unit2.setText(change2);
                calculateArea(change1, change2);
            }
        });
    }

    public void calculateArea(String str1, String str2) {
        edit2_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateArea(change1, change2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        double get = 0;
        if(!(edit2_1.getText().toString().equals(""))) {
            get = Double.parseDouble(edit2_1.getText().toString());
        }

        if(str1.equals("제곱미터(m^2)")) {
            ans2_1.setText(String.valueOf(makeFormat(get)));
            ans2_2.setText(String.valueOf(makeFormat(get*0.01)));
            ans2_3.setText(String.valueOf(makeFormat(get*0.0001)));
            ans2_4.setText(String.valueOf(makeFormat(get*0.0000001)));
            ans2_5.setText(String.valueOf(makeFormat(get*10.76391)));
            ans2_6.setText(String.valueOf(makeFormat(get*1.19599)));
            ans2_7.setText(String.valueOf(makeFormat(get*0.000247)));
            ans2_8.setText(String.valueOf(makeFormat(get*10.89)));
        } else if(str1.equals("아르(a)")) {
            ans2_1.setText(String.valueOf(makeFormat(get*100)));
            ans2_2.setText(String.valueOf(makeFormat(get)));
            ans2_3.setText(String.valueOf(makeFormat(get*0.01)));
            ans2_4.setText(String.valueOf(makeFormat(get*0.0001)));
            ans2_5.setText(String.valueOf(makeFormat(get*1076.39104)));
            ans2_6.setText(String.valueOf(makeFormat(get*119.599005)));
            ans2_7.setText(String.valueOf(makeFormat(get*0.024711)));
            ans2_8.setText(String.valueOf(makeFormat(get*1089)));
        } else if(str1.equals("헥타르(ha)")) {
            ans2_1.setText(String.valueOf(makeFormat(get*10000)));
            ans2_2.setText(String.valueOf(makeFormat(get*100)));
            ans2_3.setText(String.valueOf(makeFormat(get)));
            ans2_4.setText(String.valueOf(makeFormat(get*0.01)));
            ans2_5.setText(String.valueOf(makeFormat(get*107639.104)));
            ans2_6.setText(String.valueOf(makeFormat(get*11959.9005)));
            ans2_7.setText(String.valueOf(makeFormat(get*2.471054)));
            ans2_8.setText(String.valueOf(makeFormat(get*108900)));
        } else if(str1.equals("제곱킬로미터(km^2)")) {
            ans2_1.setText(String.valueOf(makeFormat(get*1000000)));
            ans2_2.setText(String.valueOf(makeFormat(get*10000)));
            ans2_3.setText(String.valueOf(makeFormat(get*100)));
            ans2_4.setText(String.valueOf(makeFormat(get)));
            ans2_5.setText(String.valueOf(makeFormat(get*10763910.4)));
            ans2_6.setText(String.valueOf(makeFormat(get*1195990.05)));
            ans2_7.setText(String.valueOf(makeFormat(get*247.105381)));
            ans2_8.setText(String.valueOf(makeFormat(get*10890000)));
        } else if(str1.equals("제곱피트(ft^2)")) {
            ans2_1.setText(String.valueOf(makeFormat(get*0.092903)));
            ans2_2.setText(String.valueOf(makeFormat(get*0.000929)));
            ans2_3.setText(String.valueOf(makeFormat(get*0.0000092903)));
            ans2_4.setText(String.valueOf(makeFormat(get*0.000000092903)));
            ans2_5.setText(String.valueOf(makeFormat(get)));
            ans2_6.setText(String.valueOf(makeFormat(get*0.111111)));
            ans2_7.setText(String.valueOf(makeFormat(get*0.000023)));
            ans2_8.setText(String.valueOf(makeFormat(get*1.011714)));
        } else if(str1.equals("제곱야드(yd^2)")) {
            ans2_1.setText(String.valueOf(makeFormat(get*0.836127)));
            ans2_2.setText(String.valueOf(makeFormat(get*0.008361)));
            ans2_3.setText(String.valueOf(makeFormat(get*0.000084)));
            ans2_4.setText(String.valueOf(makeFormat(get*8.3613e-7)));
            ans2_5.setText(String.valueOf(makeFormat(get*9)));
            ans2_6.setText(String.valueOf(makeFormat(get)));
            ans2_7.setText(String.valueOf(makeFormat(get*0.000207)));
            ans2_8.setText(String.valueOf(makeFormat(get*9.105427)));
        } else if(str1.equals("에이커(ac)")) {
            ans2_1.setText(String.valueOf(makeFormat(get*4046.85642)));
            ans2_2.setText(String.valueOf(makeFormat(get*40.468564)));
            ans2_3.setText(String.valueOf(makeFormat(get*0.404686)));
            ans2_4.setText(String.valueOf(makeFormat(get*0.004047)));
            ans2_5.setText(String.valueOf(makeFormat(get*43560)));
            ans2_6.setText(String.valueOf(makeFormat(get*4840)));
            ans2_7.setText(String.valueOf(makeFormat(get)));
            ans2_8.setText(String.valueOf(makeFormat(get*44070.2664)));
        } else if(str1.equals("평방자")) {
            ans2_1.setText(String.valueOf(makeFormat(get*0.091827)));
            ans2_2.setText(String.valueOf(makeFormat(get*0.000918)));
            ans2_3.setText(String.valueOf(makeFormat(get*9.1827e-6)));
            ans2_4.setText(String.valueOf(makeFormat(get*9.1827e-8)));
            ans2_5.setText(String.valueOf(makeFormat(get*0.988422)));
            ans2_6.setText(String.valueOf(makeFormat(get*0.109825)));
            ans2_7.setText(String.valueOf(makeFormat(get*0.000023)));
            ans2_8.setText(String.valueOf(makeFormat(get)));
        }

        if(str2.equals("제곱미터(m^2)")) {
            edit2_2.setText(ans2_1.getText());
        } else if(str2.equals("아르(a)")) {
            edit2_2.setText(ans2_2.getText());
        } else if(str2.equals("헥타르(ha)")) {
            edit2_2.setText(ans2_3.getText());
        } else if(str2.equals("제곱킬로미터(km^2)")) {
            edit2_2.setText(ans2_4.getText());
        } else if(str2.equals("제곱피트(ft^2)")) {
            edit2_2.setText(ans2_5.getText());
        } else if(str2.equals("제곱야드(yd^2)")) {
            edit2_2.setText(ans2_6.getText());
        } else if(str2.equals("에이커(ac)")) {
            edit2_2.setText(ans2_7.getText());
        } else if(str2.equals("평방자")) {
            edit2_2.setText(ans2_8.getText());
        }
    }

    public void setWeightSpinner(View view) {
        edit3_1 = (EditText) view.findViewById(R.id.edit3_1);
        edit3_2 = (TextView) view.findViewById(R.id.edit3_2);

        ans3_1 = (TextView) view.findViewById(R.id.answer3_1);
        ans3_2 = (TextView) view.findViewById(R.id.answer3_2);
        ans3_3 = (TextView) view.findViewById(R.id.answer3_3);
        ans3_4 = (TextView) view.findViewById(R.id.answer3_4);
        ans3_5 = (TextView) view.findViewById(R.id.answer3_5);
        ans3_6 = (TextView) view.findViewById(R.id.answer3_6);
        ans3_7 = (TextView) view.findViewById(R.id.answer3_7);
        ans3_8 = (TextView) view.findViewById(R.id.answer3_8);

        lengthSp3_1 = (Spinner) view.findViewById(R.id.sp3_1);
        lengthSp3_2 = (Spinner) view.findViewById(R.id.sp3_2);
        unit1 = (TextView) view.findViewById(R.id.unit3_1);
        unit2 = (TextView) view.findViewById(R.id.unit3_2);

        String length[] = {"밀리그램(mg)", "그램(g)", "킬로그램(kg)", "톤(t)", "킬로톤(kt)", "그레인(gr)", "온즈(oz)", "파운드(lb)"};
        for (int i = 0; i < length.length; i++) {
            dataArray.add(length[i]);
        }

        ArrayAdapter<String> lengthAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dataArray);
        lengthSp3_1.setAdapter(lengthAdapter1);
        lengthSp3_2.setAdapter(lengthAdapter1);

        lengthSp3_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change1 = dataArray.get(i);
                unit1.setText(change1);
                calculateWeight(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change1 = dataArray.get(0);
                unit1.setText(change1);
                calculateWeight(change1, change2);
            }
        });

        lengthSp3_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change2 = dataArray.get(i);
                unit2.setText(change2);
                calculateWeight(change1, change2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                change2 = dataArray.get(0);
                unit2.setText(change2);
                calculateWeight(change1, change2);
            }
        });
    }

    public void calculateWeight(String str1, String str2) {
        edit3_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateWeight(change1, change2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        double get = 0;
        if (!(edit3_1.getText().toString().equals(""))) {
            get = Double.parseDouble(edit3_1.getText().toString());
        }

        if (str1.equals("밀리그램(mg)")) {
            ans3_1.setText(String.valueOf(makeFormat(get)));
            ans3_2.setText(String.valueOf(makeFormat(get * 0.001)));
            ans3_3.setText(String.valueOf(makeFormat(get * 0.000001)));
            ans3_4.setText(String.valueOf(makeFormat(get * 0.000000001)));
            ans3_5.setText(String.valueOf(makeFormat(get * 0.0000000000001)));
            ans3_6.setText(String.valueOf(makeFormat(get / 64.79891)));
            ans3_7.setText(String.valueOf(makeFormat(get / 28349.5231)));
            ans3_8.setText(String.valueOf(makeFormat(get / 453592.37)));
        } else if (str1.equals("그램(g)")) {
            ans3_1.setText(String.valueOf(makeFormat(get * 1000)));
            ans3_2.setText(String.valueOf(makeFormat(get)));
            ans3_3.setText(String.valueOf(makeFormat(get / 1000)));
            ans3_4.setText(String.valueOf(makeFormat(get / 1000000)));
            ans3_5.setText(String.valueOf(makeFormat(get / 1000000000)));
            ans3_6.setText(String.valueOf(makeFormat(get * 15.432358)));
            ans3_7.setText(String.valueOf(makeFormat(get * 0.035274)));
            ans3_8.setText(String.valueOf(makeFormat(get * 0.002205)));
        } else if (str1.equals("킬로그램(kg)")) {
            ans3_1.setText(String.valueOf(makeFormat(get * 1000000)));
            ans3_2.setText(String.valueOf(makeFormat(get * 1000)));
            ans3_3.setText(String.valueOf(makeFormat(get)));
            ans3_4.setText(String.valueOf(makeFormat(get * 0.001)));
            ans3_5.setText(String.valueOf(makeFormat(get * 0.000001)));
            ans3_6.setText(String.valueOf(makeFormat(get * 15432.3584)));
            ans3_7.setText(String.valueOf(makeFormat(get * 35.273962)));
            ans3_8.setText(String.valueOf(makeFormat(get * 2.204623)));
        } else if (str1.equals("톤(t)")) {
            ans3_1.setText(String.valueOf(makeFormat(get * 1000000000)));
            ans3_2.setText(String.valueOf(makeFormat(get * 1000000)));
            ans3_3.setText(String.valueOf(makeFormat(get * 1000)));
            ans3_4.setText(String.valueOf(makeFormat(get)));
            ans3_5.setText(String.valueOf(makeFormat(get * 0.001)));
            ans3_6.setText(String.valueOf(makeFormat(get * 15432358.4)));
            ans3_7.setText(String.valueOf(makeFormat(get * 35273.9619)));
            ans3_8.setText(String.valueOf(makeFormat(get * 2204.62262)));
        } else if (str1.equals("킬로톤(kt)")) {
            ans3_1.setText(String.valueOf(makeFormat(get / 0.000000000001)));
            ans3_2.setText(String.valueOf(makeFormat(get * 2.54)));
            ans3_3.setText(String.valueOf(makeFormat(get * 0.0254)));
            ans3_4.setText(String.valueOf(makeFormat(get * 0.000025)));
            ans3_5.setText(String.valueOf(makeFormat(get)));
            ans3_6.setText(String.valueOf(makeFormat(get * 0.083333)));
            ans3_7.setText(String.valueOf(makeFormat(get * 0.027778)));
            ans3_8.setText(String.valueOf(makeFormat(get * 0.000016)));
        } else if (str1.equals("그레인(gr)")) {
            ans3_1.setText(String.valueOf(makeFormat(get * 64.79891)));
            ans3_2.setText(String.valueOf(makeFormat(get * 0.064799)));
            ans3_3.setText(String.valueOf(makeFormat(get * 0.000065)));
            ans3_4.setText(String.valueOf(makeFormat(get * 64.799e-8)));
            ans3_5.setText(String.valueOf(makeFormat(get * 6.4799e-11)));
            ans3_6.setText(String.valueOf(makeFormat(get)));
            ans3_7.setText(String.valueOf(makeFormat(get * 0.002286)));
            ans3_8.setText(String.valueOf(makeFormat(get * 0.000143)));
        } else if (str1.equals("온스(oz)")) {
            ans3_1.setText(String.valueOf(makeFormat(get * 28349.5231)));
            ans3_2.setText(String.valueOf(makeFormat(get * 28.349523)));
            ans3_3.setText(String.valueOf(makeFormat(get * 0.02835)));
            ans3_4.setText(String.valueOf(makeFormat(get * 0.000028)));
            ans3_5.setText(String.valueOf(makeFormat(get * 2.835e-8)));
            ans3_6.setText(String.valueOf(makeFormat(get * 437.5)));
            ans3_7.setText(String.valueOf(makeFormat(get)));
            ans3_8.setText(String.valueOf(makeFormat(get * 0.0625)));
        } else if (str1.equals("파운드(lb)")) {
            ans3_1.setText(String.valueOf(makeFormat(get * 453592.37)));
            ans3_2.setText(String.valueOf(makeFormat(get * 453.59237)));
            ans3_3.setText(String.valueOf(makeFormat(get * 0.453592)));
            ans3_4.setText(String.valueOf(makeFormat(get * 0.000454)));
            ans3_5.setText(String.valueOf(makeFormat(get * 4.5359e-7)));
            ans3_6.setText(String.valueOf(makeFormat(get * 7000)));
            ans3_7.setText(String.valueOf(makeFormat(get * 16)));
            ans3_8.setText(String.valueOf(makeFormat(get)));
        }

        if (str2.equals("밀리그램(mg)")) {
            edit3_2.setText(ans3_1.getText());
        } else if (str2.equals("그램(g)")) {
            edit3_2.setText(ans3_2.getText());
        } else if (str2.equals("킬로그램(kg)")) {
            edit3_2.setText(ans3_3.getText());
        } else if (str2.equals("톤(t)")) {
            edit3_2.setText(ans3_4.getText());
        } else if (str2.equals("킬로톤(kt)")) {
            edit3_2.setText(ans3_5.getText());
        } else if (str2.equals("그레인(gr)")) {
            edit3_2.setText(ans3_6.getText());
        } else if (str2.equals("온즈(oz)")) {
            edit3_2.setText(ans3_7.getText());
        } else if (str2.equals("파운드(lb)")) {
            edit3_2.setText(ans3_8.getText());
        }
    }
}
