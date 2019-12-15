package michaelborisov.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import michaelborisov.dagger.heater.cord.ElectricCord;
import michaelborisov.dagger.heater.cord.NormalElectricCord;
import michaelborisov.dagger.heater.corpus.ExpensivePlasticHeaterCorpus;
import michaelborisov.dagger.heater.corpus.HeaterCorpus;
import michaelborisov.dagger.heater.corpus.PlasticHeaterCorpus;

@Module
public class PlasticHeaterModule {

    private int priceCategory;

    PlasticHeaterModule() {
        priceCategory = 0;
    }

    PlasticHeaterModule(int category) {
        priceCategory = category;
    }

    @Provides
    @Singleton
    HeaterCorpus provideCorpus() {
        if (this.priceCategory == 0) {
            return new PlasticHeaterCorpus();
        } else {
            return new ExpensivePlasticHeaterCorpus();
        }
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
