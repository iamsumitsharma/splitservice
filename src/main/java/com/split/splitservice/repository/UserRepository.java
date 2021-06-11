package com.split.splitservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.split.splitservice.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
}
