package es.esy.practikality.healr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CollabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collab);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WebView wv = (WebView) findViewById(R.id.webView1);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://arnabsagar.typeform.com/to/mJOJAV");
    }
}
