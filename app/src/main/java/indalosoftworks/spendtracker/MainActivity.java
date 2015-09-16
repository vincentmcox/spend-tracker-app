package indalosoftworks.spendtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
    Button resetButton, addEnvelopeButton;
    ExpandableListView expListView;
    ExpandableListAdapter adapter;
    SpendApp app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get UI references
        resetButton = (Button) findViewById(R.id.btn_reset);
        addEnvelopeButton = (Button) findViewById(R.id.btn_add_env);
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        app = new SpendApp();
        app = (SpendApp) getApplicationContext();

        //set up expandable list adapter
        adapter = new ExpandableListAdapter(this, app.getHolder(), app);
        expListView.setAdapter(adapter);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get rid of the remaining stuff
            }
        });
        addEnvelopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start the EditActivity
                startActivity(new Intent(getApplicationContext(), AddNewEnvelopeActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
