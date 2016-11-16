package br.com.devmedia.agenda.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtil {

	private static final String DATE_PATTERN = "dd/MM/yyyy";

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DateUtil.DATE_PATTERN);

	public static String format(final LocalDate date) {

		if (date == null) {
			return null;
		}
		return DateUtil.DATE_FORMATTER.format(date);
	}

	public static LocalDate parse(final String dateString) {

		try {
			return DateUtil.DATE_FORMATTER.parse(dateString, LocalDate::from);
		} catch (final DateTimeParseException e) {
			return null;
		}
	}

	public static boolean validDate(final String dateString) {

		return DateUtil.parse(dateString) != null;
	}

	public static LocalDate from(Date date) {

		if (date == null) {
			return null;
		}
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}

	public static Date from(LocalDate localdate) {

		if (localdate == null) {
			return null;
		}
		return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
