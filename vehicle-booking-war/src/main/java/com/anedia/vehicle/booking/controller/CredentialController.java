package com.anedia.vehicle.booking.controller;

import com.anedia.vehicle.booking.entity.CredentialBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CredentialController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody CredentialBean login(){
        CredentialBean credentialBean = new CredentialBean();
        credentialBean.setUserID("sdfsdf");
        credentialBean.setPassword("sdfsd");
        return credentialBean;
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public @ResponseBody CredentialBean login1(){
        CredentialBean credentialBean = new CredentialBean();
        credentialBean.setUserID("Sujatha");
        credentialBean.setPassword("Aman");
        return credentialBean;
    }
}
