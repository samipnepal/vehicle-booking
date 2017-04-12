package com.anedia.vehicle.booking.controller;

import com.anedia.vehicle.booking.entity.CredentialBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xyadnep on 4/12/17.
 */
@Controller
public class CredentialController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CredentialBean login(CredentialBean credentialBean){
        return null;
    }
}
