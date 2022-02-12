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

export enum MachineStatus {
  RUNNING, STOPPED
}

export interface Machine {
  id: number,
  name: string,
  status: string,
  createdBy:User,
  createdAt: Date,
  active:boolean
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

export interface ErrorMessage {
  id: number,
  date: Date,
  machineId: number,
  methodName: string,
  errorMessage: string
}

