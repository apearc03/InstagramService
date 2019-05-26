package instagramService.controllers;

import instagramService.config.Config;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class UsernameController {

    @Inject
    Config config;

    @RequestMapping(value = "/user")
    public String getUser() {
        return config.getTest();
    }

}
