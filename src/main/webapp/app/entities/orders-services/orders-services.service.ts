import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrdersServices } from 'app/shared/model/orders-services.model';

type EntityResponseType = HttpResponse<IOrdersServices>;
type EntityArrayResponseType = HttpResponse<IOrdersServices[]>;

@Injectable({ providedIn: 'root' })
export class OrdersServicesService {
  public resourceUrl = SERVER_API_URL + 'api/orders-services';

  constructor(protected http: HttpClient) {}

  create(ordersServices: IOrdersServices): Observable<EntityResponseType> {
    return this.http.post<IOrdersServices>(this.resourceUrl, ordersServices, { observe: 'response' });
  }

  update(ordersServices: IOrdersServices): Observable<EntityResponseType> {
    return this.http.put<IOrdersServices>(this.resourceUrl, ordersServices, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrdersServices>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrdersServices[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
