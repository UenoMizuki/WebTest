package com.example.demo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Configurations;
import com.example.demo.domain.ConfigurationsRepository;

import code.Test;

@Controller
public class MessageController {
	@Autowired
	ConfigurationsRepository configurationsRepository;
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
    @GetMapping("/shooting")
    public String shooting(Model model) {
    	model.addAttribute("messageForm", new MessageForm());

        List<Message> messages = service.getRecentMessages(100);
        //htmlを登録
        model.addAttribute("shooting", messages);
        //html
        return "shooting";
    }
    @GetMapping("/erukku")
    public String erukku(Model model) {
        model.addAttribute("messageForm", new MessageForm());

        List<Message> messages = service.getRecentMessages(100);
        //htmlを登録
        model.addAttribute("erukku", messages);
        //html
        return "erukku";
    }
    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messageForm", new MessageForm());

        List<Message> messages = service.getRecentMessages(100);
        model.addAttribute("messages", messages);
        //////
        System.out.println("[START] データベースに接続してデータを取得します。");
        Page<Configurations> configs = configurationsRepository.findAll(PageRequest.of(0, 10));
        for (Configurations config : configs) {
            System.out.println(config.getName() + " = " + config.getValue());
        }
        System.out.println("[END  ] データベースに接続してデータを取得します。");
        //////
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