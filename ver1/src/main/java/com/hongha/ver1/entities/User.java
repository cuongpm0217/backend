package com.hongha.ver1.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.hongha.ver1.entities.listeners.UserListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EntityListeners(UserListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="_user")
public class User extends BaseEntityAudit{
	
	private static final long serialVersionUID = 1L;
	@Column(unique = true,nullable = false,updatable = false)
	private String username;
	@Column(nullable = false)
	@Builder.Default
	private String password="QWE098&^%lkjGHJ)(*456";//set pass default
	@Column(unique = true,nullable = false)
	private String email;
	@Column(name = "full_name")
	private String fullName;
	@Column
	@Builder.Default
	private boolean gender = true;
	@Column
	private Date dob;
	@Column
	private String avatar;
	@Column
	@Builder.Default
	private String address1 = "TT.Ngô Đồng, Giao Thủy, Nam Định";
	@Column
	private String address2;
	@Column
	private String phone1;
	@Column
	private String phone2;
	@Column(name="branch_id")
	private long branchId;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role"
            ,joinColumns =@JoinColumn(name="user_id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Builder.Default
    private Set<Role> roles= new HashSet<Role>();	
    public User(String username,String email,String password,long branchId){
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setBranchId(branchId);
    }
}
