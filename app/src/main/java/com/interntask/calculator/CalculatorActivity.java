package com.interntask.calculator;

import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class CalculatorActivity extends FragmentActivity {

    private TextView mScreen;
    private String mDisplay = "";
    private String mCurrentOperator = "";
    private String mResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        int orientation = this.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            ViewPager pager = (ViewPager) findViewById(R.id.buttons_container);
            pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        }

        mScreen = (TextView)findViewById(R.id.text_view);
        mScreen.setText(mDisplay);
    }

    private void updateScreen(){
        mScreen.setText(mDisplay);
    }

    public void onClickNumber(View v){
        if(mResult != ""){
            clear();
            updateScreen();
        }
        Button b = (Button) v;
        mDisplay += b.getText();
        updateScreen();
    }

    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '*':
            case '/':return true;
            default: return false;
        }
    }

    public void onClickOperator(View v){
        if(mDisplay == "") return;

        Button b = (Button)v;

        if(mResult != ""){
            String _display = mResult;
            clear();
            mDisplay = _display;
        }

        if(mCurrentOperator != ""){
            Log.d("CalcX", ""+mDisplay.charAt(mDisplay.length()-1));
            if(isOperator(mDisplay.charAt(mDisplay.length()-1))){
                mDisplay = mDisplay.replace(mDisplay.charAt(mDisplay.length()-1), b.getText().charAt(0));
                updateScreen();
                return;
            }else{
                getResult();
                mDisplay = mResult;
                mResult = "";

            }
            mCurrentOperator = b.getText().toString();
        }
        mDisplay += b.getText();
        mCurrentOperator = b.getText().toString();
        updateScreen();
    }

    private void clear(){
        mDisplay = "";
        mCurrentOperator = "";
        mResult = "";
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }

    private double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "*": return Double.valueOf(a) * Double.valueOf(b);
            case "/": try{
                return Double.valueOf(a) / Double.valueOf(b);
            }catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
            default: return -1;
        }
    }

    private boolean getResult(){
        if(mCurrentOperator == "") return false;
        String[] operation = mDisplay.split(Pattern.quote(mCurrentOperator));
        if(operation.length < 2) return false;
        mResult = String.valueOf(operate(operation[0], operation[1], mCurrentOperator));
        return true;
    }

    public void onClickEqual(View v){
        if(mDisplay == "") return;
        if(!getResult()) return;
        mScreen.setText(mDisplay + "\n" + String.valueOf(mResult));
    }

    public void isNotImplemented(View v) {
        Toast.makeText(CalculatorActivity.this,
                R.string.isNotImplemented,
                Toast.LENGTH_SHORT).show();

    }
}
