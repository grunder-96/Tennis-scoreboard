package org.edu.pet.repository;

public interface SharedRepository<E> {

    E save(E entity);
}