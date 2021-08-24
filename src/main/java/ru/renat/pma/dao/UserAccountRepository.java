package ru.renat.pma.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import ru.renat.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

	@Override
	List<UserAccount> findAll();
}
