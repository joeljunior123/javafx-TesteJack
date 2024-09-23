package model.dao;

import java.util.List;

import model.entities.Withdraw;

public interface WithdrawDao {

	void insert(Withdraw obj);
	void update(Withdraw obj);
	void deleteById(Integer id);
	Withdraw findById(Integer id);
	List<Withdraw> findAll();
}
