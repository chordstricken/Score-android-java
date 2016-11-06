package blackmast.com.score;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * This class is responsible for generating a list of clickable numbers from a list.
 * It is the first visible view on opening the App.
 */
public class SelectorList extends BaseAdapter {

    /**
     * @var Context
     */
    private Context mContext;

    /**
     * @var int color of the test
     */
    public int colorText;

    /**
     * @var int color of background
     */
    public int colorBackground;

    /**
     * @var int color of each item
     */
    public int colorItem;

    /**
     * List of score options
     * @var String[]
     */
    private String[] _options = {"0", "1", "2", "3", "5", "8", "13", "21", "34", "?", "♨"};

    /**
     * @param Context c
     */
    public SelectorList(Context c) {
        this.mContext = c;

        this.colorText       = c.getResources().getColor(R.color.text_color);
        this.colorBackground = c.getResources().getColor(R.color.background_color);
        this.colorItem       = c.getResources().getColor(R.color.list_item_color);
    }

    /**
     * @return int this._options.length
     */
    public int getCount() {
        return this._options.length;
    }

    /**
     * @param int position
     * @return Object null;
     */
    public Object getItem(int position) {
        return null;
    }

    /**
     * @param int position
     * @return long 0
     */
    public long getItemId(int position) {
        return 0;
    }


    /**
     * create a new ImageView for each item referenced by the Adapter
     * @param int optionIndex
     * @param View convertView
     * @param ViewGroup parent
     */
    public View getView(int optionIndex, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = new TextView(this.mContext);
            GradientDrawable shape = new GradientDrawable();

            shape.setColor(this.colorItem);
            shape.setCornerRadius(20);

            textView.setText(this._options[optionIndex]);
            textView.setBackground(shape);
            textView.setTextColor(this.colorText);

            textView.setTextSize(28);
            textView.setCursorVisible(false);
            textView.setGravity(0x11);

            textView.setLayoutParams(new GridView.LayoutParams(150, 150));
            textView.setPadding(0, 15, 0, 15);
        } else {
            textView = (TextView) convertView;
        }

        return textView;
    }
}
