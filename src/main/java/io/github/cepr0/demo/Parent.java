package io.github.cepr0.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

/**
 * @author Sergei Poznanski, 2018-03-18
 */
@Data
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "parents")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicInsert
@DynamicUpdate
public class Parent implements Serializable {

	@Id @GeneratedValue
	private Integer id;

	@Column(length = 32, nullable = false)
	private String name;

	@Enumerated(STRING)
	@Column(length = 6, nullable = false)
	private Gender gender;

	@Enumerated(STRING)
	@ElementCollection
	@JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_role_parent"))
	@Column(name = "role", length = 5)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@BatchSize(size = 20)
	private Set<Role> roles;

	public Parent(String name, Gender gender, Role role, Role... roles) {
		this.name = name;
		this.gender = gender;
		this.roles = EnumSet.of(role, roles);
	}
}
