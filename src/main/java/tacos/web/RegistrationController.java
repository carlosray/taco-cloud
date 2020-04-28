package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.model.RegistrationForm;
import tacos.repo.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private PasswordEncoder encoder;
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        userRepository.save(form.toUser(encoder));
        return "redirect:/login";
    }

}
