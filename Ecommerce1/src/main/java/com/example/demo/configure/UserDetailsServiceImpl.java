package com.example.demo.configure;

import com.example.demo.dao.BuyersrReprository;
import com.example.demo.dao.SellerReporository;
import com.example.demo.enteties.Buyers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BuyersrReprository signinRepo;
    @Autowired
    private SellerReporository sellerReporository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Buyers buyersByUser_name = signinRepo.getBuyersByUser_name(username);
        //Seller sellerByUserName = sellerReporository.getSellerByUserName(username);

       // if(buyersByUser_name==null){
            CustomUserDetails customUserDetails=new CustomUserDetails(buyersByUser_name);
            return customUserDetails;
        //}
       /* else{
            CustomUserDetails customUserDetails=new CustomUserDetails(sellerByUserName);
            return customUserDetails;
        }*/




    }
}
