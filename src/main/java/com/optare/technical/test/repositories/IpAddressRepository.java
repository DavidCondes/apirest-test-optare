package com.optare.technical.test.repositories;

import com.optare.technical.test.models.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {

    ArrayList<IpAddress> findByIp(String ip);
}
