package com.example.controller;

import com.example.model.Transaction;
import com.example.model.entity.bank.Bank;
import com.example.model.entity.client.Client;
import com.example.service.BankService;
import com.example.service.InvalidAccountBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@ComponentScan("com.example.service")
public class OperationController {

    private BankService bankService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String deposit(HttpServletRequest request, @Valid @ModelAttribute(value = "transaction") Transaction transaction, BindingResult bindingResult, Model model) {

        model.addAttribute("transaction", new Transaction());

        if (bindingResult.hasErrors()) {
            return "index";
        }

        HttpSession session = request.getSession();
        Bank bank = (Bank) session.getAttribute("bank");
        Client client = (Client) session.getAttribute("client");

        String withdrawRequested = request.getParameter("withdraw");
        String depositRequested = request.getParameter("deposit");

        String currency = request.getParameter("currency");

        if (withdrawRequested != null) {
            int amount = Integer.parseInt(withdrawRequested);

            if (bankService.withdraw(bank, client, amount, currency)) {
                model.addAttribute("balance", bankService.checking(bank, client));
                return "confirmation";
            } else {

                return "failedOperation";
            }

        }
        if (depositRequested != null) {
            int amount = Integer.parseInt(depositRequested);
            bankService.deposit(bank, client, amount, currency);
            model.addAttribute("balance", bankService.checking(bank, client));
            return "confirmation";
        }
        return "index";
    }
}
