export class Role {
  id: number;
  name: string;
  rights: Right[];
  created: Date;
  updated: Date;
}

export class AddRole {
  name: string;
  rights: string[];
}

export class UpdateRole {
  name: string;
  rights: string[];
}
