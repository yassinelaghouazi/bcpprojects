import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOrdersProducts } from 'app/shared/model/orders-products.model';

type EntityResponseType = HttpResponse<IOrdersProducts>;
type EntityArrayResponseType = HttpResponse<IOrdersProducts[]>;

@Injectable({ providedIn: 'root' })
export class OrdersProductsService {
  public resourceUrl = SERVER_API_URL + 'api/orders-products';

  constructor(protected http: HttpClient) {}

  create(ordersProducts: IOrdersProducts): Observable<EntityResponseType> {
    return this.http.post<IOrdersProducts>(this.resourceUrl, ordersProducts, { observe: 'response' });
  }

  update(ordersProducts: IOrdersProducts): Observable<EntityResponseType> {
    return this.http.put<IOrdersProducts>(this.resourceUrl, ordersProducts, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrdersProducts>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrdersProducts[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
