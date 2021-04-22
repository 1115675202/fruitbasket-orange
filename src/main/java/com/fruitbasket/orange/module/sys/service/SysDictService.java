package com.fruitbasket.orange.module.sys.service;

import com.fruitbasket.orange.module.sys.repository.SysDictRep;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class SysDictService {

	private final SysDictRep sysDictRep;

	public SysDictService(SysDictRep sysDictRep) {
		this.sysDictRep = sysDictRep;
	}
}
