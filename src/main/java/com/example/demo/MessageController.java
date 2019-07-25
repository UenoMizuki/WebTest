package com.example.demo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import code.Test;

@Controller
public class MessageController {

    @Autowired
    private MessageService service;
    
    @ModelAttribute("test")
    public Test test() {
    	return new Test();
    }
    
    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("messageForm", new MessageForm());

        List<Message> messages = service.getRecentMessages(100);
        model.addAttribute("test", messages);

        return "test";
    }
    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messageForm", new MessageForm());

        List<Message> messages = service.getRecentMessages(100);
        model.addAttribute("messages", messages);

        return "messages";
    }

    @PostMapping("/messages")
    public String messagesPost(Model model, @Valid MessageForm messageForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            List<Message> messages = service.getRecentMessages(100);
            model.addAttribute("messages", messages);
            return "messages";
        }

        service.save(new Message(messageForm.getName(), messageForm.getText(), request.getRemoteAddr()));
        System.out.println(request.getRemoteAddr());
        return "redirect:/messages";
    }

}