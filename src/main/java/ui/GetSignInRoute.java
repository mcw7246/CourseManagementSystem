package ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetSignInRoute implements Route
{
  private final TemplateEngine templateEngine;

  static final String VIEW_NAME = "signin.ftl";

  //Attributes
  static final String TITLE_ATTR = "title";

  public GetSignInRoute(TemplateEngine templateEngine){
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine must not be null.");
  }

  @Override
  public Object handle(Request request, Response response){
    final Session session = request.session();
    final Map<String, Object> vm = new HashMap<>();

    vm.put(TITLE_ATTR, "Signin");

    return templateEngine.render(templateEngine.modelAndView(vm, VIEW_NAME));

  }
}
