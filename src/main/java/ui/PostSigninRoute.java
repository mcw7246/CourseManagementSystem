package ui;

import application.UserManager;
import spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostSigninRoute implements Route
{
  static final String VIEW_NAME = "signin.ftl";

  //Attributes
  static final String TITLE_ATTR = "title";

  //Params
  static final String USERNAME_PARAM = "username";
  static final String PASSWORD_PARAM = "password";

  final TemplateEngine templateEngine;
  final UserManager userManager;

  public PostSigninRoute(final TemplateEngine templateEngine, UserManager userManager){
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine must not be null.");
    this.userManager = Objects.requireNonNull(userManager, "userManager must not be null");
  }

  public Object handle(Request request, Response response){
    final Session session = request.session();

    Map<String, Object> vm = new HashMap<>();

    vm.put(TITLE_ATTR, "Signin");


    //retrieves what the user put in for the username and password fields
    final String username = request.queryParams(USERNAME_PARAM);
    final String password = request.queryParams(PASSWORD_PARAM);

    //a username was entered into the field
    if(username != null){
      try{
        //there is a record of the username that was inputted
        if(userManager.hasRecord(username)){
          //checks if the password matches what the database has
          if(userManager.passwordMatches(username, password)){
            //tells the program that an existing user is signed in
            session.attribute(GetHomeRoute.SIGNIN_ATTR, true);
            session.attribute(GetHomeRoute.USERNAME_ATTR, username);

            response.redirect(WebServer.HOME_URL);
            return "";
          }
        }
        else{
          System.out.println("not found");
        }
      }catch(SQLException e){
        System.out.println(e.getMessage());
      }

    }
    return templateEngine.render(templateEngine.modelAndView(vm, VIEW_NAME));
  }
}
