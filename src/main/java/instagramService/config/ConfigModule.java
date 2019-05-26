package instagramService.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.inject.Singleton;
import java.io.InputStream;

@Configuration
public class ConfigModule {

    @Bean
    @Singleton
    public Config provideConfig() {
        final Yaml yaml = new Yaml(new Constructor(Config.class));
        final InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");
        return yaml.load(inputStream);
    }

}
