-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- usuario: evbem_web, senha: senhaevbem
INSERT INTO oauth2.oauth_client_details
	(
	 client_id,
	 resource_ids,
	 client_secret,
	 scope,
	 authorized_grant_types,
	 web_server_redirect_uri,
	 authorities,
	 access_token_validity,
	 refresh_token_validity,
	 additional_information
	 )
VALUES
	(
	 'evbem_web',
	 'evbem_api',
	 '$2a$10$tfDfQBysdhGZQkyA6p9lOOf6MYpJwuElFsPgeZc1PJgGUX2JTGKte',
	 'read,write',
	 'client_credentials,password',
	 'http://127.0.0.1',
	 'ROLE_USER',
	 7200, 
	 0, 
	 '{}'
	 );

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
