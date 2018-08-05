// import { sandboxOf } from 'angular-playground';
// import { SharedModuleSub } from '../shared/shared.module';
// import { CustomersCardComponent } from './customers-card.component';
// import { RouterTestingModule } from '@angular/router/testing';
// import { CoreModule2 } from '../core/core.module';
// import { customers } from '../shared/mocks';

// const sandboxConfig = {
//   imports: [ SharedModuleSub, CoreModule2, RouterTestingModule ],
//   label: 'Customers Card Component'
// };

// export default sandboxOf(CustomersCardComponent, sandboxConfig)
//   .add('With Many Customers', {
//     template: `<cm-customers-card [customers]="customers"></cm-customers-card>`,
//     context: {
//       customers: customers
//     }
//   })
//   .add('With 10 Customers', {
//     template: `<cm-customers-card [customers]="customers"></cm-customers-card>`,
//     context: {
//       customers: customers.slice(0, 10)
//     }
//   })
//   .add('With 4 Customers', {
//     template: `<cm-customers-card [customers]="customers"></cm-customers-card>`,
//     context: {
//       customers: customers.slice(0, 4)
//     }
//   })
//   .add('Without Customers', {
//     template: `<cm-customers-card></cm-customers-card>`
//   });

