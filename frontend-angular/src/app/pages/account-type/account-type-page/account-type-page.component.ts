import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {includesIgnoreCase} from "../../../core/utils";
import {AccountType} from "../../../core/dto/AccountType";
import {AccountTypeService} from "../../../core/account-type.service";

@Component({
  selector: 'app-account-type-page',
  templateUrl: './account-type-page.component.html',
  styleUrls: ['./account-type-page.component.css']
})
export class AccountTypePageComponent implements OnInit {
  elements: AccountType[] = [];

  show: AccountType[] = [];

  sortValue = {key: null, value: null};

  loading = false;

  adding = false;

  deleting = [];

  editIndex = -1;

  validating = [];

  searchString = '';

  addForm: FormGroup = this.builder.group({
    name: this.builder.control('', [Validators.required, Validators.minLength(2)])
  });

  editForm: FormGroup = this.builder.group({
    name: this.builder.control('', [Validators.required, Validators.minLength(2)])
  });

  constructor(
    private accountTypeService: AccountTypeService,
    private message: NzMessageService,
    private builder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.load();
  }

  load() {
    this.fetch();
  }

  fetch() {
    this.loading = true;
    this.accountTypeService.findAll().subscribe(elements => {
      this.elements = elements;
      this.filter();
      this.loading = false;
    });
  }

  reset() {
    this.addForm.reset();
  }

  add() {
    this.adding = true;
    this.accountTypeService.add(this.addForm.value).subscribe(() => {
      this.message.success(`The Account type '${this.addForm.controls.name.value}' has been added.`);
      this.load();
      this.reset();
      this.adding = false;
    });
  }

  edit(element: AccountType) {
    this.editIndex = element.id;

    this.editForm.reset();

    this.editForm.setValue({
      name: element.name
    });
  }

  validate(element: AccountType) {
    this.validating[element.id] = true;
    this.accountTypeService.update(element.id, this.editForm.value).subscribe(
      () => {
        this.message.success(`The account type '${element.name}' has been updated.`);
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

  cancel() {
    this.editIndex = -1;
  }

  delete(element: AccountType) {
    const id = element.id;
    this.deleting[id] = true;
    this.accountTypeService.delete(id).subscribe(
      () => {
        this.deleting[id] = false;
        this.message.success(`The account type '${element.name}' has been deleted.`);
        this.load();
      },
      response => {
        this.deleting[id] = false;
        this.message.error(response.error.message);
      }
    );
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
