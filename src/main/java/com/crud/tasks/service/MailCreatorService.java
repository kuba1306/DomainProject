package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.controller.TaskController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskController taskController;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://kuba1306.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_mail", adminConfig.getAdminMail());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", (IContext) context);
    }


    public String buildScheduledMail(String message) {
        message = "at the moment You have got: " + taskController.getTasks().getBody().size() + " tasks in your application";
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("prev_message", "Check Your application!");
        context.setVariable("message2", "At the moment You have got: " + taskController.getTasks().getBody().size()+ " tasks in your application");
        context.setVariable("tasks_url", "https://kuba1306.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_mail", adminConfig.getAdminMail());
        context.setVariable("show_button", true);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail2", context);

    }
}