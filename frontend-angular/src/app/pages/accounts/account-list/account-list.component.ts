import {Component, OnInit} from '@angular/core';
import {Role} from "../../../core/dto/Role";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {includesIgnoreCase} from "../../../core/utils";
import {AccountType} from "../../../core/dto/AccountType";
import {AccountTypeService} from "../../../core/account-type.service";
import {AccountsService} from "../../../core/accounts.service";
import {Account} from "../../../core/dto/Account";
import {Observable} from "rxjs";
import {SecurityService} from "../../../core/security.service";

@Component({
  selector: 'app-account-list',
  templateUrl: './account-list.component.html',
  styleUrls: ['./account-list.component.css']
})
export class AccountListComponent implements OnInit {

  elements: Account[] = [];

  show: Account[] = [];

  types: AccountType[] = [];

  sortValue = {key: null, value: null};

  loading = false;

  adding = false;

  deleting = [];

  editIndex = -1;

  validating = [];

  approving = [];

  searchString = '';

  idx: number = 0;

  allRights: boolean = this.securityService.hasAllRights;

  canShow: boolean = this.securityService.hasShowAccounts;

  canShowOwn: boolean = this.securityService.hasShowAccountsOwn;

  canShowWaitingForApproval: boolean = this.securityService.hasShowAccountsWaitingApproval;

  canAdd: boolean = this.securityService.hasAddAccount;

  canUpdate: boolean = this.securityService.hasAddAccount;

  canApprove: boolean = this.securityService.hasApproveAccount;

  canDecline : boolean = this.securityService.hasDeclineAccount;

  canDelete: boolean = this.securityService.hasAddAccount;


  addForm: FormGroup = this.builder.group({
    name: this.builder.control('', [Validators.required, Validators.minLength(2)]),
    type: this.builder.control(null, Validators.required)
  });

  editForm: FormGroup = this.builder.group({
    name: this.builder.control('', [Validators.required, Validators.minLength(2)]),
    type: this.builder.control(null, Validators.required)
  });

  constructor(
    private accountService: AccountsService,
    private accountTypeService: AccountTypeService,
    private message: NzMessageService,
    private builder: FormBuilder,
    private securityService: SecurityService
  ) {
  }

  ngOnInit() {
    this.load();
    this.securityService.hasAllRights$.subscribe(value => this.allRights = value);
    this.securityService.hasShowAccounts$.subscribe(value => this.canShow = value);
    this.securityService.hasShowAccountsOwn$.subscribe(value => this.canShowOwn = value);
    this.securityService.hasShowAccountsWaitingApproval$.subscribe(value => this.canShowWaitingForApproval = value);
    this.securityService.hasAddAccount$.subscribe(value => this.canAdd = value);
    this.securityService.hasUpdateAccount$.subscribe(value => this.canUpdate = value);
    this.securityService.hasApproveAccount$.subscribe(value => this.canApprove = value);
    this.securityService.hasDeclineAccount$.subscribe(value => this.canDecline= value);
    this.securityService.hasDeleteAccount$.subscribe(value => this.canDelete = value);
  }

  load() {
    this.fetchAccounts();
    this.fetchAccountTypes();
  }

  fetchAccounts() {
    this.loading = true;

    let observable: Observable<Account[]>;

    switch (this.idx) {
      case 0:
        observable = this.accountService.findAll();
        break;
      case 1:
        observable = this.accountService.findOwn();
        break;
      case 2:
        observable = this.accountService.findWaitingApproval();
        break
    }

    if (observable) {
      observable.subscribe(elements => {
        this.elements = elements;
        this.filter();
        this.loading = false;
      });
    }
  }

  fetchAccountTypes() {
    this.loading = true;
    this.accountTypeService.findAll().subscribe(elements => {
      this.types = elements;
      this.loading = false;
    });
  }

  reset() {
    this.addForm.reset();
  }

  add() {
    this.adding = true;
    this.accountService.add(this.addForm.value).subscribe(() => {
      this.message.success(`The account '${this.addForm.controls.name.value}' has been added.`);
      this.load();
      this.reset();
      this.adding = false;
    }, response => {
      this.message.error(response.error.message);
      this.adding = false;
    });
  }

  edit(element: Account) {
    this.editIndex = element.id;

    this.editForm.reset();

    this.editForm.setValue({
      name: element.name,
      type: element.type.id
    });
  }

  validate(element: Account) {
    this.validating[element.id] = true;
    this.accountService.update(element.id, this.editForm.value).subscribe(
      () => {
        this.message.success(`The account '${element.name}' has been updated.`);
        this.load();
        this.cancel();
        this.validating[element.id] = false;
      },
      response => {
        this.validating[element.id] = false;
        this.message.error(response.error.message);
      }
    );
  }

  approve(element: Account) {
    this.approving[element.id] = true;
    this.accountService.approve(element.id).subscribe(
      () => {
        this.message.success(`The account '${element.name}' has been approved.`);
        this.load();
        this.cancel();
        this.approving[element.id] = false;
      },
      response => {
        this.approving[element.id] = false;
        this.message.error(response.error.message);
      }
    );
  }

  reject(element: Account) {
    this.approving[element.id] = true;
    this.accountService.reject(element.id).subscribe(
      () => {
        this.message.success(`The account '${element.name}' has been rejected.`);
        this.load();
        this.cancel();
        this.approving[element.id] = false;
      },
      response => {
        this.approving[element.id] = false;
        this.message.error(response.error.message);
      }
    );
  }

  cancel() {
    this.editIndex = -1;
  }

  delete(element: Account) {
    const id = element.id;
    this.deleting[id] = true;
    this.accountService.delete(id).subscribe(
      () => {
        this.deleting[id] = false;
        this.message.success(`The account '${element.name}' has been deleted.`);
        this.load();
      },
      response => {
        this.deleting[id] = false;
        this.message.error(response.error.message);
      }
    );
  }

  compare(one: AccountType, tow: AccountType): boolean {
    return one && tow && one.id === tow.id;
  }

  filter() {
    this.search();
    this.sort();
  }

  search() {
    this.show = this.elements.filter(element =>
      includesIgnoreCase(element, this.searchString, 'name')
    );
  }

  setSort(value) {
    this.sortValue = value;
    this.sort();
  }

  sort() {
    switch (this.sortValue.key) {
      case 'name':
        if (this.sortValue.value === 'ascend') {
          this.show = [...this.show.sort((a, b) => a.name.toLowerCase().localeCompare(b.name.toLowerCase()))];
        } else if (this.sortValue.value === 'descend') {
          this.show = [...this.show.sort((a, b) => b.name.toLowerCase().localeCompare(a.name.toLowerCase()))];
        } else {
          this.show = [...this.elements];
          this.search();
        }
        break;
    }
  }
}
