import { IProvider } from 'app/shared/model/provider.model';

export interface IProducts {
  id?: number;
  idProducts?: number;
  productsDesc?: string;
  recommendedPrice?: number;
  recommendedProviders?: IProvider[];
  ordersProductsId?: number;
}

export class Products implements IProducts {
  constructor(
    public id?: number,
    public idProducts?: number,
    public productsDesc?: string,
    public recommendedPrice?: number,
    public recommendedProviders?: IProvider[],
    public ordersProductsId?: number
  ) {}
}
