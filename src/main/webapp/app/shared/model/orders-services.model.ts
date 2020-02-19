import { IOrders } from 'app/shared/model/orders.model';
import { IServices } from 'app/shared/model/services.model';

export interface IOrdersServices {
  id?: number;
  idOrdersServices?: number;
  tarifJournalier?: number;
  joursHomme?: number;
  orders?: IOrders[];
  services?: IServices[];
}

export class OrdersServices implements IOrdersServices {
  constructor(
    public id?: number,
    public idOrdersServices?: number,
    public tarifJournalier?: number,
    public joursHomme?: number,
    public orders?: IOrders[],
    public services?: IServices[]
  ) {}
}
