package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Withdraw;

public class WithdrawService {
	
	public List<Withdraw> findAll(){
		List<Withdraw> list = new ArrayList<>();
		list.add(new Withdraw(1, 500));
		list.add(new Withdraw(2, 600));
		list.add(new Withdraw(3, 375));
		return list;
	}

}
