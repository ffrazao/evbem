package br.gov.df.emater.repositorio_principal.conversor;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.gov.df.emater.comum.UtilitarioData;

public class TimestampJsonDeserializer extends JsonDeserializer<Calendar> {

	public TimestampJsonDeserializer() {
	}

	@Override
	public Calendar deserialize(final JsonParser jp, final DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		Calendar result = null;
		final String vlr = jp.getText();
		try {
			result = UtilitarioData.getInstance().formataMilisegundos(vlr);
		} catch (final ParseException e1) {
			try {
				result = UtilitarioData.getInstance().formataTimestamp(vlr);
			} catch (final ParseException e2) {
				try {
					result = UtilitarioData.getInstance().formataDataHora(vlr);
				} catch (final ParseException e3) {
					try {
						result = UtilitarioData.getInstance().formataData(vlr);
					} catch (final ParseException e4) {
						new RuntimeException(e4);
					}
				}
			}
		}
		return result;
	}
}
