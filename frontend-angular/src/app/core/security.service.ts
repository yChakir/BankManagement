import { Injectable } from '@angular/core';
import {UserService} from "./user.service";
import {Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  hasAllRights: boolean;
  hasAllRights$: Subject<boolean> = new Subject<boolean>();

  hasShowProfile: boolean;
  hasShowProfile$: Subject<boolean> = new Subject<boolean>();

  hasShowAccountTypes: boolean;
  hasShowAccountTypes$: Subject<boolean> = new Subject<boolean>();

  hasAddAccountType: boolean;
  hasAddAccountType$: Subject<boolean> = new Subject<boolean>();

  hasUpdateAccountType: boolean;
  hasUpdateAccountType$: Subject<boolean> = new Subject<boolean>();

  hasDeleteAccountType: boolean;
  hasDeleteAccountType$: Subject<boolean> = new Subject<boolean>();

  hasShowAccounts: boolean;
  hasShowAccounts$: Subject<boolean> = new Subject<boolean>();

  hasShowAccountsOwn: boolean;
  hasShowAccountsOwn$: Subject<boolean> = new Subject<boolean>();

  hasShowAccountsWaitingApproval: boolean;
  hasShowAccountsWaitingApproval$: Subject<boolean> = new Subject<boolean>();

  hasAddAccount: boolean;
  hasAddAccount$: Subject<boolean> = new Subject<boolean>();

  hasUpdateAccount: boolean;
  hasUpdateAccount$: Subject<boolean> = new Subject<boolean>();

  hasApproveAccount: boolean;
  hasApproveAccount$: Subject<boolean> = new Subject<boolean>();

  hasDeclineAccount: boolean;
  hasDeclineAccount$: Subject<boolean> = new Subject<boolean>();

  hasDeleteAccount: boolean;
  hasDeleteAccount$: Subject<boolean> = new Subject<boolean>();

  hasShowUsers: boolean;
  hasShowUsers$: Subject<boolean> = new Subject<boolean>();

  hasAddUser: boolean;
  hasAddUser$: Subject<boolean> = new Subject<boolean>();

  hasUpdateUser: boolean;
  hasUpdateUser$: Subject<boolean> = new Subject<boolean>();

  hasUpdateUserRoles: boolean;
  hasUpdateUserRoles$: Subject<boolean> = new Subject<boolean>();

  hasDeleteUser: boolean;
  hasDeleteUser$: Subject<boolean> = new Subject<boolean>();

  hasShowRoles: boolean;
  hasShowRoles$: Subject<boolean> = new Subject<boolean>();

  hasAddRole: boolean;
  hasAddRole$: Subject<boolean> = new Subject<boolean>();

  hasUpdateRole: boolean;
  hasUpdateRole$: Subject<boolean> = new Subject<boolean>();

  hasDeleteRole: boolean;
  hasDeleteRole$: Subject<boolean> = new Subject<boolean>();

  hasShowRights: boolean;
  hasShowRights$: Subject<boolean> = new Subject<boolean>();

  hasShowRightsOwn: boolean;
  hasShowRightsOwn$: Subject<boolean> = new Subject<boolean>();

  hasUpdateRight: boolean;
  hasUpdateRight$: Subject<boolean> = new Subject<boolean>();

  hasActivateRight: boolean;
  hasActivateRight$: Subject<boolean> = new Subject<boolean>();

  hasDeactivateRight: boolean;
  hasDeactivateRight$: Subject<boolean> = new Subject<boolean>();

  hasShowHistory: boolean;
  hasShowHistory$: Subject<boolean> = new Subject<boolean>();

  constructor(
    private http: HttpClient
  ) {
  }

  public fetch() {
    this.http.get<Right[]>(`${environment.api.url}/api/v1/rights/own`).subscribe(value => {
      value.forEach(right => {
        switch (right.name) {
          case environment.rights.allRights: {
            this.hasAllRights = true;
            this.hasAllRights$.next(true);
            break
          }
          case environment.rights.showProfile: {
            this.hasShowProfile = true;
            this.hasShowProfile$.next(true);
            break
          }
          case environment.rights.showUsers: {
            this.hasShowUsers = true;
            this.hasShowUsers$.next(true);
            break
          }
          case environment.rights.addUser: {
            this.hasAddUser = true;
            this.hasAddUser$.next(true);
            break
          }
          case environment.rights.updateUser: {
            this.hasUpdateUser = true;
            this.hasUpdateUser$.next(true);
            break
          }
          case environment.rights.updateUserRoles: {
            this.hasUpdateUserRoles= true;
            this.hasUpdateUserRoles$.next(true);
            break
          }
          case environment.rights.deleteUser: {
            this.hasDeleteUser = true;
            this.hasDeleteUser$.next(true);
            break
          }
          case environment.rights.showAccountTypes: {
            this.hasShowAccountTypes= true;
            this.hasShowAccountTypes$.next(true);
            break
          }
          case environment.rights.addAccountType: {
            this.hasAddAccountType= true;
            this.hasAddAccountType$.next(true);
            break
          }
          case environment.rights.updateAccountType: {
            this.hasUpdateAccountType = true;
            this.hasUpdateAccountType$.next(true);
            break
          }
          case environment.rights.deleteAccountType: {
            this.hasDeleteAccountType = true;
            this.hasDeleteAccountType$.next(true);
            break
          }
          case environment.rights.showAccounts: {
            this.hasShowAccounts = true;
            this.hasShowAccounts$.next(true);
            break
          }
          case environment.rights.showAccountsOwn: {
            this.hasShowAccountsOwn = true;
            this.hasShowAccountsOwn$.next(true);
            break
          }
          case environment.rights.showAccountsWaitingApproval: {
            this.hasShowAccountsWaitingApproval = true;
            this.hasShowAccountsWaitingApproval$.next(true);
            break
          }
          case environment.rights.addAccount: {
            this.hasAddAccount = true;
            this.hasAddAccount$.next(true);
            break
          }
          case environment.rights.updateAccount: {
            this.hasUpdateAccount = true;
            this.hasUpdateAccount$.next(true);
            break
          }
          case environment.rights.approveAccount: {
            this.hasApproveAccount = true;
            this.hasApproveAccount$.next(true);
            break
          }
          case environment.rights.declineAccount: {
            this.hasDeclineAccount = true;
            this.hasDeclineAccount$.next(true);
            break
          }
          case environment.rights.deleteAccount: {
            this.hasDeleteAccount = true;
            this.hasDeleteAccount$.next(true);
            break
          }
          case environment.rights.showRoles: {
            this.hasShowRoles = true;
            this.hasShowRoles$.next(true);
            break
          }
          case environment.rights.addRole: {
            this.hasAddRole = true;
            this.hasAddRole$.next(true);
            break
          }
          case environment.rights.updateRole: {
            this.hasUpdateRole = true;
            this.hasUpdateRole$.next(true);
            break
          }
          case environment.rights.deleteRole: {
            this.hasDeleteRole = true;
            this.hasDeleteRole$.next(true);
            break
          }
          case environment.rights.showRights: {
            this.hasShowRights = true;
            this.hasShowRights$.next(true);
            break
          }
          case environment.rights.showRightsOwn: {
            this.hasShowRightsOwn = true;
            this.hasShowRightsOwn$.next(true);
            break
          }
          case environment.rights.updateRight: {
            this.hasUpdateRight = true;
            this.hasUpdateRight$.next(true);
            break
          }
          case environment.rights.activateRight: {
            this.hasActivateRight = true;
            this.hasActivateRight$.next(true);
            break
          }
          case environment.rights.deactivateRight: {
            this.hasDeactivateRight = true;
            this.hasDeactivateRight$.next(true);
            break
          }
          case environment.rights.showHistory: {
            this.hasShowHistory = true;
            this.hasShowHistory$.next(true);
            break
          }
        }
      })
    })
  }
}
