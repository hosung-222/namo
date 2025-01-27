package com.namo.spring.db.mysql.domains.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.util.StringUtils;

import com.namo.spring.db.mysql.common.model.BaseTimeEntity;
import com.namo.spring.db.mysql.domains.schedule.type.Location;
import com.namo.spring.db.mysql.domains.schedule.type.Period;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Schedule extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "title", nullable = false, length = 50)
	private String title;

	@Embedded
	private Period period;

	@Embedded
	private Location location;

	@Builder
	public Schedule(String title, Period period, Location location) {
		if (!StringUtils.hasText(title))
			throw new IllegalArgumentException("title은 null이거나 빈 문자열일 수 없습니다.");

		this.title = title;
		this.period = period;
		this.location = location;
	}

	public Schedule of(String title, Period period, Location location) {
		return Schedule.builder()
				.title(title)
				.period(period)
				.location(location)
				.build();
	}
}
