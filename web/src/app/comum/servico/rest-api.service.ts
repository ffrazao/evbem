import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  constructor(
    private _http: HttpClient
  ) { 
  }

  protected estaOnLine() {
    return navigator.onLine;
  }
}
