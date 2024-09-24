package com.cbs.authentication.model;

import java.time.ZonedDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The class which represent the roles entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "RoleBuilder")
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    private String description;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
