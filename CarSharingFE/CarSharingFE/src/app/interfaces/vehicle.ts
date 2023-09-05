export interface Vehicle{
    id: number,
    licensePlate: string,
    idModel: {id: number},
    country: string,
    region: string,
    city: string,
    street: string,
    houseNumber: string,
    booked: boolean,
    latitude: number,
    longitude: number
}
