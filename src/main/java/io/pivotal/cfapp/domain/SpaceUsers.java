package io.pivotal.cfapp.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@JsonPropertyOrder({"organization", "space", "auditors", "developers", "managers", "users", "user-count"})
public class SpaceUsers {

	@Id
	@JsonIgnore
	private Long pk;

	@JsonProperty("organization")
	private String organization;

	@JsonProperty("space")
	private String space;

	@Default
	@JsonProperty("auditors")
	private List<String> auditors = new ArrayList<>();

	@Default
	@JsonProperty("developers")
	private List<String> developers = new ArrayList<>();

	@Default
	@JsonProperty("managers")
	private List<String> managers = new ArrayList<>();

	@JsonCreator
	public SpaceUsers(
		Long pk,
		@JsonProperty("organization") String organization,
		@JsonProperty("space") String space,
		@JsonProperty("auditors") List<String> auditors,
		@JsonProperty("developers") List<String> developers,
		@JsonProperty("managers") List<String> managers
	) {
		this.pk = pk;
		this.organization = organization;
		this.space = space;
		this.auditors = auditors;
		this.developers = developers;
		this.managers = managers;
	}

	@JsonProperty("users")
	public Set<String> getUsers() {
		Set<String> users = new HashSet<>();
		users.addAll(auditors);
		users.addAll(developers);
		users.addAll(managers);
		return users;
	}

	@JsonProperty("user-count")
	public Integer getUserCount() {
		return getUsers().size();
	}
}
