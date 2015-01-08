package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity{
		
	Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
	Button Plus, Minus, Multiply, Divide, Equals;	
	Button C, CE, Backspace;
	TextView Result, History;
	double var1, var2;
	
	boolean ClearInput, NewCalculation;
	StringBuffer input, history, operator, log;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ClearInput = true;
        NewCalculation = true;
        input = new StringBuffer();
        operator = new StringBuffer();
        log = new StringBuffer();
        
        One = (Button) findViewById(R.id.One);
        Two = (Button) findViewById(R.id.Two);
        Three = (Button) findViewById(R.id.Three);
        Four = (Button) findViewById(R.id.Four);
        Five = (Button) findViewById(R.id.Five);
        Six = (Button) findViewById(R.id.Six);
        Seven = (Button) findViewById(R.id.Seven);
        Eight = (Button) findViewById(R.id.Eight);
        Nine = (Button) findViewById(R.id.Nine);
        Zero = (Button) findViewById(R.id.Zero);
        
        Plus = (Button) findViewById(R.id.Plus);
        Minus = (Button) findViewById(R.id.Minus);
        Multiply = (Button) findViewById(R.id.Multiply);
        Divide = (Button) findViewById(R.id.Divide);
        Equals = (Button) findViewById(R.id.Equals);
        
        C = (Button) findViewById(R.id.C);
        CE = (Button) findViewById(R.id.CE);
        Backspace = (Button) findViewById(R.id.Backspace);
        
        Result = (TextView) findViewById(R.id.editText);
        History = (TextView) findViewById(R.id.History);
        
        //---------------------------------------------------
        
        One.setOnClickListener(inputNum);
        Two.setOnClickListener(inputNum);
        Three.setOnClickListener(inputNum);
        Four.setOnClickListener(inputNum);
        Five.setOnClickListener(inputNum);
        Six.setOnClickListener(inputNum);
        Seven.setOnClickListener(inputNum);
        Eight.setOnClickListener(inputNum);
        Nine.setOnClickListener(inputNum);
        Zero.setOnClickListener(inputNum);
        
        Plus.setOnClickListener(Operator);
        Minus.setOnClickListener(Operator);
        Multiply.setOnClickListener(Operator);
        Divide.setOnClickListener(Operator);
        Equals.setOnClickListener(GetResult);
        
        C.setOnClickListener(Clear);
        CE.setOnClickListener(ClearEntry);
        Backspace.setOnClickListener(BackSpace);      
    
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }		
    
    OnClickListener inputNum = new OnClickListener() {
        @Override
        public void onClick(View v) {
        Button btn = (Button) v;
         if(ClearInput==true)
        	 input.replace(0,input.length(),btn.getText().toString());
         else
        	 input.append(btn.getText().toString());
         Result.setText(input.toString());
         ClearInput=false;
        }
    };		
    
    OnClickListener BackSpace = new OnClickListener() {
        @Override
        public void onClick(View v) {        
        
        if(input.length()>0)
        	input.deleteCharAt(input.length()-1);
        if(input.length()==0){
        	input.insert(0,'0');
        	ClearInput=true;
        	}        		
        Result.setText(input.toString());                
        }
    };		
    
    OnClickListener ClearEntry = new OnClickListener() {
        @Override
        public void onClick(View v) {
        input.replace(0,input.length(),"0");
        Result.setText(input.toString());
        ClearInput=true;
        }
    };	
    
    OnClickListener Clear = new OnClickListener() {
        @Override
        public void onClick(View v) {
        	input.replace(0,input.length(),"0");
        	log.delete(0, log.length());
        	History.setText(log.toString());
            Result.setText(input.toString());
            ClearInput=true;           
            NewCalculation=true;
        }
    };	
    
    OnClickListener Operator = new OnClickListener() {
        @Override
        public void onClick(View v) {
        Button btn = (Button) v;
        operator.replace(0, input.length(), btn.getText().toString());   
        var1 = Double.parseDouble(input.toString());
        log.append(input);
        log.append(btn.getText().toString());
        input.replace(0,input.length(),"0");
        ClearInput=true;
        History.setText(log.toString());
        Result.setText(input.toString());        
        }
    };	
    
    OnClickListener GetResult = new OnClickListener() {
        @Override
        public void onClick(View v) {
        Button btn = (Button) v;
        log.append(input.toString());
        switch(operator.charAt(0)){
        case '+':
        	var1+=Double.parseDouble(input.toString());
        	break;
        case '-':
        	var1-=Double.parseDouble(input.toString());
        	break;
        case '*':
        	var1*=Double.parseDouble(input.toString());
        	break;
        case '/':
        	var1/=Double.parseDouble(input.toString());
        	break;
        }
        log.append('=');
        log.append(String.valueOf(var1));
        History.setText(log.toString());
        Result.setText(String.valueOf(var1));        
        }
    };
}
