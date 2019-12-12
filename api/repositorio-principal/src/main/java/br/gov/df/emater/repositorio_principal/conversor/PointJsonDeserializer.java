package br.gov.df.emater.repositorio_principal.conversor;

import java.io.IOException;

import org.springframework.data.geo.Point;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PointJsonDeserializer extends JsonDeserializer<Point> {

	@Override
	public Point deserialize(final JsonParser jp, final DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		try {
			final String text = jp.getText();
			if ((text == null) || (text.length() <= 0)) {
				return null;
			}

			final String[] coordinates = text.toUpperCase().replaceFirst("POINT ?\\(", "").replaceFirst("\\)", "")
					.split(" ");
			final double lat = Double.parseDouble(coordinates[0]);
			final double lon = Double.parseDouble(coordinates[1]);

			final Point point = new Point(lat, lon);
			return point;
		} catch (final Exception e) {
			return null;
		}
	}

}