import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBopportunity } from 'app/shared/model/bopportunity.model';

type EntityResponseType = HttpResponse<IBopportunity>;
type EntityArrayResponseType = HttpResponse<IBopportunity[]>;

@Injectable({ providedIn: 'root' })
export class BopportunityService {
  public resourceUrl = SERVER_API_URL + 'api/bopportunities';

  constructor(protected http: HttpClient) {}

  create(bopportunity: IBopportunity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bopportunity);
    return this.http
      .post<IBopportunity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bopportunity: IBopportunity): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bopportunity);
    return this.http
      .put<IBopportunity>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBopportunity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBopportunity[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bopportunity: IBopportunity): IBopportunity {
    const copy: IBopportunity = Object.assign({}, bopportunity, {
      dateRemisePlis:
        bopportunity.dateRemisePlis && bopportunity.dateRemisePlis.isValid() ? bopportunity.dateRemisePlis.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateRemisePlis = res.body.dateRemisePlis ? moment(res.body.dateRemisePlis) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bopportunity: IBopportunity) => {
        bopportunity.dateRemisePlis = bopportunity.dateRemisePlis ? moment(bopportunity.dateRemisePlis) : undefined;
      });
    }
    return res;
  }
}
