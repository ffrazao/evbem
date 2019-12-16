package br.gov.df.emater.negocio.base;

public class NegocioIntegridadeDadosException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioIntegridadeDadosException() {
		super();
	}

	public NegocioIntegridadeDadosException(final String message) {
		super(message);
	}

	public NegocioIntegridadeDadosException(final String mensagem, final Object... itens) {
		this(String.format(mensagem, itens));
	}

	public NegocioIntegridadeDadosException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NegocioIntegridadeDadosException(final String mensagem, final Throwable cause, final Object... itens) {
		this(String.format(mensagem, itens), cause);
	}

	public NegocioIntegridadeDadosException(final Throwable cause) {
		super(cause);
	}
}
