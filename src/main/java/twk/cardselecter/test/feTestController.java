package twk.cardselecter.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class feTestController {

    @GetMapping
    public FeTest testLogin(){
        return new FeTest("test", "test1234", "name");
    }
}
