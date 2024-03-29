package com.thoughtmechanix.licenses.utils;

import org.springframework.util.Assert;

public class UserContextHolder {

  public static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

  public static final UserContext getContext() {
    UserContext context = userContext.get();

    if (context == null) {
      context = createEmptyContext();
      userContext.set(context);
    }

    return userContext.get();
  }

  public static final UserContext createEmptyContext() {
    return new UserContext();
  }

  public static final void setContext(UserContext context) {
    Assert.notNull(context, "Only non-null UserContext instance are permitted");
    userContext.set(context);
  }
}
