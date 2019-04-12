import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent} from './layout/header/header.component';
import {FooterComponent} from './layout/footer/footer.component';
import {DrawerComponent} from './layout/drawer/drawer.component';
import {NgZorroAntdModule} from "ng-zorro-antd";
import {CoreModule} from "../core/core.module";
import {AppRoutingModule} from "../app-routing.module";

@NgModule({
  declarations: [HeaderComponent, FooterComponent, DrawerComponent],
  imports: [
    CommonModule,
    NgZorroAntdModule,
    CoreModule,
    AppRoutingModule
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    DrawerComponent
  ]
})
export class SharedModule {
}
