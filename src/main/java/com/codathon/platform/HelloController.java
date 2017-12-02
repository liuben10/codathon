package com.codathon.platform;

import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RequestMapping("/api")
public class HelloController {

  @Data
  public static class Result<T> {
      private T body;

      public Result(T body) {
        this.body = body;
      }
  }

  @Before(
          @BeforeElement(value = RequestLoggingInterceptor.class)
  )
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/v0/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Result<String> hello() {
    System.out.println("Received a request");
    return new Result("Hello");
  }
}
