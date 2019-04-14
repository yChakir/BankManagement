// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  api: {
    url: "http://localhost:8080"
  },
  jwt: {
    header: "Authorization",
    bearer: "Bearer "
  },
  routes: {
    login: "/login",
    register: "/register",
    validateEmail: "/validate-email",
    forgotPassword: "/forgot-password",
    resetPassword: "/reset-password",
    profile: "/profile",
    users: "/users",
    accounts: "/accounts",
    accountType: "/account-type",
    roles: "/roles",
    rights: "/rights",
    history: "/history",

  },
  rights: {
    allRights: 'ALL_RIGHTS',
    showProfile: 'SHOW_PROFILE',
    showUsers: 'SHOW_USERS',
    addUser: 'ADD_USER',
    updateUser: 'UPDATE_USER',
    updateUserRoles: 'UPDATE_USER_ROLES',
    deleteUser: 'DELETE_USER',
    showAccountTypes: 'SHOW_ACCOUNT_TYPES',
    addAccountType: 'ADD_ACCOUNT_TYPE',
    updateAccountType: 'UPDATE_ACCOUNT_TYPE',
    deleteAccountType: 'DELETE_ACCOUNT_TYPE',
    showAccounts: 'SHOW_ACCOUNTS',
    showAccountsOwn: 'SHOW_ACCOUNTS_OWN',
    showAccountsWaitingApproval: 'SHOW_ACCOUNTS_WAITING_FOR_APPROVAL',
    addAccount: 'ADD_ACCOUNT',
    updateAccount: 'UPDATE_ACCOUNT',
    approveAccount: 'APPROVE_ACCOUNT',
    declineAccount: 'DECLINE_ACCOUNT',
    deleteAccount: 'DELETE_ACCOUNT',
    showRoles: 'SHOW_ROLES',
    addRole: 'ADD_ROLE',
    updateRole: 'UPDATE_ROLE',
    deleteRole: 'DELETE_ROLE',
    showRights: 'SHOW_RIGHTS',
    showRightsOwn: 'SHOW_RIGHTS_OWN',
    updateRight: 'UPDATE_RIGHT',
    activateRight: 'ACTIVATE_RIGHT',
    deactivateRight: 'DEACTIVATE_RIGHT',
    showHistory: 'SHOW_HISTORY',
  },
  production: false
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
