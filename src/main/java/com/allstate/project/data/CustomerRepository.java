package com.allstate.project.data;

import com.allstate.project.entities.Customer;
import com.mongodb.client.model.Collation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Collection;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

	public List<Customer> findByLastName(String lastName);

	@Query("{'firstName': ?0}")
	public List<Customer> customFindByFirstName(String firstName);
	@Query("{'email': ?0}")
	public Customer customFindByemail(String email);
	@Query("{'id': ?0}")
	public Customer customFindByid(ObjectId id);
	@DeleteQuery
	public void deleteByid(ObjectId id);
	//@Query("{ 'firstName' : {$regex:?0} }, { 'lastName' : {$regex:?1} , { 'email' : {$regex:?2} ")
	 //@Query(value = "{ $or: [ { 'firstName' : {$regex: ?0,$options:'i'} }, { 'lastName' : {$regex: ?1,$options:'i'} , { 'email' : {$regex: ?2,$options:'i'} } ] }")
	//@Query( "{ 'firstName' : {$regex: ?0,$options:'i'} }, { 'lastName' : {$regex: ?1,$options:'i'} , { 'email' : {$regex: ?2,$options:'i'} }")
	@Query("{ $and: [ { 'firstName' : {$regex: ?0,$options:'i'} }, { 'lastName' : {$regex: ?1,$options:'i'}}, { 'email' : {$regex: ?2,$options:'i'} } ] }")
	public Collection<Customer> customFindByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}