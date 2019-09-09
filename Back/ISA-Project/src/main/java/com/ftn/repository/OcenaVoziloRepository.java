package com.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.rentacar.OcenaVozilo;


@Repository
public interface OcenaVoziloRepository extends JpaRepository<OcenaVozilo, Long>
{
	List<OcenaVozilo> findByVoziloVoziloId(Long id) ; // vraca listu ocena za jedno vozilo
	List<OcenaVozilo> findByUserId(Long id) ; // vraca listu ocena tog korisnika
	OcenaVozilo findOneById(Long id); // vraca ocenu vozila na osnovu id-ja
}
