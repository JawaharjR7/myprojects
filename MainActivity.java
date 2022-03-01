package com.example.billingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText rate1,rate2,rate3,rate4,rate5,rate6,rate7,qty1,qty2,qty3,qty4,qty5,qty6,qty7;
    EditText item1,item2,item3,item4,item5,item6,item7,hsn1,hsn2,hsn3,hsn4,hsn5,hsn6,hsn7,po1,po2,po3,po4,po5,po6,po7,dc1,dc2,dc3,dc4,dc5,dc6,dc7;
    EditText amount1,amount2,amount3,amount4,amount5,amount6,amount7,cgst,igst,subtotal,roundoff,gtotal;
    EditText billname,billaddress,gst,billno;
    TextView rupee;
    Button generatebill,add1,add2,add3,add4,add5,add6,add7;
    float r1,r2,r3,r4,r5,r6,r7,q1,q2,q3,q4,q5,q6,q7,a1,a2,a3,a4,a5,a6,a7,st,ct,it,ro,t,gt;
    Date dateobj;
    DateFormat dateFormat;

    DecimalFormat f=new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billname=findViewById(R.id.billname);
        billaddress=findViewById(R.id.billaddress);
        gst=findViewById(R.id.billgst);
        billno=findViewById(R.id.billno);
        item1=findViewById(R.id.billitem1);
        item2=findViewById(R.id.billitem2);
        item3=findViewById(R.id.billitem3);
        item4=findViewById(R.id.billitem4);
        item5=findViewById(R.id.billitem5);
        item6=findViewById(R.id.billitem6);
        item7=findViewById(R.id.billitem7);
        hsn1=findViewById(R.id.billhsn1);
        hsn2=findViewById(R.id.billhsn2);
        hsn3=findViewById(R.id.billhsn3);
        hsn4=findViewById(R.id.billhsn4);
        hsn5=findViewById(R.id.billhsn5);
        hsn6=findViewById(R.id.billhsn6);
        hsn7=findViewById(R.id.billhsn7);
        po1=findViewById(R.id.billpo1);
        po2=findViewById(R.id.billpo2);
        po3=findViewById(R.id.billpo3);
        po4=findViewById(R.id.billpo4);
        po5=findViewById(R.id.billpo5);
        po6=findViewById(R.id.billpo6);
        po7=findViewById(R.id.billpo7);
        dc1=findViewById(R.id.billdc1);
        dc2=findViewById(R.id.billdc2);
        dc3=findViewById(R.id.billdc3);
        dc4=findViewById(R.id.billdc4);
        dc5=findViewById(R.id.billdc5);
        dc6=findViewById(R.id.billdc6);
        dc7=findViewById(R.id.billdc7);
        qty1=findViewById(R.id.billqty1);
        qty2=findViewById(R.id.billqty2);
        qty3=findViewById(R.id.billqty3);
        qty4=findViewById(R.id.billqty4);
        qty5=findViewById(R.id.billqty5);
        qty6=findViewById(R.id.billqty6);
        qty7=findViewById(R.id.billqty7);
        rate1=findViewById(R.id.billrate1);
        rate2=findViewById(R.id.billrate2);
        rate3=findViewById(R.id.billrate3);
        rate4=findViewById(R.id.billrate4);
        rate5=findViewById(R.id.billrate5);
        rate6=findViewById(R.id.billrate6);
        rate7=findViewById(R.id.billrate7);
        amount1=findViewById(R.id.billamount1);
        amount2=findViewById(R.id.billamount2);
        amount3=findViewById(R.id.billamount3);
        amount4=findViewById(R.id.billamount4);
        amount5=findViewById(R.id.billamount5);
        amount6=findViewById(R.id.billamount6);
        amount7=findViewById(R.id.billamount7);
        subtotal=findViewById(R.id.billsubtotals);
        cgst=findViewById(R.id.billcgsts);
        igst=findViewById(R.id.billigsts);
        roundoff=findViewById(R.id.billroundoff);
        gtotal=findViewById(R.id.billtotals);
        rupee=findViewById(R.id.billtotalwords);
        add1=findViewById(R.id.billadd1);
        add2=findViewById(R.id.billadd2);
        add3=findViewById(R.id.billadd3);
        add4=findViewById(R.id.billadd4);
        add5=findViewById(R.id.billadd5);
        add6=findViewById(R.id.billadd6);
        add7=findViewById(R.id.billadd7);
        generatebill=findViewById(R.id.generatebill);



        add1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r1=Float.parseFloat(rate1.getText().toString());
                q1=Float.parseFloat(qty1.getText().toString());
                a1=r1*q1;
                st=st+a1;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount1.setText(Float.toString(a1));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("Rupees " +numberToWord(rw)+" only.");
                add1.setEnabled(false);

            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r2=Float.parseFloat(rate2.getText().toString());
                q2=Float.parseFloat(qty2.getText().toString());
                a2=r2*q2;
                st=st+a2;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount2.setText(Float.toString(a2));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("INR " +numberToWord(rw)+" Rupees only.");
                add2.setEnabled(false);

            }
        });
        add3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r3=Float.parseFloat(rate3.getText().toString());
                q3=Float.parseFloat(qty3.getText().toString());
                a3=r3*q3;
                st=st+a3;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount1.setText(Float.toString(a3));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("INR " +numberToWord(rw)+" Rupees only.");
                add3.setEnabled(false);


            }
        });
        add4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r4=Float.parseFloat(rate4.getText().toString());
                q4=Float.parseFloat(qty4.getText().toString());
                a4=r4*q4;
                st=st+a4;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount1.setText(Float.toString(a4));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("INR " +numberToWord(rw)+" Rupees only.");
                add4.setEnabled(false);


            }
        });
        add5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r5=Float.parseFloat(rate5.getText().toString());
                q5=Float.parseFloat(qty5.getText().toString());
                a5=r5*q5;
                st=st+a5;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount1.setText(Float.toString(a5));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("INR " +numberToWord(rw)+" Rupees only.");
                add5.setEnabled(false);


            }
        });

        add6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r6=Float.parseFloat(rate6.getText().toString());
                q6=Float.parseFloat(qty6.getText().toString());
                a6=r6*q6;
                st=st+a6;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount1.setText(Float.toString(a6));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("INR " +numberToWord(rw)+" Rupees only.");
                add6.setEnabled(false);


            }
        });
        add7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                r7=Float.parseFloat(rate7.getText().toString());
                q7=Float.parseFloat(qty7.getText().toString());
                a7=r7*q7;
                st=st+a7;
                ct=((st/100)*9);
                it=((st/100)*9);
                String formatted = f.format(st);
                st=Float.parseFloat(String.valueOf(formatted));
                String formatted2 = f.format(ct);
                ct=Float.parseFloat(String.valueOf(formatted2));
                String formatted3 = f.format(it);
                it=Float.parseFloat(String.valueOf(formatted3));
                t=st+ct+it;
                gt=Math.round(t);
                String formatted5 = f.format(gt);
                ro=gt-t;
                String formatted4 = f.format(ro);
                ro=Float.parseFloat(String.valueOf(formatted4));
                amount1.setText(Float.toString(a7));
                subtotal.setText(Float.toString(st));
                cgst.setText(Float.toString(ct));
                igst.setText(Float.toString(it));
                roundoff.setText(Float.toString(ro));
                gtotal.setText(formatted5);
                int rw=(int) gt;
                rupee.setText("INR " +numberToWord(rw)+" Rupees only.");
                add7.setEnabled(false);


            }
        });


        generatebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String dbclientname = billname.getText().toString();
                String dbclientaddress = billaddress.getText().toString();
                String dbclientgst = gst.getText().toString();
                String dbdate = dateobj.toString();
                String dbitem1 = item1.getText().toString();
                int dbhsn1 = Integer.parseInt(String.valueOf(hsn1));
                int dbpo1 = Integer.parseInt(String.valueOf(po1));
                int dbdc1 = Integer.parseInt(String.valueOf(dc1));
                int dbrate1 = Integer.parseInt(String.valueOf(rate1));
                int dbqty1 = Integer.parseInt(String.valueOf(qty1));
                int dbamount1 = Integer.parseInt(String.valueOf(amount1));
                String dbitem2 = item2.getText().toString();
                int dbhsn2 = Integer.parseInt(String.valueOf(hsn2));
                int dbpo2 = Integer.parseInt(String.valueOf(po2));
                int dbdc2 = Integer.parseInt(String.valueOf(dc2));
                int dbrate2 = Integer.parseInt(String.valueOf(rate2));
                int dbqty2 = Integer.parseInt(String.valueOf(qty2));
                int dbamount2 = Integer.parseInt(String.valueOf(amount2));
                String dbitem3 = item3.getText().toString();
                int dbhsn3 = Integer.parseInt(String.valueOf(hsn3));
                int dbpo3 = Integer.parseInt(String.valueOf(po3));
                int dbdc3 = Integer.parseInt(String.valueOf(dc3));
                int dbrate3 = Integer.parseInt(String.valueOf(rate3));
                int dbqty3 = Integer.parseInt(String.valueOf(qty3));
                int dbamount3 = Integer.parseInt(String.valueOf(amount3));
                String dbitem4 = item4.getText().toString();
                int dbhsn4 = Integer.parseInt(String.valueOf(hsn4));
                int dbpo4 = Integer.parseInt(String.valueOf(po4));
                int dbdc4 = Integer.parseInt(String.valueOf(dc4));
                int dbrate4 = Integer.parseInt(String.valueOf(rate4));
                int dbqty4 = Integer.parseInt(String.valueOf(qty4));
                int dbamount4 = Integer.parseInt(String.valueOf(amount4));
                String dbitem5 = item5.getText().toString();
                int dbhsn5 = Integer.parseInt(String.valueOf(hsn5));
                int dbpo5 = Integer.parseInt(String.valueOf(po5));
                int dbdc5 = Integer.parseInt(String.valueOf(dc5));
                int dbrate5 = Integer.parseInt(String.valueOf(rate5));
                int dbqty5 = Integer.parseInt(String.valueOf(qty5));
                int dbamount5 = Integer.parseInt(String.valueOf(amount5));
                String dbitem6 = item6.getText().toString();
                int dbhsn6 = Integer.parseInt(String.valueOf(hsn6));
                int dbpo6 = Integer.parseInt(String.valueOf(po6));
                int dbdc6 = Integer.parseInt(String.valueOf(dc6));
                int dbrate6 = Integer.parseInt(String.valueOf(rate6));
                int dbqty6 = Integer.parseInt(String.valueOf(qty6));
                int dbamount6 = Integer.parseInt(String.valueOf(amount6));
                String dbitem7 = item7.getText().toString();
                int dbhsn7 = Integer.parseInt(String.valueOf(hsn7));
                int dbpo7 = Integer.parseInt(String.valueOf(po7));
                int dbdc7 = Integer.parseInt(String.valueOf(dc7));
                int dbrate7 = Integer.parseInt(String.valueOf(rate7));
                int dbqty7 = Integer.parseInt(String.valueOf(qty7));
                int dbamount7 = Integer.parseInt(String.valueOf(amount7));
                int dbsubtotal = Integer.parseInt(String.valueOf(subtotal));
                int dbcgst = Integer.parseInt(String.valueOf(cgst));
                int dbigst = Integer.parseInt(String.valueOf(igst));
                int dbroundoff = Integer.parseInt(String.valueOf(roundoff));
                int dbgtotal = Integer.parseInt(String.valueOf(gtotal));*/


                if(billno.getText().length()==0 || billname.getText().length()==0){
                    Toast.makeText(MainActivity.this,"Client Name & Bill No. is Required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"PDF Created",Toast.LENGTH_SHORT).show();

                    createBill();
                }
            }
        });
    }

    private static String numberToWord(int number) {
        // variable to hold string representation of number
        String words = "";
        String unitsArray[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                "Eighteen", "Nineteen" };
        String tensArray[] = { "Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty",
                "Sixty", "Seventy", "Eighty", "Ninety" };

        if (number == 0) {
            return "Zero";
        }
        // add minus before conversion if the number is less than 0
        if (number < 0) {
            // convert the number to a string
            String numberStr = "" + number;
            // remove minus before the number
            numberStr = numberStr.substring(1);
            // add minus before the number and convert the rest of number
            return "Minus " + numberToWord(Integer.parseInt(numberStr));
        }
        // check if number is divisible by 1 million
        if ((number / 100000) > 0) {
            words += numberToWord(number / 100000) + " Lakh ";
            number %= 100000;
        }
        // check if number is divisible by 1 thousand
        if ((number / 1000) > 0) {
            words += numberToWord(number / 1000) + " Thousand ";
            number %= 1000;
        }
        // check if number is divisible by 1 hundred
        if ((number / 100) > 0) {
            words += numberToWord(number / 100) + " Hundred And ";
            number %= 100;
        }

        if (number > 0) {
            // check if number is within teens
            if (number < 20) {
                // fetch the appropriate value from unit array
                words += unitsArray[number];
            } else {
                // fetch the appropriate value from tens array
                words += tensArray[number / 10];
                if ((number % 10) > 0) {
                    words += "-" + unitsArray[number % 10];
                }
            }
        }
        return words;
    }

    private void createBill() {


        dateobj = new Date();

        PdfDocument myPdfDocument= new PdfDocument();
        Paint mypaint = new Paint();
        Paint titlepaint= new Paint();

        PdfDocument.PageInfo myPageInfo1 =new PdfDocument.PageInfo.Builder(2480,3508,1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
        Canvas canvas =myPage1.getCanvas();

        mypaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(40,40,2448,3400,mypaint);
        canvas.drawRect(40,40,2448,800,mypaint);
        canvas.drawRect(40,800,2448,2000,mypaint);
        canvas.drawRect(40,2000,2448,2700,mypaint);
        canvas.drawRect(40,2700,2448,3100,mypaint);
        canvas.drawRect(40,3100,2448,3400,mypaint);
        mypaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(1700,40,2448,130,mypaint);
        mypaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(1700,280,2448,280,mypaint);
        canvas.drawLine(1700,450,2448,450,mypaint);
        canvas.drawLine(1700,600,2448,600,mypaint);
        canvas.drawLine(1700,700,2448,700,mypaint);
        canvas.drawLine(2050,800,2050,2700,mypaint);
        canvas.drawLine(1750,800,1750,2000,mypaint);
        canvas.drawLine(1500,800,1500,2700,mypaint);
        canvas.drawLine(1300,800,1300,2000,mypaint);
        canvas.drawLine(1100,800,1100,2000,mypaint);
        canvas.drawLine(800,800,800,2000,mypaint);
        canvas.drawLine(1500,2140,2448,2140,mypaint);
        canvas.drawLine(1500,2280,2448,2280,mypaint);
        canvas.drawLine(1500,2420,2448,2420,mypaint);
        canvas.drawLine(1500,2560,2448,2560,mypaint);
        canvas.drawLine(1500,2700,1500,3100,mypaint);
        canvas.drawLine(1700,40,1700,800,mypaint);
        canvas.drawLine(40,700,1700,700,mypaint);
        canvas.drawLine(40,950,2448,950,mypaint);
        canvas.drawLine(40,350,1700,350,mypaint);

        mypaint.setTextSize(100);
        mypaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("ABC PVT LTD", 80,150,mypaint);
        mypaint.setStyle(Paint.Style.FILL);
        mypaint.setTextSize(38);
        canvas.drawText("Software Solution",80,200,mypaint);
        mypaint.setTextSize(38);
        canvas.drawText("123-b, Dubai Street",1200,100,mypaint);
        canvas.drawText("(Opp. Fun Mall)",1200,150,mypaint);
        canvas.drawText("Near Airport",1300,200,mypaint);
        canvas.drawText("Coimbatore-641002",1140,250,mypaint);
        canvas.drawText("E-mail : abcpvtltd@gmail.com",1050,300,mypaint);

        canvas.drawText("Party's Name & Address M/s :",60,420,mypaint);
        mypaint.setTextSize(55);
        canvas.drawText(""+billname.getText(),600,470,mypaint);
        mypaint.setTextSize(38);
        canvas.drawText(""+billaddress.getText(),600,520,mypaint);
        canvas.drawText("Opp. Fun Mall",600,570,mypaint);
        canvas.drawText("Coimbatore - 641002",600,620,mypaint);
        canvas.drawText("GSTIN : ",450,670,mypaint);
        canvas.drawText(""+gst.getText(),600,670,mypaint);

        mypaint.setColor(Color.WHITE);
        mypaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mypaint.setTextSize(55);
        canvas.drawText("INVOICE",1975,100,mypaint);

        mypaint.setColor(Color.BLACK);
        mypaint.setStyle(Paint.Style.FILL);
        mypaint.setTextSize(40);
        canvas.drawText("Cell : 9123456789",1850,200,mypaint);
        canvas.drawText("9876543210",1946,250,mypaint);
        canvas.drawText("GSTIN : 33fgsdfhkvjdbsn",1850,375,mypaint);
        canvas.drawText("Payment - Cash / Credit",1850,550,mypaint);
        canvas.drawText("No. "+billno.getText(),1850,650,mypaint);
        dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        canvas.drawText("DATE : "+dateFormat.format(dateobj),1850,775,mypaint);
        canvas.drawText("Documents Through",70,775,mypaint);

        mypaint.setTextSize(50);
        canvas.drawText("PARTICULARS",200,900,mypaint);
        canvas.drawText("HSN",875,855,mypaint);
        canvas.drawText("CODE",860,910,mypaint);
        canvas.drawText("P.O.No.",1120,900,mypaint);
        canvas.drawText("D.C.No.",1320,900,mypaint);
        canvas.drawText("RATE",1550,900,mypaint);
        canvas.drawText("QUANTITY",1775,900,mypaint);
        canvas.drawText("AMOUNT",2150,855,mypaint);
        canvas.drawText("Rs.",2225,910,mypaint);

        mypaint.setTextSize(40);
        if(item1.getText().length()!=0){
            canvas.drawText(""+item1.getText(),175,1050,mypaint);
            canvas.drawText(""+hsn1.getText(),850,1050,mypaint);
            mypaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(""+po1.getText(),1220,1050,mypaint);
            canvas.drawText(""+dc1.getText(),1420,1050,mypaint);
            canvas.drawText(""+rate1.getText(),1650,1050,mypaint);
            canvas.drawText(""+qty1.getText(),1875,1050,mypaint);
            canvas.drawText(""+amount1.getText(),2250,1050,mypaint);
            canvas.drawLine(40,1100,2448,1100,mypaint);

            if(item2.getText().length()!=0){
                mypaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(""+item2.getText(),175,1200,mypaint);
                canvas.drawText(""+hsn2.getText(),850,1200,mypaint);
                mypaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(""+po2.getText(),1220,1200,mypaint);
                canvas.drawText(""+dc2.getText(),1420,1200,mypaint);
                canvas.drawText(""+rate2.getText(),1650,1200,mypaint);
                canvas.drawText(""+qty2.getText(),1875,1200,mypaint);
                canvas.drawText(""+amount2.getText(),2250,1200,mypaint);
                canvas.drawLine(40,1250,2448,1250,mypaint);

                if(item3.getText().length()!=0){
                    mypaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(""+item3.getText(),175,1350,mypaint);
                    canvas.drawText(""+hsn3.getText(),850,1350,mypaint);
                    mypaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(""+po3.getText(),1220,1350,mypaint);
                    canvas.drawText(""+dc3.getText(),1420,1350,mypaint);
                    canvas.drawText(""+rate3.getText(),1650,1350,mypaint);
                    canvas.drawText(""+qty3.getText(),1875,1350,mypaint);
                    canvas.drawText(""+amount3.getText(),2250,1350,mypaint);
                    canvas.drawLine(40,1400,2448,1400,mypaint);

                    if(item4.getText().length()!=0){
                        mypaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText(""+item4.getText(),175,1500,mypaint);
                        canvas.drawText(""+hsn4.getText(),850,1500,mypaint);
                        mypaint.setTextAlign(Paint.Align.RIGHT);
                        canvas.drawText(""+po4.getText(),1220,1500,mypaint);
                        canvas.drawText(""+dc4.getText(),1420,1500,mypaint);
                        canvas.drawText(""+rate4.getText(),1650,1500,mypaint);
                        canvas.drawText(""+qty4.getText(),1875,1500,mypaint);
                        canvas.drawText(""+amount4.getText(),2250,1500,mypaint);
                        canvas.drawLine(40,1550,2448,1550,mypaint);

                        if(item5.getText().length()!=0){
                            mypaint.setTextAlign(Paint.Align.LEFT);
                            canvas.drawText(""+item5.getText(),175,1650,mypaint);
                            canvas.drawText(""+hsn5.getText(),850,1650,mypaint);
                            mypaint.setTextAlign(Paint.Align.RIGHT);
                            canvas.drawText(""+po5.getText(),1220,1650,mypaint);
                            canvas.drawText(""+dc5.getText(),1420,1650,mypaint);
                            canvas.drawText(""+rate5.getText(),1650,1650,mypaint);
                            canvas.drawText(""+qty5.getText(),1875,1650,mypaint);
                            canvas.drawText(""+amount5.getText(),2250,1650,mypaint);
                            canvas.drawLine(40,1700,2448,1700,mypaint);

                            if(item6.getText().length()!=0){
                                mypaint.setTextAlign(Paint.Align.LEFT);
                                canvas.drawText(""+item6.getText(),175,1800,mypaint);
                                canvas.drawText(""+hsn6.getText(),850,1800,mypaint);
                                mypaint.setTextAlign(Paint.Align.RIGHT);
                                canvas.drawText(""+po6.getText(),1220,1800,mypaint);
                                canvas.drawText(""+dc6.getText(),1420,1800,mypaint);
                                canvas.drawText(""+rate6.getText(),1650,1800,mypaint);
                                canvas.drawText(""+qty6.getText(),1875,1800,mypaint);
                                canvas.drawText(""+amount6.getText(),2250,1800,mypaint);
                                canvas.drawLine(40,1850,2448,1850,mypaint);

                                if(item7.getText().length()!=0){
                                    mypaint.setTextAlign(Paint.Align.LEFT);
                                    canvas.drawText(""+item7.getText(),175,1950,mypaint);
                                    canvas.drawText(""+hsn7.getText(),850,1950,mypaint);
                                    mypaint.setTextAlign(Paint.Align.RIGHT);
                                    canvas.drawText(""+po7.getText(),1220,1950,mypaint);
                                    canvas.drawText(""+dc7.getText(),1420,1950,mypaint);
                                    canvas.drawText(""+rate7.getText(),1650,1950,mypaint);
                                    canvas.drawText(""+qty7.getText(),1875,1950,mypaint);
                                    canvas.drawText(""+amount7.getText(),2250,1950,mypaint);

                                }
                            }
                        }
                    }
                }
            }
        }

        mypaint.setTextAlign(Paint.Align.RIGHT);
        mypaint.setTextSize(55);
        canvas.drawText("Sub Total",2000,2100,mypaint);
        canvas.drawText(""+subtotal.getText(),2400,2100,mypaint);
        canvas.drawText("CGST 9%",2000,2250,mypaint);
        canvas.drawText(""+cgst.getText(),2400,2250,mypaint);
        canvas.drawText("IGST 9%",2000,2390,mypaint);
        canvas.drawText(""+igst.getText(),2400,2390,mypaint);
        canvas.drawText("Round Off",2000,2525,mypaint);
        canvas.drawText(""+roundoff.getText(),2400,2525,mypaint);
        canvas.drawText("Grand Total",2000,2650,mypaint);
        canvas.drawText(""+gtotal.getText(),2400,2650,mypaint);

        mypaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText(""+rupee.getText(),100,2800,mypaint);
        canvas.drawText("For",1575,2800,mypaint);
        mypaint.setTextSize(75);
        canvas.drawText("ABC PVT LTD",1675,2800,mypaint);

        mypaint.setTextSize(45);
        mypaint.setUnderlineText(true);
        canvas.drawText("TERMS AND CONDITIONS :",60,3200,mypaint);

        mypaint.setTextSize(40);
        mypaint.setUnderlineText(false);
        canvas.drawText("1. Interest @ 24% will be charged for payment after 90 days.",60,3250,mypaint);
        canvas.drawText("2. Shortage or Damage claim will be allowed only if the information",60,3300,mypaint);
        canvas.drawText("   given in written format within 3 days of the receipt of the goods.",60,3350,mypaint);
        canvas.drawText("3. Payment by Crossed & Order Cheque is requested",1400,3200,mypaint);
        canvas.drawText("4. Goods once sold cannot be taken back.",1400,3250,mypaint);
        canvas.drawText("5. Subjected to Sathyamangalam Jurisdiction only.",1400,3300,mypaint);
        canvas.drawText("6. No Tax against Form H.",1400,3350,mypaint);









        myPdfDocument.finishPage(myPage1);
        String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file= new File(path,""+billname.getText()+".pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myPdfDocument.close();

    }
}
