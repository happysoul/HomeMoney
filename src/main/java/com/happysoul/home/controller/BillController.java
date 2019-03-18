package com.happysoul.home.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.happysoul.home.entity.Bill;
import com.happysoul.home.service.IBillService;

/**
 * @author fuhs
 * @since 2019-03-14
 */
@RestController
@RequestMapping("/bill")
public class BillController extends BaseController {

	@Autowired
	IBillService billService;
	
	@ResponseBody
	@RequestMapping("getBills")
	public IPage<Bill> getBills(Integer page,Integer size){
		return billService.page(new Page<Bill>(page(page),size(size)));
	}
	
	@PostMapping("save")
	public void save(Bill bill) {
		System.out.println("==save==");
		
		//更新
		if(bill.getId()!=null && bill.getId()>0) {
			billService.updateById(bill);
		//保存	
		}else {
			billService.save(bill);
		}
		
		
	}
	
}

