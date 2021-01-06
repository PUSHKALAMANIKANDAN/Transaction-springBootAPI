package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionModel;
import com.example.demo.model.User;
import com.example.demo.repository.TransRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionrepository;
	
	@Autowired
	TransRepository repo;
	
	@Autowired
	UserRepository userRepository;
	
	public TransactionModel save(TransactionModel model) {
		return transactionrepository.save(model);
	}
	public ArrayList<TransactionModel> getAllTransaction() {
			return transactionrepository.findAllByOrderByDateDesc();
	}
	public Page<TransactionModel> findByDate(LocalDate date, Pageable pageable) {
		
		return transactionrepository.findByDate(date,pageable);
	}
	
	
	
	
	public Transaction saveTransaction(Transaction model) {
		 double balance;
		User user=userRepository.findByUser(model.getUser().getUser());
		   if (user != null) {
			   if(model.getType().equalsIgnoreCase("CREDIT"))
			   {
				 balance= user.getAccountBalance();
				balance-=model.getAmount();
				user.setAccountBalance(balance);
			   }
			   else {
				   balance= user.getAccountBalance();
					balance+=model.getAmount();
					user.setAccountBalance(balance);
			   }
		   }
		   userRepository.save(user);
		return repo.save(model);
	}
	
public Page<Transaction> findTransactionByDate(LocalDate date, Pageable pageable) {
		
		return repo.findByDate(date,pageable);
	}
public ArrayList<Transaction> getAllTransactions() {
	return repo.findAllByOrderByDateDesc();
}
public Double getAccountBalanceofUser(String account) {
	
	return userRepository.findAccountBalanceByAccountNumber(account);
}
public User saveUser(User user) {
	return userRepository.save(user);
}
	

}
