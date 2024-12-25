/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CostumerDto } from '../../models/costumer-dto';

export interface CreateTest$Params {
      body: CostumerDto
}

export function createTest(http: HttpClient, rootUrl: string, params: CreateTest$Params, context?: HttpContext): Observable<StrictHttpResponse<CostumerDto>> {
  const rb = new RequestBuilder(rootUrl, createTest.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<CostumerDto>;
    })
  );
}

createTest.PATH = '/api/v1/test/add';