package com.zidni.newbooks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class PDFViewAdapter extends Activity implements OnPageChangeListener, OnLoadCompleteListener {


    public static final String PDF_FILE = "Paradoxical_Sajid1.pdf";
    public static final String TAG = PDFViewAdapter.class.getSimpleName();
    public PDFView pdfView;
    Integer pageNumber;
    String pdfFileName;

    Integer currentPageNumber;
    Integer savedPage;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdfviewlist);


        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);

        savedPage = mySharedPreferences.getInt("retrievedPage", 0);

        pageNumber = savedPage;

    }

    public void sajid1() {
        pdfView.findViewById(R.id.sajid1);
        displayFromAsset();

    }


    public void displayFromAsset() {

       pdfView.fromAsset("Paradoxical_Sajid1.pdf")
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();


    }


    @Override
    public void onPageChanged(int page, int pageCount) {

        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));

    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }


    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();


        savedPage = pdfView.getCurrentPage();
        myEditor.putInt("retrievedPage", savedPage);
        myEditor.apply();


    }

}