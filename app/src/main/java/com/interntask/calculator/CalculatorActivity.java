package com.interntask.calculator;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.regex.Pattern;

public class CalculatorActivity extends AppCompatActivity{

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

        if(savedInstanceState != null) {
            mDisplay = savedInstanceState.getString("display_value");
            mResult = savedInstanceState.getString("result");
            mCurrentOperator = savedInstanceState.getString("currentOperator");
        }

        mScreen = (TextView)findViewById(R.id.text_view);
        mScreen.setText(mDisplay);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(myToolbar);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("display_value", String.valueOf(mScreen.getText()));
        savedInstanceState.putString("result", mResult);
        savedInstanceState.putString("currentOperator", mCurrentOperator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                // User chose the "History" item, show the app settings UI...
                ViewFlipper vf  = (ViewFlipper) findViewById(R.id.view_flipper);
                vf.showNext();
                return true;

            case R.id.action_logout:
                // User chose the "Logout" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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
            case "/": if(Double.valueOf(b) != 0) {
                return Double.valueOf(a) / Double.valueOf(b);
            }
            else {
                Toast.makeText(CalculatorActivity.this,
                        R.string.divideByZeroMsg,
                        Toast.LENGTH_LONG).show();
            }
            default: return -1;
        }
    }

    @SuppressLint("DefaultLocale")
    private boolean getResult(){
        if(mCurrentOperator == "") return false;
        String[] operation = mDisplay.split(Pattern.quote(mCurrentOperator));
        if(operation.length < 2) return false;
        mResult = String.format("%.5f", operate(operation[0], operation[1], mCurrentOperator));
        return true;
    }

    @SuppressLint("SetTextI18n")
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

