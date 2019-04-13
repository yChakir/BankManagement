class ResetPassword {

  private username: string;

  private token: string;

  private password: string;

  constructor(username: string, token: string, password: string) {
    this.username = username;
    this.token = token;
    this.password = password;
  }
}
