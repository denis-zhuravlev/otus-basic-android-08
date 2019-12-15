package michaelborisov.dagger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import michaelborisov.dagger.heater.ElectricHeater;
import michaelborisov.dagger.heater.cord.ElectricCord;
import michaelborisov.dagger.heater.corpus.HeaterCorpus;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class DaggerMockitoUnit {


    @Inject
    ElectricCord mCord;

    @Inject
    HeaterCorpus mCorpus;


    @Inject
    ElectricHeater mHeater;

    @Before
    public void setUp() {
        TestComponent component = DaggerTestComponent.builder()
                .plasticHeaterModule(new TestModule()).build();
        component.inject(this);
    }

    @Test
    public void testDoSomething() {
        when(mCord.getLength()).thenReturn(0.5f);
        Assert.assertEquals(mCord.getLength(), 0.5f, 0.00001);
        System.out.println(mCord.getLength());
    }

    @Test
    public void testAssembledHeater() {
        when(mCord.getLength()).thenReturn(1.25f);
        when(mCorpus.getMaterial()).thenReturn("Eco plastic");
        mHeater.assemble();
    }

}
