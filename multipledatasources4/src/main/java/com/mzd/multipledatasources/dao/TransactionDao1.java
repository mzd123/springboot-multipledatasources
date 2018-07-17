package com.mzd.multipledatasources.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mzd.multipledatasources.bean.TestBean;
import com.mzd.multipledatasources.mapper.TransactionMapping1;

@Component

public class TransactionDao1 {
	@Autowired
	private TransactionMapping1 tm1;

	public void save(TestBean t) {
		tm1.save(t);
	}

}
