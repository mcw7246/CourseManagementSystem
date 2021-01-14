import com.google.gson.Gson;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;
import ui.WebServer;

import java.io.InputStream;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Application
{
  private static final Logger LOG = Logger.getLogger(Application.class.getName());
  private final WebServer webServer;

  private Application(final WebServer webServer){
    Objects.requireNonNull(webServer, "webServer must not be null.");
    this.webServer = webServer;
  }

  public void initialize(){
    LOG.config("Course Management Sytsem is initializing.");
    webServer.initialize();
    LOG.config("Course Management System initialization complete.");
  }

  public static void main(String[] args){
    try{
      ClassLoader classLoader = Application.class.getClassLoader();
      final InputStream logConfig = classLoader.getResourceAsStream("log.properties");
      LogManager.getLogManager().readConfiguration(logConfig);
    }catch(Exception e){
      e.printStackTrace();
      System.err.println("could not initialize log manager because: " + e.getMessage());
    }

    final TemplateEngine templateEngine = new FreeMarkerEngine();

    final Gson gson = new Gson();

    final WebServer webServer = new WebServer(templateEngine);

    final Application app = new Application(webServer);
    app.initialize();

  }
}
