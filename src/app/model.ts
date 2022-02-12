export interface LoginResponse {
  jwt: string;
}

export interface DecodedJWT {
  sub: string,
  can_restart_machines: boolean,
  can_search_machines: boolean,
  can_delete_users: boolean,
  can_update_users: boolean,
  can_start_machines: boolean,
  can_stop_machines: boolean,
  can_read_users: boolean,
  exp: number,
  iat: number,
  can_create_users: boolean,
  can_create_machines: boolean
}

export enum PermissionType {
  CAN_CREATE_USERS,
  CAN_READ_USERS,
  CAN_UPDATE_USERS,
  CAN_DELETE_USERS
}

export interface Permission {
  id: number,
  type: PermissionType,
  value: boolean,
  user: User
}



export interface User {
  id: number,
  firstName: string,
  lastName: string,
  username: string,
  password: string,
  can_create_users: boolean;
  can_read_users: boolean
  can_update_users: boolean
  can_delete_users: boolean
  can_search_machines: boolean
  can_start_machines: boolean
  can_stop_machines: boolean
  can_restart_machines: boolean
  can_create_machines: boolean
  can_destroy_machines: boolean
}


