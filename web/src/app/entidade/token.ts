export interface Token {
    usuario: string;
    access_token: string;
    token_type: string;
    expires_in: number; 
    scope: string;
}