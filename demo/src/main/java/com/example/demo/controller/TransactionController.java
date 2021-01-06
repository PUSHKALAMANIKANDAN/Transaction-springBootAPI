package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionModel;
import com.example.demo.model.User;
import com.example.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
	
	 private final Logger log = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	@GetMapping("/transactionModel")
	public ResponseEntity<List<TransactionModel>> getAllTransaction() {
		ArrayList<TransactionModel>transactionList = transactionService.getAllTransaction();
		return new ResponseEntity<List<TransactionModel>>(transactionList, HttpStatus.OK);
	}
	
	@PostMapping("/transaction/transactionModel")
	public ResponseEntity<TransactionModel> addTransaction(@RequestBody TransactionModel model) {
		log.debug("transactionmodel{}",model);
		model=transactionService.save(model);		
		return new ResponseEntity<TransactionModel>(model, HttpStatus.OK);
	}

	
	@GetMapping("/transactionModel/{date}")
	public ResponseEntity<List<TransactionModel>> getTransactionByDate(@PathVariable("date") String date, Pageable pageable )
	{
		LocalDate localDate = LocalDate.parse(date);
		log.debug("date{}"+date);
		Page<TransactionModel> page = transactionService.findByDate(localDate,pageable);
	        return ResponseEntity.ok(page.getContent());
	}
	
	
	
	
	@PostMapping("/transaction")
	public ResponseEntity<Transaction> amountTransaction(@RequestBody Transaction model) {
		log.debug("transactionmodel{}",model);
		model=transactionService.saveTransaction(model);		
		return new ResponseEntity<Transaction>(model, HttpStatus.OK);
	}


	@GetMapping("/transaction/{date}")
	public ResponseEntity<List<Transaction>> getTransaction(@PathVariable("date") String date, Pageable pageable )
	{
		LocalDate localDate = LocalDate.parse(date);
		log.debug("date{}"+date);
		Page<Transaction> page = transactionService.findTransactionByDate(localDate,pageable);
	        return ResponseEntity.ok(page.getContent());
	}

	@GetMapping("/transaction")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		ArrayList<Transaction>transactionList = transactionService.getAllTransactions();
		return new ResponseEntity<List<Transaction>>(transactionList, HttpStatus.OK);
	}
	
	@GetMapping("/transaction/{accountnumber}")
	public ResponseEntity<Double> getCurrentBalanceofUser(@PathVariable("account") String account ) {
		Double amount=transactionService.getAccountBalanceofUser(account);
		return new ResponseEntity<Double>(amount, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		log.debug("user{}",user);
		user=transactionService.saveUser(user);		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}


