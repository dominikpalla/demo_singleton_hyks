package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Date;

@Controller
public class MyController {

    private Storage storage;

    @Autowired
    public MyController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("storage", storage);
        return "main";
    }

    @PostMapping("/")
    public String save(HttpServletRequest request){
        String name = request.getParameter("name");
        int number = Integer.parseInt(request.getParameter("number"));
        String dateString = request.getParameter("date");

        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format", e);
        }

        storage.name = name;
        storage.number = number;
        storage.date = date;

        return "redirect:/";
    }


}
