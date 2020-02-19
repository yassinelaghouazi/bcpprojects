import { Moment } from 'moment';
import { IProject } from 'app/shared/model/project.model';
import { IProvider } from 'app/shared/model/provider.model';

export interface IOrders {
  id?: number;
  idOrders?: number;
  ordersDesc?: string;
  totalht?: number;
  totaltva?: number;
  totalttc?: number;
  ordersDate?: Moment;
  expectedDelivery?: Moment;
  tvamoyenne?: number;
  projects?: IProject[];
  providers?: IProvider[];
  deliveryId?: number;
  ordersProductsId?: number;
  ordersServicesId?: number;
  reglementId?: number;
}

export class Orders implements IOrders {
  constructor(
    public id?: number,
    public idOrders?: number,
    public ordersDesc?: string,
    public totalht?: number,
    public totaltva?: number,
    public totalttc?: number,
    public ordersDate?: Moment,
    public expectedDelivery?: Moment,
    public tvamoyenne?: number,
    public projects?: IProject[],
    public providers?: IProvider[],
    public deliveryId?: number,
    public ordersProductsId?: number,
    public ordersServicesId?: number,
    public reglementId?: number
  ) {}
}
