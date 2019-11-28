package br.gov.df.emater.comum;

import java.io.File;

public final class UtilitarioArquivo {

	public static String removeArquivo(final String filePath) {
		// These first few lines the same as Justin's
		final File f = new File(filePath);

		// if it's a directory, don't remove the extention
		if (f.isDirectory()) {
			return filePath;
		}

		final String name = f.getName();

		return filePath.substring(0, filePath.lastIndexOf(name));
	}

	private static String removeDados(final String filePath, final String caractere) {
		// These first few lines the same as Justin's
		final File f = new File(filePath);

		// if it's a directory, don't remove the extention
		if (f.isDirectory()) {
			return filePath;
		}

		final String name = f.getName();

		// Now we know it's a file - don't need to do any special hidden
		// checking or contains() checking because of:
		final int lastPeriodPos = name.lastIndexOf(caractere);
		if (lastPeriodPos <= 0) {
			// No period after first character - return name as it was passed in
			return filePath;
		} else {
			// Remove the last period and everything after it
			final File renamed = new File(f.getParent(), name.substring(0, lastPeriodPos));
			return renamed.getPath();
		}
	}

	public static String removeExtensao(final String filePath) {
		return UtilitarioArquivo.removeDados(filePath, ".");
	}
}