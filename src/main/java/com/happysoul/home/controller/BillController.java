package com.happysoul.home.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happysoul.home.dao.BillDao;
import com.happysoul.home.entity.Bill;

@RestController
@RequestMapping("bill")
public class BillController {

	@Autowired
	BillDao billDao;
	
	@GetMapping("getBills")
	public Page<Bill> getBills(Integer pageNumber,Integer pageSize){
		
		Bill bill = new Bill();
		
		ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//                .withMatcher("materialName", 	GenericPropertyMatchers.contains()) //姓名采用“开始匹配”的方式查询
//                .withMatcher("registerTime", 	GenericPropertyMatchers.contains()) //姓名采用“开始匹配”的方式查询
//                .withMatcher("status", 			GenericPropertyMatchers.contains()) //姓名采用“开始匹配”的方式查询
//                .withIgnorePaths("id")
                ;  //忽略属性：是否关注。因为是基本类型，需要忽略掉

		if(pageNumber==null) pageNumber=0;
		if(pageNumber>0) pageNumber=pageNumber-1;
        if(pageSize==null) pageSize=30;
		Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.Direction.DESC,"id");
		Page<Bill> page = billDao.findAll(Example.of(bill, matcher), pageable);
		
		return page;
	}
	
	@PostMapping("save")
	public void save(Bill bill) {
		
		if(bill.getId()==null) {
			bill.setCreateTime(new Date());
			bill.setSignTime(new Date());
			billDao.save(bill);
		}else {
			Bill b = billDao.getOne(bill.getId());
			b.setSignTime(bill.getSignTime());
			b.setName(bill.getName());
			b.setMoney(bill.getMoney());
			b.setPrepaid(bill.getPrepaid());
			b.setMark(bill.getMark());
			billDao.save(b);
		}
		
	}
	
}
