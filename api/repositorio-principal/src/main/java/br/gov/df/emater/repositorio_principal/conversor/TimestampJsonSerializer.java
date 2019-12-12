package br.gov.df.emater.repositorio_principal.conversor;

import java.io.IOException;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.gov.df.emater.comum.UtilitarioData;

public class TimestampJsonSerializer extends JsonSerializer<Calendar> {

	@Override
	public void serialize(final Calendar date, final JsonGenerator gen, final SerializerProvider provider)
			throws IOException, JsonProcessingException {
		if (date != null) {
			gen.writeString(UtilitarioData.getInstance().formataTimestamp(date));
		}
	}
}