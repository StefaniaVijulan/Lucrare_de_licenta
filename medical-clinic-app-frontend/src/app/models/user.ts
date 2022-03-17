export enum Role{
    USER, MODERATOR
}
export class User{
    id:number;
    cnp: String;
    firstName: String;
    lastName: String;
    emailUser: String;
    specialty: String;
    password: String;
    role: Role;

    constructor(){}
}