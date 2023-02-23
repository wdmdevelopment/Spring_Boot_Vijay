package com.employee.attendance.serviceimpl;

import java.time.LocalDate;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.employee.attendance.dto.AttendanceDetails;
import com.employee.attendance.dto.AttendanceRequest;
import com.employee.attendance.dto.OverAllUserReport;
import com.employee.attendance.entity.Attendance;
import com.employee.attendance.entity.Employee;
import com.employee.attendance.entity.LeaveCalendar;
import com.employee.attendance.entity.User;
import com.employee.attendance.exception.AttendaceIdNotFoundException;
import com.employee.attendance.exception.AttendaceInputMissingException;
import com.employee.attendance.exception.EmployeeIdNotFoundException;
import com.employee.attendance.exception.EmployeeInputMissingException;
import com.employee.attendance.exception.UserInputMissingException;
import com.employee.attendance.repository.AttentdanceRepository;
import com.employee.attendance.repository.EmployeeRepository;
import com.employee.attendance.repository.LeaveRepository;
import com.employee.attendance.repository.UserRepository;
import com.employee.attendance.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttentdanceRepository attendanceRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	LeaveRepository leaveRepository;

	@Autowired
	UserRepository userRepository;

	@Value("${attendance.per.day.hour}")
	private long perDayHour;

	public Attendance saveAttendance(AttendanceRequest attendanceRequest) {
		try {
		
			
			Employee employee =employeeRepository.findById(attendanceRequest.getEmployeeId())
					.orElseThrow(() -> new EmployeeIdNotFoundException("Employee not exist with id: " + attendanceRequest.getEmployeeId()));
			
			User user =userRepository.findById(attendanceRequest.getUserId());
			Attendance attendance = new Attendance();
			attendance.setDate(attendanceRequest.getDate());
			attendance.setStartTime(attendanceRequest.getStartTime());
			attendance.setEndTime(attendanceRequest.getEndTime());
			attendance.setEmployee(employee);
			attendance.setUser(user);
			

			return attendanceRepository.save(attendance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AttendaceInputMissingException("Exception occured in save Employee"+e.getMessage());
		}
	}

	

	public List<AttendanceDetails> perDayDetails(long empId) {
		try {
			
			Employee employee =employeeRepository.findById(empId)
					.orElseThrow(() -> new EmployeeIdNotFoundException("Employee not exist with id: " + empId));
			List<Attendance> attList = attendanceRepository.findByEmployeeId(empId);
			
			LocalDate currentDate = LocalDate.now();
			List<LocalDate> list = getDatesBetweenUsingJava(employee.getJoinDate(), currentDate);
			List<AttendanceDetails> perDayDetails = new ArrayList<>();

			long perDay = perDayHour * 3600;

			long hoursPerDayHour = TimeUnit.SECONDS.toHours(perDay);
			long minutePerDayHour = TimeUnit.SECONDS.toMinutes(perDay) - (TimeUnit.SECONDS.toHours(perDay) * 60);
			long secondPerDayHour = TimeUnit.SECONDS.toSeconds(perDay) - (TimeUnit.SECONDS.toMinutes(perDay) * 60);

			

				for (Attendance e : attList) {

					AttendanceDetails attendance = new AttendanceDetails();
					attendance.setDate(e.getDate());
					attendance.setStartTime(e.getStartTime());
					attendance.setEndTime(e.getEndTime());
					attendance.setAttendanceId(e.getId());
					attendance.setTargetHour(hoursPerDayHour + " :" + minutePerDayHour + " :" + secondPerDayHour);
					
					
					long secs = getTimeIntervalInSecs(e.getStartTime(), e.getEndTime());

					long hours = TimeUnit.SECONDS.toHours(secs);
					long minute = TimeUnit.SECONDS.toMinutes(secs) - (TimeUnit.SECONDS.toHours(secs) * 60);
					long second = TimeUnit.SECONDS.toSeconds(secs) - (TimeUnit.SECONDS.toMinutes(secs) * 60);
					
					
					attendance.setActualHour(hours + " : " + minute + " : " + second);
					attendance.setEmployeeId(empId);


					if (perDay < secs) {

						attendance.setStatus("Over Time");

					} else {

						attendance.setStatus("Under Time");

					}

					perDayDetails.add(attendance);

				}
			

			return perDayDetails;
		} catch (Exception e) {
			e.printStackTrace();

			throw new AttendaceInputMissingException(e.getMessage());
		}
	}

	public List<OverAllUserReport> getAllAttendance(long empId) {

		Employee employee =employeeRepository.findById(empId)
				.orElseThrow(() -> new EmployeeIdNotFoundException("Employee not exist with id: " + empId));
		List<Attendance> attList = attendanceRepository.findByEmployeeId(empId);

//		LocalDate currentDate = LocalDate.now();

//		List<LocalDate> list = getDatesBetweenUsingJava(employee.getJoinDate(), currentDate);

		long overAllSecs = 0;
//		for (LocalDate localDate : list) {
			for (Attendance e : attList) {
//				if (e.getDate().equals(localDate)) {
					long secs = getTimeIntervalInSecs(e.getStartTime(), e.getEndTime());

					overAllSecs = overAllSecs + secs;
//				}
			}
//		}

		long overAllTotalHours = attList.size() * perDayHour * 3600;

		long hoursPerDayHour = TimeUnit.SECONDS.toHours(overAllTotalHours);
		long minutePerDayHour = TimeUnit.SECONDS.toMinutes(overAllTotalHours)
				- (TimeUnit.SECONDS.toHours(overAllTotalHours) * 60);
		long secondPerDayHour = TimeUnit.SECONDS.toSeconds(overAllTotalHours)
				- (TimeUnit.SECONDS.toMinutes(overAllTotalHours) * 60);
		String targetHourEmployee = hoursPerDayHour + ":" + minutePerDayHour + ":" + secondPerDayHour;

		long hours = TimeUnit.SECONDS.toHours(overAllSecs);
		long minute = TimeUnit.SECONDS.toMinutes(overAllSecs) - (TimeUnit.SECONDS.toHours(overAllSecs) * 60);
		long second = TimeUnit.SECONDS.toSeconds(overAllSecs) - (TimeUnit.SECONDS.toMinutes(overAllSecs) * 60);

		String actualSpendHourEmployee = hours + ":" + minute + ":" + second;
		System.out.println(actualSpendHourEmployee);

		long balanceHours = hours-hoursPerDayHour ;
		long balanceMinutes = minute - minutePerDayHour;
		long balanceSecounds = second - secondPerDayHour;
		String totalBalanceHours = balanceHours + ":" + balanceMinutes + ":" + balanceSecounds;
		System.out.println(totalBalanceHours);

		List<OverAllUserReport> overAlluserReport = new ArrayList<>();

		OverAllUserReport userReport = new OverAllUserReport();

//		userReport.setEmployeeName(attList.get(0).getEmployee().getUser().getName());
		userReport.setActualHoursSpenByEmployee(actualSpendHourEmployee);
		userReport.setOverTotalHours(targetHourEmployee);
		userReport.setBalanceHour(totalBalanceHours);
		
		System.out.println(overAllTotalHours+"=========="+overAllSecs);
		if (overAllTotalHours < overAllSecs) {

			userReport.setStatus("Over Time");
		} else {

			userReport.setStatus("Under Time");
		}
		overAlluserReport.add(userReport);
		System.out.println(overAllTotalHours + "      " + overAllSecs);

		return overAlluserReport;

	}

	public long getTimeIntervalInSecs(LocalTime localTime, LocalTime localTime2) {
		return localTime.until(localTime2, ChronoUnit.SECONDS);
	}

	public List<LocalDate> getDatesBetweenUsingJava(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.filter(e -> !isWeekEnd(e)).filter(f -> !getLeaveCalendarDates().contains(f))
				.collect(Collectors.toList());
	}

	public boolean isWeekEnd(LocalDate localDate) {
		String dayOfWeek = localDate.getDayOfWeek().toString();
		if ("SUNDAY".equalsIgnoreCase(dayOfWeek)) {
			return true;
		}
		return false;
	}

	public List<LocalDate> getLeaveCalendarDates() {
		List<LeaveCalendar> findAll = leaveRepository.findAll();
		List<LocalDate> collect = findAll.stream().map(e -> e.getHolidayLeaveDate()).collect(Collectors.toList());

		return collect;

	}

	public List<OverAllUserReport> getOverAllDetails() {
		try {

			List<Attendance> listAtt = attendanceRepository.findAll();

			Map<Object, List<Attendance>> collect = listAtt.stream()
					.collect(Collectors.groupingBy(p -> p.getEmployee().getId()));

			System.out.println(collect);

			List<OverAllUserReport> overAlluserReport = new ArrayList<>();
			for (Map.Entry<Object, List<Attendance>> entry : collect.entrySet()) {
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

				OverAllUserReport report = getEmployeeAttendanceStatus(entry);
				overAlluserReport.add(report);
			}

			return overAlluserReport;

		} catch (Exception e) {
			e.printStackTrace();
			throw new AttendaceInputMissingException(e.getMessage());
		}

	}

	private OverAllUserReport getEmployeeAttendanceStatus(Entry<Object, List<Attendance>> entry) {
//		LocalDate currentDate = LocalDate.now();
//		List<LocalDate> list = getDatesBetweenUsingJava(entry.getValue().get(0).getEmployee().getJoinDate(),
//				currentDate);

		long overAllSecs = 0;
//		for (LocalDate localDate : list) {
			for (Attendance e : entry.getValue()) {
//				if (e.getDate().equals(localDate)) {
					long secs = getTimeIntervalInSecs(e.getStartTime(), e.getEndTime());

					overAllSecs = overAllSecs + secs;
//				}
//			}
		}

		long overAllTotalHours = entry.getValue().size() * perDayHour * 3600;

		long actualHoursSpenByEmployee = overAllSecs;

		long hoursPerDayHour = TimeUnit.SECONDS.toHours(overAllTotalHours);
		long minutePerDayHour = TimeUnit.SECONDS.toMinutes(overAllTotalHours)
				- (TimeUnit.SECONDS.toHours(overAllTotalHours) * 60);
		long secondPerDayHour = TimeUnit.SECONDS.toSeconds(overAllTotalHours)
				- (TimeUnit.SECONDS.toMinutes(overAllTotalHours) * 60);

		String targetHourEmployee = hoursPerDayHour + ":" + minutePerDayHour + ":" + secondPerDayHour;
		System.out.println(targetHourEmployee);

		long hours = TimeUnit.SECONDS.toHours(actualHoursSpenByEmployee);
		long minute = TimeUnit.SECONDS.toMinutes(actualHoursSpenByEmployee)
				- (TimeUnit.SECONDS.toHours(actualHoursSpenByEmployee) * 60);
		long second = TimeUnit.SECONDS.toSeconds(actualHoursSpenByEmployee)
				- (TimeUnit.SECONDS.toMinutes(actualHoursSpenByEmployee) * 60);

		String actualSpendHourEmployee = hours + ":" + minute + ":" + second ;
		System.out.println(actualSpendHourEmployee);

		long balanceHours = hours-hoursPerDayHour ;
		long balanceMinutes = minute - minutePerDayHour;
		long balanceSecounds = second - secondPerDayHour;
		String totalBalanceHours = balanceHours + ":" +balanceMinutes + ":" + balanceSecounds;
		System.out.println(totalBalanceHours);

		List<OverAllUserReport> overAlluserReport = new ArrayList<>();

		OverAllUserReport userReport = new OverAllUserReport();
		userReport.setEmployeeName(entry.getValue().get(0).getUser().getName());
		userReport.setActualHoursSpenByEmployee(actualSpendHourEmployee);
		userReport.setOverTotalHours(targetHourEmployee);
		userReport.setBalanceHour(totalBalanceHours);

		if (overAllSecs > overAllTotalHours) {
			userReport.setStatus("Over Time");
		} else {
			userReport.setStatus("Under Time");
		}
		overAlluserReport.add(userReport);
		return userReport;
	}

	public List<Attendance> getAllDetails() {
		return attendanceRepository.findAll();
	}

	public Attendance getAttendanceById(long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new AttendaceIdNotFoundException("Attendance not exist with id: " + id));
		return attendance;
	}

	public Attendance updateAttendance(long id, AttendanceRequest attendanceRequest) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElseThrow(() -> new AttendaceIdNotFoundException("Attendance not exist with id: " + id));
		attendance.setDate(attendanceRequest.getDate());
		attendance.setStartTime(attendanceRequest.getStartTime());
		attendance.setEndTime(attendanceRequest.getEndTime());

		return attendanceRepository.save(attendance);
	}

	public void deleteAttendance(long id) {

		Optional<Attendance> attendance = attendanceRepository.findById(id);
		if (attendance.isPresent()) {
			attendanceRepository.deleteById(id);
		} else {
			throw new AttendaceIdNotFoundException("Attendance not exist with id: " + id);
		}

	}





}