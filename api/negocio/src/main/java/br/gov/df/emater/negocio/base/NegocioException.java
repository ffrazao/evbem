package br.gov.df.emater.negocio.base;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException() {
		super();
	}

	public NegocioException(final String message) {
		super(message);
	}

	public NegocioException(final String mensagem, final Object... itens) {
		this(String.format(mensagem, itens));
	}

	public NegocioException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NegocioException(final String mensagem, final Throwable cause, final Object... itens) {
		this(String.format(mensagem, itens), cause);
	}

	public NegocioException(final Throwable cause) {
		super(cause);
	}
}
