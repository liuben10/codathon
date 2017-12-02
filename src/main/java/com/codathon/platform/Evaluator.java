package com.codathon.platform;

import org.springframework.web.bind.annotation.RequestBody;
import java.lang.reflect.InvocationTargetException;

/**
 */
public interface Evaluator {
  EvaluationResult<String> evaluate(@RequestBody Evaluation evaluationRequest) throws InvocationTargetException;
}
