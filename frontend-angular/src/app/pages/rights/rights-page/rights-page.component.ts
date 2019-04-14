import {Component, OnInit} from '@angular/core';
import {RightsService} from "../../../core/rights.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {includesIgnoreCase} from "../../../core/utils";

@Component({
  selector: 'app-rights-page',
  templateUrl: './rights-page.component.html',
  styleUrls: ['./rights-page.component.css']
})
export class RightsPageComponent implements OnInit {
  elements: Right[] = [];
  show: Right[] = [];

  sortValue = {key: null, value: null};

  loading = false;

  editIndex = -1;

  activating = [];
  validating = [];
  searchString = '';

  form: FormGroup = this.builder.group({
    description: this.builder.control('', [Validators.required, Validators.minLength(2)])
  });

  constructor(
    private messageService: NzMessageService,
    private service: RightsService,
    private builder: FormBuilder
  ) {
  }

  ngOnInit() {
    this.fetch();
  }

  fetch() {
    this.loading = true;
    this.service.findAll().subscribe(elements => {
      this.elements = elements;
      this.filter();
      this.loading = false;
    });
  }

  edit(right: Right) {
    this.form.reset();

    this.editIndex = right.id;

    this.form.setValue({description: right.description});
  }

  validate(element: Right) {
    this.validating[element.id] = true;
    this.service.update(element.name, this.form.controls.description.value).subscribe(() => {
      this.messageService.success(`The right '${element.name}' has been edited.`);
      this.fetch();
      this.cancel();
      this.validating[element.id] = false;
    }, response => {
      this.validating[element.id] = false;
      this.messageService.error(response.error.message);
    });
  }

  cancel() {
    this.editIndex = -1;
  }

  deactivate(right: Right) {
    const id = right.id;
    this.activating[id] = true;
    this.service.deactivate(right.name).subscribe(() => {
      this.activating[id] = false;
      this.messageService.success(`The Right '${right.name}' has been deactivated.`);
      this.load();
    }, response => {
      this.activating[id] = false;
      this.messageService.error(response.error.message);
    });
  }

  activate(right: Right) {
    const id = right.id;
    this.activating[id] = true;
    this.service.activate(right.name).subscribe(() => {
      this.activating[id] = false;
      this.messageService.success(`The right '${right.name}' has been activated.`);
      this.fetch();
    }, response => {
      this.activating[id] = false;
      this.messageService.error(response.error.message);
    });
  }

  filter() {
    this.search();
    this.sort();
  }

  search() {
    this.show = this.elements.filter(element => {
      return includesIgnoreCase(element, this.searchString, 'name', 'description');
    });
  }

  setSort(value) {
    this.sortValue = value;
    this.sort();
  }

  sort() {
    switch (this.sortValue.key) {
      case 'name':
        if (this.sortValue.value === 'ascend') {
          this.show = [...this.show.sort((a, b) => a.name.localeCompare(b.name))];
        } else if (this.sortValue.value === 'descend') {
          this.show = [...this.show.sort((a, b) => b.name.localeCompare(a.name))];
        } else {
          this.show = [...this.elements];
          this.search();
        }
        break;
      case 'description':
        if (this.sortValue.value === 'ascend') {
          this.show = [...this.show.sort((a, b) => a.description.toLowerCase().localeCompare(b.description.toLowerCase()))];
        } else if (this.sortValue.value === 'descend') {
          this.show = [...this.show.sort((a, b) => b.description.toLowerCase().localeCompare(a.description.toLowerCase()))];
        } else {
          this.show = [...this.elements];
          this.search();
        }
        break;
      case 'state':
        if (this.sortValue.value === 'ascend') {
          this.show = [...this.show.sort((a, b) => a.active ? 1 : b.active ? -1 : 0)];
        } else if (this.sortValue.value === 'descend') {
          this.show = [...this.show.sort((a, b) => b.active ? 1 : a.active ? -1 : 0)];
        } else {
          this.show = [...this.elements];
          this.search();
        }
        break;
    }
  }

}
