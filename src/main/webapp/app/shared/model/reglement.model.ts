import { Moment } from 'moment';
import { IProvider } from 'app/shared/model/provider.model';
import { IProject } from 'app/shared/model/project.model';
import { IOrders } from 'app/shared/model/orders.model';
import { IDelivery } from 'app/shared/model/delivery.model';
import { IModePaiement } from 'app/shared/model/mode-paiement.model';

export interface IReglement {
  id?: number;
  idReglement?: number;
  dateEffetReglement?: Moment;
  dateReglement?: Moment;
  montantReglement?: number;
  commentaire?: string;
  providers?: IProvider[];
  projects?: IProject[];
  orders?: IOrders[];
  deliveries?: IDelivery[];
  modePaiements?: IModePaiement[];
}

export class Reglement implements IReglement {
  constructor(
    public id?: number,
    public idReglement?: number,
    public dateEffetReglement?: Moment,
    public dateReglement?: Moment,
    public montantReglement?: number,
    public commentaire?: string,
    public providers?: IProvider[],
    public projects?: IProject[],
    public orders?: IOrders[],
    public deliveries?: IDelivery[],
    public modePaiements?: IModePaiement[]
  ) {}
}
