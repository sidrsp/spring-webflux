package com.sidrsp.springwebflux.controller;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class MyController {

  @GetMapping("hello")
  public Mono<String> hello() {
    Mono<String> greetingMono = getGreeting();
    System.out.println("returning main execution!!");
    return greetingMono;
  }

  private Mono<String> getGreeting() {
    return Mono.just("Hello Sid").delayElement(Duration.ofSeconds(5));
  }

  @GetMapping("getUser")
  public Mono<String> getUserDetails() {

    Mono<String> userDetailMono = getGreeting()
        .zipWith(getUserDetailsFromDB())
        .map(tuple -> tuple.getT1() + tuple.getT2());

    System.out.println("returning main execution!!");
    return userDetailMono;
  }

  private Mono<String> getUserDetailsFromDB() {
    return Mono.just("User1").delayElement(Duration.ofSeconds(3));
  }

}
