package br.gov.df.emater.repositorio_principal.conversor;

import java.io.IOException;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PointJsonSerializer extends JsonSerializer<Point> {

	@Override
	public void serialize(final Point value, final JsonGenerator jgen, final SerializerProvider provider)
			throws IOException, JsonProcessingException {

		String jsonValue = "null";
		try {
			if (value != null) {
				final double lat = value.getY();
				final double lon = value.getX();
				jsonValue = String.format("POINT (%s %s)", lat, lon);
			}
		} catch (final Exception e) {
		}

		jgen.writeString(jsonValue);
	}

}