package model.dao;

import db.DB;
import model.dao.impl.WithdrawDaoJDBC;

public class DaoFactory {

	public static WithdrawDao createWithdrawDao() {
		return new WithdrawDaoJDBC(DB.getConnection());
	}
}