package com.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.rentacar.RentACar;
import com.ftn.model.rentacar.Vozilo;

@Repository
public interface RentCarRepository extends JpaRepository<RentACar, Long>
{
	/*public Category save(Category cat);
	public List<Category> findAll();
	public void deleteById(Long id);
	public void deleteByName(String name);
	List<Rating> findByUserId(Long id) ;
	User findOneById(Long id);
	User findOneByEmail(String email);
	 */
	
	public RentACar save(RentACar rent);
	public List<RentACar> findAll() ; // svi servisi
		
	public void deleteByRentACarId(Long id);
	public void deleteByNaziv(String name);
	List<RentACar> findByAdministrator(Long id) ; // servisi tog administratora
	RentACar findOneByRentACarId(Long id);
	RentACar findOneByNaziv(String naziv) ;
	
	// Test
	RentACar findOneByAdministratorId(Long id);
	
}

