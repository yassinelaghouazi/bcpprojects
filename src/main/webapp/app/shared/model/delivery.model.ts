import { Moment } from 'moment';
import { IProject } from 'app/shared/model/project.model';
import { IOrders } from 'app/shared/model/orders.model';

export interface IDelivery {
  id?: number;
  idDelivery?: number;
  deliveryDescription?: string;
  deliveryDate?: Moment;
  subTotal?: number;
  vatamount?: number;
  total?: number;
  projects?: IProject[];
  orders?: IOrders[];
  reglementId?: number;
}

export class Delivery implements IDelivery {
  constructor(
    public id?: number,
    public idDelivery?: number,
    public deliveryDescription?: string,
    public deliveryDate?: Moment,
    public subTotal?: number,
    public vatamount?: number,
    public total?: number,
    public projects?: IProject[],
    public orders?: IOrders[],
    public reglementId?: number
  ) {}
}
