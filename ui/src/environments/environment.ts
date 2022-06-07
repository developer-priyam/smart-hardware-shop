// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  baseURLProduct: 'http://localhost:8081/',
  baseURLCart: 'http://localhost:8082/',
  baseURLUser: 'http://localhost:8083/',
  // baseURL: 'http://localhost:8080/', // Skipped API Gateway route due to inefficient performance of API Gateway
  cartService: 'cart-service',
  inventoryService: 'inventory-service',
  userService: 'user-service'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
