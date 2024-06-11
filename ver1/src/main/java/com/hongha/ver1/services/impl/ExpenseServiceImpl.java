package com.hongha.ver1.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongha.ver1.entities.Expense;
import com.hongha.ver1.repositories.ExpenseRepository;
import com.hongha.ver1.services.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseRepository expRepo;

	@Override
	@Transactional
	public Expense save(Expense expenseRequest) {
		Expense isInserted = expRepo.save(expenseRequest);
		if (isInserted != null) {
			return isInserted;
		} else {
			throw new RuntimeException("Can't create Expense");
		}

	}

	@Override
	public Expense findById(long id) {
		Expense selected = expRepo.getReferenceById(id);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Expense:" + String.valueOf(id));
		}
	}

	@Override
	public Expense findByUUID(UUID genId) {
		Expense selected = expRepo.findByUUID(genId);
		if (selected != null) {
			return selected;
		} else {
			throw new RuntimeException("Not found Expense:" + String.valueOf(genId));
		}
	}

	@Override
	public List<Expense> getAll() {
		return expRepo.findAll();
	}

	@Override
	@Transactional
	public Expense update(long id, Expense expenseRequest) {
		Expense selected = expRepo.getReferenceById(id);
		if (selected != null) {
			selected.setName(expenseRequest.getName());
			selected.setDetail(expenseRequest.getDetail());
			selected.setCost(expenseRequest.getCost());
			Expense updated = expRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Expense:" + String.valueOf(id));
			}
		} else {
			throw new RuntimeException("Not found Expense:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public void delete(long id) {
		Expense selected = expRepo.getReferenceById(id);
		if (selected != null) {
			expRepo.deleteById(id);
		} else {
			throw new RuntimeException("Not found Expense:" + String.valueOf(id));
		}
	}

	@Override
	@Transactional
	public Expense updateByUUID(UUID genID, Expense expenseRequest) {
		Expense selected = expRepo.findByUUID(genID);
		if (selected != null) {
			selected.setName(expenseRequest.getName());
			selected.setDetail(expenseRequest.getDetail());
			selected.setCost(expenseRequest.getCost());
			Expense updated = expRepo.save(selected);
			if (updated != null) {
				return updated;
			} else {
				throw new RuntimeException("Can't update Expense:" + String.valueOf(genID));
			}
		} else {
			throw new RuntimeException("Not found Expense:" + String.valueOf(genID));
		}
	}

	@Override
	@Transactional
	public void deleteByUUID(UUID genID) {
		Expense selected = expRepo.findByUUID(genID);
		if (selected != null) {
			expRepo.deleteById(selected.getId());
		} else {
			throw new RuntimeException("Not found Expense:" + String.valueOf(genID));
		}
	}
}
