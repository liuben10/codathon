package com.codathon.platform;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 */
@Data
@NoArgsConstructor
public class EvaluationResult<T> {
  private T body;

  public EvaluationResult(T body) {
    this.body = body;
  }
}
