package com.happysoul.home.service.impl;

import com.happysoul.home.entity.Bill;
import com.happysoul.home.mapper.BillMapper;
import com.happysoul.home.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fuhs
 * @since 2019-03-14
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

}
