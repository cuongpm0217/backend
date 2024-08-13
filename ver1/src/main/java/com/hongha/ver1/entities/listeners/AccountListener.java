package com.hongha.ver1.entities.listeners;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Account;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;

import com.hongha.ver1.services.impl.BeanUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;


@Component
public class AccountListener {
	
	@PostPersist
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void afterCreate(Account target) {
		perform(EAction.CREATE, target.getId());
	}

	@PostUpdate
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void afterUpdate(Account target) {
		perform(EAction.UPDATE, target.getId());
		// clear cache
//		accountRedisService.clear();
	}

	@PostRemove
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void afterRemove(Account target) {
		perform(EAction.DELETE, target.getId());
		// clear cache		
//		accountRedisService.clear();
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private void perform(EAction action, Long id) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		entityManager.persist(new History(Account.class.getName(), action, String.valueOf(id)));
	}
}
