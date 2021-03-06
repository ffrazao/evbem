// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  // autorizadorUrl: `http://10.22.2.109:8081`,
  // apiUrl: `http://10.22.2.109:8080`,
  // autorizadorUrl: `http://192.168.43.198:8081`,
  // apiUrl: `http://192.168.43.198:8080`,
  // autorizadorUrl: `http://192.168.122.1:8081`, // 192.168.25.104
  // apiUrl: `http://frazao-HP-ENVY-4:8080`,
  // autorizadorUrl: `http://192.168.25.104:8081`,
  // apiUrl: `http://192.168.25.104:8080`,
  autorizadorUrl: 'http://192.168.25.147:8081',
  apiUrl: 'http://192.168.25.147:8080',
  CLIENT_ID: `evbem_web`,
  CLIENT_SECRET: `evbem_web`,
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
