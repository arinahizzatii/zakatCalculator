package com.example.zakatcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class activity_aboutpage extends AppCompatActivity
{
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutpage);
        textView = findViewById(R.id.textViewLink);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        gitHub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goLink("https://github.com/arinahizzatii");
            }//close onClick
        });
    }
    public void openHomeActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void goLink(String Link)
    {
        Uri uri = Uri.parse(Link);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}