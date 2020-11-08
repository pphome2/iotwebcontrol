package com.wswdteamapp;

        import androidx.appcompat.app.ActionBar;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Color;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.webkit.WebResourceError;
        import android.webkit.WebResourceRequest;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.Toast;

public class OtherWebActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_other_web);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setTitle(R.string.other_web_page);
            }

            WebView myWebView = (WebView) findViewById(R.id.otherwebv);
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setSafeBrowsingEnabled(true);
            myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            myWebView.setBackgroundColor(Color.parseColor("#afafaf"));
            myWebView.setScrollbarFadingEnabled(false);

            myWebView.setWebViewClient(new WebViewClient(){
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.URL_error, Toast.LENGTH_SHORT);
                    toast.show();
                    view.loadUrl("about:blank");
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    final Uri uri = request.getUrl();
                    if (uri.toString().startsWith("mailto:")) {
                        //Handle mail Urls
                        startActivity(new Intent(Intent.ACTION_SENDTO, uri));
                    } else if (uri.toString().startsWith("tel:")) {
                        //Handle telephony Urls
                        startActivity(new Intent(Intent.ACTION_DIAL, uri));
                    } else {
                        //Handle Web Urls
                        view.loadUrl(uri.toString());
                    }
                    return true;
                }
            });

            String str = MainActivity.ourl;
            if (str.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_other_url, Toast.LENGTH_SHORT);
                toast.show();
            } else{
                myWebView.loadUrl(str);
            }
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
}