-- schema principal
CREATE TABLE IF NOT EXISTS principal_recurso(
  id INTEGER PRIMARY KEY,
  tipo TEXT observacao TEXT
);

CREATE TABLE IF NOT EXISTS principal_pessoa(
  id INTEGER PRIMARY KEY,
  nome TEXT,
  tipo TEXT,
  FOREIGN KEY (id) REFERENCES principal_recurso(id)
);

-- schema comum
CREATE TABLE IF NOT EXISTS comum_local(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  longitude REAL,
  latitude REAL,
  altitude REAL,
  velocidade REAL,
  momento INTEGER,
  descricao TEXT
);

-- schema pessoa
CREATE TABLE IF NOT EXISTS pessoa_pessoa_fisica(
  id INTEGER PRIMARY KEY,
  cpf TEXT,
  sexo TEXT,
  cnh_numero TEXT,
  cnh_categoria TEXT,
  cnh_vencimento INTEGER,
  FOREIGN KEY (id) REFERENCES principal_pessoa(id)
);

CREATE TABLE IF NOT EXISTS pessoa_pessoa_juridica(
  id INTEGER PRIMARY KEY,
  cnpj TEXT,
  FOREIGN KEY (id) REFERENCES principal_pessoa(id)
);

CREATE TABLE IF NOT EXISTS pessoa_grupo_social(
  id INTEGER PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES principal_pessoa(id)
);

-- schema funcional
CREATE TABLE IF NOT EXISTS funcional_empregador(
  id INTEGER PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES pessoa_pessoa_juridica(id)
);

CREATE TABLE IF NOT EXISTS funcional_unidade_organizacional_tipo(id INTEGER PRIMARY KEY, nome TEXT);

CREATE TABLE IF NOT EXISTS funcional_unidade_organizacional(
  id INTEGER PRIMARY KEY,
  empregador_id INTEGER,
  unidade_organizacional_tipo_id INTEGER,
  tipo TEXT,
  FOREIGN KEY (empregador_id) REFERENCES funcional_empregador(id),
  FOREIGN KEY (unidade_organizacional_tipo_id) REFERENCES funcional_unidade_organizacional_tipo(id)
);

CREATE TABLE IF NOT EXISTS funcional_unidade_organizacional_hierarquia(
  id INTEGER PRIMARY KEY,
  unidade_organizacional_principal_id INTEGER,
  unidade_organizacional_id INTEGER,
  tipo TEXT,
  FOREIGN KEY (unidade_organizacional_principal_id) REFERENCES funcional_unidade_organizacional(id),
  FOREIGN KEY (unidade_organizacional_id) REFERENCES funcional_unidade_organizacional(id)
);

-- schema produto
CREATE TABLE IF NOT EXISTS produto_tipo(
  id INTEGER PRIMARY KEY,
  codigo TEXT,
  nome TEXT,
  pai_id INTEGER,
  FOREIGN KEY (pai_id) REFERENCES produto_tipo(id)
);

CREATE TABLE IF NOT EXISTS produto_modelo(
  id INTEGER PRIMARY KEY,
  codigo TEXT,
  nome TEXT,
  descricao TEXT,
  tipo_id INTEGER,
  FOREIGN KEY (tipo_id) REFERENCES produto_tipo(id)
);

CREATE TABLE IF NOT EXISTS produto_marca(
  id INTEGER PRIMARY KEY,
  nome TEXT,
  pai_id INTEGER,
  FOREIGN KEY (pai_id) REFERENCES produto_marca(id)
);

CREATE TABLE IF NOT EXISTS produto_modelo_marca(
  id INTEGER PRIMARY KEY,
  modelo_id INTEGER,
  marca_id INTEGER,
  FOREIGN KEY (modelo_id) REFERENCES produto_modelo(id),
  FOREIGN KEY (marca_id) REFERENCES produto_marca(id)
);

-- schema principal
CREATE TABLE IF NOT EXISTS principal_produto(
  id INTEGER PRIMARY KEY,
  modelo_id INTEGER,
  marca_id INTEGER,
  numero_serie TEXT,
  FOREIGN KEY (modelo_id) REFERENCES produto_modelo(id),
  FOREIGN KEY (marca_id) REFERENCES produto_marca(id)
);

-- schema produto
CREATE TABLE IF NOT EXISTS produto_bem_patrimonial(
  id INTEGER PRIMARY KEY,
  sigla_proprietario TEXT,
  identificacao_patrimonial TEXT,
  pessoa_id INTEGER,
  modelo_id INTEGER,
  marca_id INTEGER,
  numero_serie TEXT,
  FOREIGN KEY (pessoa_id) REFERENCES principal_pessoa(id),
  FOREIGN KEY (modelo_id) REFERENCES produto_modelo(id),
  FOREIGN KEY (marca_id) REFERENCES produto_marca(id)
);

-- schema evento
CREATE TABLE IF NOT EXISTS evento_evento(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  tipo_id INTEGER,
  marca_id INTEGER,
  modelo_id INTEGER,
  sigla_proprietario TEXT,
  identificacao_patrimonial TEXT,
  ano_fabricacao INTEGER,
  ano_modelo INTEGER,
  combustivel TEXT,
  placa TEXT,
  FOREIGN KEY (tipo_id) REFERENCES veiculo_tipo(id),
  FOREIGN KEY (marca_id) REFERENCES veiculo_marca(id),
  FOREIGN KEY (modelo_id) REFERENCES veiculo_modelo(id)
);

-- schema veiculo
CREATE TABLE IF NOT EXISTS veiculo_veiculo(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  tipo_id INTEGER,
  marca_id INTEGER,
  modelo_id INTEGER,
  sigla_proprietario TEXT,
  identificacao_patrimonial TEXT,
  ano_fabricacao INTEGER,
  ano_modelo INTEGER,
  combustivel TEXT,
  placa TEXT,
  FOREIGN KEY (tipo_id) REFERENCES veiculo_tipo(id),
  FOREIGN KEY (marca_id) REFERENCES veiculo_marca(id),
  FOREIGN KEY (modelo_id) REFERENCES veiculo_modelo(id)
);

CREATE TABLE IF NOT EXISTS veiculo_veiculo_evento(
  id INTEGER PRIMARY KEY,
  tipo TEXT,
  FOREIGN KEY (id) REFERENCES evento_evento(id)
);

CREATE TABLE IF NOT EXISTS veiculo_viagem(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  localSaida TEXT,
  localSaidaDescricao TEXT,
  quilometragemSaida REAL,
  localChegada TEXT,
  localChegadaDescricao TEXT,
  quilometragemChegada REAL,
  pessoa_id INTEGER,
  unidade_organizacional_id INTEGER,
  -- sincronizacao
  id_servidor INTEGER,
  sincronizado INTEGER,
  apagar INTEGER,
  FOREIGN KEY (id) REFERENCES veiculo_veiculo_evento(id),
  FOREIGN KEY (pessoa_id) REFERENCES principal_pessoa(id),
  FOREIGN KEY (unidade_organizacional_id) REFERENCES funcional_unidade_organizacional(id)
);

CREATE TABLE IF NOT EXISTS veiculo_rota(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  viagem_id INTEGER,
  momento INTEGER,
  ponto TEXT,
  velocidade REAL,
  id_servidor INTEGER,
  sincronizado INTEGER,
  apagar INTEGER,
  FOREIGN KEY (viagem_id) REFERENCES veiculo_viagem(id)
);

-- INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2206, 'Zura 806 SL');