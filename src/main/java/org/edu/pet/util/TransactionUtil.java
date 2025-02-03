package org.edu.pet.util;

import lombok.experimental.UtilityClass;
import org.edu.pet.exception.DatabaseException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

@UtilityClass
public class TransactionUtil {

    public <R> R executeInTransaction(Function<Session, R> action, Session session) {

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            R result = action.apply(session);
            transaction.commit();

            return result;
        } catch (Exception e) {
            rollbackTransaction(transaction);
            throw new DatabaseException("An error occurred during the database query");
        }
    }

    private void rollbackTransaction(Transaction transaction) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
    }
}