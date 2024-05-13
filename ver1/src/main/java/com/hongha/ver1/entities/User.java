package com.hongha.ver1.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="_user")
public class User extends BaseEntityAudit{
	@Column(unique = true,nullable = false,updatable = false)
	private String username;
	@Column(nullable = false)
	private String password="QWE098&^%lkjGHJ)(*456";//set pass default
	@Column(unique = true,nullable = false)
	private String email;
	@Column
	private boolean gender = true;
	@Column
	private Date dob;
	@Column
	private String avatar;
	@Column
	private String address1 = "TT.Ngô Đồng, Giao Thủy, Nam Định";
	@Column
	private String address2;
	@Column
	private String phone1;
	@Column
	private String phone2;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role"
            ,joinColumns =@JoinColumn(name="user_id")
            ,inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles= new HashSet<>();
    public User(String username,String email,String password){
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }
}
