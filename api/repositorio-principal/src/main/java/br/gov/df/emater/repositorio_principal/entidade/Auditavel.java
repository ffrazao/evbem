package br.gov.df.emater.repositorio_principal.entidade;

import java.sql.Timestamp;

import br.gov.df.emater.repositorio_principal.entidade.sistema.Usuario;

public interface Auditavel {

	public Timestamp getAtualizadoEm();

	public Usuario getAtualizadoUsuario();

	public Integer getAtualizadoUsuarioId();

	public Timestamp getCriadoEm();

	public Usuario getCriadoUsuario();

	public Integer getCriadoUsuarioId();

	public void setAtualizadoUsuario(Usuario atualizadoUsuario);

	public void setAtualizadoUsuarioId(Integer atualizadoUsuarioId);

	public void setCriadoUsuario(Usuario criadoUsuario);

	public void setCriadoUsuarioId(Integer criadoUsuarioId);

}
