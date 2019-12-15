package michaelborisov.dagger;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = PlasticHeaterModule.class)
public interface TestComponent extends HeaterComponent {

    void inject(DaggerMockitoUnit test);
}
