export interface Rental{
    id: number,
    idUser: number,
    idVehicle: number,
    idAdmin: number,
    dateTimeStartRental: number,
    dateTimeEndRental: number,
    typeRental: string,
    payed: boolean
}