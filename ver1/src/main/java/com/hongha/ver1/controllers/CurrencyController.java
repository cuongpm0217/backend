package com.hongha.ver1.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hongha.ver1.dtos.CurrencyDTO;
import com.hongha.ver1.entities.Currency;
import com.hongha.ver1.services.CurrencyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String code,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "code") String sortBy, @RequestParam(defaultValue = "des") String sortType) {
		try {
			Page<Currency> currencies;
			if (code == null || code != "") {
				currencies = currencyService.getAll(page, size, sortBy, sortType);
			} else {
				currencies = currencyService.findByCodeContaining(code, page, size, sortBy, sortType);
			}
			// set No > DTO
			List<CurrencyDTO> currencyDTOs = currencies.stream()
					.map(currency -> mapper.map(currency, CurrencyDTO.class)).collect(Collectors.toList());
			for (int i = 0; i < currencyDTOs.size(); i++) {
				currencyDTOs.get(i).setNo(i + 1);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("currency", currencyDTOs);
			response.put("currentPage", currencies.getNumber());
			response.put("totalItems", currencies.getTotalElements());
			response.put("totalPages", currencies.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody CurrencyDTO currencyDTO) {
		Currency currency = mapper.map(currencyDTO, Currency.class);
		Currency currencyCreated = currencyService.save(currency);
		CurrencyDTO result = mapper.map(currencyCreated, CurrencyDTO.class);
		String msg = "";
		Map<String, Object> response = new HashMap<>();
		HttpStatus status;
		if (result != null) {
			msg = "create Success";
			status = HttpStatus.CREATED;
		} else {
			msg = "create Fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.put("currency", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@GetMapping("/USD-VND/")
	public ResponseEntity<Map<String, Object>> getExchangeRateUSD() {
		String code = "USD";
		CurrencyDTO result = mapper.map(currencyService.getExchangeVNDByCode(code), CurrencyDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getCode();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found code" + code;
		}
		response.put("currency", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use uuid
	@GetMapping("/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		CurrencyDTO result = mapper.map(currencyService.findByUUID(uuid), CurrencyDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getCode();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("currency", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping("/update={uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody CurrencyDTO currencyDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, currencyDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Currency currency = mapper.map(currencyDTO, Currency.class);
		CurrencyDTO result = mapper.map(currencyService.updateByUUID(uuid, currency), CurrencyDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getCode();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("currency", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping("/delete={uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = currencyService.deleteByUUID(uuid);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result) {
			status = HttpStatus.OK;
			msg = "DELETED";
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("result", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

}
