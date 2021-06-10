package com.split.splitservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.split.splitservice.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer>{

	@Query("select sum(e.amount) from Expense e where e.paidBy=0")
	double balance(String userId);
	
	@Query("select sum(e.amount) from Expense e where e.paidBy=:paidBy")
	double findBalancePaidBy(@Param("paidBy")int paidBy);

	@Query("select sum(e.amount) from Expense e where e.paidTo=:paidTo")
	double findBalancePaidTo(@Param("paidTo")int paidTo);

	List<Expense> findByPaidBy(int userId);

	List<Expense> findByPaidTo(int userId);
	
//	@Query("select e from expense e")
//	double balance2(String userId);
}
