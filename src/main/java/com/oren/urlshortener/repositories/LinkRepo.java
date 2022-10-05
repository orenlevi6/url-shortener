package com.oren.urlshortener.repositories;

import com.oren.urlshortener.beans.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepo extends CrudRepository<Link, String> {

}
