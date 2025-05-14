package com.salesianostriana.dam.LlinaresSomeRaul.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    @Autowired
    protected R repositorio;


    public T save(T t) {
        return repositorio.save(t);
    }

    public T findById(ID id) {

        return repositorio.findById(id).orElse(null);
    }

    public List<T> findAll() {
        return repositorio.findAll();
    }


    public T edit(T t) {
        return repositorio.save(t);
    }

    public void delete(T t) {
        repositorio.delete(t);
    }

    public void deleteById(ID id) {
        repositorio.deleteById(id);
    }

}
