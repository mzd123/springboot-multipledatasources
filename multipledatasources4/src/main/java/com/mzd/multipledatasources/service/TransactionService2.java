package com.mzd.multipledatasources.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mzd.multipledatasources.bean.TeachersBean;
import com.mzd.multipledatasources.dao.TransactionDao2;

@Service
public class TransactionService2 {
	@Autowired
	private TransactionDao2 ts2;

	@Transactional
	public void test02_saveTeachersBean(TeachersBean t) {
		ts2.save(t);
	}

}
