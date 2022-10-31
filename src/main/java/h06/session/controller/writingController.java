package h06.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class writingController {

    @GetMapping("/")
    public String hi() {
        return "hi";
    }

}
