package ru.renat.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.renat.pma.dao.UserAccountRepository;
import ru.renat.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userRepo;
	
	public UserAccount save(UserAccount userAccount) {
		return userRepo.save(userAccount);
	}
	
	public List<UserAccount> getAll(){
		return userRepo.findAll();
	}
	

}
