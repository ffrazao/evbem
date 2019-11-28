package br.gov.df.emater.comum;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class UtilitarioData {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	private static UtilitarioData instance;

	private static final SimpleDateFormat MILISEGUNDOS_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

	public static UtilitarioData getInstance() {
		if (UtilitarioData.instance == null) {
			UtilitarioData.instance = new UtilitarioData();
		}
		return UtilitarioData.instance;
	}

	public static void main(final String[] args) throws ParseException {
		final Calendar data = UtilitarioData.getInstance().formataData("01/04/2014");
		System.out.println(UtilitarioData.getInstance().formataData(data));

		final Calendar dataHora = UtilitarioData.getInstance().formataDataHora("01/04/2014 23:09:22");
		System.out.println(UtilitarioData.getInstance().formataDataHora(dataHora));
	}

	private UtilitarioData() {
	}

	public synchronized String formataData(final Calendar date) {
		return UtilitarioData.DATE_FORMAT.format(date.getTime());
	}

	public synchronized Calendar formataData(final String date) throws ParseException {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(UtilitarioData.DATE_FORMAT.parse(date));
		return calendar;
	}

	public synchronized String formataDataHora(final Calendar date) {
		return UtilitarioData.DATE_TIME_FORMAT.format(date.getTime());
	}

	public synchronized Calendar formataDataHora(final String date) throws ParseException {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(UtilitarioData.DATE_TIME_FORMAT.parse(date));
		return calendar;
	}

	public synchronized String formataMilisegundos(final Calendar date) {
		return UtilitarioData.MILISEGUNDOS_FORMAT.format(date.getTime());
	}

	public synchronized Calendar formataMilisegundos(final String date) throws ParseException {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(UtilitarioData.MILISEGUNDOS_FORMAT.parse(date));
		return calendar;
	}

	public synchronized String formataTimestamp(final Calendar date) {
		return UtilitarioData.TIMESTAMP_FORMAT.format(date.getTime());
	}

	public synchronized Calendar formataTimestamp(final String date) throws ParseException {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(UtilitarioData.TIMESTAMP_FORMAT.parse(date));
		return calendar;
	}

	public synchronized Calendar sqlTimestampToCalendar(final Timestamp date) {
		if (date == null) {
			return null;
		}
		final Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar;
	}

}
