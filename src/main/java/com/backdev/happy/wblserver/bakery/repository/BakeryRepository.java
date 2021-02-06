package com.backdev.happy.wblserver.bakery.repository;

import com.backdev.happy.wblserver.bakery.domain.Bakery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BakeryRepository extends JpaRepository<Bakery,Long> {

}
