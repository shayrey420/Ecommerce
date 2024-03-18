package com.example.demo.controller;

import com.example.demo.dao.*;
import com.example.demo.enteties.*;

import com.example.demo.helper.Message;
import com.example.global.GlobalData;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    BuyersrReprository buyersrReprository;
    @Autowired
    SellerReporository sellerReporository;
    @Autowired
    SellerItemRepo sellerItemRepo;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    ShoppingcartRepo shoppingcartRepo;
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public int productI;

    @GetMapping("/dashboard")
    public String buyerDashboard(Principal principal, Model m){
        String name = principal.getName();
        System.out.println(name);
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        String fullAdress=buyersByUser_name.getShipping_adress()+","+buyersByUser_name.getShipping_city()+","+buyersByUser_name.getShipping_division();
        m.addAttribute("Title",fullName);
        m.addAttribute("obj",buyersByUser_name);
        m.addAttribute("buyer",buyersByUser_name);
        m.addAttribute("fullAdress",fullAdress);
        m.addAttribute("cardCount",GlobalData.cart.size());
        Seller sellerById = sellerReporository.getSellerById(buyersByUser_name.getId());

        if(sellerById==null)
        return "normal/buyerDashboard";
        else  return "normal/buyerSeller";
    }
    @PostMapping("/sellerHandeler")
    public String sellerHandeler(@ModelAttribute Seller seller, BindingResult result, HttpSession session, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model m, Principal principal){
        try {
            String name = principal.getName();
            Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
            String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
            m.addAttribute("Title",fullName);
            m.addAttribute("buyer",buyersByUser_name);
            if (result.hasErrors()) {

                return "normal/sellerForm";
            }
            if(!agreement) {
                System.out.println("Not checked");
                throw new Exception("You didnot accept terms and conditions");

            }
            seller.setRole("ROLE_Seller");
        seller.setLast_name(buyersByUser_name.getLast_name());
        seller.setFirst_name(buyersByUser_name.getFirst_name());
        seller.setUserName(buyersByUser_name.getUser_name());
        seller.setBuyers(buyersByUser_name);

        sellerReporository.save(seller);
            return "redirect:/buyer/dashboard";
        }catch (Exception e){
            session.setAttribute("message", new Message(e.getMessage()+",Please try again.","alert-danger"));
            return "normal/sellerForm";
        }

    }
    @GetMapping("/sellerForm")
    public String sellerHandeler(Principal principal,Model m){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        return "normal/sellerForm";
    }
    @GetMapping("/itemView")
    public String itemView(Principal principal,Model m){
        String name = principal.getName();
        System.out.println(name);
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("buyer",buyersByUser_name);
        m.addAttribute("Title",fullName);
        m.addAttribute("cardCount",GlobalData.cart.size());
        return "normal/itemView";

    }
    @PostMapping("/itemHandeler")
    public String itemHandeler(@ModelAttribute Item item,Principal principal, Model m, @RequestParam("profileImage") MultipartFile file) throws IOException {
        String name = principal.getName();
        System.out.println(name);
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("buyer",buyersByUser_name);
        m.addAttribute("item",item);
        m.addAttribute("Title",fullName);
        Seller sellerById = sellerReporository.getSellerById(buyersByUser_name.getId());

        if(!file.isEmpty()) {
            item.setImage(file.getOriginalFilename());
            File saveFile = new ClassPathResource("static/image").getFile();
            Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
            System.out.println(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }else {
            item.setImage("defaultItem.jpg");
            System.out.println("exception coming from homeController signup form");
        }
        item.setSeller_name(sellerById.getUserName());
        SellerItem s=new SellerItem();
        s.setItem(item);
        s.setSeller(sellerById);

        System.out.println(s.getSeller().getId());
        itemRepo.save(item);
        sellerItemRepo.save(s);


        return "normal/itemView";
    }
    @GetMapping("/allItem")
    public String allView(Model m,Principal principal){
        String name = principal.getName();
        System.out.println(name);
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        String fullAdress=buyersByUser_name.getShipping_adress()+","+buyersByUser_name.getShipping_city()+","+buyersByUser_name.getShipping_division();
        m.addAttribute("Title",fullName);
        m.addAttribute("obj",buyersByUser_name);
        m.addAttribute("buyer",buyersByUser_name);
        Seller sellerById = sellerReporository.getSellerById(buyersByUser_name.getId());
        m.addAttribute("cardCount",GlobalData.cart.size());
        List<Item> items = itemRepo.findBySeller_name(sellerById.getUserName());
        m.addAttribute("items",items);
        return "normal/allItem";
    }
    @GetMapping("/addTocart/{id}")
    public String Cart(@PathVariable int id,Principal principal,Model m){
        String name = principal.getName();
        System.out.println(name);
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        Seller sellerById = sellerReporository.getSellerById(buyersByUser_name.getId());

        GlobalData.cart.add(itemRepo.findItemById(id));
        return "redirect:/buyer/home";
    }
    @GetMapping("/remove/{id}")
    public String removeItem(@PathVariable int id,Principal principal,Model m){
        System.out.println(id);
        String name = principal.getName();
        System.out.println(name);
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        GlobalData.cart.remove(id);
        return "redirect:/buyer/cart";
    }
    @GetMapping("/cart")
    public String cardGet(Model m,Principal principal){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);

        m.addAttribute("cardCount",GlobalData.cart.size());
        m.addAttribute("total",GlobalData.cart.stream().mapToDouble(Item::getUnit_price).sum());
        m.addAttribute("cart",GlobalData.cart);

        double vat=GlobalData.cart.stream().mapToDouble(Item::getUnit_price).sum();
        double vatTotal=vat+vat*.05;
        m.addAttribute("vatTotal",vatTotal);
        return "normal/cart";
    }
    @GetMapping("/home")
    public String buyerHome(Model m,Principal principal){
        List<Item> allItem = itemRepo.findAllItem();
        String name = principal.getName();

        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("buyer",buyersByUser_name);

        m.addAttribute("Title",fullName);
        m.addAttribute("items",allItem);
        m.addAttribute("cardCount", GlobalData.cart.size());
        return "normal/home";
    }
    @PostMapping("/payment")
    public String Payment(@ModelAttribute Payment payment,Model m,Principal principal){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        //Seller sellerById = sellerReporository.getSellerById(buyersByUser_name.getId());

        m.addAttribute("cardCount",GlobalData.cart.size());
        m.addAttribute("total",GlobalData.cart.stream().mapToDouble(Item::getUnit_price).sum());
        m.addAttribute("cart",GlobalData.cart);

        int size=GlobalData.cart.size();
        double vat=GlobalData.cart.stream().mapToDouble(Item::getUnit_price).sum();
        double vatTotal=vat+vat*.05;


        Shopping_cart cart=new Shopping_cart();

         cart.setTotal_quantity(size);
         cart.setTotal_amount(vatTotal);
         cart.setBuyers(buyersByUser_name);
         cart.setCancel(false);
         cart.setBuyers(buyersByUser_name);
         cart.setOrder_date(payment.getCard_expiratoion());
         cart.setShipping_Date(payment.getCard_expiratoion());
         cart.setPayment_date(payment.getCard_expiratoion());

         payment.setCurrentAdress(buyersByUser_name.getShipping_adress());
        payment.setDivision(buyersByUser_name.getShipping_division());
        payment.setPermanentAdress(buyersByUser_name.getShipping_city());
        payment.setZipCode(2100);
        payment.setUpazila(buyersByUser_name.getShipping_upazila());
        payment.setShopping_cart(cart);

        paymentRepo.save(payment);
        //shoppingcartRepo.save(cart);
        int i= payment.getId();
        GlobalData.cart.clear();
        return "redirect:/buyer/purchaseHistory";
    }
    @GetMapping("/review/{id}")
    public String review(@PathVariable("id") Integer id,Principal principal,Model m){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);

        m.addAttribute("productId",id);
        productI=id;

        return "normal/review";
    }
    @PostMapping("/reviewHandeler/{id}")
    public String reviewHandeler(@ModelAttribute Review review,Principal principal,@PathVariable("id") Integer id,@RequestParam("rating") String rating){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        review.setBuyers(buyersByUser_name);
        int id1 =Integer.parseInt(rating);
        review.setReating(id1);
        reviewRepo.save(review);

        Payment paymentByID = paymentRepo.getPaymentByID(id);
        paymentByID.setReview(review);
        paymentRepo.save(paymentByID);
        return "redirect:/buyer/dashboard";
    }
    @GetMapping("/purchaseHistory")
    public String purchaseHistory(Principal principal,Model m){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        List<Shopping_cart> allItem = shoppingcartRepo.findAllItem(buyersByUser_name.getId());

        m.addAttribute("cart",allItem);

        return "normal/purchaseHistory";
    }
    @RequestMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id")Integer id){
        itemRepo.deleteById(id);
        return "redirect:/buyer/allItem";
    }
    @GetMapping("/item/update/{id}")
    public String updateitemForm(Principal principal,Model m,@PathVariable("id")Integer id){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        m.addAttribute("id",id);
        return "normal/updateForm";
    }
    @PostMapping("/updateItem/{id}")
    public String updateItem(@PathVariable("id")Integer id,@RequestParam ("price") String price){
        double newPrice = Double.parseDouble(price);
        itemRepo.updateItem(id,newPrice);

        return "redirect:/buyer/allItem";
    }
    @GetMapping("/updateBuyer/{id}")
    public String updateBuyer(Principal principal,Model m,@PathVariable("id")Integer id){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        m.addAttribute("id",id);
        return "normal/buyerUpdate";
    }
    @PostMapping("/buyerUpdate/handeler/{id}")
    public String buyerUpdateHandeler(@PathVariable("id")Integer id,@RequestParam("Adress")String adress,
                                      @RequestParam("phone")String phone,Principal principal,Model m){
        String naame = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(naame);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);

        buyersrReprository.updatbuyer(adress,phone,id);
        return "redirect:/buyer/dashboard";
    }

    @GetMapping("/updateSeller/{id}")
    public String updateSeller(Principal principal,Model m,@PathVariable("id")Integer id){
        String naame = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(naame);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        m.addAttribute("id",id);
        return "normal/sellerUpdate";
    }
    @PostMapping("/sellerUpdate/handeler/{id}")
    public String sellerUpdateHandeler(@PathVariable("id")Integer id,@RequestParam("Adress")String adress,
                                      @RequestParam("phone")Integer phone,Principal principal,Model m){
        String naame = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(naame);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);

        sellerReporository.updatSeller(adress,phone,id);

        return "redirect:/buyer/dashboard";
    }
    @GetMapping("/profile")
    public String profile(Principal principal,Model m){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        Seller sellerById = sellerReporository.getSellerById(buyersByUser_name.getId());
        m.addAttribute("seller",sellerById);
        m.addAttribute("sellerId",sellerById.getId());
        m.addAttribute("buyerId",buyersByUser_name.getId());
        return "normal/profile";
    }
    @GetMapping("/showReview/{id}")
    public String showReview(@PathVariable("id") Integer id,Model m,Principal principal){
        String name = principal.getName();
        Buyers buyersByUser_name = buyersrReprository.getBuyersByUser_name(name);
        String fullName=buyersByUser_name.getFirst_name()+" "+buyersByUser_name.getLast_name();
        m.addAttribute("Title",fullName);
        m.addAttribute("buyer",buyersByUser_name);
        List<Review> reviewByBuyers = reviewRepo.getReviewByBuyers(id);
        m.addAttribute("review",reviewByBuyers);
        return "normal/buyerReview";
    }

}
