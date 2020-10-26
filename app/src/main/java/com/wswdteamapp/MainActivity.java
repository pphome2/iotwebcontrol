package com.wswdteamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static String wurl = "https://pphome2.github.io";
    public static String ourl = "";
    public static WebViewClient myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.maintitle);

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        String str=sp.getString("def_url", "@string/button");

        if (str.isEmpty()) {
            SharedPreferences.Editor edt = sp.edit();
            edt.putString("def_url", "@string/def_url");
            edt.commit();
        } else {

        }

        setButton(this);

        Button mButton = (Button) this.findViewById(R.id.button1);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button2);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button3);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button4);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button5);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button6);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button7);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button8);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button9);
        mButton.setOnClickListener(this);
        mButton = (Button) this.findViewById(R.id.button10);
        mButton.setOnClickListener(this);

        WebView myWebView = (WebView) findViewById(R.id.owebv);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSafeBrowsingEnabled(true);
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebView.setScrollbarFadingEnabled(false);
        myWebView.setBackgroundColor(Color.parseColor("#afafaf"));
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setButton(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu smenu) {
        getMenuInflater().inflate(R.menu.menu_items, smenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.m_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.m_dev:
                intent = new Intent(this, WebActivity.class);
                startActivity(intent);
                return true;
            case R.id.m_otherweb:
                intent = new Intent(this, OtherWebActivity.class);
                startActivity(intent);
                return true;
            case R.id.m_quit:
                quitMenuApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        String str2 = "";

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        WebView myWebView = (WebView) findViewById(R.id.owebv);
        String str;
        str = sp.getString("def_url", "");
        switch (v.getId()){
            case R.id.button1:
                str2 = sp.getString("edit_url_1", "");
                break;
            case R.id.button2:
                str2 = sp.getString("edit_url_2", "");
                break;
            case R.id.button3:
                str2 = sp.getString("edit_url_3", "");
                break;
            case R.id.button4:
                str2 = sp.getString("edit_url_4", "");
                break;
            case R.id.button5:
                str2 = sp.getString("edit_url_5", "");
                break;
            case R.id.button6:
                str2 = sp.getString("edit_url_6", "");
                break;
            case R.id.button7:
                str2 = sp.getString("edit_url_7", "");
                break;
            case R.id.button8:
                str2 = sp.getString("edit_url_8", "");
                break;
            case R.id.button9:
                str2 = sp.getString("edit_url_9", "");
                break;
            case R.id.button10:
                str2 = sp.getString("edit_url_0", "");
                break;
            default:
                str2 = "";
                break;
        }
        if ((!str2.startsWith("http://")) && (!str2.startsWith("https://"))) {
            str = str + str2;
        } else {
            str = str2;
        }
        if (str.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.no_other_url, Toast.LENGTH_SHORT);
            toast.setMargin(50, 50);
            toast.show();
        } else {
            myWebView.loadUrl(str);
        }
    }



    private void setButton(AppCompatActivity th){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(th);
        String ou = sp.getString("other_page_url", "");
        if (!ou.isEmpty()){
                if ((!ou.startsWith("https://")) && (!ou.startsWith("http://"))){
                    String ou2 = sp.getString("def_url", "");
                    MainActivity.ourl = ou2 + ou;
                }
            }
        String str = sp.getString("edit_b_1", "");
        String str2 = sp.getString("edit_url_1", "");
        Button mButton = (Button) th.findViewById(R.id.button1);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_2", "");
        str2 = sp.getString("edit_url_2", "");
        mButton = (Button) th.findViewById(R.id.button2);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_3", "");
        str2 = sp.getString("edit_url_3", "");
        mButton = (Button) th.findViewById(R.id.button3);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_4", "");
        str2 = sp.getString("edit_url_4", "");
        mButton = (Button) th.findViewById(R.id.button4);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_5", "");
        str2 = sp.getString("edit_url_5", "");
        mButton = (Button) th.findViewById(R.id.button5);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_6", "");
        str2 = sp.getString("edit_url_6", "");
        mButton = (Button) th.findViewById(R.id.button6);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_7", "");
        str2 = sp.getString("edit_url_7", "");
        mButton = (Button) th.findViewById(R.id.button7);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_8", "");
        str2 = sp.getString("edit_url_8", "");
        mButton = (Button) th.findViewById(R.id.button8);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_9", "");
        str2 = sp.getString("edit_url_9", "");
        mButton = (Button) th.findViewById(R.id.button9);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }
        str=sp.getString("edit_b_0", "");
        str2 = sp.getString("edit_url_0", "");
        mButton = (Button) th.findViewById(R.id.button10);
        if ((!str.isEmpty() )&&(!str2.isEmpty())) {
            mButton.setVisibility(VISIBLE);
            mButton.setText(str);
        }else{
            mButton.setVisibility(INVISIBLE);
        }

        WebView myWebView = (WebView) findViewById(R.id.owebv);
        str = sp.getString("def_url", "");
        if (!str.endsWith("/")) {
            str2 = str + "/";
        } else {
            str2 = str;
        }
        if ((!str2.startsWith("http://")) && (!str2.startsWith("https://"))){
            str2 = "https://" + str2;
        }
        if (str != str2){
            SharedPreferences.Editor edt = sp.edit();
            edt.putString("def_url", str2);
            edt.commit();
            str = sp.getString("def_url", "");
        }
        if (sp.getBoolean("welcome_page",false)) {
            str2 = sp.getString("welcome_url", "");
            if (str2.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_other_url, Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();
            } else {
                if ((!str2.startsWith("https://")) && (!str2.startsWith("http://"))){
                    str2 = str + str2;
                }
                Toast toast = Toast.makeText(getApplicationContext(), str2, Toast.LENGTH_SHORT);
                toast.setMargin(50, 50);
                toast.show();
                myWebView.loadUrl(str2);
            }
        }
    }

    public void quitApp(View v) {
        finishAndRemoveTask();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);

    }

    private void quitMenuApp() {
        finishAndRemoveTask();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }


}
