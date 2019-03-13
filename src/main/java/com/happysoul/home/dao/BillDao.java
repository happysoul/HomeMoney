package com.happysoul.home.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happysoul.home.entity.Bill;

@Repository
public interface BillDao extends JpaRepository<Bill, Integer> {

	
	
	
}
