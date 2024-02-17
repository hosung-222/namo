package com.example.namo2.domain.moim.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moim_schedule_alarm")
@Getter
@NoArgsConstructor
public class MoimScheduleAlarm {
    @Id @Column(name = "moim_schedule_alarm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "alarm_date")
    private Integer alarmDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moim_schedule_user_id")
    private MoimScheduleAndUser moimScheduleAndUser;

    @Builder
    public MoimScheduleAlarm(Long id, Integer alarmDate, MoimScheduleAndUser moimScheduleAndUser) {
        this.id = id;
        this.alarmDate = alarmDate;
        this.moimScheduleAndUser = moimScheduleAndUser;
    }
}
