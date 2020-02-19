import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModePaiement } from 'app/shared/model/mode-paiement.model';

type EntityResponseType = HttpResponse<IModePaiement>;
type EntityArrayResponseType = HttpResponse<IModePaiement[]>;

@Injectable({ providedIn: 'root' })
export class ModePaiementService {
  public resourceUrl = SERVER_API_URL + 'api/mode-paiements';

  constructor(protected http: HttpClient) {}

  create(modePaiement: IModePaiement): Observable<EntityResponseType> {
    return this.http.post<IModePaiement>(this.resourceUrl, modePaiement, { observe: 'response' });
  }

  update(modePaiement: IModePaiement): Observable<EntityResponseType> {
    return this.http.put<IModePaiement>(this.resourceUrl, modePaiement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModePaiement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModePaiement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
