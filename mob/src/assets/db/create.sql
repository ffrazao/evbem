/* ############### PRODUTOR RURAL ############### */

/* 1 - Tipo Usuário */

CREATE TABLE IF NOT EXISTS 
  tipo_usuario(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    descricao TEXT,
    funcionario INTEGER);

/* 2 - Usuário*/

CREATE TABLE IF NOT EXISTS 
  usuario(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    data_nascimento INTEGER,
    cpf INTEGER,
    numero_emater INTEGER,
    senha TEXT,
    tipo_usuario_id INTEGER,
    foto_perfil_b_64 TEXT,
    carteira_produtor_id INTEGER,
    foto_perfil_b64 BLOB,
    email_preferencial_id INTEGER,
    FOREIGN KEY (tipo_usuario_id) REFERENCES tipo_usuario(id));

/* 3 - Usuario Email */

CREATE TABLE IF NOT EXISTS 
  usuario_email(
    id INTEGER PRIMARY KEY,
    email TEXT,
    preferencial INTEGER,
    usuario_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id));

/* 4 - Carteirinha */

CREATE TABLE IF NOT EXISTS 
  carteirinha(
    id INTEGER PRIMARY KEY,
    emissao INTEGER,
    expiracao INTEGER,
    nis TEXT,
    dap TEXT,
    valida INTEGER,
    usuario_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id));

/* 5 - Situacao Fundiária */

CREATE TABLE IF NOT EXISTS 
  situacao_fundiaria(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 6 - Unidade Organizacional */

CREATE TABLE IF NOT EXISTS 
  unidade_organizacional(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 7 - Comunidade */

CREATE TABLE IF NOT EXISTS 
  comunidade(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    unidade_organizacional_id INTEGER,
    FOREIGN KEY (unidade_organizacional_id) REFERENCES unidade_organizacional(id));

/* 8 - Propriedade */

CREATE TABLE IF NOT EXISTS 
  propriedade(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    id_emater INTEGER,
    area_total REAL,
    situacao_fundiaria_id INTEGER,
    comunidade_id INTEGER,
    FOREIGN KEY (situacao_fundiaria_id) REFERENCES situacao_fundiaria(id),
    FOREIGN KEY (comunidade_id) REFERENCES comunidade(id));

/* 9 - Tipo Vinculo */

CREATE TABLE IF NOT EXISTS 
  tipo_vinculo(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 10 - Posse */

CREATE TABLE IF NOT EXISTS 
  posse(
    id INTEGER PRIMARY KEY,
    usuario_id INTEGER,
    propriedade_id INTEGER,
    tipo_vinculo_id INTEGER,
    propriedade_nome TEXT,
    foto TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (propriedade_id) REFERENCES propriedade(id),
    FOREIGN KEY (tipo_vinculo_id) REFERENCES tipo_vinculo(id));

/* ############### CALENDARIO DE EVENTOS ############### */

/* 11 - Calendario de eventos */

CREATE TABLE IF NOT EXISTS 
  calendario_evento(
    id INTEGER PRIMARY KEY,
    titulo TEXT,
    descricao TEXT,
    local TEXT,
    data_inicio INTEGER,
    data_fim INTEGER,
    dia_inteiro INTEGER);

/* ###############        NOTÍCIAS       ############### */

/* 12 - Publicação */

CREATE TABLE IF NOT EXISTS 
  publicacao(
    id INTEGER PRIMARY KEY,
    titulo TEXT,
    url TEXT,
    linha_fina TEXT,
    texto TEXT);

/* 13 - Publicacao Fotos */

CREATE TABLE IF NOT EXISTS 
  publicacao(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    ordem INTEGER,
    legenda TEXT,
    foto BLOB,
    publicacao_id INTEGER,
    FOREIGN KEY (publicacao_id) REFERENCES publicacao(id));

/* ###############     RASTREABILIDADE - RELACIONAMENTO    ############### */

/* 14 - Relacionamento */

CREATE TABLE IF NOT EXISTS 
  relacionamento(
    id INTEGER PRIMARY KEY,
    external_id INTEGER,
    nome TEXT,
    cpf_cnpj TEXT,
    inscricao_estadual TEXT,
    endereco TEXT,
    email TEXT,
    cidade TEXT,
    estado TEXT,
    cep TEXT,
    usuario_id INTEGER,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id));

/* 15 - Relacionamento Tipo */

CREATE TABLE IF NOT EXISTS 
  relacionamento_tipo(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    funcionario INTEGER,
    fornecedor INTEGER);

/* 16 - Relacionamento2Tipo */

CREATE TABLE IF NOT EXISTS 
  relacionamento2tipo(
    id INTEGER,
    relacionamento_id INTEGER,
    relacionamento_tipo_id INTEGER,
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id),
    FOREIGN KEY (relacionamento_tipo_id) REFERENCES relacionamento_tipo(id),
    UNIQUE(relacionamento_id, relacionamento_tipo_id));

/* 17 - Relacionamento Contato */

CREATE TABLE IF NOT EXISTS 
  relacionamento_contato(
    id INTEGER PRIMARY KEY,
    telefone TEXT,
    relacionamento_id INTEGER,
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id));

/* ###############     SETRAB - BANCO DE EMPREGOS   ############### */    

CREATE TABLE IF NOT EXISTS
  cbo(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    codigo INTEGER,
    sinonimo TEXT
  );

CREATE TABLE IF NOT EXISTS
  vaga_emprego(
    id INTEGER PRIMARY KEY,
    external_id INTEGER,
    salario REAL,
    quantidade INTEGER,
    forma_pagamento TEXT,
    ativa BOOLEAN,
    data_criacao INTEGER,
    data_preenchimento INTEGER,
    posse_id INTEGER,
    cbo_id INTEGER,
    FOREIGN KEY (posse_id) REFERENCES posse(id),
    FOREIGN KEY (cbo_id) REFERENCES cbo(id)
  );

/* ###############     CEASA - INFORMAÇṌES DE PRODUTO   ############### */

/* 18 - Unidade de Medida */

CREATE TABLE IF NOT EXISTS 
  unidade_peso(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    sigla TEXT);

/* 19 - Produto Classificação */

CREATE TABLE IF NOT EXISTS 
  produto_classificacao(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 20 - Unidade Comercialização */

CREATE TABLE IF NOT EXISTS 
  unidade_venda(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 20 - Unidade Comercialização */

CREATE TABLE IF NOT EXISTS 
  unidade_colheita(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* ###############     RASTREABILIDADE - CULTURA   ############### */

/* 22 - Cultura */

CREATE TABLE IF NOT EXISTS 
  cultura(
    id INTEGER PRIMARY KEY,
    nome TEXT,
    estimativa_maturacao INTEGER);



/* ###############     RASTREABILIDADE - TALHÃO   ############### */

/* 23 - Unidade Medida Area */

CREATE TABLE IF NOT EXISTS 
  unidade_medida_area(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 24 - Talhão */

CREATE TABLE IF NOT EXISTS 
  talhao(
    id INTEGER PRIMARY KEY,
    data_criacao INTEGER,
    data_finalizacao INTEGER,
    nome TEXT,
    posse_id INTEGER,
    cultura_id INTEGER,
    variedade TEXT,
    relacionamento_id INTEGER,
    unidade_medida_area_id INTEGER,
    data_inicio_colheita INTEGER,
    area REAL,
    ativo INTEGER,
    sigla TEXT,
    FOREIGN KEY (posse_id) REFERENCES posse(id),
    FOREIGN KEY (cultura_id) REFERENCES cultura(id),
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id),
    FOREIGN KEY (unidade_medida_area_id) REFERENCES unidade_medida_area(id));

/* 25 - Talhão Lote */

CREATE TABLE IF NOT EXISTS 
  talhao_lote(
    id INTEGER PRIMARY KEY,
    talhao_id INTEGER,
    etiqueta TEXT UNIQUE,
    data_criacao_lote INTEGER,
    FOREIGN KEY (talhao_id) REFERENCES talhao(id));

/* ###############     RASTREABILIDADE - REGISTRO GENERICO   ############### */

/* 26 - Registro */

CREATE TABLE IF NOT EXISTS 
  registro(
    id INTEGER PRIMARY KEY ,
    external_id INTEGER,
    talhao_id TEXT,
    data_acao INTEGER,
    data_criacao INTEGER, 
    FOREIGN KEY (talhao_id) REFERENCES talhao(id));

/* 27 - Registro Anexo */

CREATE TABLE IF NOT EXISTS 
  registro_anexo(
    id INTEGER PRIMARY KEY,
    external_id INTEGER,
    nome TEXT,
    file_type NUMBER,
    local_arquivo TEXT,
    registro_id INTEGER,
    data_criacao INTEGER,
    FOREIGN KEY (registro_id) REFERENCES registro(id));

/* 28 - Registro2Responsavel */

CREATE TABLE IF NOT EXISTS 
  registro_responsavel(
    id INTEGER PRIMARY KEY,
    external_id INTEGER,
    registro_id INTEGER,
    relacionamento_id INTEGER,
    relacionamento_tipo_id INTEGER,
    FOREIGN KEY (registro_id) REFERENCES registro(id),
    FOREIGN KEY (relacionamento_id) REFERENCES relacionamento(id),
    FOREIGN KEY (relacionamento_tipo_id) REFERENCES relacionamento_tipo(id),
    UNIQUE(registro_id,relacionamento_tipo_id,relacionamento_id));

/* ###############     RASTREABILIDADE - REGISTRO - OCORRÊNCIA   ############### */

/* 29 - Ocorrência */

CREATE TABLE IF NOT EXISTS 
  registro_ocorrencia(
    id INTEGER PRIMARY KEY,
    ocorrencia TEXT,
    descricao TEXT,
    FOREIGN KEY (id) REFERENCES registro(id));

/* ###############     RASTREABILIDADE - REGISTRO - COLHEITA   ############### */

/* 30 - Colheita */

CREATE TABLE IF NOT EXISTS 
  registro_colheita(
    id INTEGER PRIMARY KEY,
    unidade_colheita_id INTEGER,
    unidade_peso_id INTEGER,
    peso_liquido INTEGER,
    quantidade INTEGER,
    lote_id INTEGER,
    FOREIGN KEY (id) REFERENCES registro(id),
    FOREIGN KEY (unidade_peso_id) REFERENCES unidade_peso(id),
    FOREIGN KEY (lote_id) REFERENCES talhao_lote(id));

/* ###############     RASTREABILIDADE - REGISTRO - VENDA   ############### */

/* 31 - Venda */

CREATE TABLE IF NOT EXISTS 
  registro_venda(
    id INTEGER PRIMARY KEY,
    produto_classificacao_id INTEGER,
    unidade_venda_id INTEGER,
    unidade_peso_id INTEGER,
    peso_liquido REAL,
    quantidade_vendida REAL,
    valor_unitario REAL,
    valor_total REAL,
    FOREIGN KEY (id) REFERENCES registro(id),
    FOREIGN KEY (produto_classificacao_id) REFERENCES produto_classificacao(id),
    FOREIGN KEY (unidade_peso_id) REFERENCES unidade_peso(id),
    FOREIGN KEY (unidade_venda_id) REFERENCES unidade_venda(id));

/* ###############     RASTREABILIDADE - REGISTRO - PLANTIO   ############### */

/* 33 - Registo Plantio Unidade Medida */

CREATE TABLE IF NOT EXISTS 
  registro_plantio_unidade_medida(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 34 - Registro Plantio */

CREATE TABLE IF NOT EXISTS 
  registro_plantio(
    id INTEGER PRIMARY KEY,
    irrigado INTEGER,
    organico INTEGER,
    protegido INTEGER,
    unidade_medida_plantio_id INTEGER,
    quantidade_plantada INTEGER,
    inicio_previsto_colheita INTEGER,
    fim_previsto_colheita INTEGER,
    fornecedor_id INTEGER,
    FOREIGN KEY (id) REFERENCES registro(id),
    FOREIGN KEY (unidade_medida_plantio_id) REFERENCES registro_plantio_unidade_medida(id));

/* ###############     RASTREABILIDADE - REGISTRO - APLICAÇÃO   ############### */

/* 35 - Agrotoxico */

CREATE TABLE IF NOT EXISTS 
  agrotoxico(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 37 - Registro Aplicação Tipo Dose */

CREATE TABLE IF NOT EXISTS 
  registro_aplicacao_tipo_dose(
    id INTEGER PRIMARY KEY,
    nome TEXT);

/* 39 - Registro Aplicação */

CREATE TABLE IF NOT EXISTS 
  registro_aplicacao(
    id INTEGER PRIMARY KEY,
    preventiva INTEGER,
    volume_calda REAL,
    praga_diagnostico TEXT,
    carencia INTEGER,
    tipo_aplicacao_registro_id INTEGER,
    FOREIGN KEY (id) REFERENCES registro(id),
    FOREIGN KEY (tipo_aplicacao_registro_id) REFERENCES registro_aplicacao_tipo_produto(id));

/* 38 - Registro Aplicação Reagente */

CREATE TABLE IF NOT EXISTS 
  registro_aplicacao_reagente(
    id INTEGER PRIMARY KEY,
    agrotoxico_id INTEGER,
    tipo_dose_id INTEGER,
    aplicacao_id INTEGER,
    dose REAL,
    carencia INTEGER,
    outros TEXT,
    nome TEXT,
    FOREIGN KEY (agrotoxico_id) REFERENCES agrotoxico(id),
    FOREIGN KEY (aplicacao_id) REFERENCES registro_aplicacao(id),
    FOREIGN KEY (tipo_dose_id) REFERENCES registro_aplicacao_tipo_dose(id));

/* 41 - Token */

CREATE TABLE IF NOT EXISTS 
  token (
    id INTEGER PRIMARY KEY,
    token_value TEXT);

CREATE TABLE IF NOT EXISTS
  request_pool (
    id INTEGER PRIMARY KEY,
    event_type INTEGER,
    request_type INTEGER,
    request_item_id INTEGER,
    request_data TEXT
  );

/* ###############     DADOS FIXO (SOMENTE PARA TESTE)   ############### */


/* Unidade de colheita */
INSERT or IGNORE INTO unidade_colheita(id, nome) VALUES (1, 'Bandejas');
INSERT or IGNORE INTO unidade_colheita(id, nome) VALUES (2, 'Caixas');
INSERT or IGNORE INTO unidade_colheita(id, nome) VALUES (3, 'Dúzias');
INSERT or IGNORE INTO unidade_colheita(id, nome) VALUES (4, 'Maços');
INSERT or IGNORE INTO unidade_colheita(id, nome) VALUES (5, 'Sacos');
INSERT or IGNORE INTO unidade_colheita(id, nome) VALUES (6, 'Unidades');

/* Unidade de peso */
INSERT or IGNORE INTO unidade_peso(id, nome, sigla) VALUES (1, 'Quilograma', 'kg');
INSERT or IGNORE INTO unidade_peso(id, nome, sigla) VALUES (2, 'Grama', 'g');


/* Unidade de venda */
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (1, 'Bandejas');
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (2, 'Caixas');
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (3, 'Dúzias');
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (4, 'Maços');
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (5, 'Quilogramas');
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (6, 'Sacos');
INSERT or IGNORE INTO unidade_venda(id, nome) VALUES (7, 'Unidades');

/* Produto Classificação */
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (1, 'Especial');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (2, 'Extra');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (3, 'Graúdo');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (4, 'Médio');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (5, 'Pequeno');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (6, 'Primeira');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (7, 'Padrão');
INSERT or IGNORE INTO produto_classificacao(id, nome) VALUES (8, 'Não Classificado');

/* Tipo de Relacionamento */
INSERT or IGNORE INTO relacionamento_tipo(id, nome, funcionario, fornecedor) VALUES (1, 'Fornecedor', 0, 1);
INSERT or IGNORE INTO relacionamento_tipo(id, nome, funcionario, fornecedor) VALUES (2, 'Funcionario', 1, 0);
INSERT or IGNORE INTO relacionamento_tipo(id, nome, funcionario, fornecedor) VALUES (3, 'Comprador', 0, 0);

/* Unidade de Medida de Área */ 
INSERT or IGNORE INTO unidade_medida_area(id, nome) VALUES (1, 'Hectare (ha)');
INSERT or IGNORE INTO unidade_medida_area(id, nome) VALUES (2, 'Metros Quadrados (m²)');

/* Cultura */
INSERT or IGNORE INTO cultura(id, nome) VALUES (1, 'Abacate');
INSERT or IGNORE INTO cultura(id, nome) VALUES (2, 'Abacaxi');
INSERT or IGNORE INTO cultura(id, nome) VALUES (3, 'Abóbora');
INSERT or IGNORE INTO cultura(id, nome) VALUES (4, 'Abobrinha');
INSERT or IGNORE INTO cultura(id, nome) VALUES (5, 'Açaí');
INSERT or IGNORE INTO cultura(id, nome) VALUES (6, 'Acelga');
INSERT or IGNORE INTO cultura(id, nome) VALUES (7, 'Acerola');
INSERT or IGNORE INTO cultura(id, nome) VALUES (8, 'Agrião');
INSERT or IGNORE INTO cultura(id, nome) VALUES (9, 'Aipo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (10, 'Alecrim');
INSERT or IGNORE INTO cultura(id, nome) VALUES (11, 'Alface');
INSERT or IGNORE INTO cultura(id, nome) VALUES (12, 'Alho');
INSERT or IGNORE INTO cultura(id, nome) VALUES (13, 'Alho Porro');
INSERT or IGNORE INTO cultura(id, nome) VALUES (14, 'Almeirão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (15, 'Ameixa');
INSERT or IGNORE INTO cultura(id, nome) VALUES (16, 'Amora');
INSERT or IGNORE INTO cultura(id, nome) VALUES (17, 'Aspargo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (18, 'Atemóia');
INSERT or IGNORE INTO cultura(id, nome) VALUES (19, 'Banana');
INSERT or IGNORE INTO cultura(id, nome) VALUES (20, 'Batata');
INSERT or IGNORE INTO cultura(id, nome) VALUES (21, 'Batata-Doce');
INSERT or IGNORE INTO cultura(id, nome) VALUES (22, 'Batata Yacon');
INSERT or IGNORE INTO cultura(id, nome) VALUES (23, 'Beringela');
INSERT or IGNORE INTO cultura(id, nome) VALUES (24, 'Beterraba');
INSERT or IGNORE INTO cultura(id, nome) VALUES (25, 'Brócolis');
INSERT or IGNORE INTO cultura(id, nome) VALUES (26, 'Cacau');
INSERT or IGNORE INTO cultura(id, nome) VALUES (27, 'Caju');
INSERT or IGNORE INTO cultura(id, nome) VALUES (28, 'Caqui');
INSERT or IGNORE INTO cultura(id, nome) VALUES (29, 'Cará');
INSERT or IGNORE INTO cultura(id, nome) VALUES (30, 'Carambola');
INSERT or IGNORE INTO cultura(id, nome) VALUES (31, 'Cebola');
INSERT or IGNORE INTO cultura(id, nome) VALUES (32, 'Cebolinha');
INSERT or IGNORE INTO cultura(id, nome) VALUES (33, 'Cenoura');
INSERT or IGNORE INTO cultura(id, nome) VALUES (34, 'Chicória');
INSERT or IGNORE INTO cultura(id, nome) VALUES (35, 'Chuchu');
INSERT or IGNORE INTO cultura(id, nome) VALUES (36, 'Coco');
INSERT or IGNORE INTO cultura(id, nome) VALUES (37, 'Coentro');
INSERT or IGNORE INTO cultura(id, nome) VALUES (38, 'Couve');
INSERT or IGNORE INTO cultura(id, nome) VALUES (39, 'Couve-Chinesa');
INSERT or IGNORE INTO cultura(id, nome) VALUES (40, 'Couve-de-Bruxelas');
INSERT or IGNORE INTO cultura(id, nome) VALUES (41, 'Couve-flor');
INSERT or IGNORE INTO cultura(id, nome) VALUES (42, 'Cupuaçu');
INSERT or IGNORE INTO cultura(id, nome) VALUES (43, 'Erva-doce');
INSERT or IGNORE INTO cultura(id, nome) VALUES (44, 'Espinafre');
INSERT or IGNORE INTO cultura(id, nome) VALUES (45, 'Estragão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (46, 'Figo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (47, 'Framboesa');
INSERT or IGNORE INTO cultura(id, nome) VALUES (48, 'Fruta-do-Conde');
INSERT or IGNORE INTO cultura(id, nome) VALUES (49, 'Gengibre');
INSERT or IGNORE INTO cultura(id, nome) VALUES (50, 'Goiaba');
INSERT or IGNORE INTO cultura(id, nome) VALUES (51, 'Graviola');
INSERT or IGNORE INTO cultura(id, nome) VALUES (52, 'Hortelã');
INSERT or IGNORE INTO cultura(id, nome) VALUES (53, 'Inhame');
INSERT or IGNORE INTO cultura(id, nome) VALUES (54, 'Jiló');
INSERT or IGNORE INTO cultura(id, nome) VALUES (55, 'Kiwi');
INSERT or IGNORE INTO cultura(id, nome) VALUES (56, 'Laranja');
INSERT or IGNORE INTO cultura(id, nome) VALUES (57, 'Lima');
INSERT or IGNORE INTO cultura(id, nome) VALUES (58, 'Limão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (59, 'Maçã');
INSERT or IGNORE INTO cultura(id, nome) VALUES (60, 'Mamão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (61, 'Mandioca');
INSERT or IGNORE INTO cultura(id, nome) VALUES (62, 'Mandioquinha-salsa');
INSERT or IGNORE INTO cultura(id, nome) VALUES (63, 'Manga');
INSERT or IGNORE INTO cultura(id, nome) VALUES (64, 'Manjericão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (65, 'Manjerona');
INSERT or IGNORE INTO cultura(id, nome) VALUES (66, 'Maracujá');
INSERT or IGNORE INTO cultura(id, nome) VALUES (67, 'Marmelo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (68, 'Maxixe');
INSERT or IGNORE INTO cultura(id, nome) VALUES (69, 'Melância');
INSERT or IGNORE INTO cultura(id, nome) VALUES (70, 'Melão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (71, 'Mirtilo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (72, 'Morango');
INSERT or IGNORE INTO cultura(id, nome) VALUES (73, 'Mostarda');
INSERT or IGNORE INTO cultura(id, nome) VALUES (74, 'Nabo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (75, 'Nectarina');
INSERT or IGNORE INTO cultura(id, nome) VALUES (76, 'Nêspera');
INSERT or IGNORE INTO cultura(id, nome) VALUES (77, 'Orégano');
INSERT or IGNORE INTO cultura(id, nome) VALUES (78, 'Pepino');
INSERT or IGNORE INTO cultura(id, nome) VALUES (79, 'Pera');
INSERT or IGNORE INTO cultura(id, nome) VALUES (80, 'Pêssego');
INSERT or IGNORE INTO cultura(id, nome) VALUES (81, 'Pimenta');
INSERT or IGNORE INTO cultura(id, nome) VALUES (82, 'Pimentão');
INSERT or IGNORE INTO cultura(id, nome) VALUES (83, 'Pitanga');
INSERT or IGNORE INTO cultura(id, nome) VALUES (84, 'Quiabo');
INSERT or IGNORE INTO cultura(id, nome) VALUES (85, 'Rabanete');
INSERT or IGNORE INTO cultura(id, nome) VALUES (86, 'Repolho');
INSERT or IGNORE INTO cultura(id, nome) VALUES (87, 'Romã');
INSERT or IGNORE INTO cultura(id, nome) VALUES (88, 'Rúcula');
INSERT or IGNORE INTO cultura(id, nome) VALUES (89, 'Salsa');
INSERT or IGNORE INTO cultura(id, nome) VALUES (90, 'Sálvia');
INSERT or IGNORE INTO cultura(id, nome) VALUES (91, 'Tangerina');
INSERT or IGNORE INTO cultura(id, nome) VALUES (92, 'Tomate');
INSERT or IGNORE INTO cultura(id, nome) VALUES (93, 'Toranja');
INSERT or IGNORE INTO cultura(id, nome) VALUES (94, 'Uva');

/* Registro de unidade de plantio */
INSERT or IGNORE INTO registro_plantio_unidade_medida(id, nome) VALUES (1, 'Covas');
INSERT or IGNORE INTO registro_plantio_unidade_medida(id, nome) VALUES (2, 'Mudas');
INSERT or IGNORE INTO registro_plantio_unidade_medida(id, nome) VALUES (3, 'Sementes');

/* Tipo de dose */
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (1, 'g');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (2, 'g/100l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (3, 'g/20l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (4, 'g/ha');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (5, 'kg');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (6, 'kg/100l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (7, 'kg/20l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (8, 'kg/ha');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (9, 'l/100l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (10, 'l/20l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (11, 'l/ha');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (12, 'ml/100l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (13, 'ml/20l');
INSERT or IGNORE INTO registro_aplicacao_tipo_dose(id, nome) values (14, 'ml/ha');

/* CBOs */
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (1, 620105,'Supervisor de exploração agrícola','Supervisor de exploração agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (2, 620105,'Supervisor de exploração agrícola','Capataz da exploração agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (3, 620105,'Supervisor de exploração agrícola','Capataz de horticultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (4, 620105,'Supervisor de exploração agrícola','Capataz na fruticultura e na floricultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (5, 620105,'Supervisor de exploração agrícola','Capataz na lavoura (exceto na floricultura, fruticultura e horticultura)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (6, 620105,'Supervisor de exploração agrícola','Encarregado de horticultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (7, 620105,'Supervisor de exploração agrícola','Encarregado de hortifrutigrangeiros');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (8, 620105,'Supervisor de exploração agrícola','Fiscal de lavoura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (9, 620105,'Supervisor de exploração agrícola','Monitor agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (10, 620105,'Supervisor de exploração agrícola','Orientador de plantio');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (11, 620110,'Supervisor de exploração agropecuária','Supervisor de exploração agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (12, 620110,'Supervisor de exploração agropecuária','Capataz da exploração agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (13, 620110,'Supervisor de exploração agropecuária','Capataz rural');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (14, 620110,'Supervisor de exploração agropecuária','Encarregado na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (15, 620110,'Supervisor de exploração agropecuária','Fiscal de propriedade agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (16, 620115,'Supervisor de exploração pecuária','Supervisor de exploração pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (17, 620115,'Supervisor de exploração pecuária','Capataz (criação de gado bovino)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (18, 620115,'Supervisor de exploração pecuária','Capataz de currais bovinos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (19, 620115,'Supervisor de exploração pecuária','Capataz na exploração de pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (20, 620115,'Supervisor de exploração pecuária','Capataz na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (21, 620115,'Supervisor de exploração pecuária','Encarregado na exploração de pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (22, 621005,'Trabalhador agropecuário em geral','Trabalhador agropecuário em geral');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (23, 621005,'Trabalhador agropecuário em geral','Agregado - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (24, 621005,'Trabalhador agropecuário em geral','Arameiro (colocador de arames)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (25, 621005,'Trabalhador agropecuário em geral','Arrendatário - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (26, 621005,'Trabalhador agropecuário em geral','Bóia-fria - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (27, 621005,'Trabalhador agropecuário em geral','Camarada - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (28, 621005,'Trabalhador agropecuário em geral','Campeiro - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (29, 621005,'Trabalhador agropecuário em geral','Camponês na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (30, 621005,'Trabalhador agropecuário em geral','Colono - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (31, 621005,'Trabalhador agropecuário em geral','Curador de animais - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (32, 621005,'Trabalhador agropecuário em geral','Destocador - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (33, 621005,'Trabalhador agropecuário em geral','Diarista - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (34, 621005,'Trabalhador agropecuário em geral','Exterminador de insetos - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (35, 621005,'Trabalhador agropecuário em geral','Fazedor de cerca - inclusive na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (36, 621005,'Trabalhador agropecuário em geral','Limpador de pasto - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (37, 621005,'Trabalhador agropecuário em geral','Meeiro - na agropecuária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (38, 621005,'Trabalhador agropecuário em geral','Operador de engenho');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (39, 621005,'Trabalhador agropecuário em geral','Parceiro na agropecuária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (40, 621005,'Trabalhador agropecuário em geral','Peão - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (41, 621005,'Trabalhador agropecuário em geral','Pegador de animais - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (42, 621005,'Trabalhador agropecuário em geral','Peneirador - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (43, 621005,'Trabalhador agropecuário em geral','Rendeiro na agropecuária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (44, 621005,'Trabalhador agropecuário em geral','Roceiro - na agropecuária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (45, 621005,'Trabalhador agropecuário em geral','Trabalhador braçal - na agropecuária - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (46, 621005,'Trabalhador agropecuário em geral','Trabalhador braçal - na agropecuária - exclusive conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (47, 621005,'Trabalhador agropecuário em geral','Trabalhador braçal - na agropecuária - exclusive empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (48, 621005,'Trabalhador agropecuário em geral','Trabalhador da coleta de sementes');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (49, 621005,'Trabalhador agropecuário em geral','Trabalhador da produção de sementes agrícolas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (50, 621005,'Trabalhador agropecuário em geral','Trabalhador de enxada - na agropecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (51, 621005,'Trabalhador agropecuário em geral','Trabalhador na formação de pastagem');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (52, 621005,'Trabalhador agropecuário em geral','Trabalhador rural - na agropecuária - exclusive conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (53, 621005,'Trabalhador agropecuário em geral','Trabalhador rural - na agropecuária - exclusive empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (54, 622005,'Caseiro (agricultura)','Caseiro (agricultura)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (55, 622005,'Caseiro (agricultura)','Chacareiro - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (56, 622005,'Caseiro (agricultura)','Rancheiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (57, 622010,'Jardineiro','Jardineiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (58, 622010,'Jardineiro','Jardineiro (árvores para ornamentação urbana)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (59, 622010,'Jardineiro','Regador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (60, 622010,'Jardineiro','Trabalhador do plantio e trato de árvores ornamentais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (61, 622015,'Trabalhador na produção de mudas e sementes','Trabalhador na produção de mudas e sementes');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (62, 622015,'Trabalhador na produção de mudas e sementes','Colhedor de sementes');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (63, 622015,'Trabalhador na produção de mudas e sementes','Embalador de mudas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (64, 622015,'Trabalhador na produção de mudas e sementes','Viveirista agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (65, 622020,'Trabalhador volante da agricultura','Trabalhador volante da agricultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (66, 622020,'Trabalhador volante da agricultura','Abanador na agricultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (67, 622020,'Trabalhador volante da agricultura','Adubador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (68, 622020,'Trabalhador volante da agricultura','Ajudante de serviço de (aplicação de produtos agroquímicos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (69, 622020,'Trabalhador volante da agricultura','Apanhador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (70, 622020,'Trabalhador volante da agricultura','Aplicador agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (71, 622020,'Trabalhador volante da agricultura','Arrancador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (72, 622020,'Trabalhador volante da agricultura','Auxiliar de agricultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (73, 622020,'Trabalhador volante da agricultura','Bóia-fria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (74, 622020,'Trabalhador volante da agricultura','Cabeça-de-campo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (75, 622020,'Trabalhador volante da agricultura','Capinador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (76, 622020,'Trabalhador volante da agricultura','Capinador - na lavoura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (77, 622020,'Trabalhador volante da agricultura','Capineiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (78, 622020,'Trabalhador volante da agricultura','Capinheiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (79, 622020,'Trabalhador volante da agricultura','Capinzeiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (80, 622020,'Trabalhador volante da agricultura','Carpidor - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (81, 622020,'Trabalhador volante da agricultura','Catadeira - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (82, 622020,'Trabalhador volante da agricultura','Catador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (83, 622020,'Trabalhador volante da agricultura','Cavador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (84, 622020,'Trabalhador volante da agricultura','Ceifador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (85, 622020,'Trabalhador volante da agricultura','Ceifador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (86, 622020,'Trabalhador volante da agricultura','Ceifeiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (87, 622020,'Trabalhador volante da agricultura','Cerqueiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (88, 622020,'Trabalhador volante da agricultura','Chefe de turma volante - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (89, 622020,'Trabalhador volante da agricultura','Coletor na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (90, 622020,'Trabalhador volante da agricultura','Colhedor - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (91, 622020,'Trabalhador volante da agricultura','Colhedor de lavoura (exceto na floricultura, fruticultura e horticultura)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (92, 622020,'Trabalhador volante da agricultura','Cultivador de cultura permanente');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (93, 622020,'Trabalhador volante da agricultura','Cultivador de cultura temporária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (94, 622020,'Trabalhador volante da agricultura','Debulhador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (95, 622020,'Trabalhador volante da agricultura','Descascador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (96, 622020,'Trabalhador volante da agricultura','Destalador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (97, 622020,'Trabalhador volante da agricultura','Diarista na agricultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (98, 622020,'Trabalhador volante da agricultura','Empreiteiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (99, 622020,'Trabalhador volante da agricultura','Encarregado de silos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (100, 622020,'Trabalhador volante da agricultura','Encoivarador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (101, 622020,'Trabalhador volante da agricultura','Enxadeiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (102, 622020,'Trabalhador volante da agricultura','Enxadeiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (103, 622020,'Trabalhador volante da agricultura','Escolhedor - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (104, 622020,'Trabalhador volante da agricultura','Esparramador de adubos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (105, 622020,'Trabalhador volante da agricultura','Estercador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (106, 622020,'Trabalhador volante da agricultura','Foiceiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (107, 622020,'Trabalhador volante da agricultura','Foiceiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (108, 622020,'Trabalhador volante da agricultura','Formador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (109, 622020,'Trabalhador volante da agricultura','Formigueiro (combate às formigas)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (110, 622020,'Trabalhador volante da agricultura','Lavrador - na cultura - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (111, 622020,'Trabalhador volante da agricultura','Lavrador de cultura permanente - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (112, 622020,'Trabalhador volante da agricultura','Lavrador de cultura temporária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (113, 622020,'Trabalhador volante da agricultura','Lavrador na horticultura e na floricultura - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (114, 622020,'Trabalhador volante da agricultura','Matador de formiga - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (115, 622020,'Trabalhador volante da agricultura','Plantador - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (116, 622020,'Trabalhador volante da agricultura','Plantador de cultura permanente');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (117, 622020,'Trabalhador volante da agricultura','Plantador de cultura temporária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (118, 622020,'Trabalhador volante da agricultura','Podador agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (119, 622020,'Trabalhador volante da agricultura','Roçador - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (120, 622020,'Trabalhador volante da agricultura','Ronda de formiga (combate às formigas)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (121, 622020,'Trabalhador volante da agricultura','Safrista');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (122, 622020,'Trabalhador volante da agricultura','Selecionador e embalador de colheitas agrícolas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (123, 622020,'Trabalhador volante da agricultura','Semeador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (124, 622020,'Trabalhador volante da agricultura','Sementeiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (125, 622020,'Trabalhador volante da agricultura','Tarefeiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (126, 622020,'Trabalhador volante da agricultura','Tirador de palha - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (127, 622020,'Trabalhador volante da agricultura','Trabalhador agrícola polivalente');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (128, 622020,'Trabalhador volante da agricultura','Valeiro - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (129, 622020,'Trabalhador volante da agricultura','Volante na agricultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (130, 622105,'Trabalhador da cultura de arroz','Trabalhador da cultura de arroz');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (131, 622105,'Trabalhador da cultura de arroz','Arrozeiro - na cultura - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (132, 622105,'Trabalhador da cultura de arroz','Colhedor de arroz');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (133, 622105,'Trabalhador da cultura de arroz','Cortador de arroz');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (134, 622105,'Trabalhador da cultura de arroz','Plantador de arroz - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (135, 622105,'Trabalhador da cultura de arroz','Plantador de arroz - empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (136, 622105,'Trabalhador da cultura de arroz','Rizicultor - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (137, 622105,'Trabalhador da cultura de arroz','Rizicultor - empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (138, 622105,'Trabalhador da cultura de arroz','Secador de arroz');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (139, 622110,'Trabalhador da cultura de cana-de-açúcar','Trabalhador da cultura de cana-de-açúcar');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (140, 622110,'Trabalhador da cultura de cana-de-açúcar','Colhedor de cana-de-açúcar');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (141, 622110,'Trabalhador da cultura de cana-de-açúcar','Cortador de cana-de-açúcar');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (142, 622110,'Trabalhador da cultura de cana-de-açúcar','Plantador de cana-de-açúcar - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (143, 622110,'Trabalhador da cultura de cana-de-açúcar','Plantador de cana-de-açúcar - empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (144, 622110,'Trabalhador da cultura de cana-de-açúcar','Tombador de cana-de-açúcar');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (145, 622115,'Trabalhador da cultura de milho e sorgo','Trabalhador da cultura de milho e sorgo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (146, 622115,'Trabalhador da cultura de milho e sorgo','Plantador de milho e sorgo - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (147, 622115,'Trabalhador da cultura de milho e sorgo','Plantador de milho e sorgo - empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (148, 622120,'Trabalhador da cultura de trigo, aveia, cevada e triticale','Trabalhador da cultura de trigo, aveia, cevada e triticale');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (149, 622120,'Trabalhador da cultura de trigo, aveia, cevada e triticale','Plantador de trigo - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (150, 622120,'Trabalhador da cultura de trigo, aveia, cevada e triticale','Plantador de trigo - empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (151, 622120,'Trabalhador da cultura de trigo, aveia, cevada e triticale','Triticultor - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (152, 622120,'Trabalhador da cultura de trigo, aveia, cevada e triticale','Triticultor - empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (153, 622205,'Trabalhador da cultura de algodão','Trabalhador da cultura de algodão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (154, 622205,'Trabalhador da cultura de algodão','Apanhador de algodão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (155, 622205,'Trabalhador da cultura de algodão','Catador de algodão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (156, 622205,'Trabalhador da cultura de algodão','Colhedor de algodão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (157, 622205,'Trabalhador da cultura de algodão','Cotonicultor');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (158, 622205,'Trabalhador da cultura de algodão','Cultivador de algodão - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (159, 622205,'Trabalhador da cultura de algodão','Cultivador de algodão - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (160, 622205,'Trabalhador da cultura de algodão','Plantador de algodão - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (161, 622210,'Trabalhador da cultura de sisal','Trabalhador da cultura de sisal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (162, 622210,'Trabalhador da cultura de sisal','Bagaceiro de sisal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (163, 622210,'Trabalhador da cultura de sisal','Batedor de sisal - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (164, 622210,'Trabalhador da cultura de sisal','Cultivador de agave - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (165, 622210,'Trabalhador da cultura de sisal','Cultivador de agave - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (166, 622210,'Trabalhador da cultura de sisal','Cultivador de sisal - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (167, 622210,'Trabalhador da cultura de sisal','Cultivador de sisal - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (168, 622210,'Trabalhador da cultura de sisal','Desfibrador de agave');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (169, 622210,'Trabalhador da cultura de sisal','Desifbrador de sisal - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (170, 622210,'Trabalhador da cultura de sisal','Fibreiro de sisal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (171, 622210,'Trabalhador da cultura de sisal','Operador de batedor de fibras');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (172, 622210,'Trabalhador da cultura de sisal','Plantador de sisal ou agave - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (173, 622210,'Trabalhador da cultura de sisal','Puxador de sisal - na cultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (174, 622210,'Trabalhador da cultura de sisal','Resideiro de sisal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (175, 622210,'Trabalhador da cultura de sisal','Trabalhador na cultura de agave');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (176, 622215,'Trabalhador da cultura do rami','Trabalhador da cultura do rami');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (177, 622215,'Trabalhador da cultura do rami','Bagaceiro de rami');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (178, 622215,'Trabalhador da cultura do rami','Batedor de rami');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (179, 622215,'Trabalhador da cultura do rami','Carregador de rami');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (180, 622215,'Trabalhador da cultura do rami','Cortador de rami');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (181, 622215,'Trabalhador da cultura do rami','Cultivador de rami - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (182, 622215,'Trabalhador da cultura do rami','Cultivador de rami - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (183, 622215,'Trabalhador da cultura do rami','Fibreiro de rami');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (184, 622305,'Trabalhador na olericultura (frutos e sementes)','Trabalhador na olericultura (frutos e sementes)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (185, 622305,'Trabalhador na olericultura (frutos e sementes)','Trabalhador da cultura de feijão, lentilha e ervilha');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (186, 622305,'Trabalhador na olericultura (frutos e sementes)','Trabalhador na cultura de tomate');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (187, 622310,'Trabalhador na olericultura (legumes)','Trabalhador na olericultura (legumes)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (188, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Trabalhador na olericultura (raízes, bulbos e tubérculos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (189, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Plantador de beterraba');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (190, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Trabalhador na cultura de batata-doce');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (191, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Trabalhador na cultura de batata-inglesa');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (192, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Trabalhador na cultura de beterraba');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (193, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Trabalhador na cultura de cebola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (194, 622315,'Trabalhador na olericultura (raízes, bulbos e tubérculos)','Trabalhador na cultura de mandioca');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (195, 622320,'Trabalhador na olericultura (talos, folhas e flores)','Trabalhador na olericultura (talos, folhas e flores)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (196, 622320,'Trabalhador na olericultura (talos, folhas e flores)','Trabalhador na cultura de hortaliças');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (197, 622405,'Trabalhador no cultivo de flores e folhagens de corte','Trabalhador no cultivo de flores e folhagens de corte');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (198, 622405,'Trabalhador no cultivo de flores e folhagens de corte','Floricultor no cultivo de flores e folhagens de corte');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (199, 622405,'Trabalhador no cultivo de flores e folhagens de corte','Trabalhador na floricultura (flores e folhagens de corte)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (200, 622410,'Trabalhador no cultivo de flores em vaso','Trabalhador no cultivo de flores em vaso');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (201, 622410,'Trabalhador no cultivo de flores em vaso','Floricultor no cultivo de flores em vaso');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (202, 622410,'Trabalhador no cultivo de flores em vaso','Trabalhador na floricultura (flores em vaso)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (203, 622415,'Trabalhador no cultivo de forrações','Trabalhador no cultivo de forrações');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (204, 622415,'Trabalhador no cultivo de forrações','Floricultor no cultivo de forragens');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (205, 622415,'Trabalhador no cultivo de forrações','Trabalhador na floricultura (forrações)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (206, 622420,'Trabalhador no cultivo de mudas','Trabalhador no cultivo de mudas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (207, 622420,'Trabalhador no cultivo de mudas','Floricultor no cultivo de mudas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (208, 622420,'Trabalhador no cultivo de mudas','Trabalhador na floricultura (cultivo de mudas)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (209, 622425,'Trabalhador no cultivo de plantas ornamentais','Trabalhador no cultivo de plantas ornamentais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (210, 622425,'Trabalhador no cultivo de plantas ornamentais','Floricultor no cultivo de plantas ornamentais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (211, 622425,'Trabalhador no cultivo de plantas ornamentais','Trabalhador da cultura de plantas ornamentais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (212, 622425,'Trabalhador no cultivo de plantas ornamentais','Trabalhador na floricultura (plantas ornamentais)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (213, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador no cultivo de árvores frutíferas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (214, 622505,'Trabalhador no cultivo de árvores frutíferas','Apanhador de laranja');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (215, 622505,'Trabalhador no cultivo de árvores frutíferas','Colhedor de banana');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (216, 622505,'Trabalhador no cultivo de árvores frutíferas','Colhedor de caju');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (217, 622505,'Trabalhador no cultivo de árvores frutíferas','Colhedor de laranja');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (218, 622505,'Trabalhador no cultivo de árvores frutíferas','Colhedor de manga');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (219, 622505,'Trabalhador no cultivo de árvores frutíferas','Colhedor de pêssego');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (220, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de abacate');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (221, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de acerola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (222, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de ameixa');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (223, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de amora');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (224, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de atemóia');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (225, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de banana');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (226, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de cajá');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (227, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de caju');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (228, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de caqui');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (229, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de carambola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (230, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de cítricos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (231, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de cupuaçu');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (232, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de fruta-pão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (233, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de goiaba');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (234, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de graviola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (235, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de jaca');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (236, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de jenipapo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (237, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de laranja e outros cítricos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (238, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de maçã');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (239, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de manga');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (240, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de nectarina');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (241, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de pêra');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (242, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de pêssego');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (243, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de pinha');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (244, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de pitanga');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (245, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de tamarindo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (246, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador da cultura de umbu');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (247, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador de fruticultura em geral');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (248, 622505,'Trabalhador no cultivo de árvores frutíferas','Trabalhador na cultura de romã');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (249, 622510,'Trabalhador no cultivo de espécies frutíferas rasteiras','Trabalhador no cultivo de espécies frutíferas rasteiras');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (250, 622510,'Trabalhador no cultivo de espécies frutíferas rasteiras','Trabalhador da cultura de abacaxi');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (251, 622510,'Trabalhador no cultivo de espécies frutíferas rasteiras','Trabalhador da cultura de melancia');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (252, 622510,'Trabalhador no cultivo de espécies frutíferas rasteiras','Trabalhador da cultura de melão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (253, 622510,'Trabalhador no cultivo de espécies frutíferas rasteiras','Trabalhador da cultura de morango');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (254, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador no cultivo de trepadeiras frutíferas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (255, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Colhedor de uva');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (256, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador da cultura de framboesa');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (257, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador da cultura de maracujá');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (258, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador da cultura de uva');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (259, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador no cultivo de quiui (kiwi)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (260, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador no cultivo de uva de mesa');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (261, 622515,'Trabalhador no cultivo de trepadeiras frutíferas','Trabalhador no cultivo de uva de vinho e suco');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (262, 622605,'Trabalhador da cultura de cacau','Trabalhador da cultura de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (263, 622605,'Trabalhador da cultura de cacau','Barcaceiro - na cultura de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (264, 622605,'Trabalhador da cultura de cacau','Cacauicultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (265, 622605,'Trabalhador da cultura de cacau','Colhedor de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (266, 622605,'Trabalhador da cultura de cacau','Embandeirador de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (267, 622605,'Trabalhador da cultura de cacau','Enxertador de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (268, 622605,'Trabalhador da cultura de cacau','Podador da cultura de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (269, 622605,'Trabalhador da cultura de cacau','Quebrador - na cultura de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (270, 622605,'Trabalhador da cultura de cacau','Tirador - na cultura de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (271, 622605,'Trabalhador da cultura de cacau','Tropeiro - na cultura de cacau');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (272, 622610,'Trabalhador da cultura de café','Trabalhador da cultura de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (273, 622610,'Trabalhador da cultura de café','Apanhador de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (274, 622610,'Trabalhador da cultura de café','Arruador de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (275, 622610,'Trabalhador da cultura de café','Cafeicultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (276, 622610,'Trabalhador da cultura de café','Catador de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (277, 622610,'Trabalhador da cultura de café','Colhedor de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (278, 622610,'Trabalhador da cultura de café','Terreirista de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (279, 622610,'Trabalhador da cultura de café','Terrereiro de café');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (280, 622615,'Trabalhador da cultura de erva-mate','Trabalhador da cultura de erva-mate');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (281, 622615,'Trabalhador da cultura de erva-mate','Colhedor de erva-mate');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (282, 622615,'Trabalhador da cultura de erva-mate','Plantador da matecultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (283, 622615,'Trabalhador da cultura de erva-mate','Podador de erva-mate');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (284, 622615,'Trabalhador da cultura de erva-mate','Tarefeiro na cultura de erva-mate');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (285, 622615,'Trabalhador da cultura de erva-mate','Trabalhador da matecultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (286, 622620,'Trabalhador da cultura de fumo','Trabalhador da cultura de fumo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (287, 622620,'Trabalhador da cultura de fumo','Colhedor de fumo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (288, 622620,'Trabalhador da cultura de fumo','Cultivador de fumo - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (289, 622620,'Trabalhador da cultura de fumo','Fumeiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (290, 622620,'Trabalhador da cultura de fumo','Fumicultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (291, 622625,'Trabalhador da cultura de guaraná','Trabalhador da cultura de guaraná');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (292, 622625,'Trabalhador da cultura de guaraná','Colhedor de guaraná');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (293, 622625,'Trabalhador da cultura de guaraná','Cultivador de guaraná - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (294, 622625,'Trabalhador da cultura de guaraná','Guaranazeiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (295, 622625,'Trabalhador da cultura de guaraná','Podador de guaraná');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (296, 622625,'Trabalhador da cultura de guaraná','Torrador de guaraná');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (297, 622705,'Trabalhador na cultura de amendoim','Trabalhador na cultura de amendoim');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (298, 622710,'Trabalhador na cultura de canola','Trabalhador na cultura de canola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (299, 622715,'Trabalhador na cultura de coco-da-baía','Trabalhador na cultura de coco-da-baía');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (300, 622715,'Trabalhador na cultura de coco-da-baía','Colhedor de coco');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (301, 622715,'Trabalhador na cultura de coco-da-baía','Subidor de coqueiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (302, 622720,'Trabalhador na cultura de dendê','Trabalhador na cultura de dendê');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (303, 622720,'Trabalhador na cultura de dendê','Cortador de dendê');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (304, 622725,'Trabalhador na cultura de mamona','Trabalhador na cultura de mamona');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (305, 622725,'Trabalhador na cultura de mamona','Quebrador de mamona');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (306, 622730,'Trabalhador na cultura de soja','Trabalhador na cultura de soja');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (307, 622735,'Trabalhador na cultura do girassol','Trabalhador na cultura do girassol');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (308, 622740,'Trabalhador na cultura do linho','Trabalhador na cultura do linho');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (309, 622805,'Trabalhador da cultura de especiarias','Trabalhador da cultura de especiarias');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (310, 622805,'Trabalhador da cultura de especiarias','Lavrador da cultura de especiarias');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (311, 622805,'Trabalhador da cultura de especiarias','Trabalhador na cultura de pimenta-do-reino');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (312, 622810,'Trabalhador da cultura de plantas aromáticas e medicinais','Trabalhador da cultura de plantas aromáticas e medicinais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (313, 623005,'Adestrador de animais','Adestrador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (314, 623005,'Adestrador de animais','Amansador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (315, 623005,'Adestrador de animais','Amestrador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (316, 623005,'Adestrador de animais','Condicionador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (317, 623005,'Adestrador de animais','Domador - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (318, 623005,'Adestrador de animais','Domador (asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (319, 623005,'Adestrador de animais','Domador de animais domésticos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (320, 623005,'Adestrador de animais','Domador (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (321, 623005,'Adestrador de animais','Educador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (322, 623005,'Adestrador de animais','Instrutor de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (323, 623005,'Adestrador de animais','Treinador de animais domésticos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (324, 623010,'Inseminador','Inseminador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (325, 623010,'Inseminador','Inseminador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (326, 623015,'Trabalhador de pecuária polivalente','Trabalhador de pecuária polivalente');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (327, 623015,'Trabalhador de pecuária polivalente','Arraçoador (pecuária polivalente)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (328, 623015,'Trabalhador de pecuária polivalente','Assinalador - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (329, 623015,'Trabalhador de pecuária polivalente','Campeiro - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (330, 623015,'Trabalhador de pecuária polivalente','Capataz');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (331, 623015,'Trabalhador de pecuária polivalente','Castrador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (332, 623015,'Trabalhador de pecuária polivalente','Castrador - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (333, 623015,'Trabalhador de pecuária polivalente','Cevador (pecuária)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (334, 623015,'Trabalhador de pecuária polivalente','Condutor de bois - na criação');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (335, 623015,'Trabalhador de pecuária polivalente','Condutor de bovinos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (336, 623015,'Trabalhador de pecuária polivalente','Embretador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (337, 623015,'Trabalhador de pecuária polivalente','Manoseador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (338, 623015,'Trabalhador de pecuária polivalente','Peão de cavalo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (339, 623015,'Trabalhador de pecuária polivalente','Peão de estábulo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (340, 623015,'Trabalhador de pecuária polivalente','Preparador de ração natural para gado');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (341, 623020,'Tratador de animais','Tratador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (342, 623020,'Tratador de animais','Cuidador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (343, 623020,'Tratador de animais','Tratador - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (344, 623020,'Tratador de animais','Tratador de animais - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (345, 623020,'Tratador de animais','Tratador de animais (jardim zoológico)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (346, 623020,'Tratador de animais','Vacinador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (347, 623025,'Casqueador de animais','Casqueador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (348, 623025,'Casqueador de animais','Casqueador de bovinos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (349, 623030,'Ferrador de animais','Ferrador de animais');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (350, 623030,'Ferrador de animais','Ferrador de equinos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (351, 623030,'Ferrador de animais','Ferrageador de equinos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (352, 623105,'Trabalhador da pecuária (asininos e muares)','Trabalhador da pecuária (asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (353, 623105,'Trabalhador da pecuária (asininos e muares)','Adestrador de animais de trabalho ( asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (354, 623105,'Trabalhador da pecuária (asininos e muares)','Campeiro (asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (355, 623105,'Trabalhador da pecuária (asininos e muares)','Peão (asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (356, 623105,'Trabalhador da pecuária (asininos e muares)','Tratador (asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (357, 623105,'Trabalhador da pecuária (asininos e muares)','Treinador (asininos e muares)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (358, 623110,'Trabalhador da pecuária (bovinos corte)','Trabalhador da pecuária (bovinos corte)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (359, 623110,'Trabalhador da pecuária (bovinos corte)','Ajudante de boiadeiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (360, 623110,'Trabalhador da pecuária (bovinos corte)','Ajudante de vaqueiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (361, 623110,'Trabalhador da pecuária (bovinos corte)','Arrebanhador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (362, 623110,'Trabalhador da pecuária (bovinos corte)','Auxiliar de vaqueiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (363, 623110,'Trabalhador da pecuária (bovinos corte)','Batedor de pasto');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (364, 623110,'Trabalhador da pecuária (bovinos corte)','Campeiro (bovinos de corte)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (365, 623110,'Trabalhador da pecuária (bovinos corte)','Peão de pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (366, 623110,'Trabalhador da pecuária (bovinos corte)','Tocador de gado - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (367, 623110,'Trabalhador da pecuária (bovinos corte)','Trabalhador rural');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (368, 623110,'Trabalhador da pecuária (bovinos corte)','Vaqueiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (369, 623110,'Trabalhador da pecuária (bovinos corte)','Vaqueiro - na agropecuária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (370, 623110,'Trabalhador da pecuária (bovinos corte)','Vaqueiro (bovinos corte)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (371, 623110,'Trabalhador da pecuária (bovinos corte)','Vaqueiro inseminador (bovinos corte)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (372, 623115,'Trabalhador da pecuária (bovinos leite)','Trabalhador da pecuária (bovinos leite)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (373, 623115,'Trabalhador da pecuária (bovinos leite)','Apartador de gado');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (374, 623115,'Trabalhador da pecuária (bovinos leite)','Operador de ordenhadeira');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (375, 623115,'Trabalhador da pecuária (bovinos leite)','Ordenhador - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (376, 623115,'Trabalhador da pecuária (bovinos leite)','Retireiro - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (377, 623115,'Trabalhador da pecuária (bovinos leite)','Retireiro inseminador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (378, 623115,'Trabalhador da pecuária (bovinos leite)','Trabalhador - na pecuária - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (379, 623115,'Trabalhador da pecuária (bovinos leite)','Vaqueiro (bovinhos leite)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (380, 623115,'Trabalhador da pecuária (bovinos leite)','Vaqueiro inseminador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (381, 623120,'Trabalhador da pecuária (bubalinos)','Trabalhador da pecuária (bubalinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (382, 623120,'Trabalhador da pecuária (bubalinos)','Campeiro (bubalinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (383, 623120,'Trabalhador da pecuária (bubalinos)','Peão (bubalinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (384, 623120,'Trabalhador da pecuária (bubalinos)','Retireiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (385, 623120,'Trabalhador da pecuária (bubalinos)','Trabalhador rural (bubalinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (386, 623120,'Trabalhador da pecuária (bubalinos)','Vaqueiro (bubalinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (387, 623125,'Trabalhador da pecuária (eqüinos)','Trabalhador da pecuária (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (388, 623125,'Trabalhador da pecuária (eqüinos)','Adestrador (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (389, 623125,'Trabalhador da pecuária (eqüinos)','Campeiro (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (390, 623125,'Trabalhador da pecuária (eqüinos)','Cavalariço');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (391, 623125,'Trabalhador da pecuária (eqüinos)','Cavaleiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (392, 623125,'Trabalhador da pecuária (eqüinos)','Encilhador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (393, 623125,'Trabalhador da pecuária (eqüinos)','Pantaneiro - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (394, 623125,'Trabalhador da pecuária (eqüinos)','Peão (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (395, 623125,'Trabalhador da pecuária (eqüinos)','Repassador - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (396, 623125,'Trabalhador da pecuária (eqüinos)','Tratador (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (397, 623125,'Trabalhador da pecuária (eqüinos)','Treinador (eqüinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (398, 623205,'Trabalhador da caprinocultura','Trabalhador da caprinocultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (399, 623205,'Trabalhador da caprinocultura','Caprinocultor - exclusive conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (400, 623205,'Trabalhador da caprinocultura','Retireiro (caprinos)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (401, 623205,'Trabalhador da caprinocultura','Tratador de animais - caprinos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (402, 623210,'Trabalhador da ovinocultura','Trabalhador da ovinocultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (403, 623210,'Trabalhador da ovinocultura','Ovinocultor - exclusive conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (404, 623210,'Trabalhador da ovinocultura','Pastor - na pecuária');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (405, 623210,'Trabalhador da ovinocultura','Tosador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (406, 623210,'Trabalhador da ovinocultura','Tosquiador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (407, 623210,'Trabalhador da ovinocultura','Trabalhador de manutenção e preparação de tosqueadeiras');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (408, 623215,'Trabalhador da suinocultura','Trabalhador da suinocultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (409, 623215,'Trabalhador da suinocultura','Suinocultor - exclusive contaprópria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (410, 623305,'Trabalhador da avicultura de corte','Trabalhador da avicultura de corte');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (411, 623305,'Trabalhador da avicultura de corte','Avicultor de corte - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (412, 623310,'Trabalhador da avicultura de postura','Trabalhador da avicultura de postura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (413, 623310,'Trabalhador da avicultura de postura','Avicultorde postura - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (414, 623310,'Trabalhador da avicultura de postura','Avicultor - exclusive conta própria na avicultura de postura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (415, 623315,'Operador de incubadora','Operador de incubadora');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (416, 623315,'Operador de incubadora','Auxiliar de incubação');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (417, 623315,'Operador de incubadora','Incubador de ovos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (418, 623320,'Trabalhador da cunicultura','Trabalhador da cunicultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (419, 623320,'Trabalhador da cunicultura','Cunicultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (420, 623325,'Sexador','Sexador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (421, 623325,'Sexador','Selecionador de pintos por sexo');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (422, 623325,'Sexador','Sexador de pintos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (423, 623405,'Trabalhador em criatórios de animais produtores de veneno','Trabalhador em criatórios de animais produtores de veneno');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (424, 623405,'Trabalhador em criatórios de animais produtores de veneno','Cobreiro');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (425, 623405,'Trabalhador em criatórios de animais produtores de veneno','Serpentarista');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (426, 623410,'Trabalhador na apicultura','Trabalhador na apicultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (427, 623410,'Trabalhador na apicultura','Apicultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (428, 623410,'Trabalhador na apicultura','Criador de abelhas - exclusive conta própia e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (429, 623415,'Trabalhador na minhocultura','Trabalhador na minhocultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (430, 623415,'Trabalhador na minhocultura','Minhoqueiro - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (431, 623420,'Trabalhador na sericicultura','Trabalhador na sericicultura');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (432, 623420,'Trabalhador na sericicultura','Criador de bicho-da-seda - conta própria');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (433, 623420,'Trabalhador na sericicultura','Parceiro do bicho-da-seda');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (434, 623420,'Trabalhador na sericicultura','Sericicultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (435, 623420,'Trabalhador na sericicultura','Sericultor - exclusive conta própria e empregador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (436, 623420,'Trabalhador na sericicultura','Trabalhador sericícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (437, 641005,'Operador de colheitadeira','Operador de colheitadeira');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (438, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de máquinas de beneficiamento de produtos agrícolas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (439, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de estufas mecânicas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (440, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de máquinas agrícolas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (441, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de motobomba');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (442, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de secadeiras no beneficiamento de produtos agrícolas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (443, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de secador de resíduos');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (444, 641010,'Operador de máquinas de beneficiamento de produtos agrícolas','Operador de secador (produtos agrícolas)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (445, 641015,'Tratorista agrícola','Tratorista agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (446, 641015,'Tratorista agrícola','Arador');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (447, 641015,'Tratorista agrícola','Operador de adubadeira');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (448, 641015,'Tratorista agrícola','Operador de implementos agrícolas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (449, 641015,'Tratorista agrícola','Operador de máquina agrícola');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (450, 641015,'Tratorista agrícola','Tratoristaoperador de roçadeira');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (451, 641015,'Tratorista agrícola','Tratorista operador de semeadeira');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (452, 642005,'Operador de colhedor florestal','Operador de colhedor florestal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (453, 642005,'Operador de colhedor florestal','Operador de máquinas florestais (colheitadeira)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (454, 642010,'Operador de máquinas florestais estáticas','Operador de máquinas florestais estáticas');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (455, 642015,'Operador de trator florestal','Operador de trator florestal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (456, 642015,'Operador de trator florestal','Operador de máquinas florestais (tratores)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (457, 642015,'Operador de trator florestal','Tratorista florestal');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (458, 643005,'Trabalhador na operação de sistema de irrigação localizada (microaspersão e gotejamento)','Trabalhador na operação de sistema de irrigação localizada (microaspersão e gotejamento)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (459, 643010,'Trabalhador na operação de sistema de irrigação por aspersão (pivô central)','Trabalhador na operação de sistema de irrigação por aspersão (pivô central)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (460, 643015,'Trabalhador na operação de sistemas convencionais de irrigação por aspersão','Trabalhador na operação de sistemas convencionais de irrigação por aspersão');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (461, 643020,'Trabalhador na operação de sistemas de irrigação e aspersão (alto propelido)','Trabalhador na operação de sistemas de irrigação e aspersão (alto propelido)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (462, 643020,'Trabalhador na operação de sistemas de irrigação e aspersão (alto propelido)','Trabalhador na operação de sistemas de irrigação e aspersão (canhão)');
INSERT or IGNORE INTO cbo(id, codigo, nome, sinonimo) VALUES (463, 643025,'Trabalhador na operação de sistemas de irrigação por superfície e drenagem','Trabalhador na operação de sistemas de irrigação por superfície e drenagem');

/* Agrotóxicos */
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1, '2,4-D (240) + Picloram (64) SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2, '2,4-D 806 RN');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (3, '2,4-D 806 SL Alamos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (4, '2,4-D Agritec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (5, '2,4-D Agroimport');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (6, '2,4-D Amina 840 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (7, '2,4-D Amina CCAB 806 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (8, '2,4-D CROP 806 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (9, '2,4-D Fersol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (10, '2,4-D Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (11, '2,4-D Super Amine SG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (12, '2,4-D Tecnomyl');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (13, 'Abacus HC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (14, 'Abadin 72 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (15, 'Abamectin 72 EC Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (16, 'Abamectin Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (17, 'Abamectin Prentiss');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (18, 'Abamex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (19, 'Abamex BR 18');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (20, 'Abamit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (21, 'Able');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (22, 'Abone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (23, 'Absoluto 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (24, 'Absoluto SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (25, 'Absoluto WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (26, 'Academic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (27, 'Acaramik');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (28, 'Acarit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (29, 'Acarit EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (30, 'Accent');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (31, 'Accurate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (32, 'Acefato CCAB 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (33, 'Acefato Fersol 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (34, 'Acefato Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (35, 'Acehero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (36, 'Acert');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (37, 'Aceta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (38, 'Acetamiprid CCAB 200 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (39, 'Acetamiprid Crop');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (40, 'Acetamiprid Nortox 200 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (41, 'Acetamiprid Nortox SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (42, 'Acetamiprid STK 200 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (43, 'Acillatem');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (44, 'Aclamado BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (45, 'Acrobat MZ');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (46, 'Acronis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (47, 'Acrux');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (48, 'Acrux 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (49, 'Actara 250 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (50, 'Actara 750 SG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (51, 'Actellic 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (52, 'Actelliclambda');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (53, 'Actend');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (54, 'Actigard');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (55, 'Acucor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (56, 'Adage 350 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (57, 'Adante Xtra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (58, 'Adapty');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (59, 'Aderis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (60, 'Adifac');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (61, 'Admiral 100 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (62, 'Advance');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (63, 'Afalon 450 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (64, 'Afalon SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (65, 'Affinity 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (66, 'Affinity 400 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (67, 'AfincoBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (68, 'Afitrix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (69, 'Agata');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (70, 'Agefix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (71, 'Agile');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (72, 'Agree');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (73, 'Agria');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (74, 'Agrilist');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (75, 'Agrinose');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (76, 'Agritoato 400');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (77, 'Agritone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (78, 'Agroben 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (79, 'Agroneem.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (80, 'Agro-Oil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (81, 'Agrothio 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (82, 'Aim');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (83, 'Airone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (84, 'Airone Inox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (85, 'Airone Scudo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (86, 'Akito');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (87, 'Alaclor + Atrazina SC Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (88, 'Alaclor Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (89, 'Albatross');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (90, 'Albatross 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (91, 'Alea');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (92, 'Alia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (93, 'ALIBI');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (94, 'Alibi Flora');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (95, 'Alicerce');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (96, 'Aliette');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (97, 'Alika');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (98, 'Alion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (99, 'Alion Pro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (100, 'Ally');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (101, 'Alstar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (102, 'Alsystin 250 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (103, 'Alsystin SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (104, 'Alsystin WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (105, 'Altacor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (106, 'Altacor BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (107, 'Alterne');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (108, 'Altima');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (109, 'Alto 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (110, 'Alverde');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (111, 'Amaiz');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (112, 'Ameris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (113, 'Ametista');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (114, 'Ametrex 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (115, 'Ametrex WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (116, 'Ametrina 500 SC Rainbow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (117, 'Ametrina Alta 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (118, 'Ametrina Atanor 50 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (119, 'Aminamar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (120, 'Aminol 806');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (121, 'Amistar 500 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (122, 'Amistar Top');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (123, 'Amistar WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (124, 'Amplexus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (125, 'Ampligo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (126, 'Ampligo Pro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (127, 'Amplo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (128, 'Amulet');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (129, 'Amulet TOP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (130, 'Anchor SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (131, 'Ancosar 720');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (132, 'Andril Prime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (133, 'Antracol 700 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (134, 'Antrimo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (135, 'Apache 100 GR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (136, 'Apollo 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (137, 'Appalus 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (138, 'Applaud 250');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (139, 'Applicato 50 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (140, 'Approve');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (141, 'Approve WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (142, 'Aproach Prima');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (143, 'Apron RFC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (144, 'Aptika');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (145, 'Aptur-PF.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (146, 'Aquila');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (147, 'Aramo 200');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (148, 'Arcadia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (149, 'Arcar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (150, 'Arena');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (151, 'Argenfrut RV');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (152, 'Arizium');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (153, 'Armigen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (154, 'Arrange');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (155, 'Arrank');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (156, 'Array 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (157, 'Arreio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (158, 'Arreio Milenia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (159, 'Arreio Pasto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (160, 'Arriba GR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (161, 'Arrivo 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (162, 'Arrow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (163, 'Artea');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (164, 'Artys');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (165, 'Artys BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (166, 'Aspy 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (167, 'Assaris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (168, 'Assist');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (169, 'Astral');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (170, 'Astro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (171, 'Asulox 400');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (172, 'Atabron 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (173, 'Atak');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (174, 'Atectra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (175, 'Atento');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (176, 'Ativum');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (177, 'Atraer WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (178, 'Atralhida');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (179, 'Atranex 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (180, 'Atranex WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (181, 'Atrasimex 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (182, 'Atrasimex WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (183, 'Atrazina + Nicosulfuron Nortox WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (184, 'Atrazina 500 SC Alamos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (185, 'Atrazina 500 SC Rainbow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (186, 'Atrazina 900 WG Atanor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (187, 'Atrazina Agro Import');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (188, 'Atrazina CCAB 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (189, 'Atrazina Fersol 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (190, 'Atrazina Max Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (191, 'Atrazina Nortox 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (192, 'Atrazina SD 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (193, 'Atrevido');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (194, 'Attic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (195, 'Atualist');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (196, 'Atulamina 806 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (197, 'AUG 103');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (198, 'AUG 117');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (199, 'AUG 122');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (200, 'AUG 126');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (201, 'AUG 137');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (202, 'Auge');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (203, 'Auin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (204, 'Auin CE.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (205, 'Aura');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (206, 'Aura 200');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (207, 'Aurora');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (208, 'Aurora 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (209, 'AutênticoBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (210, 'Authority');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (211, 'Aval');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (212, 'Aval 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (213, 'Avant 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (214, 'Avatar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (215, 'Avaunt 150');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (216, 'Avguron Extra SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (217, 'Avicta 500 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (218, 'Avicta 500 FS Pro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (219, 'AvidoBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (220, 'Avura');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (221, 'Azact CE');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (222, 'Azamax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (223, 'Azimut');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (224, 'Azimut Supra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (225, 'Azox 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (226, 'Azoxistrobina CCAB 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (227, 'Bac Control Max EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (228, 'Bac-Control Max WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (229, 'Bac-Control WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (230, 'Baculo-Soja');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (231, 'Baculovirus AEE');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (232, 'Baculovirus Soja WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (233, 'Ballveria');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (234, 'Bamako 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (235, 'Band');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (236, 'Banjo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (237, 'Banter');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (238, 'Banzai');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (239, 'Barao');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (240, 'Baris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (241, 'Basagran 480');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (242, 'Basagran 600');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (243, 'Basamid');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (244, 'Batalha 240 SL.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (245, 'Batent');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (246, 'Battle');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (247, 'Battus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (248, 'Bavistin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (249, 'Bayfidan EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (250, 'Baytan FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (251, 'Bazuka 216 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (252, 'BeauveControl');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (253, 'BEAUVECONTROL EXTREME');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (254, 'Beauvel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (255, 'Beauveria JCO');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (256, 'Beauveria Oligos WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (257, 'Beauvetec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (258, 'Bedane');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (259, 'Bellis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (260, 'Belt');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (261, 'Belure');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (262, 'Belure TOP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (263, 'Bench');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (264, 'Bendazol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (265, 'Benevia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (266, 'Benforce');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (267, 'BequeBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (268, 'Biflex treebags');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (269, 'Bim 750 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (270, 'Bimate SA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (271, 'Bio BM');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (272, 'Bio Bonagota');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (273, 'Bio Broca');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (274, 'Bio Carambolae');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (275, 'Bio Defense');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (276, 'Bio Helicoverpa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (277, 'Bio Lobesia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (278, 'Bio Neo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (279, 'Bio Pectinophora');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (280, 'Bio Pseudoplusia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (281, 'Bio Rhynchophorus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (282, 'Bio Serrico');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (283, 'Bio Spodoptera');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (284, 'Bio Trimedlure');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (285, 'Bio Tuta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (286, 'Bio Zenon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (287, 'Biobac');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (288, 'Biobaci');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (289, 'Biobev');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (290, 'BioBVB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (291, 'Bioceratitis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (292, 'Biocydia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (293, 'Bioeco Cotesia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (294, 'Biogenol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (295, 'Biographolita');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (296, 'Bio-Hulk');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (297, 'Bio-imune.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (298, 'Bioisca');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (299, 'Biolita');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (300, 'Biometha GR Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (301, 'Bion 500 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (302, 'Bioprogress');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (303, 'Biorhizium GR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (304, 'Biorhizium WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (305, 'Biotesia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (306, 'Bioveria WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (307, 'Biovespa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (308, 'Bistar 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (309, 'Biver');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (310, 'Blade');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (311, 'Blast');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (312, 'Blazer Sol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (313, 'Bleran');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (314, 'Blindado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (315, 'Blitz');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (316, 'Blowout');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (317, 'Boiadeiro 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (318, 'Bold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (319, 'Bolero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (320, 'Boral 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (321, 'Boral Full');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (322, 'Boreal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (323, 'Borneo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (324, 'Botran 750');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (325, 'Bouveriz WP Biocontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (326, 'Bovebio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (327, 'Bovemax EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (328, 'Boveria-Turbo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (329, 'Boveril WP PL63');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (330, 'Boveryd');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (331, 'Boveryd FR 25');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (332, 'Braddock');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (333, 'Brasao');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (334, 'Bravonil 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (335, 'Bravonil 720');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (336, 'Bravonil 750 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (337, 'Bravonil Ultrex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (338, 'Brigada EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (339, 'Brigade 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (340, 'Brigade 25 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (341, 'BrilhanteBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (342, 'Brio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (343, 'Brion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (344, 'Brisa WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (345, 'BritBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (346, 'Broker 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (347, 'Brometila');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (348, 'Bromex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (349, 'Browser');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (350, 'Brutus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (351, 'BTControl');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (352, 'BT-Turbo Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (353, 'Bucanero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (354, 'Bulldock 125 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (355, 'Bumper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (356, 'Bunema 330 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (357, 'Buran');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (358, 'Butiron');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (359, 'Cabrio Top');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (360, 'Calaris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (361, 'Califorce.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (362, 'Callisto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (363, 'Calypso');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (364, 'Camp-D');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (365, 'Campeon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (366, 'Campestre 240 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (367, 'Canastra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (368, 'Canion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (369, 'Cantus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (370, 'CapatazBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (371, 'Capo WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (372, 'Capote');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (373, 'Capri');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (374, 'Captain 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (375, 'Captain 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (376, 'Captan 200 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (377, 'Captan 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (378, 'Captan Fersol 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (379, 'Captan SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (380, 'Captor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (381, 'Capture 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (382, 'Capture 120 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (383, 'Capture 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (384, 'Caramba 90');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (385, 'Carben 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (386, 'Carbendazim CCAB 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (387, 'Carbendazim Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (388, 'Carbendazim Nortox 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (389, 'Carbendazim SC Cheminova');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (390, 'Carbendazim Stk 500 Sc-A');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (391, 'Carbendazim STK 500 SC-B');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (392, 'Carbine 500 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (393, 'Carbomax 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (394, 'Cardeal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (395, 'Carial');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (396, 'Carial Opti');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (397, 'Carnadine');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (398, 'Cartago');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (399, 'Cartap BR 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (400, 'Cartarys');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (401, 'CartuchoVIT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (402, 'Cartugen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (403, 'Cartugen CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (404, 'Cascade 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (405, 'Castor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (406, 'Catcher 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (407, 'Cefanol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (408, 'Celebrate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (409, 'Celest XL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (410, 'Celta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (411, 'Cenit Gat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (412, 'Censor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (413, 'Census - GAT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (414, 'Centauro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (415, 'Centric');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (416, 'Centurion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (417, 'Cercobin 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (418, 'Cercobin 700 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (419, 'Cercobin 875 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (420, 'Cerconil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (421, 'Cerconil WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (422, 'Certero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (423, 'Certeza N');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (424, 'Certus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (425, 'Cetro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (426, 'Challenger');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (427, 'Chancella');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (428, 'Charrua 430 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (429, 'Chess 500 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (430, 'Chiave 215 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (431, 'Chiave Sup');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (432, 'Chiva WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (433, 'Chloromo 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (434, 'Chlorsab 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (435, 'Chopper Florestal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (436, 'Chrysogen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (437, 'Chrysogen CCAB.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (438, 'Ciclone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (439, 'Ciclone 48 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (440, 'Cierto 100 GR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (441, 'Cigaral');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (442, 'Cignus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (443, 'Cimox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (444, 'Cinelli 250 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (445, 'Cipermetrin 250 EC CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (446, 'Cipermetrina 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (447, 'Cipermetrina 250 EC CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (448, 'Cipermetrina Nortox 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (449, 'Cipertrin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (450, 'Citroagricola');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (451, 'Clariva PN');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (452, 'Clariva PN BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (453, 'Classic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (454, 'Clean Spray');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (455, 'Cleaner');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (456, 'Cleaner XTRA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (457, 'Clearup');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (458, 'Clenil XTRA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (459, 'Cletodim CCAB 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (460, 'Cletodim Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (461, 'Clincher');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (462, 'Clipper Sinon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (463, 'Clomanex 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (464, 'Clomazone 500 EC FMC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (465, 'Clomom');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (466, 'Clorim');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (467, 'Clorimurom Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (468, 'Clorimuron CCAB 250 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (469, 'Clorimuron Master Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (470, 'Clorimuron Prentiss');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (471, 'Clorpirifos 480 EC Milenia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (472, 'Clorpirifos Fersol 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (473, 'Clorpirifos Nortox EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (474, 'Clorpirifos Poland 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (475, 'Clorpirifos Sabero 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (476, 'Closer');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (477, 'Closer SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (478, 'Coact');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (479, 'Cobox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (480, 'Cobox DF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (481, 'Cobre Atar BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (482, 'Cobre Fersol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (483, 'Collis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (484, 'Combine 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (485, 'Comboio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (486, 'Comet');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (487, 'Comissario');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (488, 'Commanche 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (489, 'Commence');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (490, 'Compact.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (491, 'Compass');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (492, 'Compete');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (493, 'Completto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (494, 'Concreto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (495, 'Condor 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (496, 'Condor EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (497, 'Confidence');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (498, 'Confidor Supra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (499, 'Connect');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (500, 'Conquest');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (501, 'Consento');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (502, 'Constant');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (503, 'Contact');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (504, 'Contain');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (505, 'Contrap');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (506, 'Controller');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (507, 'Convicto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (508, 'Convicto SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (509, 'Copa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (510, 'Copsuper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (511, 'Coragen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (512, 'Cordial 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (513, 'Cordial 100 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (514, 'Coremaxx');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (515, 'CoronelBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (516, 'Cortador 806 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (517, 'Cosmolure');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (518, 'Costar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (519, 'Cotesia - Controbil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (520, 'Cotesia Agrobio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (521, 'Cotesia Auca');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (522, 'Cotesia Bioamil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (523, 'Cotesia Biocana');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (524, 'Cotesia Biocana GO');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (525, 'Cotesia Biocontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (526, 'Cotesia Biorganic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (527, 'Cotesia BUG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (528, 'Cotesia Cetma');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (529, 'Cotesia Fitoagro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (530, 'Cotesia Flavipes Bioeffect');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (531, 'Cotesia Flavipes Bioflora');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (532, 'Cotesia Flavipes Bioresult');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (533, 'Cotesia Flavipes Paraguaçu');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (534, 'Cotesia Flavips/MCP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (535, 'Cotesia Marilia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (536, 'Cotesia Probio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (537, 'Cotesia Tecnobil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (538, 'Cotesia TF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (539, 'Cotesiaasplan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (540, 'Cotezen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (541, 'CottonQuik');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (542, 'Cougar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (543, 'Counter 150 G');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (544, 'Cover DF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (545, 'Covinex 700');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (546, 'Coyote');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (547, 'Coyote WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (548, 'Crater');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (549, 'Credit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (550, 'Credit 480');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (551, 'Creox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (552, 'Crescendo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (553, 'Creta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (554, 'Cricen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (555, 'Cronnos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (556, 'Cronnos WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (557, 'Cropstar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (558, 'Crucial');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (559, 'Cruiser 350 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (560, 'Cruiser 600 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (561, 'Cruiser Advanced');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (562, 'Cruiser Opti');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (563, 'Cryptomip');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (564, 'Crystal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (565, 'Cuantiva');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (566, 'Cultar 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (567, 'Cultifix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (568, 'Cuprital 700');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (569, 'Cuprodil WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (570, 'Cuprogarb 350');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (571, 'Cuprogarb 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (572, 'Cuprosate Gold 720 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (573, 'Cuprozeb');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (574, 'Cupuran 350 PM');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (575, 'Cupuran 500 PM');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (576, 'Curado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (577, 'Curathane');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (578, 'Curathane SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (579, 'Curavial');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (580, 'Curbix 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (581, 'Curbix 200 SC A');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (582, 'Curinga');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (583, 'Curygen EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (584, 'Curyom 550 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (585, 'Curzate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (586, 'Cyman');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (587, 'Cyper Copa 250 Ec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (588, 'Cypress 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (589, 'Cyptrin 250 CE');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (590, 'Cyptrin Prime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (591, 'CYTOLIN');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (592, 'Czar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (593, 'Dacobre WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (594, 'Daconil 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (595, 'Daconil BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (596, 'Daconil WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (597, 'Dacostar 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (598, 'Dacostar 750');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (599, 'Dacostar WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (600, 'Daga');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (601, 'DalNeem EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (602, 'Danado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (603, 'Daniato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (604, 'Danimen 300 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (605, 'Davos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (606, 'Daytona');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (607, 'Decis 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (608, 'Decis 25 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (609, 'Decis Ultra 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (610, 'Decision 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (611, 'Decisive');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (612, 'Decorum');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (613, 'Defend WDG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (614, 'Delan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (615, 'Delegate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (616, 'DemolidorBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (617, 'Denaxo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (618, 'Deoro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (619, 'Dermacor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (620, 'Dermacor BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (621, 'Derosal 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (622, 'Derosal Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (623, 'Derox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (624, 'Desali');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (625, 'Dessecan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (626, 'Dessicash');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (627, 'Dessicash 200 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (628, 'Dexa WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (629, 'Dez');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (630, 'Dez Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (631, 'Diafentiuron 500 SC Proventis.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (632, 'Diafentiuron CCAB 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (633, 'DiamanteBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (634, 'Diamond');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (635, 'Dicamax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (636, 'Dicarzol 500 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (637, 'Difcor 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (638, 'Difere');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (639, 'Diflubenzuron 240 SC Crop');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (640, 'Diflubenzuron AGP 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (641, 'Difluchem 240 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (642, 'Diflucrop');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (643, 'Diflumax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (644, 'Diflumax 240 SC Helm');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (645, 'Difo 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (646, 'Dihex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (647, 'Dimax 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (648, 'Dimetoato 500 EC Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (649, 'Dimexion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (650, 'Dimilin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (651, 'Dimilin 80 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (652, 'Dinamaz 70 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (653, 'Dinamic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (654, 'Dinaxine');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (655, 'Diox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (656, 'Dipel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (657, 'DIPEL ES-NT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (658, 'Dipel WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (659, 'Dipel WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (660, 'Diplomata K');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (661, 'Diquash 200 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (662, 'Diquat 200 SL Rainbow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (663, 'Diquat CCAB 200 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (664, 'Direct');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (665, 'Direx 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (666, 'Discover 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (667, 'Disparo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (668, 'Dithane NT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (669, 'Dithane NT WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (670, 'Dithane WG NT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (671, 'Dithiobin 780 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (672, 'Diurex Agricur 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (673, 'Diurex Agricur 800 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (674, 'Diurex WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (675, 'Diuron 500 SC Milenia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (676, 'Diuron 500 SC Rainbow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (677, 'Diuron Fersol 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (678, 'Diuron JI 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (679, 'Diuron Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (680, 'Diuron Nortox 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (681, 'Dividend Supreme');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (682, 'Dizone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (683, 'DMA 806 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (684, 'Dobbel.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (685, 'Doble');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (686, 'Dociar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (687, 'Dodex 450 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (688, 'Domark 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (689, 'Domark Excell');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (690, 'Dominum');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (691, 'Dominum EZ');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (692, 'Dominum EZ Forestry');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (693, 'Dominum XT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (694, 'Dontor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (695, 'Dorado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (696, 'Dorado EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (697, 'Dormex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (698, 'Douro 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (699, 'Drible');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (700, 'Dropp Ultra SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (701, 'Druid 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (702, 'Du Dim 80 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (703, 'Du Din');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (704, 'Dual Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (705, 'Dual Gold 915 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (706, 'Dublon SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (707, 'Ducat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (708, 'Duetto WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (709, 'Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (710, 'Duolist');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (711, 'Duravel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (712, 'Durivo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (713, 'Dynasty');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (714, 'Dytrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (715, 'Echo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (716, 'Echo WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (717, 'Eco Meta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (718, 'Ecobass');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (719, 'Ecometa Power');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (720, 'Eco-Shot');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (721, 'Ecotesia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (722, 'Ecotrich WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (723, 'Eficaz Nema');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (724, 'Eforia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (725, 'Egan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (726, 'Elatus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (727, 'Elatus 150 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (728, 'Elatus Trio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (729, 'Electro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (730, 'Eleitto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (731, 'Eleve');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (732, 'Elite');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (733, 'Ellect');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (734, 'Emerald');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (735, 'Eminent 125 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (736, 'Eminent Excell');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (737, 'Eminent Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (738, 'Emzeb 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (739, 'Enduro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (740, 'Engeo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (741, 'Engeo Pleno S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (742, 'Enlist Colex-D');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (743, 'EnlistD SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (744, 'Enlistduo Colex-D');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (745, 'Entomite');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (746, 'Entrust');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (747, 'Envidor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (748, 'Envoke');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (749, 'Envoy');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (750, 'Epimec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (751, 'Epingle 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (752, 'Epingle 100 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (753, 'Equation');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (754, 'Equip Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (755, 'Erradicur');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (756, 'Escudo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (757, 'Escudo 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (758, 'Esplanade');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (759, 'Esplanade Optima');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (760, 'Ethrel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (761, 'Ethrel 720');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (762, 'Ethrel PA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (763, 'Ethylbloc');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (764, 'Eventra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (765, 'Evidence 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (766, 'Evolution');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (767, 'Evos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (768, 'Exalt');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (769, 'Excellence Mig-66.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (770, 'Excellence Rugger.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (771, 'Excolha');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (772, 'Exemplo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (773, 'Êxito 215 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (774, 'Exor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (775, 'Exor SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (776, 'Explorer 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (777, 'Exterminador Bio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (778, 'Extrazin SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (779, 'Extreme');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (780, 'Fabian WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (781, 'Facca');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (782, 'Facero SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (783, 'Facet');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (784, 'Fagot');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (785, 'Faith');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (786, 'Faith SD 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (787, 'Faith SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (788, 'Famoso');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (789, 'Fanavid 85');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (790, 'Fanavid Flowable');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (791, 'Farmozine');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (792, 'Fascinate BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (793, 'Fason');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (794, 'Fastac 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (795, 'Fastac 100 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (796, 'Fastac Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (797, 'Fate 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (798, 'Fazor SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (799, 'Fazzer');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (800, 'Fegatex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (801, 'Feican');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (802, 'Fenix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (803, 'Fentrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (804, 'Fermag');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (805, 'Ferocitrus Furao');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (806, 'Feromônio Plato para Bicudo do Algodoeiro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (807, 'Ferômonio Plato para Lagarta Militar do Algodoeiro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (808, 'Feromônio Plato para Lagarta Rosada');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (809, 'Ferramol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (810, 'Ferrax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (811, 'Fersoil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (812, 'Fertox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (813, 'Fezan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (814, 'Fezan Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (815, 'Fidele');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (816, 'Field');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (817, 'Fielder 100 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (818, 'Fiera WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (819, 'Finale');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (820, 'Finish');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (821, 'Fipronil 80 WG Gharda');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (822, 'Fipronil Alta 250 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (823, 'Fipronil Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (824, 'Fipronil Nortox 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (825, 'Fipronova 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (826, 'Firmeza N');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (827, 'Fitoneem');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (828, 'Flak 200 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (829, 'Flama');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (830, 'Flanker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (831, 'Flare');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (832, 'Flaxton');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (833, 'Fleris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (834, 'Flex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (835, 'Flexin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (836, 'Flint 500 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (837, 'Fluazinam 500 SC Proventis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (838, 'Fluazinam Coonagro 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (839, 'Fluazinam Nortox 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (840, 'Fluente');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (841, 'Flumyzin 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (842, 'Flumyzin 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (843, 'Flupro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (844, 'Fluramim');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (845, 'Fluroxipir 80 + Picloram 80 ME Genbra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (846, 'Flutriafol Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (847, 'Focker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (848, 'Folicur 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (849, 'Folio Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (850, 'Folpan Agricur 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (851, 'Folpan Agricur 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (852, 'FONTFOP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (853, 'Fordor 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (854, 'Fore NT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (855, 'Forisk AG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (856, 'Formicida Fumacê');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (857, 'Formicida Granulado Citromax S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (858, 'Formicida Granulado Dinagro-S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (859, 'FortalezaBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (860, 'Fortenza 600 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (861, 'Fortex SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (862, 'Fortuna 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (863, 'Forum');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (864, 'Forum Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (865, 'Fox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (866, 'Fox XPRO');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (867, 'Freno 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (868, 'Front');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (869, 'Frowncide 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (870, 'Fuerza');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (871, 'Fujimite 50 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (872, 'Fulfill');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (873, 'Full');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (874, 'Fumi-Cel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (875, 'Fumi-Strip');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (876, 'Fumitoxin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (877, 'Fumitoxin- B');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (878, 'Fungicarb 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (879, 'Funginil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (880, 'Fungitol Azul');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (881, 'Fungitol Verde');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (882, 'Funguran Azul');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (883, 'Funguran Verde');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (884, 'Fuoro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (885, 'Fury 180 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (886, 'Fury 200 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (887, 'Fury 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (888, 'Fusao');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (889, 'Fusao EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (890, 'Fusiflex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (891, 'Fusilade 250 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (892, 'Fusta WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (893, 'Futur 300');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (894, 'Fysium');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (895, 'Gachon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (896, 'Galben-M');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (897, 'Galeao');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (898, 'Galgotrin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (899, 'Galigan 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (900, 'Galigan 240 F');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (901, 'Galil SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (902, 'Galileo Excell');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (903, 'Galixid');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (904, 'Gallant HL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (905, 'Gallant Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (906, 'Gallant mays');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (907, 'Gallant Milho');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (908, 'Gallant NF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (909, 'Gallant R');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (910, 'Gallaxy 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (911, 'Galloibug');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (912, 'Galop M');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (913, 'Game');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (914, 'Gamit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (915, 'Gamit 360 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (916, 'Gamit Star');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (917, 'Garant BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (918, 'Gardomil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (919, 'Garlon 480 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (920, 'Garra 450 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (921, 'Gastion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (922, 'Gastoxin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (923, 'Gastoxin B57');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (924, 'Gastoxin Pasta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (925, 'Gastoxin S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (926, 'Gaucho FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (927, 'Gauss');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (928, 'Gemini');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (929, 'Gemstar LC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (930, 'Gemstar-Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (931, 'Genius WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (932, 'Gesagard 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (933, 'Gesamena');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (934, 'Gesapax 500 Ciba-Geigy');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (935, 'Gesaprim 500 Ciba-Geigy');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (936, 'Gesaprim GrDa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (937, 'Gladium');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (938, 'Gli Ouro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (939, 'Gliato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (940, 'Glider 720 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (941, 'Glif- All');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (942, 'Glifocopa 720 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (943, 'Glifoready');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (944, 'Glifoready Nufarm');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (945, 'Gliforte');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (946, 'Glifosal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (947, 'Glifosato 480 Agripec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (948, 'Glifosato 480 SL Alamos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (949, 'Glifosato 72 WG Alamos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (950, 'Glifosato 720 WG Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (951, 'Glifosato Agripec 720 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (952, 'Glifosato Alta 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (953, 'Glifosato Atanor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (954, 'Glifosato Atanor IV');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (955, 'Glifosato Atar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (956, 'Glifosato Atar 48');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (957, 'Glifosato CCAB 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (958, 'Glifosato CCAB BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (959, 'Glifosato Fersol 480');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (960, 'Glifosato High Load');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (961, 'Glifosato IPA 480 Rainbow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (962, 'Glifosato K Atanor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (963, 'Glifosato Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (964, 'Glifosato Nortox 480 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (965, 'Glifosato Nortox 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (966, 'Glifosato Nortox SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (967, 'Glifosato Nortox WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (968, 'Glifosato Nuf BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (969, 'Glifosato Soma 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (970, 'Glifosato TK');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (971, 'Glifosato Zamba');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (972, 'Glifoxin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (973, 'Glint');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (974, 'Glister');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (975, 'Gli-Up 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (976, 'Gli-Up 720 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (977, 'Gliz 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (978, 'Gliz Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (979, 'Glizmax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (980, 'Glizmax Prime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (981, 'Glufosinate-Ammonium 200 SL Yonon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (982, 'Glyphon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (983, 'Glyphotal TR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (984, 'Glyphotal WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (985, 'Glyweed');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (986, 'Goal BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (987, 'Gold''s 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (988, 'Goltix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (989, 'Gopan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (990, 'Gramocil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (991, 'Gramoking');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (992, 'Gramoxone 200');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (993, 'Granada');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (994, 'Granary');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (995, 'GrandeBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (996, 'Grandus WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (997, 'Grant');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (998, 'Grao Verde AG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (999, 'Graolin 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1000, 'Grap Baculovirus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1001, 'Graslan 100 Peletizado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1002, 'Grassato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1003, 'Grassato 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1004, 'Graster');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1005, 'Gravun');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1006, 'Graxol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1007, 'Grazon BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1008, 'Greener');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1009, 'Grifo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1010, 'Grimectin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1011, 'GR-INN');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1012, 'Guapo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1013, 'Guerrero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1014, 'Gulliver');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1015, 'Gunner');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1016, 'Haloxifop CCAB 124,7 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1017, 'Hanami');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1018, 'Harpon WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1019, 'Heat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1020, 'Helicovex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1021, 'Helmet');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1022, 'Helmoquat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1023, 'Helmoxone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1024, 'Helmstar Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1025, 'Helymax EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1026, 'Helymax WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1027, 'Herbadox 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1028, 'Herbi D-480');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1029, 'Herbicana');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1030, 'Herbimix SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1031, 'Herbimix WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1032, 'Herbipak 500 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1033, 'Herbipak WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1034, 'Herbipropanin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1035, 'Herbipropanin 450 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1036, 'Herbitrin 500 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1037, 'Herbitrin WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1038, 'Herburon 500 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1039, 'Herburon WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1040, 'Herbzina');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1041, 'Herbzina Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1042, 'Hero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1043, 'Herold SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1044, 'Hexafort');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1045, 'Hexanil 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1046, 'Hexaplus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1047, 'Hexaron');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1048, 'Hexaron WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1049, 'Hexazinona - D Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1050, 'Hexazinona 750 Volcano');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1051, 'Hexazinona 750 WG Cropchem');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1052, 'Hexazinona Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1053, 'Hexazinona Nortox SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1054, 'Hexazinone 250 SL BASE');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1055, 'Hexazinone UPL 250 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1056, 'Hexazuron');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1057, 'Hexicana');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1058, 'Hexin 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1059, 'Hodor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1060, 'Hollic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1061, 'Hopper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1062, 'Horizon Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1063, 'Horos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1064, 'Horos Supra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1065, 'Hovex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1066, 'Hunter');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1067, 'Hussar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1068, 'Hz-NPV CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1069, 'Icarus 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1070, 'Ichiban');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1071, 'Imaxi 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1072, 'Imazacure 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1073, 'Imazaquim Ultra Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1074, 'Imazet 70 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1075, 'Imazetapir 106 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1076, 'Imazetapir CCAB 106 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1077, 'Imazetapir Plus Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1078, 'Imazetapir Prentiss');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1079, 'Imidacloprid 350 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1080, 'Imidacloprid 600 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1081, 'Imidacloprid 700 WG Helm');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1082, 'Imidacloprid Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1083, 'Imidacloprid Nufarm 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1084, 'Imidagold 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1085, 'Imidan 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1086, 'Impact 125 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1087, 'Impact Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1088, 'Impact Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1089, 'ImperadorBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1090, 'Impessive 250 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1091, 'Impulse');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1092, 'Imunit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1093, 'Incrivel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1094, 'Indoxacarbe 150 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1095, 'Infinito');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1096, 'Iniciate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1097, 'Insecto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1098, 'Insemat FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1099, 'Inseto Esteril Moscamed');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1100, 'Inside FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1101, 'Insidiomip');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1102, 'Inssimo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1103, 'Instal 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1104, 'Instivo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1105, 'Interceptor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1106, 'Intrepid 240 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1107, 'Intrepid Edge.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1108, 'Intruder');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1109, 'Invest');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1110, 'Invict');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1111, 'Ioda');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1112, 'Ipro 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1113, 'Isatalonil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1114, 'Isatalonil 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1115, 'Isca Formicida Atta Mex-S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1116, 'Iscalure Armigera');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1117, 'Iscalure Bonagota');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1118, 'Iscalure BW 10');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1119, 'Iscalure Cydia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1120, 'Iscalure Grafolita');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1121, 'Iscalure TML Plug');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1122, 'Iscalure Tuta');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1123, 'Ishipron');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1124, 'Isomate-OFM TT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1125, 'Jacare');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1126, 'Jackpot 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1127, 'Jaguar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1128, 'Jambtrin 120 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1129, 'Jaran 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1130, 'Java 200 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1131, 'Javelin WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1132, 'JB TRI-P.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1133, 'Jess');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1134, 'Jornada');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1135, 'Judoka');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1136, 'Jump');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1137, 'Juno');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1138, 'Kabuki');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1139, 'Kadma');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1140, 'Kaiso 250 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1141, 'Kaiso Sorbie');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1142, 'Kaiso Sorbie BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1143, 'Kaligreen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1144, 'Kalontra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1145, 'Kamex 900 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1146, 'Kaner 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1147, 'Karate Zeon 250 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1148, 'Karate Zeon 50 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1149, 'Karathane EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1150, 'Karmex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1151, 'Karmex 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1152, 'Kasan 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1153, 'Kasan Max 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1154, 'Kasumin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1155, 'Katana');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1156, 'Keep 125 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1157, 'Keepdry');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1158, 'Kelion 50 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1159, 'Keltor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1160, 'Kennox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1161, 'Kentan 40 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1162, 'Keshet 25 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1163, 'Keyzol EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1164, 'Kicker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1165, 'Kicker Sup');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1166, 'Kifix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1167, 'Klorpan 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1168, 'K-Obiol 25 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1169, 'K-Obiol 2P');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1170, 'Kocide WDG Bioactive');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1171, 'Kohinor 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1172, 'Konazol 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1173, 'K-Othrine 2P');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1174, 'Kraft 36 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1175, 'Kraken 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1176, 'Kraton 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1177, 'Krismat WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1178, 'Kroll');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1179, 'Kromo 250 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1180, 'Krost 806 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1181, 'Krost 970 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1182, 'Krovar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1183, 'Kumulus DF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1184, 'Kumulus DF-AG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1185, 'Kyron 40 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1186, 'Kyron 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1187, 'Labrador');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1188, 'Lactofen AGP 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1189, 'Lakree Fogging');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1190, 'Lambda Cialotrina CCAB 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1191, 'Lamper 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1192, 'Landrin Po');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1193, 'Lannate BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1194, 'Larvin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1195, 'Larvin 350');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1196, 'Larvin 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1197, 'Laser 400 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1198, 'Lasitrap');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1199, 'Lava');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1200, 'Lava 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1201, 'Lava 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1202, 'Lavra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1203, 'Lead');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1204, 'Leale SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1205, 'Lecar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1206, 'Legacy');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1207, 'Legado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1208, 'Legend 250 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1209, 'Leopard');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1210, 'Lepigen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1211, 'Lepigen CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1212, 'Liberty');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1213, 'Lifter');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1214, 'Ligero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1215, 'Limpidu');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1216, 'Linurex Agricur 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1217, 'Lobster 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1218, 'Locker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1219, 'Login');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1220, 'Loop');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1221, 'Lord');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1222, 'Lorsban 480 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1223, 'Lost');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1224, 'Lousal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1225, 'Loyant');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1226, 'Lucens');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1227, 'Lufenuron Nortox 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1228, 'Luger');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1229, 'Lumica');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1230, 'Luretape BW-10');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1231, 'Macromip Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1232, 'Mademato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1233, 'Maestro 250 FS.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1234, 'Maestro 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1235, 'Maestro FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1236, 'Magic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1237, 'Magister');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1238, 'Magnate 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1239, 'Magneto SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1240, 'Magnific');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1241, 'Magnum');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1242, 'MagnusBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1243, 'Magtoxin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1244, 'Majestic.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1245, 'Majesty');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1246, 'Malathion 1000 EC Cheminova');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1247, 'Malathion 20');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1248, 'Malathion 440 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1249, 'Malathion 500 EC Cheminova');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1250, 'Malathion Prentiss');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1251, 'Malathion UL Cheminova');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1252, 'Manage 150');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1253, 'Mancozeb CCAB 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1254, 'Mancozeb Indofil 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1255, 'Mancozeb Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1256, 'Mancozeb Nortox 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1257, 'Mancozeb Sabero 800 Wp');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1258, 'Mandarim');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1259, 'Manfil 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1260, 'Mannejo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1261, 'Mantis 400 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1262, 'Manzate 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1263, 'Manzate WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1264, 'Marathon 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1265, 'Markab 350 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1266, 'Marshal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1267, 'Marshal 400');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1268, 'Marshal 400 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1269, 'Marshal Star');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1270, 'Marte WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1271, 'Masumo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1272, 'Match EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1273, 'Matric');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1274, 'Matrine');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1275, 'Maxcel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1276, 'Maxim');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1277, 'Maxim Advanced');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1278, 'Maxim Quattro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1279, 'Maxim XL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1280, 'Maxim XL Professional');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1281, 'Maxizato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1282, 'Mayran');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1283, 'Mazotam 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1284, 'MegaBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1285, 'MegaBR Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1286, 'Memory');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1287, 'Meothrin 300');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1288, 'Mepiquat 50 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1289, 'Mepiquat Chloride 25% SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1290, 'Meristo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1291, 'Merpan 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1292, 'Merpan 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1293, 'Mertin 400');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1294, 'Meson 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1295, 'Mesotriona CCAB 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1296, 'Mesotriona Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1297, 'Mesotriona Nortox 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1298, 'Mesotrione 480 SC Proventis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1299, 'Meta Turbo SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1300, 'Metabiol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1301, 'Metagan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1302, 'Metapremium');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1303, 'Metarfito');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1304, 'Metarhizen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1305, 'Metarhizen WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1306, 'Metarhizium JCO');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1307, 'Metarhizium JCO WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1308, 'Metarhizium Oligos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1309, 'Metarhizium Oligos WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1310, 'Metarhizium Probio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1311, 'Metarhryd FR 25');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1312, 'Metarhyd');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1313, 'Metarplan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1314, 'Metarril WP E9');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1315, 'Metarriz GR Biocontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1316, 'Metarriz Plus WP Biocontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1317, 'Metarriz WP Biocontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1318, 'Metatec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1319, 'Meta-Turbo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1320, 'MethaControl');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1321, 'Methamax EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1322, 'Methomex 215 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1323, 'Metiê');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1324, 'Metiltiofan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1325, 'Metiz');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1326, 'Metribuzin Tide 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1327, 'Metrimex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1328, 'Metrimex 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1329, 'Metry');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1330, 'Metsuram 600 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1331, 'Micene');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1332, 'Micromite 240 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1333, 'Microthiol Disperss WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1334, 'Midas BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1335, 'Migdo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1336, 'MilbekNock');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1337, 'Mimic 240 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1338, 'Miner Oil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1339, 'Minx 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1340, 'Mirador 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1341, 'Mirant');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1342, 'Mirex - S2');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1343, 'Mirex-S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1344, 'Mirex-S Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1345, 'Mirza 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1346, 'Missil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1347, 'Miura EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1348, 'Moddus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1349, 'Mofotil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1350, 'Mojjave');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1351, 'Monaris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1352, 'Monceren 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1353, 'Moncut');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1354, 'Monitrap');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1355, 'Mospilan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1356, 'Mospilan WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1357, 'Most');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1358, 'MSMA 720');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1359, 'MSMA 720 Dow AgroSciences');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1360, 'Much 600 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1361, 'Muneo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1362, 'Mustang 350 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1363, 'Mythos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1364, 'Nadran');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1365, 'Naja');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1366, 'Narval 40 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1367, 'Nat Fungi');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1368, 'Natera');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1369, 'Nativo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1370, 'Nato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1371, 'Natucontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1372, 'Natur''l Oleo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1373, 'Navajo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1374, 'Navigator');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1375, 'Nemacontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1376, 'Nemacur');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1377, 'Nemacur EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1378, 'Nemakill.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1379, 'Nemat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1380, 'Nematec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1381, 'Neolist');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1382, 'Neomip');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1383, 'Neomip Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1384, 'Neoram 37.5 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1385, 'Nexide');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1386, 'Nico');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1387, 'Nico 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1388, 'Nicopec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1389, 'Nicosulfuron Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1390, 'Nicosulfuron Nortox 40 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1391, 'Nillus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1392, 'Nimitz EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1393, 'Nimitz TS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1394, 'Nippon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1395, 'Nippon 40');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1396, 'Nisshin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1397, 'Noctovi GL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1398, 'Nomad EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1399, 'Nominee 400 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1400, 'Nomolt 150');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1401, 'No-Nema.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1402, 'Nongrass');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1403, 'Nordik');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1404, 'Norton');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1405, 'NotavelBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1406, 'Notorio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1407, 'Novazin Proquimur');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1408, 'Novum');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1409, 'Nufos 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1410, 'Nufosate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1411, 'Nufosate BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1412, 'Nufosate WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1413, 'Nufuron');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1414, 'Nuprid 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1415, 'Nuquat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1416, 'Nuzoxy 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1417, 'Oberon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1418, 'Obny');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1419, 'Octane');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1420, 'Odin 430 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1421, 'Ogiva');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1422, 'Okay');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1423, 'Oleaje Prime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1424, 'Oleo Vegetal Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1425, 'Oleo Vegetal Samarita');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1426, 'Omite 300 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1427, 'Omite 720 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1428, 'Oncol 10 G');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1429, 'Oncol Sipcam');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1430, 'Onesa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1431, 'Onic 300');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1432, 'Onix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1433, 'Onix OG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1434, 'Only');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1435, 'Opala');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1436, 'Opera');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1437, 'Opera Ultra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1438, 'Oppa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1439, 'Oppa BR EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1440, 'Optix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1441, 'Oranis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1442, 'Orbis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1443, 'Orbit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1444, 'Oregon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1445, 'Orfeu');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1446, 'Organic WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1447, 'Origan 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1448, 'Oris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1449, 'Orius 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1450, 'OriusIBI');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1451, 'Orix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1452, 'Orkestra SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1453, 'Orthene 750 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1454, 'Orthene Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1455, 'Orthocide 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1456, 'Ortus 50 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1457, 'Ousado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1458, 'Outliner');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1459, 'Owner');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1460, 'Paclo BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1461, 'Pacto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1462, 'Padron');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1463, 'Paicer WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1464, 'Palace');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1465, 'Pallex');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1466, 'Pampa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1467, 'Panga 900 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1468, 'Panoramic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1469, 'Pantani 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1470, 'Panther 120 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1471, 'Panzer 250 WDG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1472, 'Paradox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1473, 'Paramaster');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1474, 'Paraquat 200 SL Alamos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1475, 'Paraquate Alta 200 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1476, 'ParrudoBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1477, 'Parsec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1478, 'Pastor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1479, 'Patrol SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1480, 'Pausato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1481, 'Pectichem');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1482, 'Pendimethalin Sanachem 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1483, 'Penncozeb 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1484, 'Penncozeb WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1485, 'Penoxsulam Pre-mistura Dow Agrosciences');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1486, 'Perflan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1487, 'Perflan 800 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1488, 'Perform 240 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1489, 'Perito 970 SG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1490, 'Permetrin 384 EC CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1491, 'Permetrina CCAB 384 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1492, 'Permetrina Fersol 384 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1493, 'Permit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1494, 'Permit Star');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1495, 'Persist SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1496, 'Pertag 384 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1497, 'PFC-Control');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1498, 'Pherodis HA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1499, 'Phostek');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1500, 'Phostoxin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1501, 'Photon SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1502, 'Picloram Nortox 240 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1503, 'Picus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1504, 'Pilarich');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1505, 'Pilarico');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1506, 'Pilarsato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1507, 'Pilartime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1508, 'Pilon WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1509, 'Pilot');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1510, 'PingBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1511, 'Pique 240 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1512, 'Pirate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1513, 'Pireo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1514, 'Pirephos EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1515, 'Pirestar 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1516, 'Piriproxifen Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1517, 'Piritilen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1518, 'Pistol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1519, 'Pistol 106 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1520, 'Pitcher 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1521, 'Pivot 100 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1522, 'Pix HC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1523, 'Pladox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1524, 'Planador');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1525, 'Planador XT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1526, 'Plantvax 750 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1527, 'Plateau');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1528, 'Platinum Neo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1529, 'Pledge SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1530, 'Poast');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1531, 'Poast Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1532, 'Pocco 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1533, 'Podium EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1534, 'Polaris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1535, 'Polo 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1536, 'Polyram DF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1537, 'Polytrin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1538, 'Polytrin 400/40 CE');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1539, 'Poncho');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1540, 'PonteiroBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1541, 'Pontiac 350 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1542, 'Ponto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1543, 'Ponto Final');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1544, 'Pooper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1545, 'Poquer');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1546, 'Porcel 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1547, 'Portero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1548, 'Posmil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1549, 'Potensato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1550, 'Potenza Sinon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1551, 'Potenzor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1552, 'Pottente');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1553, 'Pounce 384 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1554, 'Pramato');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1555, 'Pramilho');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1556, 'Pratico');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1557, 'Preciso');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1558, 'Predatox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1559, 'Predileto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1560, 'Predom 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1561, 'Premerlin 600 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1562, 'Premier');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1563, 'Premier Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1564, 'Premio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1565, 'Premis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1566, 'Pren-D 806');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1567, 'Prep');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1568, 'Presence');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1569, 'Prestige');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1570, 'Prestige Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1571, 'Pretiobug');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1572, 'Pretorian');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1573, 'Prevail');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1574, 'Prevent');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1575, 'Prever');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1576, 'Previcur BCS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1577, 'Previcur N');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1578, 'Previnil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1579, 'Prez');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1580, 'Primagram Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1581, 'Primaiz Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1582, 'Primatop SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1583, 'Primeplus BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1584, 'Primestra Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1585, 'Primoleo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1586, 'Pri-Mordial');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1587, 'Priori');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1588, 'Priori Top');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1589, 'Priori Xtra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1590, 'Prisma');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1591, 'Prisma Plus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1592, 'Privilege');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1593, 'Proclaim 50');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1594, 'Produtivo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1595, 'ProdutorBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1596, 'Profit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1597, 'Pro-Gibb');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1598, 'Progibb 400');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1599, 'Proliant');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1600, 'Proline');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1601, 'Promalin');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1602, 'Pronto WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1603, 'Proof');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1604, 'Propanil Fersol 360 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1605, 'Propanil Milenia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1606, 'Propargite Fersol 720 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1607, 'Propiconazole Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1608, 'Proplant');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1609, 'Prospect');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1610, 'Prostore 25 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1611, 'ProTone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1612, 'Protreat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1613, 'Provado 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1614, 'Provence 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1615, 'Provence Total');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1616, 'Pugil WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1617, 'Pulsor 240 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1618, 'Punto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1619, 'PureSpray 15E');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1620, 'Purpureonyd FR 25');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1621, 'Pyrinex 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1622, 'Quadris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1623, 'Quality');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1624, 'Quallis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1625, 'Quartz SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1626, 'Quartzo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1627, 'Quatdown');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1628, 'Quatermon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1629, 'Quicksilver 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1630, 'Quimioleo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1631, 'Rabcide 200');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1632, 'Racio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1633, 'Radan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1634, 'Radar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1635, 'Radar WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1636, 'Radiant 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1637, 'Radiant 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1638, 'Radix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1639, 'Rage');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1640, 'Rainburon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1641, 'Raio');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1642, 'Rajer 250 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1643, 'Raksha 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1644, 'Ralbuzin 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1645, 'Ramexane 850 PM');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1646, 'Rancho');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1647, 'Rancona 450 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1648, 'Rancona T');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1649, 'Ranger');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1650, 'Ranman');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1651, 'Rapel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1652, 'Rapid');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1653, 'Raprus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1654, 'Rapsode');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1655, 'Raptor 70 DG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1656, 'Rascal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1657, 'Raxil FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1658, 'Rayo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1659, 'Reacher');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1660, 'Reator 360 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1661, 'Reconil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1662, 'Recop');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1663, 'Record');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1664, 'Redshield 750');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1665, 'Reference');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1666, 'Regalia Maxx');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1667, 'Regent 20 GR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1668, 'Regent 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1669, 'Regent Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1670, 'Reglone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1671, 'Rephon 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1672, 'Resource 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1673, 'Retain');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1674, 'Revogar 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1675, 'Revolux');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1676, 'Revus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1677, 'Revus Opti');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1678, 'Rhino');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1679, 'Rhodiauram SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1680, 'Ricer');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1681, 'Ridomil Gold Bravo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1682, 'Ridomil Gold MZ');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1683, 'Ridover');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1684, 'Rifle');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1685, 'Rimon 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1686, 'Rimon Supra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1687, 'Rincoforol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1688, 'Riper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1689, 'Rival 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1690, 'Rivamax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1691, 'Rivax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1692, 'Riza 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1693, 'Rizoderma');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1694, 'Rizos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1695, 'Rizos OG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1696, 'Rizotec');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1697, 'RMD-1');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1698, 'Rocks');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1699, 'Rodazim 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1700, 'Rodeo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1701, 'Rodolia 200 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1702, 'Rometsol 600 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1703, 'Ronat-A');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1704, 'Ronstar 250 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1705, 'Ronstar SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1706, 'Rotamik');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1707, 'Rotaprid 350 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1708, 'Rotashock');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1709, 'Rotaxil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1710, 'Rotaxil 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1711, 'Rouker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1712, 'Roundup Original');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1713, 'Roundup Original DI');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1714, 'Roundup Original Mais');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1715, 'Roundup Ready');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1716, 'Roundup Ready Milho');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1717, 'Roundup Transorb');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1718, 'Roundup Transorb R');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1719, 'Roundup Ultra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1720, 'Roundup WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1721, 'Rovral');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1722, 'Rovral SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1723, 'Royaltac EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1724, 'Rubric');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1725, 'Rufast 50 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1726, 'Rugby 100 GR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1727, 'Rugby 200 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1728, 'Ruget');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1729, 'Rumo WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1730, 'Runner');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1731, 'Rustler');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1732, 'Rustler WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1733, 'Sabizeb 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1734, 'Sabre');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1735, 'Saddler 350 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1736, 'Safety');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1737, 'Salasat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1738, 'Salasat 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1739, 'Salero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1740, 'Saluzi 600 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1741, 'Samor-Gat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1742, 'Sanfly');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1743, 'Sanmite');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1744, 'Sanmite EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1745, 'Sanson 40 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1746, 'Sanson AZ');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1747, 'Sanson Evo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1748, 'Satir-GAT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1749, 'Saudaris');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1750, 'Saurus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1751, 'Sauvage');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1752, 'Savana');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1753, 'Savey WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1754, 'Savivo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1755, 'Scooter.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1756, 'Score');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1757, 'Score Flexi');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1758, 'Scorpion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1759, 'Scout');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1760, 'Script');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1761, 'Sector');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1762, 'Seculo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1763, 'Seizer 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1764, 'Select 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1765, 'Select One Pack');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1766, 'Selefen');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1767, 'Sementiran 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1768, 'Semevin 350');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1769, 'Sempra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1770, 'Sencor 480');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1771, 'Senior WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1772, 'Sensei');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1773, 'Sequence');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1774, 'Serenade');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1775, 'Sesitra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1776, 'Seven');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1777, 'SeveroBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1778, 'Sevin 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1779, 'Sevin 850 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1780, 'Shadow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1781, 'Shadow 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1782, 'Shake');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1783, 'Shambda 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1784, 'Shar Conazol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1785, 'Shar-Teb');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1786, 'Shar-Teb 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1787, 'Shelter');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1788, 'Shelter FS.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1789, 'Shocker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1790, 'Shyper 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1791, 'Sialex 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1792, 'Siber');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1793, 'Siena');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1794, 'Silicon Protect');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1795, 'Silverado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1796, 'Simatop Rainbow');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1797, 'Simbio Mix');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1798, 'Simboll 125 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1799, 'Simetrex SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1800, 'Simtrac 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1801, 'Sinerge EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1802, 'Sinfone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1803, 'SingularBR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1804, 'Siptran');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1805, 'Siptran 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1806, 'Siptran 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1807, 'Siptroil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1808, 'Sirius 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1809, 'Sivanto Prime 200 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1810, 'Skip 125 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1811, 'Smart');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1812, 'SmartFresh');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1813, 'Smartfresh Smarttabs');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1814, 'SmartFresh Technology');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1815, 'Smite');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1816, 'Sniper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1817, 'Soberan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1818, 'Soccer SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1819, 'Solara 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1820, 'Soldier');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1821, 'Solist 430 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1822, 'Sombrero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1823, 'Sonata');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1824, 'Soprano 125 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1825, 'Sorba');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1826, 'Source');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1827, 'Source Top');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1828, 'Soyaclean Xtra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1829, 'Soyaguard Xtra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1830, 'Soyaquim 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1831, 'Soyatop Xtra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1832, 'Soyvance');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1833, 'Space');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1834, 'Spada WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1835, 'Sparviero 50');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1836, 'Spectro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1837, 'Sperto');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1838, 'Sphere Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1839, 'Sphere Max A');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1840, 'Sphere Max B');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1841, 'Spical');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1842, 'Spider 840 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1843, 'Spike');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1844, 'Spindle');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1845, 'SPITZ');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1846, 'Splat Cida Grafo Bona');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1847, 'Splat Grafo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1848, 'Splat Grafo Bona');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1849, 'Splat ME');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1850, 'Splendor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1851, 'Sponsor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1852, 'Spot SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1853, 'Spotlight');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1854, 'Spraykill');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1855, 'Sprayquat');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1856, 'Spring WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1857, 'Sprint WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1858, 'Staff');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1859, 'Stage');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1860, 'Stallion 150 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1861, 'Stallion 60 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1862, 'Stam 360');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1863, 'Stam 480');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1864, 'Stam 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1865, 'Stampir BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1866, 'Standak');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1867, 'Standak Duo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1868, 'Standak Top');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1869, 'Staple 280 CS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1870, 'Starane 200');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1871, 'Starice');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1872, 'Starion');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1873, 'Start');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1874, 'Station 240 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1875, 'Status');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1876, 'Stimo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1877, 'Stimo WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1878, 'Stimucontrol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1879, 'Stimulate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1880, 'Stinger');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1881, 'Stinger WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1882, 'STK ZIM');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1883, 'Stone');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1884, 'Stopper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1885, 'Stoy 40 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1886, 'Stratego 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1887, 'Stratiomip');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1888, 'Streak 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1889, 'Stregga EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1890, 'Stroby SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1891, 'Style');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1892, 'Success 0,02 CB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1893, 'Sugarina');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1894, 'Sulfato de Cobre Agrimar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1895, 'Sulfato de Cobre Inderco');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1896, 'Sulfato de Cobre Microsal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1897, 'Sulflow SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1898, 'Sulfur 800');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1899, 'Sulfure 750');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1900, 'Sultan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1901, 'Sumidan 150 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1902, 'Sumidan 25 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1903, 'Sumifog 70');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1904, 'Sumigran 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1905, 'Sumigranplus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1906, 'Sumiguard 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1907, 'Sumilex 500 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1908, 'Sumirody 300');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1909, 'Sumisoya');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1910, 'Sumisoya 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1911, 'Sumithion 500 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1912, 'Sumithion UBV');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1913, 'Summit 250 FS Genbra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1914, 'Sumô');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1915, 'Sumyzin 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1916, 'Sumyzin 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1917, 'Sunfire');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1918, 'Sunspray E');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1919, 'Supera');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1920, 'Super-Bt');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1921, 'Supermetrina Agria 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1922, 'Supimpa');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1923, 'Support');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1924, 'Support WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1925, 'Suprathion 400 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1926, 'Surcozole');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1927, 'Surpass');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1928, 'Surtivo Soja');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1929, 'Surtivo Soja CCAB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1930, 'Sweep Off');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1931, 'Sweeper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1932, 'Swing Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1933, 'Switch');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1934, 'Synero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1935, 'Systemic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1936, 'Systhane 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1937, 'Systhane 400 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1938, 'Systhane EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1939, 'Systhane WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1940, 'Tacora 250 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1941, 'Taffeta 200 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1942, 'Taffeta SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1943, 'Tairel M');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1944, 'Taj');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1945, 'Take 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1946, 'Talento');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1947, 'Talisman');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1948, 'Talstar 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1949, 'Tamiz');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1950, 'Tango Cash');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1951, 'Targa 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1952, 'Targa Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1953, 'Tarik EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1954, 'Tarik WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1955, 'Tarpi');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1956, 'Tasker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1957, 'Tattoo C');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1958, 'Taura 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1959, 'Teardown');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1960, 'Tebas');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1961, 'Tebuco 430 SC Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1962, 'Tebuco Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1963, 'Tebuco NORTOX SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1964, 'Tebuconazol 200 EC UPL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1965, 'Tebuconazole CCAB 200 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1966, 'Tebufort');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1967, 'Tebufort BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1968, 'Tebuzim 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1969, 'Tecnup');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1970, 'Tecto SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1971, 'Tejo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1972, 'Temible');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1973, 'Tempest');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1974, 'Templo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1975, 'Tenace');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1976, 'Tenaz 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1977, 'Tento 867 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1978, 'Teor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1979, 'Terra Forte');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1980, 'Terraclor 750 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1981, 'Terrazole 350 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1982, 'Texas');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1983, 'Thiobel 500');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1984, 'Thiobio 350 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1985, 'Thiodi 350 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1986, 'Thiodicarb 350 FS DVA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1987, 'Thiodiplus 350 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1988, 'Thiovit Jet');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1989, 'Thorn');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1990, 'Thunder');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1991, 'Thuricide');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1992, 'Thuricide SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1993, 'Tibet');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1994, 'Tiger 100 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1995, 'Tiger 100 EW');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1996, 'Tigre');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1997, 'Tilt');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1998, 'Timon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (1999, 'Timorex Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2000, 'Tino');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2001, 'Tiodicarbe CCAB 800 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2002, 'Tiodicarbe Nufarm 350 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2003, 'Tiofanato-Metilico 500 Helm');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2004, 'Tiofanil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2005, 'Tivaro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2006, 'TMB Tubo Mata Bicudo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2007, 'Tocha');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2008, 'Toco');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2009, 'Togar TB');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2010, 'Token');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2011, 'Topatudo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2012, 'Topgan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2013, 'Topgan 150');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2014, 'Topgan WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2015, 'Topik 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2016, 'Toplus');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2017, 'Topsin 875 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2018, 'Topstar');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2019, 'Tordon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2020, 'Tordon XT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2021, 'Toreg 50 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2022, 'Torero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2023, 'Tornado');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2024, 'Totalit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2025, 'Totril');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2026, 'Toucan 250 FS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2027, 'Touchdown');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2028, 'Tracer');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2029, 'Tractor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2030, 'Treasure');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2031, 'Trebon 100 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2032, 'Trecatol');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2033, 'Triaction.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2034, 'Triade');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2035, 'Trianum WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2036, 'Tricea');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2037, 'TrichoAgri');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2038, 'Trichobug');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2039, 'TrichoCana');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2040, 'Trichodermax EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2041, 'Trichodermil DS');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2042, 'Trichodermil SC 1306');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2043, 'Trichodermil Super SC 1306');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2044, 'Trichogramma pretiosum AMIPA');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2045, 'Trichomip-G');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2046, 'Trichomip-P');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2047, 'Tricho-Strip G');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2048, 'Tricho-Turbo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2049, 'Triclon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2050, 'Triclopir CCAB 480 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2051, 'Triclopyr 480 Volagro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2052, 'Tricovab');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2053, 'Tridium');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2054, 'Trifluralina Atanor 445 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2055, 'Trifluralina H Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2056, 'Trifluralina Milenia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2057, 'Trifluralina Nortox');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2058, 'Trifluralina Nortox Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2059, 'Trifmine');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2060, 'Trifoli');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2061, 'Trigard 750 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2062, 'Trilag');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2063, 'Trilla');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2064, 'Triller EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2065, 'Trinca');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2066, 'Trinca Caps');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2067, 'Trinity 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2068, 'Trishul 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2069, 'Trivor.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2070, 'Triziman');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2071, 'Troia');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2072, 'Troller');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2073, 'Trop');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2074, 'Trop 480 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2075, 'Trop SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2076, 'Tropero');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2077, 'Trovati');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2078, 'Troya');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2079, 'Trueno');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2080, 'Trueno EZ');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2081, 'Trueno XT');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2082, 'Truenza');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2083, 'TrulyMax');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2084, 'Trunfo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2085, 'Trunker');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2086, 'Truper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2087, 'Truzon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2088, 'Trychonyd FR 25');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2089, 'Tucson');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2090, 'Tuit Florestal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2091, 'Tupan 720 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2092, 'Turbine 500 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2093, 'Turbo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2094, 'Turuna');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2095, 'Tutor');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2096, 'Tuval');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2097, 'Twister');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2098, 'Tyson 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2099, 'U 46 BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2100, 'U 46 D-Fluid 2,4-D');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2101, 'U 46 Prime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2102, 'Ultimato SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2103, 'Unimark 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2104, 'Unimark 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2105, 'Unique.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2106, 'Unix 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2107, 'Unizeb 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2108, 'Unizeb Glory');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2109, 'Unizeb Gold');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2110, 'UPL 216 FP BR');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2111, 'Upmyl');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2112, 'UP-Stage');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2113, 'Urge 750 SP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2114, 'Uthane 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2115, 'Valeos');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2116, 'Valient');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2117, 'Vantage.');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2118, 'Vantigo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2119, 'Veget Oil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2120, 'Veldara');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2121, 'Velpar-K WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2122, 'Venture');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2123, 'Verdadero 600 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2124, 'Verdict - R');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2125, 'Verdict HL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2126, 'Verdict Max');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2127, 'Verdict Mays');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2128, 'Verdict milho');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2129, 'Verdict NF');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2130, 'Verimark');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2131, 'Verismo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2132, 'Verlon');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2133, 'Veromite');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2134, 'VERPAVEX');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2135, 'Versatilis');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2136, 'Verter');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2137, 'Verter SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2138, 'Vertimec 18 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2139, 'Vertimec 84 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2140, 'Vessarya');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2141, 'Vexter');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2142, 'Vezir');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2143, 'Vezir 100');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2144, 'Vezir WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2145, 'Viance');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2146, 'Vincit 50 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2147, 'Vincitore WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2148, 'Vindra 425 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2149, 'Viper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2150, 'Vircontrol S.F');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2151, 'Virtuoso 250 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2152, 'Vision');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2153, 'Visor 240 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2154, 'Vitavax Thiram 200 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2155, 'Vitavax-Thiram WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2156, 'Viviful');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2157, 'Viviful SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2158, 'Volcane');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2159, 'Voliam Flexi');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2160, 'Voliam Targo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2161, 'Volna 250 EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2162, 'Volpe');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2163, 'Volt');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2164, 'Vondozeb 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2165, 'Voraz');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2166, 'Voraz EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2167, 'Votivo Prime');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2168, 'Walux');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2169, 'Wanzeb');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2170, 'Warrant 700 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2171, 'Warrior');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2172, 'Wasp 480 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2173, 'Wheater*');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2174, 'Whip S');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2175, 'Wild');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2176, 'Winner');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2177, 'Winner Max EC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2178, 'Wiper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2179, 'Wiper Xtra');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2180, 'Wish 500 SC');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2181, 'Wolf');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2182, 'Xavante');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2183, 'Xentari');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2184, 'Xeque Mate');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2185, 'Xopoto 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2186, 'Yang');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2187, 'Yovel');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2188, 'Zafera');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2189, 'Zaphir');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2190, 'Zapp Pro');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2191, 'Zardo');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2192, 'Zartan');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2193, 'Zavit');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2194, 'Zest 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2195, 'Zetanil');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2196, 'Zetanil WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2197, 'Zetaram WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2198, 'Zethamaxx');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2199, 'Zethapyr 106 SL');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2200, 'Zignal');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2201, 'Zino 750 WG');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2202, 'Zipper');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2203, 'Zonic');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2204, 'Zoom');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2205, 'Zoxium 800 WP');
INSERT or IGNORE INTO agrotoxico(id, nome) VALUES (2206, 'Zura 806 SL');