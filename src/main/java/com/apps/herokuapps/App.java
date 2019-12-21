package com.apps.herokuapps;

import freemarker.template.Configuration;

import static spark.Spark.get;
import static spark.Spark.port;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        final Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(App.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello World");
    }
}
