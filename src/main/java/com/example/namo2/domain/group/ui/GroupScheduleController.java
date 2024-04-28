package com.example.namo2.domain.group.ui;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.namo2.domain.group.application.MoimScheduleFacade;
import com.example.namo2.domain.group.ui.dto.MoimScheduleRequest;
import com.example.namo2.domain.group.ui.dto.MoimScheduleResponse;
import com.example.namo2.global.annotation.swagger.ApiErrorCodes;
import com.example.namo2.global.common.response.BaseResponse;
import com.example.namo2.global.common.response.BaseResponseStatus;
import com.example.namo2.global.utils.Converter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "7. Schedule (그룹)", description = "모임 일정 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/group/schedules")
public class GroupScheduleController {
	private final MoimScheduleFacade moimScheduleFacade;
	private final Converter converter;

	@Operation(summary = "그룹 스케쥴 생성", description = "그룹 스케쥴 생성 API")
	@PostMapping("")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse<Long> createMoimSchedule(
			@Valid @RequestBody MoimScheduleRequest.PostMoimScheduleDto scheduleReq
	) {
		Long scheduleId = moimScheduleFacade.createSchedule(scheduleReq);
		return new BaseResponse(scheduleId);
	}

	@Operation(summary = "그룹 스케쥴 수정", description = "그룹 스케쥴 수정 API")
	@PatchMapping("")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse<Long> modifyMoimSchedule(
			@Valid @RequestBody MoimScheduleRequest.PatchMoimScheduleDto scheduleReq
	) {
		moimScheduleFacade.modifyMoimSchedule(scheduleReq);
		return BaseResponse.ok();
	}

	@Operation(summary = "그룹 스케쥴 카테고리 수정", description = "그룹 스케쥴 카테고리 수정 API")
	@PatchMapping("category")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse<Long> modifyMoimScheduleCategory(
			@Valid @RequestBody MoimScheduleRequest.PatchMoimScheduleCategoryDto scheduleReq,
			HttpServletRequest request
	) {
		moimScheduleFacade.modifyMoimScheduleCategory(scheduleReq, (Long)request.getAttribute("userId"));
		return BaseResponse.ok();
	}

	@Operation(summary = "그룹 스케쥴 삭제", description = "그룹 스케쥴 삭제 API")
	@DeleteMapping("/{moimScheduleId}")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse<Long> removeMoimSchedule(
			@PathVariable Long moimScheduleId,
			HttpServletRequest request
	) {
		moimScheduleFacade.removeMoimSchedule(moimScheduleId, (Long)request.getAttribute("userId"));
		return BaseResponse.ok();
	}

	@Operation(summary = "월간 그룹 스케쥴 조회", description = "월간 그룹 스케쥴 조회 API")
	@GetMapping("/{moimId}/{month}")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse<MoimScheduleResponse.MoimScheduleDto> getMonthMoimSchedules(
			@PathVariable("moimId") Long moimId,
			@PathVariable("month") String month,
			HttpServletRequest request
	) {
		List<LocalDateTime> localDateTimes = converter.convertLongToLocalDateTime(month);
		List<MoimScheduleResponse.MoimScheduleDto> schedules = moimScheduleFacade.getMonthMoimSchedules(moimId,
				localDateTimes, (Long)request.getAttribute("userId"));
		return new BaseResponse(schedules);
	}

	@Operation(summary = "모든 그룹 스케쥴 조회", description = "모든 그룹 스케쥴 조회 API")
	@GetMapping("/{moimId}/all")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse<MoimScheduleResponse.MoimScheduleDto> getAllMoimSchedules(
			@PathVariable("moimId") Long moimId,
			HttpServletRequest request
	) {
		List<MoimScheduleResponse.MoimScheduleDto> schedules
				= moimScheduleFacade.getAllMoimSchedules(moimId, (Long)request.getAttribute("userId"));
		return new BaseResponse(schedules);
	}

	@Operation(summary = "그룹 스케쥴 생성 알람", description = "그룹 스케쥴 생성 알람 API")
	@PostMapping("/alarm")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse createMoimScheduleAlarm(
			@Valid @RequestBody MoimScheduleRequest.PostMoimScheduleAlarmDto postMoimScheduleAlarmDto,
			HttpServletRequest request
	) {
		moimScheduleFacade.createMoimScheduleAlarm(postMoimScheduleAlarmDto, (Long)request.getAttribute("userId"));
		return BaseResponse.ok();
	}

	@Operation(summary = "그룹 스케쥴 변경 알람", description = "그룹 스케쥴 변경 알람 API")
	@PatchMapping("/alarm")
	@ApiErrorCodes({
			BaseResponseStatus.EMPTY_ACCESS_KEY,
			BaseResponseStatus.EXPIRATION_ACCESS_TOKEN,
			BaseResponseStatus.EXPIRATION_REFRESH_TOKEN,
			BaseResponseStatus.INTERNET_SERVER_ERROR
	})
	public BaseResponse modifyMoimScheduleAlarm(
			@Valid @RequestBody MoimScheduleRequest.PostMoimScheduleAlarmDto postMoimScheduleAlarmDto,
			HttpServletRequest request
	) {
		moimScheduleFacade.modifyMoimScheduleAlarm(postMoimScheduleAlarmDto, (Long)request.getAttribute("userId"));
		return BaseResponse.ok();
	}
}
