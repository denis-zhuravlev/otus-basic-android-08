package michaelborisov.dagger;

import javax.inject.Inject;

import michaelborisov.dagger.heater.ElectricHeater;

public class HeaterWrapper {

    @Inject
    ElectricHeater mHeater;

    void assembleReferencedHeater(){
        mHeater.assemble();
    }
}
