package com.hongha.ver1.entities.listeners;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.RepairBill;
import com.hongha.ver1.entities.History;
import com.hongha.ver1.entities.enums.EAction;
import com.hongha.ver1.services.impl.BeanUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RepairBillListener {
	@PostPersist
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void afterCreate(RepairBill target) {
		perform(EAction.CREATE, target.getId());
	}

	@PostUpdate
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void afterUpdate(RepairBill target) {
		perform(EAction.UPDATE, target.getId());
		// clear cache
	}

	@PostRemove
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void afterRemove(RepairBill target) {
		perform(EAction.DELETE, target.getId());
		// clear cache
	}

	@Transactional(propagation = Propagation.MANDATORY)
	private void perform(EAction action, Long id) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		entityManager.persist(new History(RepairBill.class.getName(), action, String.valueOf(id)));
	}
}
