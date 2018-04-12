package com.example.controller;

import com.example.model.entity.bank.Bank;
import com.example.model.entity.client.Client;
import com.example.model.entity.irs.Irs;
import com.example.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@ComponentScan("com.example.model")
public class IrsController {

    private BankService bankService;

    private Irs irs;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @Autowired
    public void setIrs(Irs irs){
        this.irs=irs;
    }

    @RequestMapping(value = "/irs", method=RequestMethod.GET)
    public String irsGet(){
        return "irs";
    }

    @RequestMapping(value = "/irs", method=RequestMethod.POST)
    public String irsPost(HttpServletRequest request, Model model){
        String requestedOperation=request.getParameter("hiddenIrsForm");
        HttpSession session=request.getSession();
        Bank bank= (Bank) session.getAttribute("bank");
        Client client= (Client) session.getAttribute("client");
        switch (requestedOperation){
            case("startMonitor"):
                bankService.startMonitor(bank, client);
                model.addAttribute("irsStatusMessage","Monitoring has started.");
                break;
            case("stopMonitor"):
                bankService.stopMonitor(bank, client);
                model.addAttribute("irsStatusMessage","Monitoring has stopped.");
                break;
            case("displayCache"):
                String cache=irs.getCache();
                System.out.println(cache);
                model.addAttribute("irsStatusMessage",cache);
                break;
            default:
                break;
        }

        return "irs";
    }
}
