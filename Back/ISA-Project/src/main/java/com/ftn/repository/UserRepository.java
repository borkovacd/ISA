package com.ftn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.model.Korisnik;

@Repository
public interface UserRepository extends JpaRepository<Korisnik, Long> 
{
	/*public Category save(Category cat);
	
	public Category findCategoryByName(String name);
	
	public List<Category> findAll();
	
	public void deleteById(Long id);
	
	public void deleteByName(String name);
	
	List<Rating> findByUserId(Long id) ;
	
	Room findOneById(Long id);
	
	User findOneById(Long id);
	User findOneByEmail(String email);



	 */
	
	public Korisnik findOneByEmail(String email);
	public Korisnik findOneById(Long id);
	public List<Korisnik> findAll();
	public void deleteById(Long id);
	public void deleteByEmail(String email);
	public Korisnik save(Korisnik korisnik);
	
	public Korisnik findByKorisnickoIme(String administratorHotela);
}
