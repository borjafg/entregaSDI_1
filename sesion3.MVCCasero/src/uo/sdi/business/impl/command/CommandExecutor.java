package uo.sdi.business.impl.command;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
//import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.persistence.util.Jpa;

public class CommandExecutor<T> {

    //@PersistenceContext
    //private EntityManager ent;

    public T execute(Command<T> cmd) throws BusinessException {
	EntityManager ent = Jpa.createEntityManager();
	EntityTransaction trx = ent.getTransaction();

	try {
	    trx.begin();
	    T res = cmd.execute();
	    trx.commit();

	    return res;
	    //return null;
	}

	catch (PersistenceException | BusinessException ex) {
	    if (trx.isActive()) {
		trx.rollback();
	    }

	    throw ex;
	}

	finally {
	    if (ent != null) {
		ent.close();
	    }
	}
    }

}