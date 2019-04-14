import {AccountType} from "./AccountType";
import {User} from "./User";

export class Account {
  id: number;
  name: string;
  status: string;
  type: AccountType;
  user: User;
  created: Date;
  updated: Date;
}

export class AddAccount {
  name: string;
  type: number;
}

export class UpdateAccount {
  name: string;
  type: number;
}
