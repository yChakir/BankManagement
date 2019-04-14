import {Component, OnInit} from '@angular/core';
import {Role} from "../../../core/dto/Role";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {RightsService} from "../../../core/rights.service";
import {RolesService} from "../../../core/roles.service";
import {includesIgnoreCase} from "../../../core/utils";
import {SecurityService} from "../../../core/security.service";

@Component({
  selector: 'app-roles-page',
  templateUrl: './roles-page.component.html',
  styleUrls: ['./roles-page.component.css']
})
export class RolesPageComponent implements OnInit {
  elements: Role[] = [];

  show: Role[] = [];

  rights: Right[] = [];

  sortValue = {key: null, value: null};

  loading = false;

  adding = false;

  deleting = [];

  editIndex = -1;

  validating = [];

  searchString = '';

  allRights: boolean = this.securityService.hasAllRights;

  canAdd: boolean = this.securityService.hasAddRole;

  canUpdate: boolean = this.securityService.hasUpdateRole;

  canDelete: boolean = this.securityService.hasDeleteRole;

  addForm: FormGroup = this.builder.group({
    name: this.builder.control('', [Validators.required, Validators.minLength(2)]),
    rights: this.builder.control(null, Validators.required)
  });

  editForm: FormGroup = this.builder.group({
    name: this.builder.control('', [Validators.required, Validators.minLength(2)]),
    rights: this.builder.control(null, Validators.required)
  });

  constructor(
    private rolesService: RolesService,
    private rightsService: RightsService,
    private message: NzMessageService,
    private builder: FormBuilder,
    private securityService: SecurityService
  ) {
  }

  ngOnInit() {
    this.load();
    this.securityService.hasAllRights$.subscribe(value => this.allRights = value);
    this.securityService.hasAddRole$.subscribe(value => this.canAdd = value);
    this.securityService.hasUpdateRole$.subscribe(value => this.canUpdate = value);
    this.securityService.hasDeleteRole$.subscribe(value => this.canDelete = value);
  }

  load() {
    this.fetchRoles();
    this.fetchRights();
  }

  fetchRoles() {
    this.loading = true;
    this.rolesService.findAll().subscribe(elements => {
      this.elements = elements;
      this.filter();
      this.loading = false;
    });
  }

  fetchRights() {
    this.loading = true;
    this.rightsService.findAll().subscribe(elements => {
      this.rights = elements;
      this.loading = false;
    });
  }

  reset() {
    this.addForm.reset();
  }

  add() {
    this.adding = true;
    const role: Role = this.addForm.value;
    this.rolesService.add(this.addForm.value).subscribe(() => {
      this.message.success(`The Role '${role.name}' has been added.`);
      this.load();
      this.reset();
      this.adding = false;
    }, response => {
      this.message.error(response.error.message);
      this.adding = false;
    });
  }

  edit(element: Role) {
    this.editIndex = element.id;

    this.editForm.reset();

    this.editForm.setValue({
      name: element.name,
      rights: element.rights.map(value => value.name)
    });
  }

  validate(element: Role) {
    this.validating[element.id] = true;
    const role: Role = {
      ...element,
      name: this.editForm.controls['name'].value,
      rights: this.editForm.controls['rights'].value
    };
    this.rolesService.update(element.id, this.editForm.value).subscribe(
      () => {
        this.message.success(`The role '${element.name}' has been updated.`);
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

  delete(element: Role) {
    const id = element.id;
    this.deleting[id] = true;
    this.rolesService.delete(id).subscribe(
      () => {
        this.deleting[id] = false;
        this.message.success(`The role '${element.name}' has been deleted.`);
        this.load();
      },
      response => {
        this.deleting[id] = false;
        this.message.error(response.error.message);
      }
    );
  }

  compare(one: Right, tow: Right): boolean {
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
