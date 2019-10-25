import { Injector } from '@angular/core';

export class StaticInjectorService {

  private static _injector: Injector;

  public static set injector(injector: Injector) {
    StaticInjectorService._injector = injector;
  }

  public static get injector(): Injector {
    return StaticInjectorService._injector;
  }

}