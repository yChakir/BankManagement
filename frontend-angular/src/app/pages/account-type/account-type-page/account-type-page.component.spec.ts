import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountTypePageComponent } from './account-type-page.component';

describe('AccountTypePageComponent', () => {
  let component: AccountTypePageComponent;
  let fixture: ComponentFixture<AccountTypePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountTypePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountTypePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
