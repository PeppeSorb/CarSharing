export interface Vehicle{
    id: number,
    licensePlate: string,
    idModel: {
        idMod: number,
        idCategory: null,
        makeAndModel: null,
        bootCapacity: null,
        averageConsumption: null,
        forNewDrivers: null,
        image: null},
    country: string,
    region: string,
    city: string,
    street: string,
    houseNumber: string,
    booked: boolean,
    latitude: number,
    longitude: number
}
