package michaelborisov.dagger;

import javax.inject.Singleton;

import dagger.Component;
import michaelborisov.dagger.heater.ElectricHeater;

@Singleton
@Component(modules = PlasticHeaterModule.class)
public interface HeaterComponent {
    ElectricHeater provideHeater();

    void inject(MainActivity act);

    void inject(HeaterWrapper wr);
}
