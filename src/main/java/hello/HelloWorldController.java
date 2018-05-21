package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static boolean isHealth = true;

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<String> check() {

        System.out.println("----------fff--------");
        if (isHealth) {
            return new ResponseEntity<>("Everything is ok1111", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not healthy", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/switch-health")
    @ResponseBody
    public ResponseEntity<String> switchHealth() {

        System.out.println("----------switch--------");
        isHealth = !isHealth;

        return new ResponseEntity<>("Everything is ok", HttpStatus.OK);
    }


}
