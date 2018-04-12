package com.example.controller;

import com.example.model.entity.bank.Bank;
import com.example.model.entity.client.Client;
import com.example.service.BankServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@ComponentScan("com.example.service")
public class IndexController {

    private BankServiceInterface bankServiceInterface;

    @Autowired
    public void setBankService(BankServiceInterface bankServiceInterface) {
        this.bankServiceInterface = bankServiceInterface;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "operation", required = false) String operation, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.isNew()) {
            Bank bank = new Bank();
            Client client = new Client("JohnDoe");
            bankServiceInterface.createAccount(bank, client);
            session.setAttribute("bank", bank);
            session.setAttribute("client", client);
            return "index";
        }

        Bank bank = (Bank) session.getAttribute("bank");
        Client client = (Client) session.getAttribute("client");


        if (operation != null) {
            if (operation.equals("withdraw")) {
                String currentBalance = bankServiceInterface.checking(bank, client);
                model.addAttribute("balance", currentBalance);
                return "withdraw";
            } else if (operation.equals("deposit")) {
                return "deposit";
            } else if (operation.equals("check")) {
                String currentBalance = bankServiceInterface.checking(bank, client);
                model.addAttribute("balance", currentBalance);
                return "checkBalance";
            }
        }
        return "index";
    }


}
