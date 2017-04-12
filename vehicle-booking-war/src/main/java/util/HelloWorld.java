package util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {

    @RequestMapping(value="{name}", method = RequestMethod.GET)
    public @ResponseBody Model getShopInJSON(@PathVariable String name) {

        Model shop = new Model();
        shop.setName(name);
        shop.setAddress("mkyong2");
        return shop;

    }
}
