package com.namo.spring.db.mysql.domains.group.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.namo.spring.db.mysql.common.model.BaseTimeEntity;
import com.namo.spring.db.mysql.domains.user.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "group_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class GroupUser extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "custom_group_name", length = 50)
	private String customGroupName;

	@Builder
	public GroupUser(User user, Group group, String customGroupName) {
		this.user = Objects.requireNonNull(user, "user은 null일 수 없습니다.");
		this.group = Objects.requireNonNull(group, "group은 null일 수 없습니다.");
		this.customGroupName = customGroupName;
	}
}
