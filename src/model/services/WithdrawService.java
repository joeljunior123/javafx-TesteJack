package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.WithdrawDao; 
import model.entities.Withdraw;

public class WithdrawService {

    private WithdrawDao dao = DaoFactory.createWithdrawDao();

    public List<Withdraw> findAll(){
        return dao.findAll();
    }
    
    public void saveOrUpdate(Withdraw obj) {
    	if (obj.getId() == null) {
			dao.insert(obj);
		} else {
    		dao.update(obj);
    	}
    }
}

