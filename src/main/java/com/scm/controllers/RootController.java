package com.scm.controllers;


import com.scm.Entities.User;
import com.scm.Helper.Helper;
import com.scm.services.UserService;
import org.springframework.security.core.Authentication;

@ControllerAdvice
public class RootController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    public RootController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
       logger.info("Adding logged in user information to the model");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in: {}", username);
        // database se data ko fetch : get user from db :
        User user = userService.getUserByEmail(username);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);
    }
}
