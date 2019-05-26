package instagramService.controllers;

import instagramService.config.ConfigLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsernameController {

    @RequestMapping(value = "/user")
    public String index() {
        return ConfigLoader.getConfig().getTest();
    }

}
