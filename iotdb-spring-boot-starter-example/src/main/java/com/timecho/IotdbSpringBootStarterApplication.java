package com.timecho;

import com.timecho.example.A;
import com.timecho.example.ARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IotdbSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotdbSpringBootStarterApplication.class, args);
    }

    @Autowired
    ARepository aRepository;

    @GetMapping("test")
    public String test(){
        A a = new A();
        a.setDevice("device1");
        a.setSensor("sensor1");
        a.setTimeStamp(System.currentTimeMillis());
        aRepository.save(a);
        return null;
    }
}

