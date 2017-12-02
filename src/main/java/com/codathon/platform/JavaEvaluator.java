package com.codathon.platform;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 */
@Component
public class JavaEvaluator implements Evaluator {

  @Override
  public EvaluationResult<String> evaluate(@RequestBody Evaluation evaluationRequest) throws InvocationTargetException {
    System.out.println("Received a request: " + evaluationRequest);
    StringBuilder finalCode = new StringBuilder(evaluationRequest.getCode());
    String codeToWrite = finalCode.insert(0, "package test;").toString();

    File root = new File("/tmp/code/java/");
    File sourceFile = new File(root, "test/Solution.java");
    sourceFile.getParentFile().mkdirs();
    try {
      Files.write(sourceFile.toPath(), codeToWrite.getBytes(StandardCharsets.UTF_8));
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      compiler.run(null, null, null, sourceFile.getPath());

      URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
      Class<?> cls = Class.forName("test.Solution", true, classLoader); // Should print "hello".
      Object instance = cls.newInstance();
      Method[] methods = cls.getDeclaredMethods();
      for (Method method : methods) {
        if (method.getName().equals("evaluate")) {
          String result = method.invoke(instance,6).toString();
          return new EvaluationResult<>(result);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return new EvaluationResult<>("Internal Server Error: 500");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }

    return new EvaluationResult("Pass");
  }
}
