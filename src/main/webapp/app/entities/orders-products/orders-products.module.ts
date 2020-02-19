import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BtpprojectsSharedModule } from 'app/shared/shared.module';
import { OrdersProductsComponent } from './orders-products.component';
import { OrdersProductsDetailComponent } from './orders-products-detail.component';
import { OrdersProductsUpdateComponent } from './orders-products-update.component';
import { OrdersProductsDeleteDialogComponent } from './orders-products-delete-dialog.component';
import { ordersProductsRoute } from './orders-products.route';

@NgModule({
  imports: [BtpprojectsSharedModule, RouterModule.forChild(ordersProductsRoute)],
  declarations: [
    OrdersProductsComponent,
    OrdersProductsDetailComponent,
    OrdersProductsUpdateComponent,
    OrdersProductsDeleteDialogComponent
  ],
  entryComponents: [OrdersProductsDeleteDialogComponent]
})
export class BtpprojectsOrdersProductsModule {}
