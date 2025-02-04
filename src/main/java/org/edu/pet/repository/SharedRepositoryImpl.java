package org.edu.pet.repository;

import org.edu.pet.util.HibernateUtil;
import org.edu.pet.util.TransactionUtil;
import org.hibernate.Session;

import java.lang.reflect.Proxy;

public abstract class SharedRepositoryImpl<E> implements SharedRepository<E> {

    protected final Session session = getCurrentSession();

    @Override
    public E save(E entity) {
        return TransactionUtil.executeInTransaction(
                session -> {
                    session.persist(entity);
                    return entity;
                },
                session
        );
    }

    private Session getCurrentSession() {
        return (Session) Proxy.newProxyInstance(HibernateUtil.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(HibernateUtil.getSessionFactory().getCurrentSession(), args));

    }
}