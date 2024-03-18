package com.example.demo.controller;

import com.example.demo.dao.BuyersrReprository;
import com.example.demo.dao.ItemRepo;
import com.example.demo.enteties.Buyers;
import com.example.demo.enteties.Item;
import com.example.demo.helper.Message;
import com.example.global.GlobalData;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;


@Controller

public class HomeController {
    @Autowired
    private BuyersrReprository buyersrReprository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ItemRepo itemRepo;


   
    @GetMapping("/base")
    public String base(){
        return "Base";
    }
    @GetMapping("/login")
    public  String login(){
        GlobalData.cart.clear();
        return "Login";
    }
    @GetMapping("/sellerForm")
    public String sellerForm(Model m){
        m.addAttribute("Title","Seller Registration");
        return "sellerForm";
    }
    @GetMapping("/buyerSignup")
    public String buyerSignup(Model m)  {
        m.addAttribute("Title","Buyer Registration");

        m.addAttribute("buyer",new Buyers());

        return "SignUP";
    }

    @PostMapping("/buyerHandeler")
    public String buyerHandeler(@Valid @ModelAttribute("buyer") Buyers buyers,@RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model m, BindingResult result,@RequestParam("profileImage") MultipartFile file, HttpSession session) throws IOException {
        try {
            if (result.hasErrors()) {

                return "SignUp";
            }
            if(!agreement) {
                System.out.println("Not checked");
                throw new Exception("You didnot accept terms and conditions");

            }
            buyers.setPassword(bCryptPasswordEncoder.encode(buyers.getPassword()));
            buyers.setRole("ROLE_Buyer");
            if (!file.isEmpty()) {
                buyers.setImage(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/image").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } else {
                buyers.setImage("default.png");
                System.out.println("exception coming from homeController signup form");
            }

            buyersrReprository.save(buyers);
            session.setAttribute("message", new Message("Successfully registered !!", "alert-success"));
        }catch (Exception e){
            session.setAttribute("message", new Message(e.getMessage()+",Please try again.","alert-danger"));
        }
        return "SignUp";
    }
   @GetMapping("/home")
    public String Home(Model m){

       List<Item> allItem = itemRepo.findAllItem();
       m.addAttribute("Title","Home");
       m.addAttribute("items",allItem);

       m.addAttribute("cardCount", GlobalData.cart.size());
       return "Home";
    }

    @GetMapping("/gadet")
    public String showGadet(Model m, Principal principal){
        m.addAttribute("Title","Gadet");
        List<Item> gadet = itemRepo.findItemByCategory("Gadet");
        m.addAttribute("items",gadet);
        return "normal/categoryItem";
    }
    @GetMapping("/jewe")
    public String showJewe(Model m, Principal principal){
        m.addAttribute("Title","Jewelaries");
        List<Item> jewelary = itemRepo.findItemByCategory("Jewelary");
        m.addAttribute("items",jewelary);
        return "normal/categoryItem";
    }
    @GetMapping("/cars")
    public String showCar(Model m, Principal principal){
        m.addAttribute("Title","Cars");
        List<Item> cars = itemRepo.findItemByCategory("Cars");
        m.addAttribute("items",cars);
        return "normal/categoryItem";
    }
    @GetMapping("/techno")
    public String showTech(Model m, Principal principal){
        m.addAttribute("Title","Technologies");
        List<Item> technology = itemRepo.findItemByCategory("Technology");
        m.addAttribute("items",technology);
        return "normal/categoryItem";
    }


}
