class Right {

  id: number;

  name: string;

  description: string;

  created: Date;

  updated: Date;

  active: boolean;

  constructor(
    id: number,
    name: string,
    description: string,
    created: Date,
    updated: Date,
    active: boolean
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.created = created;
    this.updated = updated;
    this.active = active;
  }
}
