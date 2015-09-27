package ashfox.nextgenauthentication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ashfox.nextgenauthentication.util.StatCollector;
import ashfox.nextgenauthentication.util.Storage;

public class Metrics extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

        TextView tv = (TextView) findViewById(R.id.metrics_text);
        tv.append("Training set size = " + String.valueOf(Storage.getHistory().size()) + "\n");
        tv.append("Trials: " + String.valueOf(StatCollector.getTrials()) + "\n");
        tv.append("Correct: " + String.valueOf(StatCollector.getSuccess()) + "\n");
        tv.append("False Positives: " + String.valueOf(StatCollector.getFalse_pos()) + "\n");
        tv.append("False Negatives: " + String.valueOf(StatCollector.getFalse_neg()) + "\n");
        tv.append("Accuracy: " +
                String.valueOf(StatCollector.getSuccess() * 100 / (1.0 * StatCollector.getTrials())) + "% \n");
        tv.append("False positive %: " +
                String.valueOf(StatCollector.getFalse_pos() * 100 / (1.0 * StatCollector.getTrials())) + "% \n");
        tv.append("False negative %: " +
                String.valueOf(StatCollector.getFalse_neg() * 100 / (1.0 * StatCollector.getTrials())) + "% \n");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_metrics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
