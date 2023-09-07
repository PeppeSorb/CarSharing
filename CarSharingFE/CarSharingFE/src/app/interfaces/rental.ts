import { Userr } from "./user";

export interface Rental{
    id: number,
    idUser: Userr,
    idVehicle: number,
    dateTimeStartRental: number,
    dateTimeEndRental: number,
    typeRental: string,
    payed: boolean,
    price: number,
    extrapay: boolean
}