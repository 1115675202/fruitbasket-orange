package com.example.spring.boot.zhaoyun.module.sys.service;

import com.example.spring.boot.zhaoyun.module.sys.repository.SysDictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
