package io.security.basicsecurity.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController{
    @GetMapping(value="/messages")
    public String messages()  {

        return "user/messages";
    }
    @PostMapping(value="/api/messages")
    public ResponseEntity<String> apiMessages()throws Exception{
        return ResponseEntity.ok().body("ok");
    }
}
