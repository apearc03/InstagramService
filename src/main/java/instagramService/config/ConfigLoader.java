package instagramService.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

@Component
public class ConfigLoader {

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);
    private static Config config;

    private ConfigLoader(){
    }

    @EventListener(ApplicationReadyEvent.class)
    private void loadConfig() {
        final Yaml yaml = new Yaml(new Constructor(Config.class));
        final InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yml");
        config = yaml.load(inputStream);
        LOGGER.info("Config loaded.");
    }

    public static Config getConfig() {
        return config;
    }
}
