package org.dnd4.yorijori.domain.user.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dnd4.yorijori.domain.user_follow.entity.UserFollow;

@Getter
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;

	@OneToMany(mappedBy = "follower")
	private List<UserFollow> follower = new ArrayList<>();

	@OneToMany(mappedBy = "following")
	private List<UserFollow> following = new ArrayList<>();

}
