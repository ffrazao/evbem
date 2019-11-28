package br.gov.df.emater.comum;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

// Singleton utilitario para numeros
public final class UtilitarioNumero {

	private static UtilitarioNumero instance;

	public static UtilitarioNumero getInstance() {
		if (UtilitarioNumero.instance == null) {
			UtilitarioNumero.instance = new UtilitarioNumero();
		}
		return UtilitarioNumero.instance;
	}

	private final NumberFormat BIG_DECIMAL_FORMATO;

	private final NumberFormat BIG_DECIMAL_FORMATO_MOEDA;

	private final NumberFormat BIG_DECIMAL_FORMATO_PORCENTUAL;

	private final NumberFormat NUMBER_FORMATO;

	private final NumberFormat NUMBER_FORMATO_MOEDA;

	private final NumberFormat NUMBER_FORMATO_PORCENTUAL;

	private UtilitarioNumero() {
		this.NUMBER_FORMATO = NumberFormat.getNumberInstance(Locale.getDefault());
		this.NUMBER_FORMATO_MOEDA = NumberFormat.getCurrencyInstance(Locale.getDefault());
		this.NUMBER_FORMATO_PORCENTUAL = NumberFormat.getPercentInstance(Locale.getDefault());
		this.BIG_DECIMAL_FORMATO = NumberFormat.getNumberInstance(Locale.getDefault());
		if (this.BIG_DECIMAL_FORMATO instanceof DecimalFormat) {
			((DecimalFormat) this.BIG_DECIMAL_FORMATO).setParseBigDecimal(true);
		}
		this.BIG_DECIMAL_FORMATO_MOEDA = NumberFormat.getCurrencyInstance(Locale.getDefault());
		if (this.BIG_DECIMAL_FORMATO_MOEDA instanceof DecimalFormat) {
			((DecimalFormat) this.BIG_DECIMAL_FORMATO_MOEDA).setParseBigDecimal(true);
		}
		this.BIG_DECIMAL_FORMATO_PORCENTUAL = NumberFormat.getPercentInstance(Locale.getDefault());
		if (this.BIG_DECIMAL_FORMATO_PORCENTUAL instanceof DecimalFormat) {
			((DecimalFormat) this.BIG_DECIMAL_FORMATO_PORCENTUAL).setParseBigDecimal(true);
		}
	}

	public BigDecimal stringToBigDecimal(final String numero) {
		BigDecimal result = null;
		if ((numero == null) || (numero.trim().length() == 0)) {
			return result;
		}
		Number valor = null;
		try {
			valor = this.BIG_DECIMAL_FORMATO.parse(numero);
		} catch (final ParseException e) {
			try {
				valor = this.BIG_DECIMAL_FORMATO_MOEDA.parse(numero);
			} catch (final ParseException e1) {
				try {
					valor = this.BIG_DECIMAL_FORMATO_PORCENTUAL.parse(numero);
				} catch (final ParseException e2) {
					throw new RuntimeException(String.format("Erro ao converter o string [%s] em bigdecimal", numero),
							e);
				}
			}
		}
		if (valor instanceof BigDecimal) {
			result = (BigDecimal) valor;
		} else {
			result = new BigDecimal(valor.doubleValue());
		}
		return result;
	}

	public Double stringToDouble(final String numero) {
		final Number n = this.stringToNumber(numero);
		if (n instanceof Double) {
			return (Double) n;
		} else {
			return new Double(n.doubleValue());
		}
	}

	public Float stringToFloat(final String numero) {
		final Number n = this.stringToNumber(numero);
		if (n instanceof Float) {
			return (Float) n;
		} else {
			return new Float(n.floatValue());
		}
	}

	public Number stringToNumber(final String numero) {
		Number result = null;
		if ((numero == null) || (numero.trim().length() == 0)) {
			return result;
		}
		try {
			result = this.NUMBER_FORMATO.parse(numero);
		} catch (final ParseException e) {
			try {
				result = this.NUMBER_FORMATO_MOEDA.parse(numero);
			} catch (final ParseException e1) {
				try {
					result = this.NUMBER_FORMATO_PORCENTUAL.parse(numero);
				} catch (final ParseException e2) {
					throw new RuntimeException(String.format("Erro ao converter o string [%s] em number", numero), e);
				}
			}
		}
		return result;
	}
}