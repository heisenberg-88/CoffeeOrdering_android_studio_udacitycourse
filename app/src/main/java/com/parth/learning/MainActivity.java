package com.parth.learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity =0;
    String finsumpri="notext";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void submitOrder(View view) {
        display(quantity);
        int price=0;
        price=CalculatePrice(quantity,price);
        displayPrice(CalculateSummary(price));
        finsumpri=CalculateSummary(price);
        if(quantity>=1){
            Toast.makeText(this, "Order Placed :)", Toast.LENGTH_SHORT).show();
        }
    }
    public void increment(View view) {
        if(quantity==50){
            display(quantity);
        }else {
            quantity=quantity+1;
            display(quantity);
        }
    }

    public void decrement(View view) {
        if(quantity==0){
            display(quantity);
        }else {
            quantity=quantity-1;
            display(quantity);
        }
    }

    private int CalculatePrice(int quantity, int price){
        CheckBox whippedCreamCheck = (CheckBox) findViewById(R.id.WhippedCream_id);
        CheckBox chocolateCheck =(CheckBox) findViewById(R.id.Chocolate_id) ;
        price=quantity*5;
        if(whippedCreamCheck.isChecked()){
            price=price+(2*quantity);
        }
        if(chocolateCheck.isChecked()){
            price=price+(2*quantity);
        }
        return price;
    }

    private String CalculateSummary(int price){
        EditText customerNameobj=(EditText) findViewById(R.id.customerName_id);
        CheckBox whippedCreamCheck = (CheckBox) findViewById(R.id.WhippedCream_id);
        CheckBox chocolateCheck =(CheckBox) findViewById(R.id.Chocolate_id) ;
        String finalSummary="Thank you for ordering "+customerNameobj.getText()+" !!"+"\nyour order total is $ "+ price ;
        
        if(whippedCreamCheck.isChecked()){
            finalSummary=finalSummary+"\n$ 2 of Whipped Cream is included";
        }
        if(chocolateCheck.isChecked()){
            finalSummary=finalSummary+"\n$ 2 of Extra Chocolates is included";
        }
        if(quantity==0){
            finalSummary="Please Order something !!";
        }
        finalSummary=finalSummary+" \nHave a nice day :)";
        return finalSummary;
    }


    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    private void displayPrice(String number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(number);
    }

    public void SendEMail(View view) {
        Intent emailintent = new Intent(Intent.ACTION_SEND);
        emailintent.setType("*/*");
        emailintent.putExtra(Intent.EXTRA_EMAIL, "k25parthd@gmail.com");
        emailintent.putExtra(Intent.EXTRA_SUBJECT, finsumpri);
        if (emailintent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailintent);
        }
    }
}