package ui;

import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.*;

public class WebServer
{
  private static final Logger LOG = Logger.getLogger(WebServer.class.getName());

  public static final String HOME_URL = "/";
  public static final String SIGNIN_URL = "/signin";

  private final TemplateEngine templateEngine;

  public WebServer(final TemplateEngine templateEngine){
    Objects.requireNonNull(templateEngine, "templateEngine must not be null.");
    this.templateEngine = templateEngine;
  }

  public void initialize(){
    staticFileLocation("/public");

    get(HOME_URL, new GetHomeRoute(templateEngine));
    get(SIGNIN_URL, new GetSignInRoute(templateEngine));
    post(SIGNIN_URL, new PostSigninRoute(templateEngine));
  }
}
