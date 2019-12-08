package michaelborisov.dagger.heater.cord;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class NormalElectricCord implements ElectricCord {

    @Inject
    public NormalElectricCord(){};

    @Override
    public float getLength() {
        return 2.5f;
    }
}
