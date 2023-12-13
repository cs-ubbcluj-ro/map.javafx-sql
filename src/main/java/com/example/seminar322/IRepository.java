package com.example.seminar322;


import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    public void add(T o) throws RepositoryException;

    public void remove(int id) throws RepositoryException;

    public T find(int id);

    // program to an interface, not an implementation
    public Collection<T> getAll();
}
