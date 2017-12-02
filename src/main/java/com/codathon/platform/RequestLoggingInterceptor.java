package com.codathon.platform;

import com.kastkode.springsandwich.filter.api.BeforeHandler;
import com.kastkode.springsandwich.filter.api.Flow;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 */

@Log
@Component
public class RequestLoggingInterceptor implements BeforeHandler {


  @Override
  public Flow handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod, String[] strings) throws Exception {
    final Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
    List<String> headers = new ArrayList<>();
    while(headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      headers.add(headerName);
    }
    log.info("Incoming request headers = {" + headers + "}");
//    final String body = IOUtils.toString(httpServletRequest.getInputStream(), Charset.defaultCharset());
    return Flow.CONTINUE;
  }
}
