package com.mzd.multipledatasources.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzd.multipledatasources.bean.TeachersBean;
import com.mzd.multipledatasources.bean.TestBean;
import com.mzd.multipledatasources.dao.test01.TransactionDao1;
import com.mzd.multipledatasources.dao.test02.TransactionDao2;

@Service
public class TransactionService1 {
	@Autowired
	private TransactionDao1 ts1;
	@Autowired
	private TransactionService2 ts2;
	@Autowired
	private TransactionDao2 td2;

	@Transactional
	public void savetestBean(TestBean t) {
		ts1.save(t);
	}

	@Transactional
	public void savetestBean2(TestBean t) {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher(tb);
		int i = 1 / 0;
		ts1.save(t);
	}

	@Transactional
	public void savetestBean3(TestBean t) {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		ts2.saveTeacher2(tb);
		int i = 1 / 0;
		ts1.save(t);
	}

	@Transactional
	/**
	 * 直接注入数据源2的dao层就不收这个事务控制了
	 * 
	 * @param t
	 */
	public void savetestBean4(TestBean t) {
		TeachersBean tb = new TeachersBean();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setTeachername("王老师");
		tb.setClassid("1");
		td2.save(tb);
		int i = 1 / 0;
		ts1.save(t);
	}

}
