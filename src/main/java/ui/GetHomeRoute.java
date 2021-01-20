package ui;

import freemarker.template.Template;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetHomeRoute implements Route
{
  //view name
  static final String VIEW_NAME = "home.ftl";

  //Attributes
  static final String TITLE_ATTR = "title";
  static final String SIGNIN_ATTR = "signin";
  static final String USERNAME_ATTR = "username";

  static final String TITLE = "Course Management System";
  private final TemplateEngine templateEngine;
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  public GetHomeRoute(final TemplateEngine templateEngine){
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine must not be null.");
    LOG.config("GetHomeRoute is initialized.");
  }

  @Override
  public Object handle(Request request, Response response){
    final Session session = request.session();

    final Map<String, Object> vm = new HashMap<>();
    vm.put(TITLE_ATTR, TITLE);
    if(session.attribute(SIGNIN_ATTR) == null){
      session.attribute(SIGNIN_ATTR, false);
    }
    if(session.attribute(SIGNIN_ATTR)){
      vm.put(SIGNIN_ATTR, true);
    }

    return templateEngine.render(templateEngine.modelAndView(vm, VIEW_NAME));
  }
}
