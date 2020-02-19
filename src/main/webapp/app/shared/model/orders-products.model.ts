import { IOrders } from 'app/shared/model/orders.model';
import { IProducts } from 'app/shared/model/products.model';

export interface IOrdersProducts {
  id?: number;
  idOrdersProducts?: number;
  unitPrice?: number;
  quantite?: number;
  tva?: number;
  totalHT?: number;
  montantTVA?: number;
  totalTTC?: number;
  orders?: IOrders[];
  products?: IProducts[];
}

export class OrdersProducts implements IOrdersProducts {
  constructor(
    public id?: number,
    public idOrdersProducts?: number,
    public unitPrice?: number,
    public quantite?: number,
    public tva?: number,
    public totalHT?: number,
    public montantTVA?: number,
    public totalTTC?: number,
    public orders?: IOrders[],
    public products?: IProducts[]
  ) {}
}
