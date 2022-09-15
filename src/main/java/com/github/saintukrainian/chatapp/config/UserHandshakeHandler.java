package com.github.saintukrainian.chatapp.config;

import com.github.saintukrainian.chatapp.context.UserContext;
import com.github.saintukrainian.chatapp.utils.QueryParser;
import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Slf4j
@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {

  public static final String USER_ID_PARAM = "userId";

  @Override
  protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
      Map<String, Object> attributes) {
    Map<String, String> params = QueryParser.parse(request.getURI().getQuery());
    UserPrincipal currentUser = new UserPrincipal(params.get(USER_ID_PARAM));
    UserContext.login(currentUser);
    return currentUser;
  }
}
