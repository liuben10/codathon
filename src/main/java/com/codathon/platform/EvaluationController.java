package com.codathon.platform;

import com.kastkode.springsandwich.filter.annotation.Before;
import com.kastkode.springsandwich.filter.annotation.BeforeElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.lang.reflect.InvocationTargetException;

/**
 */
@RestController
@RequestMapping("/api")
public class EvaluationController {

  private Evaluator evaluator;

  public EvaluationController(@Autowired Evaluator evaluator) {
    this.evaluator = evaluator;
  }

  @Before(
          @BeforeElement(value = RequestLoggingInterceptor.class)
  )
  @CrossOrigin(origins = "*")
  @RequestMapping(value = "/v0/evaluation", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
  public EvaluationResult<String> evaluation(@RequestBody Evaluation evaluationRequest) throws InvocationTargetException {
    return evaluator.evaluate(evaluationRequest);
  }
}
