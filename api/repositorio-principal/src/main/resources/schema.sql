-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema principal
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `principal` ;

-- -----------------------------------------------------
-- Schema principal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `principal` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema sistema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sistema` ;

-- -----------------------------------------------------
-- Schema sistema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistema` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema pessoa
-- -----------------------------------------------------
-- Cadastro de pessoas físicas, jurídicas e grupos sociais
DROP SCHEMA IF EXISTS `pessoa` ;

-- -----------------------------------------------------
-- Schema pessoa
--
-- Cadastro de pessoas físicas, jurídicas e grupos sociais
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pessoa` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema comum
-- -----------------------------------------------------
-- Tabelas comuns a todos os outros esquemas
DROP SCHEMA IF EXISTS `comum` ;

-- -----------------------------------------------------
-- Schema comum
--
-- Tabelas comuns a todos os outros esquemas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `comum` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema produto
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `produto` ;

-- -----------------------------------------------------
-- Schema produto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `produto` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema oauth2
-- -----------------------------------------------------
-- Tabelas de controle do framework de segurança Oauth2
DROP SCHEMA IF EXISTS `oauth2` ;

-- -----------------------------------------------------
-- Schema oauth2
--
-- Tabelas de controle do framework de segurança Oauth2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oauth2` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema evento
-- -----------------------------------------------------
-- schema dos eventos dos bens
DROP SCHEMA IF EXISTS `evento` ;

-- -----------------------------------------------------
-- Schema evento
--
-- schema dos eventos dos bens
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `evento` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema veiculo
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `veiculo` ;

-- -----------------------------------------------------
-- Schema veiculo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `veiculo` ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema funcional
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `funcional` ;

-- -----------------------------------------------------
-- Schema funcional
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `funcional` ;
SHOW WARNINGS;
USE `principal` ;

-- -----------------------------------------------------
-- Table `principal`.`recurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `principal`.`recurso` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('PESSOA', 'PRODUTO', 'SERVICO') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  `observacao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  PRIMARY KEY (`id`));

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `pai_id` INT UNSIGNED NULL,
  `identificavel` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'S - indica que o produto tem uma identificação e pode ser referenciado, por exemplo, através de um número de série.\nN - indica que o produto não tem uma identificação e é referenciado volumetricamente, por exemplo, x quantidade deste produto (tipo produto gasolina, 10 litros).',
  PRIMARY KEY (`id`),
  INDEX `fk_produto_tipo_1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_tipo_1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `produto`.`tipo` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`modelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`modelo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `tipo_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_versao_3_idx` (`tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_versao_3`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `produto`.`tipo` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`marca` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `fabricante` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Quando a marca identifica o fabricante e não o modelo',
  `logotipo` LONGBLOB NULL,
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  INDEX `fk_marca_marca1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_marca_marca1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `produto`.`marca` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `principal`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `principal`.`pessoa` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `tipo` ENUM('PF', 'PJ', 'GS') NOT NULL COMMENT 'Se PF - Pessoa Física\\\\nSe PJ - Pessoa Jurídica\\\\nSe GS - Grupo Social',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_pessoa`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`recurso` (`id`)
    ON DELETE CASCADE);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`unidade_medida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`unidade_medida` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `sigla` VARCHAR(255) NULL,
  `padrao_identificavel` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'somente um registro desta tabela deve possuir este atributo igual a S, todos os outros igual a N. O registro com valor S é utilizado quando o produto for identificavel',
  `unidade_basica` ENUM('S', 'N') NULL DEFAULT 'N' COMMENT 'indica que é a unidade basica de referência dos nós irmãos desta árvore. Exemplo, se o nó for peso, o quilo deve ser anotado como S e todas as outras unidades de medida equivalentes devem ter um valor de referencia equivalente a 1 quilo',
  `valor_referencia` DECIMAL(65,30) NULL COMMENT 'valor de referencia da unidade basica, utilizada para permitir a conversão entre unidades equivalentes',
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_unidade_medida_unidade_medida1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_unidade_medida_unidade_medida1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `comum`.`unidade_medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `principal`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `principal`.`produto` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `modelo_id` INT UNSIGNED NOT NULL,
  `marca_id` INT UNSIGNED NULL,
  `numero_serie` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL COMMENT 'se o valor do campo modelo_id, estiver vinculado a um registro da tabela produto_tipo, cujo campo identificável for igual a \'N\' este valor tem que ser nulo. Caso o valor seja \'S\', este campo pode ou não ser preenchido.',
  `quantidade` DECIMAL(65,30) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'se o valor do campo modelo_id, estiver vinculado a um registro da tabela produto_tipo, cujo campo identificável for igual a \'S\' este valor tem que ser 1. Caso o valor seja \'S\', este campo pode conter qualquer valor, desde que positivo.',
  `unidade_medida_id` INT UNSIGNED NOT NULL COMMENT 'unidade de medida que qualifica o campo quantidade',
  `proprietario_pessoa_id` INT UNSIGNED NULL COMMENT 'proprietario do produto',
  PRIMARY KEY (`id`),
  INDEX `fk_produto_2_idx` (`modelo_id` ASC) VISIBLE,
  INDEX `fk_produto_marca1_idx` (`marca_id` ASC) VISIBLE,
  INDEX `fk_produto_pessoa1_idx` (`proprietario_pessoa_id` ASC) VISIBLE,
  INDEX `fk_produto_3_idx` (`unidade_medida_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_1`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`recurso` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_produto_2`
    FOREIGN KEY (`modelo_id`)
    REFERENCES `produto`.`modelo` (`id`),
  CONSTRAINT `fk_produto_marca1`
    FOREIGN KEY (`marca_id`)
    REFERENCES `produto`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_pessoa1`
    FOREIGN KEY (`proprietario_pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_3`
    FOREIGN KEY (`unidade_medida_id`)
    REFERENCES `comum`.`unidade_medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `principal`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `principal`.`servico` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_servico`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`recurso` (`id`)
    ON DELETE CASCADE);

SHOW WARNINGS;
USE `sistema` ;

-- -----------------------------------------------------
-- Table `sistema`.`modulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`modulo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`funcionalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`funcionalidade` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_funcionalidade_funcionalidade1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_funcionalidade_funcionalidade1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `sistema`.`funcionalidade` (`id`));

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`acao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`acao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `tipo` ENUM('A', 'C', 'R', 'U', 'D', 'E') NOT NULL DEFAULT 'E' COMMENT 'A - ACESSAR\\\\nC - CRIAR\\\\nR - RESTAURAR\\\\nU - ATUALIZAR\\\\nD - DELETAR\\\\nE - EXECUTAR',
  `ordem` INT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_acao_acao1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_acao_acao1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `sistema`.`acao` (`id`));

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`funcionalidade_acao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`funcionalidade_acao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `funcionalidade_id` INT UNSIGNED NOT NULL,
  `acao_id` INT UNSIGNED NOT NULL,
  `concede_acesso_a` ENUM('ANONIMO', 'SEM_PERFIL', 'PERFIL', 'PERFIL_PESSOAL', 'PERFIL_FUNCIONAL') NOT NULL DEFAULT 'PERFIL' COMMENT 'ANONIMO - Disponível a todos os usuários que efetuaram login ou não\\\\nSEM_PERFIL - Disponível a todos os usuários que efetuaram login\\\\nPERFIL - Disponível a todos os usuários que efetuaram login e que tenha registro na tabela de privilégios com acesso a funcionalidade/acao\\\\nPERFIL_PESSOAL - Disponível a todos os usuários que efetuaram login, que tenha registro na tabela de privilégios com acesso a funcionalidade/acao e que tenha registro de pessoa vinculada\\\\nPERFIL_FUNCIONAL - Disponível a todos os usuários que efetuaram login, que tenha registro na tabela de privilégios com acesso a funcionalidade/acao, que tenha registro de pessoa vinculada e que a pessoa vinculada tenha registro de emprego vinculado',
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_funcionalidade_acao` (`funcionalidade_id` ASC, `acao_id` ASC) VISIBLE,
  INDEX `fk_funcionalidade_acao_2_idx` (`acao_id` ASC) VISIBLE,
  INDEX `fk_funcionalidade_acao_1_idx` (`funcionalidade_id` ASC) VISIBLE,
  INDEX `fk_funcionalidade_acao_3_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_funcionalidade_acao_1`
    FOREIGN KEY (`funcionalidade_id`)
    REFERENCES `sistema`.`funcionalidade` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_funcionalidade_acao_2`
    FOREIGN KEY (`acao_id`)
    REFERENCES `sistema`.`acao` (`id`),
  CONSTRAINT `fk_funcionalidade_acao_3`
    FOREIGN KEY (`pai_id`)
    REFERENCES `sistema`.`funcionalidade_acao` (`id`));

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`modulo_funcionalidade_acao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`modulo_funcionalidade_acao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `modulo_id` INT UNSIGNED NOT NULL,
  `funcionalidade_acao_id` INT UNSIGNED NOT NULL,
  `grupo_menu` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `exibir_menu_principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `ordem` INT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_modulo_funcionalidade_acao` (`modulo_id` ASC, `funcionalidade_acao_id` ASC) VISIBLE,
  INDEX `fk_modulo_funcionalidade_acao_1` (`modulo_id` ASC) VISIBLE,
  INDEX `fk_modulo_funcionalidade_acao_2_idx` (`funcionalidade_acao_id` ASC) VISIBLE,
  CONSTRAINT `fk_modulo_funcionalidade_acao_1`
    FOREIGN KEY (`modulo_id`)
    REFERENCES `sistema`.`modulo` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_modulo_funcionalidade_acao_2`
    FOREIGN KEY (`funcionalidade_acao_id`)
    REFERENCES `sistema`.`funcionalidade_acao` (`id`)
    ON DELETE CASCADE);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`perfil` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `administrador` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Se igual a \'S\' informa que o conteúdo da tabela privilegio deve ser ignorado e todas as funcionalidades/ações ativos devem ser concedidas a quem acionar este perfil. Porém, para ativar este perfil, o usuario também deve ser do tipo ADMIN, caso contrário, disparar uma excessão. Só deve haver um registro de perfil com o valor \'S\', todos os demais deve ser igual a \'N\' (este atributo deve ser manipulado somente pelo BD)',
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`privilegio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`privilegio` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `perfil_id` INT UNSIGNED NOT NULL,
  `funcionalidade_acao_id` INT UNSIGNED NOT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_privilegio` (`perfil_id` ASC, `funcionalidade_acao_id` ASC) VISIBLE,
  INDEX `fk_privilegio_2_idx` (`funcionalidade_acao_id` ASC) VISIBLE,
  INDEX `fk_privilegio_1_idx` (`perfil_id` ASC) VISIBLE,
  CONSTRAINT `fk_privilegio_1`
    FOREIGN KEY (`perfil_id`)
    REFERENCES `sistema`.`perfil` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_privilegio_2`
    FOREIGN KEY (`funcionalidade_acao_id`)
    REFERENCES `sistema`.`funcionalidade_acao` (`id`)
    ON DELETE CASCADE)
COMMENT = 'Conteúdo utilizado para checar funcionalidades/acões vinculadas à algum perfil';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`usuario` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('COMUM', 'ADMIN', 'SISTEMA') NOT NULL DEFAULT 'COMUM' COMMENT 'COMUM - usuário comum, somente acesso aos privilégios atribuídos ao perfil selecionado;\\\\nSISTEMA - usuário de sistema, somente acesso aos privilégios atribuidos ao perfil selecionado;\\\\nADMIN - usuário administrador, acesso a todos os privilégios. Porém, o modo administrador só será acionado se um perfil do tipo administrador também for selecionado. Caso contrário, somente acesso aos privilégios atribuidos ao perfil selecionado;\\\\n',
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `login` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `senha` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `email` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `foto` BLOB NULL,
  `pessoa_id` INT UNSIGNED NULL,
  `permitir_heranca_perfis` ENUM('S', 'N') NOT NULL DEFAULT 'S' COMMENT 'S - pode selecionar os próprios ou os perfis atribuídos à pessoa vinculada a este usuário. Exemplo selecionar os perfis atribuidos a uma lotação do registro de funcionario vinculado a pessoa\\\\nN - pode selecionar somente os perfis atribuídos ao usuário',
  `ultimo_perfil_id` INT UNSIGNED NULL COMMENT 'memoria do ultimo perfil utilizado no login. No proximo login este será o perfil utilizado. O usuário poderá trocar seu perfil ao executar o sistema',
  `observacao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_usuario_4_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_usuario_3_idx` (`ultimo_perfil_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_3`
    FOREIGN KEY (`ultimo_perfil_id`)
    REFERENCES `sistema`.`perfil` (`id`)
    ON DELETE SET NULL,
  CONSTRAINT `fk_usuario_4`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`));

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`usuario_perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`usuario_perfil` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_id` INT UNSIGNED NOT NULL,
  `perfil_id` INT UNSIGNED NOT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  `padrao` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Se igual a \'S\' indica que este será o perfil acionado, porém, somente se o campo ultimo_usuario_perfil_id do usuário for nulo. O usuário_perfil só pode ter um registro marcado como padrão. Cada usuário tem o seu perfil padrão.',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_usuario_perfil` (`usuario_id` ASC, `perfil_id` ASC) VISIBLE,
  INDEX `fk_usuario_perfil_2_idx` (`perfil_id` ASC) INVISIBLE,
  INDEX `fk_usuario_perfil_1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_perfil_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `sistema`.`usuario` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_usuario_perfil_2`
    FOREIGN KEY (`perfil_id`)
    REFERENCES `sistema`.`perfil` (`id`)
    ON DELETE CASCADE);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`forma_autenticacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`forma_autenticacao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `tipo` ENUM('MEMORIA', 'BD', 'LDAP', 'EXTERNO') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `config` JSON NULL DEFAULT NULL,
  `padrao` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Se igual a \'S\' indica que todo novo usuário poderá se habilitar por esta forma de autenticação',
  `ordem` INT NOT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `ordem_UNIQUE` (`ordem` ASC) VISIBLE,
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`usuario_forma_autenticacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`usuario_forma_autenticacao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_id` INT UNSIGNED NOT NULL,
  `forma_autenticacao_id` INT UNSIGNED NOT NULL,
  `valor` JSON NULL DEFAULT NULL COMMENT 'Configurações complementares da forma de autenticação do usuário',
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_usuario_forma_autenticacao_1` (`usuario_id` ASC, `forma_autenticacao_id` ASC) VISIBLE,
  INDEX `fk_usuario_forma_autenticacao_1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_usuario_forma_autenticacao_2_idx` (`forma_autenticacao_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_forma_autenticacao_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `sistema`.`usuario` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_usuario_forma_autenticacao_2`
    FOREIGN KEY (`forma_autenticacao_id`)
    REFERENCES `sistema`.`forma_autenticacao` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`token` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario_id` INT UNSIGNED NOT NULL,
  `token` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `criado_em` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expira_em` INT UNSIGNED NULL,
  `tipo` ENUM('ACESSO', 'TROCAR_SENHA') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `detalhe` JSON NULL DEFAULT NULL,
  `invalidado_em` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_token_1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_token_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `sistema`.`usuario` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`historico_atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`historico_atividade` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `token_id` INT UNSIGNED NOT NULL,
  `requisicao` JSON NULL DEFAULT NULL,
  `inicio` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modulo_id` INT UNSIGNED NULL,
  `funcionalidade_id` INT UNSIGNED NULL,
  `acao_id` INT UNSIGNED NULL,
  `resposta` JSON NULL DEFAULT NULL,
  `termino` TIMESTAMP NULL,
  `duracao` INT UNSIGNED NULL,
  `status` INT UNSIGNED NULL,
  `mensagem` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_historico_atividade_idx` (`token_id` ASC) VISIBLE,
  CONSTRAINT `fk_historico_atividade_1`
    FOREIGN KEY (`token_id`)
    REFERENCES `sistema`.`token` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `sistema`.`configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistema`.`configuracao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `valor` JSON NULL DEFAULT NULL,
  `usuario_id` INT UNSIGNED NULL COMMENT 'Quando a configuração é exclusiva de um usuário. Se não preenchido, é do sistema ou do módulo.',
  `modulo_id` INT UNSIGNED NULL COMMENT 'Quando a configuração é exclusiva de um módulo. Se não preenchido, é do sistema ou do usuário.',
  `pai_id` INT UNSIGNED NULL COMMENT 'indica a configuracao principal desta configuracao',
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_configuracao_3_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_configuracao_4_idx` (`modulo_id` ASC) VISIBLE,
  INDEX `fk_configuracao_5_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_configuracao_3`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `sistema`.`usuario` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_configuracao_4`
    FOREIGN KEY (`modulo_id`)
    REFERENCES `sistema`.`modulo` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_configuracao_5`
    FOREIGN KEY (`pai_id`)
    REFERENCES `sistema`.`configuracao` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;
USE `pessoa` ;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_fisica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_fisica` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cpf` CHAR(14) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `nascimento` DATE NULL,
  `falecimento` DATE NULL,
  `sexo` ENUM('M', 'F') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `cnh_numero` VARCHAR(20) NULL,
  `cnh_categoria` VARCHAR(5) NULL,
  `cnh_vencimento` DATE NULL,
  `cnh_imagem` LONGBLOB NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `cnh_numero_UNIQUE` (`cnh_numero` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_fisica_1`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_juridica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_juridica` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cnpj` CHAR(18) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `fundacao` DATE NULL,
  `encerramento` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_juridica_1`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`grupo_social`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`grupo_social` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `administrado` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Se N - Indica que não possui administrador\\\\nSe S - Indica que é administrado por um usuario',
  `dinamico` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Se S - Indica que os membros são encontrados atraves do sql de consulta\\\\nSe N - Indica que os membros são encontrados no relacionamento entre pessoas',
  `sql` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL COMMENT 'sql para encontrar pessoa_id que são membros deste grupo',
  `inicio` DATETIME NULL,
  `termino` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_grupo_social_i`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`relacionamento_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`relacionamento_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `temporal` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  `pessoa_tipo` SET('PF', 'PJ', 'GS') NOT NULL COMMENT 'indica a qual tipo de pessoa este tipo de relacionamento pode ser atribuido',
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  INDEX `fk_relacionamento_tipo_relacionamento_tipo1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_relacionamento_tipo_relacionamento_tipo1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `pessoa`.`relacionamento_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`relacionamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`relacionamento` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `relacionamento_tipo_id` INT UNSIGNED NOT NULL,
  `inicio` DATETIME NULL,
  `termino` DATETIME NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  INDEX `fk_relacionamento_1_idx` (`relacionamento_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_relacionamento_1`
    FOREIGN KEY (`relacionamento_tipo_id`)
    REFERENCES `pessoa`.`relacionamento_tipo` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`relacionamento_funcao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`relacionamento_funcao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `nome_se_feminino` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`relacionamento_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`relacionamento_pessoa` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `relacionamento_id` INT UNSIGNED NOT NULL,
  `pessoa_id` INT UNSIGNED NOT NULL,
  `relacionamento_funcao_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_relacionamento_pessoa_1` (`relacionamento_id` ASC, `pessoa_id` ASC, `relacionamento_funcao_id` ASC) VISIBLE,
  INDEX `fk_relacionamento_pessoa_1_idx` (`relacionamento_id` ASC) VISIBLE,
  INDEX `fk_relacionamento_pessoa_2_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_relacionamento_pessoa_3_idx` (`relacionamento_funcao_id` ASC) VISIBLE,
  CONSTRAINT `fk_relacionamento_pessoa_1`
    FOREIGN KEY (`relacionamento_id`)
    REFERENCES `pessoa`.`relacionamento` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_relacionamento_pessoa_2`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_relacionamento_pessoa_3`
    FOREIGN KEY (`relacionamento_funcao_id`)
    REFERENCES `pessoa`.`relacionamento_funcao` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`relacionamento_configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`relacionamento_configuracao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `relacionamento_tipo_id` INT UNSIGNED NOT NULL,
  `relacionador_funcao_id` INT UNSIGNED NOT NULL,
  `relacionado_funcao_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_relacionamento_configuracao_1_idx` (`relacionamento_tipo_id` ASC) VISIBLE,
  INDEX `fk_relacionamento_configuracao_2_idx` (`relacionador_funcao_id` ASC) VISIBLE,
  INDEX `fk_relacionamento_configuracao_3_idx` (`relacionado_funcao_id` ASC) VISIBLE,
  CONSTRAINT `fk_relacionamento_configuracao_1`
    FOREIGN KEY (`relacionamento_tipo_id`)
    REFERENCES `pessoa`.`relacionamento_tipo` (`id`),
  CONSTRAINT `fk_relacionamento_configuracao_2`
    FOREIGN KEY (`relacionador_funcao_id`)
    REFERENCES `pessoa`.`relacionamento_funcao` (`id`),
  CONSTRAINT `fk_relacionamento_configuracao_3`
    FOREIGN KEY (`relacionado_funcao_id`)
    REFERENCES `pessoa`.`relacionamento_funcao` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`arquivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`arquivo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(300) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `extensao` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `conteudo` LONGBLOB NOT NULL,
  `tamanho_bytes` INT NOT NULL,
  `md5` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `data` DATETIME NULL,
  `local` POINT NULL DEFAULT NULL,
  `autores` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `md5_UNIQUE` (`md5` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_arquivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_arquivo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT UNSIGNED NOT NULL,
  `arquivo_id` INT UNSIGNED NOT NULL,
  `principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `ordem` INT NOT NULL,
  `visibilidade` ENUM('PUBLICO', 'PARTICULAR') NOT NULL DEFAULT 'PUBLICO',
  PRIMARY KEY (`id`),
  INDEX `fk_pessoa_endereco_2_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_pessoa_arquivo_1_idx` (`arquivo_id` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_arquivo_1`
    FOREIGN KEY (`arquivo_id`)
    REFERENCES `comum`.`arquivo` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_pessoa_arquivo_2`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`email` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(64) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `dominio` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_email_1` (`usuario` ASC, `dominio` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_email` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT UNSIGNED NOT NULL,
  `email_id` INT UNSIGNED NOT NULL,
  `principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `ordem` INT NOT NULL,
  `visibilidade` ENUM('PUBLICO', 'PARTICULAR') NOT NULL DEFAULT 'PUBLICO',
  PRIMARY KEY (`id`),
  INDEX `fk_pessoa_endereco_2_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_pessoa_email_1_idx` (`email_id` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_email_1`
    FOREIGN KEY (`email_id`)
    REFERENCES `comum`.`email` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_pessoa_email_2`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`localizacao_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`localizacao_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  INDEX `fk_localizacao_tipo_1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_localizacao_tipo_1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `comum`.`localizacao_tipo` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`referencia_espacial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`referencia_espacial` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `area` POLYGON NULL DEFAULT NULL,
  `tipo` ENUM('ENDERECO', 'LOCALIZACAO') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`localizacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`localizacao` (
  `id` INT UNSIGNED NOT NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `localizacao_tipo_id` INT UNSIGNED NOT NULL,
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_localizacao_1` (`nome` ASC, `localizacao_tipo_id` ASC, `pai_id` ASC) INVISIBLE,
  INDEX `fk_localizacao_1_idx` (`localizacao_tipo_id` ASC) VISIBLE,
  INDEX `fk_localizacao_2_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_localizacao_1`
    FOREIGN KEY (`localizacao_tipo_id`)
    REFERENCES `comum`.`localizacao_tipo` (`id`),
  CONSTRAINT `fk_localizacao_2`
    FOREIGN KEY (`pai_id`)
    REFERENCES `comum`.`localizacao` (`id`),
  CONSTRAINT `fk_localizacao_3`
    FOREIGN KEY (`id`)
    REFERENCES `comum`.`referencia_espacial` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`endereco` (
  `id` INT UNSIGNED NOT NULL,
  `logradouro` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `complemento` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `numero` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `cep` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `localizacao_id` INT UNSIGNED NULL,
  `ponto_referencia` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_endereco_1` (`logradouro` ASC, `complemento` ASC, `numero` ASC, `localizacao_id` ASC) VISIBLE,
  INDEX `fk_endereco_1_idx` (`localizacao_id` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_1`
    FOREIGN KEY (`localizacao_id`)
    REFERENCES `comum`.`localizacao` (`id`),
  CONSTRAINT `fk_endereco_2`
    FOREIGN KEY (`id`)
    REFERENCES `comum`.`referencia_espacial` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_endereco` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT UNSIGNED NOT NULL,
  `endereco_id` INT UNSIGNED NOT NULL,
  `principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `ordem` INT NOT NULL,
  `visibilidade` ENUM('PUBLICO', 'PARTICULAR') NOT NULL DEFAULT 'PUBLICO',
  PRIMARY KEY (`id`),
  INDEX `fk_pessoa_endereco_1_idx` (`endereco_id` ASC) VISIBLE,
  INDEX `fk_pessoa_endereco_2_idx` (`pessoa_id` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_endereco_1`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `comum`.`endereco` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_pessoa_endereco_2`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`foto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`foto` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(300) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `extensao` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `conteudo` LONGBLOB NOT NULL,
  `tamanho_bytes` INT NOT NULL,
  `md5` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `data` DATETIME NULL,
  `local` POINT NULL DEFAULT NULL,
  `autores` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `largura_pixel` INT NULL,
  `altura_pixel` INT NULL,
  `resolucao_horizontal_dpi` INT NULL,
  `resolucao_vertical_dpi` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `md5_UNIQUE` (`md5` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_foto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_foto` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT UNSIGNED NOT NULL,
  `foto_id` INT UNSIGNED NOT NULL,
  `principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `ordem` INT NOT NULL,
  `visibilidade` ENUM('PUBLICO', 'PARTICULAR') NOT NULL DEFAULT 'PUBLICO',
  PRIMARY KEY (`id`),
  INDEX `fk_pessoa_endereco_2_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_pessoa_foto_1_idx` (`foto_id` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_foto_1`
    FOREIGN KEY (`foto_id`)
    REFERENCES `comum`.`foto` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_pessoa_foto_2`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `comum`.`telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comum`.`telefone` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ddi` VARCHAR(5) NULL,
  `ddd` VARCHAR(2) NULL,
  `numero` VARCHAR(15) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_telefone` (`ddi` ASC, `ddd` ASC, `numero` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `pessoa`.`pessoa_telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pessoa`.`pessoa_telefone` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT UNSIGNED NOT NULL,
  `telefone_id` INT UNSIGNED NOT NULL,
  `principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `ordem` INT NOT NULL,
  `visibilidade` ENUM('PUBLICO', 'PARTICULAR') NOT NULL DEFAULT 'PUBLICO',
  PRIMARY KEY (`id`),
  INDEX `fk_pessoa_endereco_2_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_pessoa_telefone_1_idx` (`telefone_id` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_telefone_1`
    FOREIGN KEY (`telefone_id`)
    REFERENCES `comum`.`telefone` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_pessoa_telefone_2`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
USE `comum` ;
USE `produto` ;

-- -----------------------------------------------------
-- Table `produto`.`bem_patrimonial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`bem_patrimonial` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `responsavel_pessoa_id` INT UNSIGNED NOT NULL COMMENT 'responsavel pela tutela e utilizacao do bem patrimonial',
  `sigla_proprietario` VARCHAR(255) NOT NULL COMMENT 'identifica a sigla do proprietario do bem',
  `identificacao_patrimonial` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL COMMENT 'numero exclusivo que indica o codigo patrimonial do produto',
  `observacao` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `identificacao_patrimonial_UNIQUE` (`sigla_proprietario` ASC, `identificacao_patrimonial` ASC) VISIBLE,
  INDEX `fk_bem_patrimonial_pessoa1_idx` (`responsavel_pessoa_id` ASC) VISIBLE,
  CONSTRAINT `fk_bem_patrimonial_1`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`produto` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_bem_patrimonial_pessoa1`
    FOREIGN KEY (`responsavel_pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`composicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`composicao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `principal_produto_id` INT UNSIGNED NOT NULL,
  `produto_id` INT UNSIGNED NOT NULL,
  `inicio` DATETIME NOT NULL,
  `termino` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_composicao_1_idx` (`principal_produto_id` ASC) VISIBLE,
  INDEX `fk_composicao_2_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_composicao_1`
    FOREIGN KEY (`principal_produto_id`)
    REFERENCES `principal`.`produto` (`id`),
  CONSTRAINT `fk_composicao_2`
    FOREIGN KEY (`produto_id`)
    REFERENCES `principal`.`produto` (`id`))
ENGINE = InnoDB
COMMENT = 'Indica a vinculação de produtos acessórios';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`modelo_marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`modelo_marca` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `modelo_id` INT UNSIGNED NOT NULL,
  `marca_id` INT UNSIGNED NOT NULL,
  INDEX `fk_modelo_marca_marca1_idx` (`marca_id` ASC) VISIBLE,
  INDEX `fk_modelo_marca_modelo1_idx` (`modelo_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_modelo_marca` (`modelo_id` ASC, `marca_id` ASC) VISIBLE,
  CONSTRAINT `fk_modelo_marca_modelo1`
    FOREIGN KEY (`modelo_id`)
    REFERENCES `produto`.`modelo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_modelo_marca_marca1`
    FOREIGN KEY (`marca_id`)
    REFERENCES `produto`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`tipo_unidade_medida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`tipo_unidade_medida` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo_id` INT UNSIGNED NOT NULL,
  `unidade_medida_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_tipo_unidade_medida_produto_tipo1_idx` (`tipo_id` ASC) VISIBLE,
  UNIQUE INDEX `uq_produto_tipo_unidade_medida` (`tipo_id` ASC, `unidade_medida_id` ASC) VISIBLE,
  INDEX `fk_produto_tipo_unidade_medida_produto_tipo2_idx` (`unidade_medida_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_tipo_unidade_medida_produto_tipo1`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `produto`.`tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_tipo_unidade_medida_produto_tipo2`
    FOREIGN KEY (`unidade_medida_id`)
    REFERENCES `comum`.`unidade_medida` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `produto`.`produto_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `produto`.`produto_pessoa` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `produto_id` INT UNSIGNED NOT NULL,
  `pessoa_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_pessoa_pessoa1_idx` (`pessoa_id` ASC) VISIBLE,
  INDEX `fk_produto_pessoa_produto1_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_pessoa_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `principal`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_pessoa_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `principal`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

SHOW WARNINGS;
USE `oauth2` ;

-- -----------------------------------------------------
-- Table `oauth2`.`oauth_access_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oauth2`.`oauth_access_token` (
  `token_id` VARCHAR(255) NULL DEFAULT NULL,
  `token` LONGBLOB NULL DEFAULT NULL,
  `authentication_id` VARCHAR(255) NULL DEFAULT NULL,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` VARCHAR(255) NULL DEFAULT NULL,
  `authentication` LONGBLOB NULL DEFAULT NULL,
  `refresh_token` VARCHAR(255) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `oauth2`.`oauth_approvals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oauth2`.`oauth_approvals` (
  `userid` VARCHAR(255) NULL DEFAULT NULL,
  `clientid` VARCHAR(255) NULL DEFAULT NULL,
  `scope` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(10) NULL DEFAULT NULL,
  `expiresat` DATETIME NULL DEFAULT NULL,
  `lastmodifiedat` DATETIME NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `oauth2`.`oauth_client_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oauth2`.`oauth_client_details` (
  `client_id` VARCHAR(255) NOT NULL,
  `resource_ids` VARCHAR(255) NULL DEFAULT NULL,
  `client_secret` VARCHAR(255) NULL DEFAULT NULL,
  `scope` VARCHAR(255) NULL DEFAULT NULL,
  `authorized_grant_types` VARCHAR(255) NULL DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(255) NULL DEFAULT NULL,
  `authorities` VARCHAR(255) NULL DEFAULT NULL,
  `access_token_validity` INT(11) NULL DEFAULT NULL,
  `refresh_token_validity` INT(11) NULL DEFAULT NULL,
  `additional_information` VARCHAR(255) NULL DEFAULT NULL,
  `autoapprove` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `oauth2`.`oauth_client_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oauth2`.`oauth_client_token` (
  `token_id` VARCHAR(255) NULL DEFAULT NULL,
  `token` LONGBLOB NULL DEFAULT NULL,
  `authentication_id` VARCHAR(255) NULL DEFAULT NULL,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  `client_id` VARCHAR(255) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `oauth2`.`oauth_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oauth2`.`oauth_code` (
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `authentication` VARBINARY(255) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `oauth2`.`oauth_refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `oauth2`.`oauth_refresh_token` (
  `token_id` VARCHAR(255) NULL DEFAULT NULL,
  `token` LONGBLOB NULL DEFAULT NULL,
  `authentication` LONGBLOB NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;
USE `evento` ;

-- -----------------------------------------------------
-- Table `evento`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento`.`tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `pai_id` INT UNSIGNED NULL,
  `recurso_tipo` SET('PESSOA', 'PRODUTO', 'SERVICO') NOT NULL,
  `uso_sistema` ENUM('S', 'N') NOT NULL DEFAULT 'N' COMMENT 'Indica que este tipo de evento é utilizado internamente pelo sistema, o usuário não interage com este tipo de registro',
  PRIMARY KEY (`id`),
  INDEX `fk_evento_tipo_evento_tipo1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_evento_tipo_evento_tipo1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `evento`.`tipo` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `evento`.`evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento`.`evento` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `planejamento` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  `recurso_id` INT UNSIGNED NOT NULL,
  `tipo_id` INT UNSIGNED NOT NULL,
  `local` POLYGON NULL DEFAULT NULL,
  `inicio` DATETIME NULL,
  `termino` DATETIME NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_padrao_item_idx` (`recurso_id` ASC) VISIBLE,
  INDEX `fk_evento_evento_tipo1_idx` (`tipo_id` ASC) VISIBLE,
  INDEX `fk_evento_evento1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_evento_evento1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `evento`.`evento` (`id`),
  CONSTRAINT `fk_evento_evento_tipo1`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `evento`.`tipo` (`id`),
  CONSTRAINT `fk_padrao_item`
    FOREIGN KEY (`recurso_id`)
    REFERENCES `principal`.`recurso` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `evento`.`funcao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento`.`funcao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `pai_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_funcao1_idx` (`pai_id` ASC) VISIBLE,
  CONSTRAINT `fk_funcao1`
    FOREIGN KEY (`pai_id`)
    REFERENCES `evento`.`funcao` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `evento`.`participacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento`.`participacao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `evento_id` INT UNSIGNED NOT NULL,
  `recurso_id` INT UNSIGNED NOT NULL,
  `funcao_id` INT UNSIGNED NOT NULL,
  `principal` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  INDEX `fk_evento_participacao_evento1_idx` (`evento_id` ASC) VISIBLE,
  INDEX `fk_evento_participacao_item1_idx` (`recurso_id` ASC) VISIBLE,
  INDEX `fk_evento_participacao_funcao1_idx` (`funcao_id` ASC) VISIBLE,
  CONSTRAINT `fk_evento_participacao_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `evento`.`evento` (`id`),
  CONSTRAINT `fk_evento_participacao_funcao1`
    FOREIGN KEY (`funcao_id`)
    REFERENCES `evento`.`funcao` (`id`),
  CONSTRAINT `fk_evento_participacao_item1`
    FOREIGN KEY (`recurso_id`)
    REFERENCES `principal`.`recurso` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `evento`.`evidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento`.`evidencia` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `evento_id` INT UNSIGNED NOT NULL,
  `descricao` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `conteudo` LONGBLOB NULL,
  `tipo` ENUM('IMAGEM', 'VIDEO', 'AUDIO', 'DOCUMENTO') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_evento_evidencia_evento1_idx` (`evento_id` ASC) VISIBLE,
  CONSTRAINT `fk_evento_evidencia_evento1`
    FOREIGN KEY (`evento_id`)
    REFERENCES `evento`.`evento` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `evento`.`tipo_funcao_configuracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evento`.`tipo_funcao_configuracao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tipo_id` INT UNSIGNED NOT NULL,
  `funcao_id` INT UNSIGNED NOT NULL,
  `quantidade` INT NULL COMMENT 'Se preenchido, limita a quantidade de vezes que há tal função no tipo de evento. Se não, não há limite. Exemplo: Tipo Aula, 1 na função de Professor, N na função de Aluno, ...',
  PRIMARY KEY (`id`),
  INDEX `fk_tipo_funcao_funcao1_idx` (`funcao_id` ASC) VISIBLE,
  INDEX `fk_tipo_funcao_tipo1_idx` (`tipo_id` ASC) VISIBLE,
  UNIQUE INDEX `uq_tipo_funcao` (`tipo_id` ASC, `funcao_id` ASC) VISIBLE,
  CONSTRAINT `fk_tipo_funcao_tipo1`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `evento`.`tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipo_funcao_funcao1`
    FOREIGN KEY (`funcao_id`)
    REFERENCES `evento`.`funcao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
USE `veiculo` ;

-- -----------------------------------------------------
-- Table `veiculo`.`veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`veiculo` (
  `id` INT UNSIGNED NOT NULL,
  `placa` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `ano_fabricacao` INT NULL,
  `ano_modelo` INT NULL,
  `renavan` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `combustivel` SET('GASOLINA', 'ETANOL', 'DIESEL') NULL,
  `cor` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  `cor_rgb` VARCHAR(6) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_veiculo_produto`
    FOREIGN KEY (`id`)
    REFERENCES `principal`.`produto` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`veiculo_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`veiculo_evento` (
  `id` INT UNSIGNED NOT NULL,
  `tipo` ENUM('ABASTECIMENTO', 'DENUNCIA', 'DESPESA', 'INFRACAO', 'LEMBRETE', 'MANUTENCAO', 'OBSERVACAO', 'SINISTRO', 'TAXAS_IMPOSTOS', 'UTILIZACAO') NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_padrao_evento1_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_padrao_evento1`
    FOREIGN KEY (`id`)
    REFERENCES `evento`.`evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`utilizacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`utilizacao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `quilometragem` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_categoria_evento1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`orgao_transito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`orgao_transito` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `sigla` VARCHAR(255) NULL,
  `unidade_federacao` CHAR(2) NULL,
  `url_site` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`infracao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`infracao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `aviso_condutor` DATETIME NULL,
  `limite_justificativa` DATE NULL,
  `orgao_autuador_id` INT UNSIGNED NOT NULL,
  `envio_email` DATETIME NULL,
  `notificacao` DATE NULL,
  `recebimento_boleto` DATE NULL,
  `valor` DECIMAL(12,0) NULL,
  `vencimento` DATE NULL,
  `pagamento` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_infracao_orgao_transito1_idx` (`orgao_autuador_id` ASC) VISIBLE,
  CONSTRAINT `fk_infracao_evento1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`),
  CONSTRAINT `fk_infracao_orgao_transito1`
    FOREIGN KEY (`orgao_autuador_id`)
    REFERENCES `veiculo`.`orgao_transito` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`abastecimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`abastecimento` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `posto_nome` VARCHAR(255) NULL COMMENT 'Nome do posto onde foi fornecido o combustível',
  `forma_pagamento` VARCHAR(255) NULL,
  `local` POINT NULL,
  `quilometragem` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_abastecimento1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`denuncia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`denuncia` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_denuncia1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`despesa_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`despesa_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`despesa` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `despesa_tipo_id` INT UNSIGNED NOT NULL,
  `quilometragem` DECIMAL(12,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_despesa_despesa_tipo1_idx` (`despesa_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_despesa1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_despesa_despesa_tipo1`
    FOREIGN KEY (`despesa_tipo_id`)
    REFERENCES `veiculo`.`despesa_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`lembrete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`lembrete` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lembrete1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`manutencao_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`manutencao_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`manutencao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`manutencao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `manutencao_tipo_id` INT UNSIGNED NOT NULL,
  `quilometragem` DECIMAL(12,2) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_manutencao_manutencao_tipo1_idx` (`manutencao_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_manutencao1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_manutencao_manutencao_tipo1`
    FOREIGN KEY (`manutencao_tipo_id`)
    REFERENCES `veiculo`.`manutencao_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`observacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`observacao` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_observacao1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`sinistro_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`sinistro_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`sinistro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`sinistro` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `quilometragem` DECIMAL(12,2) NULL,
  `sinistro_tipo_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sinistro_sinistro_tipo1_idx` (`sinistro_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_sinistro1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sinistro_sinistro_tipo1`
    FOREIGN KEY (`sinistro_tipo_id`)
    REFERENCES `veiculo`.`sinistro_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`taxa_imposto_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`taxa_imposto_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(255) NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `veiculo`.`taxa_imposto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veiculo`.`taxa_imposto` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `taxa_imposto_tipo_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_taxa_imposto_taxa_imposto_tipo1_idx` (`taxa_imposto_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_taxa_imposto1`
    FOREIGN KEY (`id`)
    REFERENCES `veiculo`.`veiculo_evento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_taxa_imposto_taxa_imposto_tipo1`
    FOREIGN KEY (`taxa_imposto_tipo_id`)
    REFERENCES `veiculo`.`taxa_imposto_tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
USE `funcional` ;

-- -----------------------------------------------------
-- Table `funcional`.`empregador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`empregador` (
  `id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_empregador_pessoa_juridica`
    FOREIGN KEY (`id`)
    REFERENCES `pessoa`.`pessoa_juridica` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`empregado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`empregado` (
  `id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_empregado_pessoa_fisica1`
    FOREIGN KEY (`id`)
    REFERENCES `pessoa`.`pessoa_fisica` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`cargo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`cargo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`empregador_cargo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`empregador_cargo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `empregador_id` INT UNSIGNED NOT NULL,
  `cargo_id` INT UNSIGNED NOT NULL,
  `ativo` ENUM('S', 'N') NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_empregador_cargo` (`empregador_id` ASC, `cargo_id` ASC) VISIBLE,
  INDEX `fk_empregador_cargo_cargo1_idx` (`cargo_id` ASC) VISIBLE,
  INDEX `fk_empregador_cargo_empregador1_idx` (`empregador_id` ASC) VISIBLE,
  CONSTRAINT `fk_empregador_cargo_cargo1`
    FOREIGN KEY (`cargo_id`)
    REFERENCES `funcional`.`cargo` (`id`),
  CONSTRAINT `fk_empregador_cargo_empregador1`
    FOREIGN KEY (`empregador_id`)
    REFERENCES `funcional`.`empregador` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`emprego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`emprego` (
  `id` INT UNSIGNED NOT NULL,
  `matricula` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `empregador_cargo_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_emprego_empregador_cargo1_idx` (`empregador_cargo_id` ASC) VISIBLE,
  CONSTRAINT `fk_emprego_empregador_cargo1`
    FOREIGN KEY (`empregador_cargo_id`)
    REFERENCES `funcional`.`empregador_cargo` (`id`),
  CONSTRAINT `fk_padrao_relacionamento1`
    FOREIGN KEY (`id`)
    REFERENCES `pessoa`.`relacionamento` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`unidade_organizacional_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`unidade_organizacional_tipo` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`unidade_organizacional`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`unidade_organizacional` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `empregador_id` INT UNSIGNED NOT NULL,
  `unidade_organizacional_tipo_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_unidade_organizacional_empregador1_idx` (`empregador_id` ASC) VISIBLE,
  INDEX `fk_unidade_organizacional_unidade_organizacional_tipo1_idx` (`unidade_organizacional_tipo_id` ASC) VISIBLE,
  CONSTRAINT `fk_unidade_organizacional_empregador1`
    FOREIGN KEY (`empregador_id`)
    REFERENCES `funcional`.`empregador` (`id`),
  CONSTRAINT `fk_unidade_organizacional_grupo_social1`
    FOREIGN KEY (`id`)
    REFERENCES `pessoa`.`grupo_social` (`id`),
  CONSTRAINT `fk_unidade_organizacional_unidade_organizacional_tipo1`
    FOREIGN KEY (`unidade_organizacional_tipo_id`)
    REFERENCES `funcional`.`unidade_organizacional_tipo` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`unidade_organizacional_gestor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`unidade_organizacional_gestor` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `unidade_organizacional_id` INT UNSIGNED NOT NULL,
  `empregado_id` INT UNSIGNED NOT NULL,
  `inicio` DATETIME NOT NULL,
  `termino` DATETIME NULL,
  `substituto` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_unidade_organizacional_gestor` (`unidade_organizacional_id` ASC, `empregado_id` ASC) VISIBLE,
  INDEX `fk_unidade_organizacional_empregado_empregado1_idx` (`empregado_id` ASC) VISIBLE,
  INDEX `fk_unidade_organizacional_empregado_unidade_organizacional1_idx` (`unidade_organizacional_id` ASC) VISIBLE,
  CONSTRAINT `fk_unidade_organizacional_empregado_empregado1`
    FOREIGN KEY (`empregado_id`)
    REFERENCES `funcional`.`empregado` (`id`),
  CONSTRAINT `fk_unidade_organizacional_empregado_unidade_organizacional1`
    FOREIGN KEY (`unidade_organizacional_id`)
    REFERENCES `funcional`.`unidade_organizacional` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`unidade_organizacional_hierarquia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`unidade_organizacional_hierarquia` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `unidade_organizacional_principal_id` INT UNSIGNED NOT NULL,
  `unidade_organizacional_id` INT UNSIGNED NOT NULL,
  `tipo` ENUM('GESTAO', 'ASSESSORAMENTO') CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL DEFAULT 'GESTAO',
  PRIMARY KEY (`id`),
  INDEX `fk_padrao_unidade_organizacional1_idx` (`unidade_organizacional_principal_id` ASC) VISIBLE,
  INDEX `fk_padrao_unidade_organizacional2_idx` (`unidade_organizacional_id` ASC) VISIBLE,
  CONSTRAINT `fk_padrao_unidade_organizacional1`
    FOREIGN KEY (`unidade_organizacional_principal_id`)
    REFERENCES `funcional`.`unidade_organizacional` (`id`),
  CONSTRAINT `fk_padrao_unidade_organizacional2`
    FOREIGN KEY (`unidade_organizacional_id`)
    REFERENCES `funcional`.`unidade_organizacional` (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `funcional`.`lotacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `funcional`.`lotacao` (
  `id` INT UNSIGNED NOT NULL,
  `temporaria` ENUM('S', 'N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lotacao_relacionamento1`
    FOREIGN KEY (`id`)
    REFERENCES `pessoa`.`relacionamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
