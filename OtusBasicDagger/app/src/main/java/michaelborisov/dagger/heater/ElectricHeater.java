package michaelborisov.dagger.heater;

import javax.inject.Inject;

import michaelborisov.dagger.heater.cord.ElectricCord;
import michaelborisov.dagger.heater.corpus.HeaterCorpus;

public class ElectricHeater {

    ElectricCord mCord;

    HeaterCorpus mCorpus;

    @Inject
    public ElectricHeater(ElectricCord mCord, HeaterCorpus mCorpus){
        this.mCord = mCord;
        this.mCorpus = mCorpus;
    }

    public void assemble() {
        if (mCord != null && mCorpus != null) {
            System.out.println("Assembled correctly");
            return;
        }
        throw new IllegalArgumentException("Parts of the heater are null");
    }
}
