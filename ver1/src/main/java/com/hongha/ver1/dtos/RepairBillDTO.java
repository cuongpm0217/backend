package com.hongha.ver1.dtos;


import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairBillDTO extends BaseDTO {
	private String code;
	private String detail;
	private CustomerDTO customerDTO;	
	private LocalDateTime startedDate;
	private LocalDateTime endDate;
	private VehicleDTO vehicleDTO;
	private BranchDTO branchDTO;
	private EmployeeDTO employeeDTO;
	private SurrogateDTO surrogateDTO;
	private double total;
	private int no;
	private List<RepairBillItemDTO> listItem;
}
