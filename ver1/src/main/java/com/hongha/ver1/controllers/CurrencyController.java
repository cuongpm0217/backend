package com.hongha.ver1.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hongha.ver1.dtos.CurrencyDTO;
import com.hongha.ver1.entities.Currency;
import com.hongha.ver1.services.CurrencyService;

@CrossOrigin(origins = "*",maxAge = 3600)
public class CurrencyController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/")
	@ResponseBody
	public List<CurrencyDTO> getAll() throws IOException {
		List<Currency> currencys = currencyService.getAll();
		return currencys.stream().map(currency -> mapper.map(currency, CurrencyDTO.class)).collect(Collectors.toList());
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public CurrencyDTO save(@RequestBody CurrencyDTO currencyDTO) {
		Currency currency = mapper.map(currencyDTO, Currency.class);
		Currency currencyCreated = currencyService.save(currency);
		return mapper.map(currencyCreated, CurrencyDTO.class);
	}

	@GetMapping("/uuid={uuid}")
	@ResponseBody
	public CurrencyDTO getOneByUUID(@PathVariable("uuid") UUID uuid) {
		return mapper.map(currencyService.findByUUID(uuid), CurrencyDTO.class);
	}

	@PutMapping("/update-currency={uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void updateByUUID(@PathVariable("uuid") UUID uuid, @RequestBody CurrencyDTO currencyDTO) {
		if (!Objects.equals(uuid, currencyDTO.getGenId())) {
			throw new IllegalArgumentException("UUIDs don't match");
		}
		Currency currency = mapper.map(currencyDTO, Currency.class);
		currencyService.updateByUUID(uuid, currency);
	}

	@DeleteMapping("/delete-currency={uuid}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUUID(@PathVariable("uuid") UUID uuid) {
		currencyService.deleteByUUID(uuid);
	}
}
