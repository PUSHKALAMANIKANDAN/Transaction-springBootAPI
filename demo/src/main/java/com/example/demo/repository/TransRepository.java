package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Transaction;

@Repository
public  interface TransRepository extends JpaRepository<Transaction,Long> {

	ArrayList<Transaction> findAllByOrderByDateDesc();
//
	Page<Transaction> findByDate(LocalDate date, Pageable pageable);

}