enum Role{
    DOCTOR,
    SECRETARY,
    MODERATOR
}
export interface User{
    cnp: String,
    firstName: String,
    lastName: String,
    emailUser: String,
    specialty: String,
    password: String,
    role: Role,
    image: String,
  
}