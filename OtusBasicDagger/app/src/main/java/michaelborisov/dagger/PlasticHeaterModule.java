package michaelborisov.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import michaelborisov.dagger.heater.cord.ElectricCord;
import michaelborisov.dagger.heater.cord.NormalElectricCord;
import michaelborisov.dagger.heater.corpus.HeaterCorpus;
import michaelborisov.dagger.heater.corpus.PlasticHeaterCorpus;

@Module
public class PlasticHeaterModule {

    @Provides
    @Singleton
    HeaterCorpus provideCorpus() {
        return new PlasticHeaterCorpus();
    }

    @Provides
    @Singleton
    ElectricCord provideCord() {
        return new NormalElectricCord();
    }


//    @Provides
//    ElectricHeater provideHeater() {
//        return new ElectricHeater();
//    }



}
