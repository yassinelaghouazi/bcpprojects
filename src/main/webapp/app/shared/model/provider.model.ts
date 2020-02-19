export interface IProvider {
  id?: number;
  idProvider?: number;
  providerName?: string;
  providerLogoContentType?: string;
  providerLogo?: any;
  ordersId?: number;
  productsId?: number;
  reglementId?: number;
}

export class Provider implements IProvider {
  constructor(
    public id?: number,
    public idProvider?: number,
    public providerName?: string,
    public providerLogoContentType?: string,
    public providerLogo?: any,
    public ordersId?: number,
    public productsId?: number,
    public reglementId?: number
  ) {}
}
