package raf.edu.rs.projekat.service;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {
    <S extends T> S save(S var1, String jwt) throws UnsupportedEncodingException;

    Optional<T> findById(ID var1, String jwt) throws UnsupportedEncodingException;

    List<T> findAll(String jwt) throws UnsupportedEncodingException;

    void deleteById(ID var1, String jwt) throws UnsupportedEncodingException;

}
