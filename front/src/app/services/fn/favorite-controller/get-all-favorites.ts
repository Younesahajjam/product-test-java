/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { FavotiteDto } from '../../models/favotite-dto';

export interface GetAllFavorites$Params {
  customerId: number;
}

export function getAllFavorites(http: HttpClient, rootUrl: string, params: GetAllFavorites$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<FavotiteDto>>> {
  const rb = new RequestBuilder(rootUrl, getAllFavorites.PATH, 'get');
  if (params) {
    rb.path('customerId', params.customerId, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<FavotiteDto>>;
    })
  );
}

getAllFavorites.PATH = '/v1/api/favorites/{customerId}';