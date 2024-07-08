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

import com.hongha.ver1.dtos.SurrogateDTO;
import com.hongha.ver1.entities.Surrogate;
import com.hongha.ver1.services.SurrogateService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/surrogate")
public class SurrogateController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private SurrogateService surrogateService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) String phone, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "name") String sortBy,
			@RequestParam(defaultValue = "asc") String sortType) {
		try {
			Page<Surrogate> surrogates = surrogateService.getAll(page, size, sortBy, sortType);
			if (name != null || name != "") {
				surrogates = surrogateService.findByName(name, page, size, sortBy, sortType);
			}
			if (phone != null || phone != "") {
				surrogates = surrogateService.findByPhone(phone, page, size, sortBy, sortType);
			}
			// set No > DTO
			List<SurrogateDTO> surrogateDTOs = surrogates.stream()
					.map(surrogate -> mapper.map(surrogate, SurrogateDTO.class)).collect(Collectors.toList());
			for (int i = 0; i < surrogateDTOs.size(); i++) {
				SurrogateDTO surrogateDTO = surrogateDTOs.get(i);
				surrogateDTO.setNo(i + 1);
				if (surrogateDTO.isGender()) {
					surrogateDTO.setTittle("Mr.");
				} else {
					surrogateDTO.setTittle("Ms.");
				}
				surrogateDTO.setName(surrogateDTO.getTittle() + surrogateDTO.getName());
			}

			Map<String, Object> response = new HashMap<>();
			response.put("surrogates", surrogateDTOs);
			response.put("currentPage", surrogates.getNumber());
			response.put("totalItems", surrogates.getTotalElements());
			response.put("totalPages", surrogates.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody SurrogateDTO surrogateDTO) {
		Surrogate surrogate = mapper.map(surrogateDTO, Surrogate.class);
		Surrogate surrogateCreated = surrogateService.save(surrogate);
		SurrogateDTO result = mapper.map(surrogateCreated, SurrogateDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			msg = "create Success";
			status = HttpStatus.CREATED;
		} else {
			msg = "create Fail";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		response.put("surrogate", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		SurrogateDTO result = mapper.map(surrogateService.findByUUID(uuid), SurrogateDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			if (result.isGender()) {
				result.setTittle("Mr.");
			} else {
				result.setTittle("Ms.");
			}
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("surrogate", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody SurrogateDTO surrogateDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, surrogateDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Surrogate surrogate = mapper.map(surrogateDTO, Surrogate.class);
		SurrogateDTO result = mapper.map(surrogateService.updateByUUID(uuid, surrogate), SurrogateDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("surrogate", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = surrogateService.deleteByUUID(uuid);
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
