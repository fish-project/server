package src.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestController {
    @GetMapping("/api/bro")
    public String getMethodName() {
        return "hello";
    }
}
