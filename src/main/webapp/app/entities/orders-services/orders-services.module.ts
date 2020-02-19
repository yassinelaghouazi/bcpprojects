import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BtpprojectsSharedModule } from 'app/shared/shared.module';
import { OrdersServicesComponent } from './orders-services.component';
import { OrdersServicesDetailComponent } from './orders-services-detail.component';
import { OrdersServicesUpdateComponent } from './orders-services-update.component';
import { OrdersServicesDeleteDialogComponent } from './orders-services-delete-dialog.component';
import { ordersServicesRoute } from './orders-services.route';

@NgModule({
  imports: [BtpprojectsSharedModule, RouterModule.forChild(ordersServicesRoute)],
  declarations: [
    OrdersServicesComponent,
    OrdersServicesDetailComponent,
    OrdersServicesUpdateComponent,
    OrdersServicesDeleteDialogComponent
  ],
  entryComponents: [OrdersServicesDeleteDialogComponent]
})
export class BtpprojectsOrdersServicesModule {}
