package com.example.demo.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TransactionModel;
@Repository
public  interface TransactionRepository extends JpaRepository<TransactionModel,Long> {

	ArrayList<TransactionModel> findAllByOrderByDateDesc();

	Page<TransactionModel> findByDate(LocalDate date, Pageable pageable);

}
