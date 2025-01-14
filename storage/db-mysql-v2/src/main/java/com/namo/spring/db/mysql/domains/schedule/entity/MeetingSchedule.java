package com.namo.spring.db.mysql.domains.schedule.entity;

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
import com.namo.spring.db.mysql.domains.category.entity.Category;
import com.namo.spring.db.mysql.domains.group.entity.Group;
import com.namo.spring.db.mysql.domains.user.entity.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "meeting_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class MeetingSchedule extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "schedule_id", nullable = false)
	private Schedule schedule;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "custom_title", length = 50)
	private String customTitle;

	@Builder
	public MeetingSchedule(User user, Schedule schedule, Category category, Group group, String customTitle) {
		this.user = Objects.requireNonNull(user, "user은 null일 수 없습니다.");
		this.schedule = Objects.requireNonNull(schedule, "schedule은 null일 수 없습니다.");
		this.category = Objects.requireNonNull(category, "category은 null일 수 없습니다.");
		this.group = Objects.requireNonNull(group, "group은 null일 수 없습니다.");
		this.customTitle = customTitle;
	}

}
