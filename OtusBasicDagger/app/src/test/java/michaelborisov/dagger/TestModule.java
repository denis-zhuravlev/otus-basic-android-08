package michaelborisov.dagger;

import org.mockito.Mockito;

import michaelborisov.dagger.heater.cord.ElectricCord;
import michaelborisov.dagger.heater.corpus.HeaterCorpus;

public class TestModule extends PlasticHeaterModule {

    @Override
    ElectricCord provideCord() {
        return Mockito.mock(ElectricCord.class);
    }

    @Override
    HeaterCorpus provideCorpus() {
        return Mockito.mock(HeaterCorpus.class);
    }
}
