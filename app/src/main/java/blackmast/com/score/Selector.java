package blackmast.com.score;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

/**
 * This class is responsible for the main Score activity.
 */
public class Selector extends Activity {

    /**
     * @var GridView for selectors
     */
    public GridView selector;

    /**
     * @var TextView display
     */
    public TextView display;

    /**
     * @var Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // set orientation to portrait
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // set these views for reference later
        this.selector = (GridView)findViewById(R.id.selector);
        this.display = (TextView)findViewById(R.id.display);

        // bind the click listener to the large score card.
        // This triggers showSelector() which displays the list of small numbers
        this.display.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showSelector();
            }
        });

        // add the click listener and adapter to the grid view
        this.selector.setAdapter(new SelectorList(this));

        // bind the click listener to the small list item. This causes the large number to appear.
        this.selector.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                showDisplay();
                TextView tv = (TextView) v;
                String text = tv.getText().toString();
                int textLength = text.length();

                if (textLength == 1) {
                    display.setTextSize(300);
                } else {
                    display.setTextSize(200);
                }

                display.setText(text);
            }
        });

    }

    /**
     * Retreats out of fullscreen and activates the "selector" layout
     */
    public void showSelector() {
        // tell screen to stop staying on
        this.display.setVisibility(View.GONE);
        this.selector.setVisibility(View.VISIBLE);
    }

    /**
     * Goes fullscreen and activates the "display" layout
     */
    public void showDisplay() {
        // Tell screen to stay on
        this.selector.setVisibility(View.GONE);
        this.display.setVisibility(View.VISIBLE);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings_list, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_update) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blackmast.org/apps/Score.apk"));
            startActivity(browserIntent);
        }
        return true;
    }

}
