package acmr.springframework.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/slave")
public class SlaveController {

    @RequestMapping("/main")
    public String toSlave() {
        return "slave/index";
    }
}
