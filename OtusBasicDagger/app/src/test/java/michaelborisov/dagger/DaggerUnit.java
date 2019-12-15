package michaelborisov.dagger;

import org.junit.Test;

import michaelborisov.dagger.heater.ElectricHeater;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DaggerUnit {
    @Test
    public void isHeaterAssembledCorrectly() {
        HeaterComponent component = DaggerHeaterComponent.builder().build();
        ElectricHeater mHeater = component.provideHeater();
        mHeater.assemble();
    }

    @Test
    public void assembleCheapHeater() {
        HeaterComponent component = DaggerHeaterComponent
                .builder()
                .plasticHeaterModule(new PlasticHeaterModule(0))
                .build();
        ElectricHeater mHeater = component.provideHeater();
        mHeater.assemble();
    }

    @Test
    public void assembleExpensiveHeater() {
        HeaterComponent component = DaggerHeaterComponent
                .builder()
                .plasticHeaterModule(new PlasticHeaterModule(1))
                .build();
        ElectricHeater mHeater = component.provideHeater();
        mHeater.assemble();
    }


}