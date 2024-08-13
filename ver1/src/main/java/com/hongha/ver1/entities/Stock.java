package com.hongha.ver1.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@EntityListeners(StockListener.class)
@Table(name = "_stock")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock extends BaseEntityAudit{	
	private static final long serialVersionUID = -6048510411049604853L;
	@Column(nullable = false,name="product_id")
	private long productId;
	@Column
	private int quantity;
	@Column(name = "location_item")
	private String locationItem;
	@Column(nullable = false)
	private long branchId;
	@Column
	private int guarantee;
	@Column
	private long carrier;//who delivery product to stock
}
