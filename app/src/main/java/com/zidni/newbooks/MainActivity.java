package com.zidni.newbooks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class MainActivity extends Activity {
    public MainActivity(Context context) {
        this.context = context;
    }

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id){
                // Send intent to SingleViewActivity
                switch (position){
                    case 0:
                        PDFViewAdapter pdfViewAdapter=new PDFViewAdapter();
                        PDFView pdfView=((PDFViewAdapter)context).findViewById(R.id.sajid1);
                        pdfViewAdapter.sajid1(pdfView);
                        break;

                }
            }
        });
    }
}