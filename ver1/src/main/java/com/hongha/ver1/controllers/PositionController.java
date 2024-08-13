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

import com.hongha.ver1.dtos.PositionDTO;
import com.hongha.ver1.entities.Position;
import com.hongha.ver1.services.PositionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/position")
public class PositionController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PositionService positionService;

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAll(@RequestParam(required = false) String query,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "level") String sortBy, @RequestParam(defaultValue = "asc") String sortType) {
		try {
			page=page-1;
			Page<Position> positions ;
			if (query != null) {
				positions = positionService.findBySearchText(query, page, size, sortBy, sortType);
			}else {
				positions = positionService.getAll(page, size, sortBy, sortType);
			}
			// set No > DTO
			List<PositionDTO> positionDTOs = positions.stream()
					.map(position -> mapper.map(position, PositionDTO.class)).collect(Collectors.toList());
			for (PositionDTO positionDTO : positionDTOs) {
				switch(positionDTO.getLevel()) {
				case 1,2:{
					positionDTO.setStyle("level1");
					break;
				}
				case 3,4,5,6,7:{
					positionDTO.setStyle("level2");
					break;
				}
				default:{
					positionDTO.setStyle("level3");
					break;
				}
				}
			}

			Map<String, Object> response = new HashMap<>();
			response.put("positions", positionDTOs);
			response.put("currentPage", positions.getNumber());
			response.put("totalItems", positions.getTotalElements());
			response.put("totalPages", positions.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> save(@RequestBody PositionDTO positionDTO) {
		Position position = mapper.map(positionDTO, Position.class);
		Position positionCreated = positionService.save(position);
		PositionDTO result = mapper.map(positionCreated, PositionDTO.class);
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
		response.put("position", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	// use UUID
	@GetMapping(value = "/{uuid}")
	public ResponseEntity<Map<String, Object>> getOneByUUID(@PathVariable("uuid") UUID uuid) {
		PositionDTO result = mapper.map(positionService.findByUUID(uuid), PositionDTO.class);
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("position", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@PutMapping(value = "/update-{uuid}")
	public ResponseEntity<Map<String, Object>> updateByUUID(@PathVariable("uuid") UUID uuid,
			@RequestBody PositionDTO positionDTO) {
		Map<String, Object> response = new HashMap<>();
		String msg = "";
		HttpStatus status;
		if (!Objects.equals(uuid, positionDTO.getGenId())) {
			status = HttpStatus.NO_CONTENT;
			msg = "Not match " + String.valueOf(uuid);
			response.put("message", msg);
			return new ResponseEntity<>(response, status);
		}
		Position position = mapper.map(positionDTO, Position.class);
		PositionDTO result = mapper.map(positionService.updateByUUID(uuid, position), PositionDTO.class);

		if (result != null) {
			status = HttpStatus.OK;
			msg = "Found:" + result.getName();
		} else {
			status = HttpStatus.NOT_FOUND;
			msg = "Not found " + String.valueOf(uuid);
		}
		response.put("position", result);
		response.put("message", msg);
		return new ResponseEntity<>(response, status);
	}

	@DeleteMapping(value = "/delete-{uuid}")
	public ResponseEntity<Map<String, Object>> deleteByUUID(@PathVariable("uuid") UUID uuid) {
		boolean result = positionService.deleteByUUID(uuid);
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
