package br.gov.df.emater.comum;

import java.util.Collection;

// Singleton utilitario para Strings
public final class UtilitarioString {

	public static String collectionToString(final Collection<?> objetos) {
		return UtilitarioString.collectionToString(objetos, false);
	}

	public static String collectionToString(final Collection<?> objetos, final boolean stringValue) {
		final StringBuilder result = new StringBuilder();
		if (objetos == null) {
			result.append("null");
		} else {
			for (final Object o : objetos) {
				if (result.length() > 0) {
					result.append(",");
				}
				if (stringValue) {
					result.append("\'").append(o).append("\'");
				} else {
					result.append(o);
				}
			}
		}
		return result.toString();
	}

	public static String complemento(final char c, final int tam) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tam; i++) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static String formataCep(final String cep) {
		final StringBuilder sb = new StringBuilder();
		sb.append(UtilitarioString.complemento('0', 8));
		sb.append(UtilitarioString.soNumero(cep.trim()));
		return String.format("%s-%s", sb.substring(sb.length() - 8, sb.length() - 3),
				sb.substring(sb.length() - 3, sb.length()));
	}

	public static boolean isEmpty(final String vlr) {
		return (vlr == null) || (vlr.trim().length() == 0);
	}

	public static String removeAspas(String valor) {
		// remover as aspas da string
		if (valor.startsWith("\"")) {
			valor = valor.substring(1);
		}
		if (valor.endsWith("\"")) {
			valor = valor.substring(0, valor.length() - 1);
		}
		return valor;
	}

	public static String soNumero(final String numero) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numero.length(); i++) {
			if (Character.isDigit(numero.charAt(i))) {
				sb.append(numero.charAt(i));
			}
		}
		return sb.toString();
	}

	public static String zeroDireita(final int num, final int tam) {
		final StringBuilder sb = new StringBuilder();
		sb.append(num);
		sb.append(UtilitarioString.complemento('0', tam));
		return sb.substring(0, tam);
	}

	public static String zeroEsquerda(final int num, final int tam) {
		final StringBuilder sb = new StringBuilder();
		sb.append(UtilitarioString.complemento('0', tam));
		sb.append(num);
		return sb.substring(sb.length() - tam, sb.length());
	}

}