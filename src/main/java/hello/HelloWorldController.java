package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static boolean isHealth = true;

    @RequestMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/health")
    @ResponseBody
    public ResponseEntity<String> check() {

        System.out.println("----------fff--------");
        if (isHealth) {
            return new ResponseEntity<>("Everything is ok1111", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("not healthy", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("/switch-health")
    @ResponseBody
    public ResponseEntity<String> switchHealth() {

        System.out.println("----------switch--------");
        isHealth = !isHealth;

        return new ResponseEntity<>("Everything is ok", HttpStatus.OK);
    }


}
