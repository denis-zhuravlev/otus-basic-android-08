package michaelborisov.dagger;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import michaelborisov.dagger.heater.ElectricHeater;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ElectricHeater heater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HeaterComponent component = DaggerHeaterComponent.builder().build();
                ElectricHeater mHeater = component.provideHeater();
                mHeater.assemble();
            }
        });

        HeaterComponent component = DaggerHeaterComponent.builder().build();
        ElectricHeater mHeater = component.provideHeater();
        component.inject(this);
        heater.assemble();

//        HeaterWrapper wr = new HeaterWrapper();
//        component.inject(wr);
//        wr.assembleReferencedHeater();

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
