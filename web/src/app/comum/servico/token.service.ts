import { Injectable } from "@angular/core";

export const KEY_AUTH_TOKEN = 'AuthToken';

@Injectable({providedIn: 'root'})
export class TokenService {

    set(value: string) {
        window.localStorage.setItem(KEY_AUTH_TOKEN, value);
    }
    get() {
        return window.localStorage.getItem(KEY_AUTH_TOKEN);
    }
    temToken() {
        return !!this.get();
    }
    clear() {
        window.localStorage.removeItem(KEY_AUTH_TOKEN);
    }

}