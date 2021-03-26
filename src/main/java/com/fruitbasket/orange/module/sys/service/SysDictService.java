package com.fruitbasket.orange.module.sys.service;

import com.fruitbasket.orange.module.sys.repository.SysDictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Service
public class SysDictService {

	@Autowired
	private SysDictRepository sysDictRepository;
}
